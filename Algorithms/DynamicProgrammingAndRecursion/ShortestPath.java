/*
*  Author: Hongwei Lu
*  Description: 
*    find shortest path of a graph 
*    using topological sort and back track.
*/


import java.util.*;


public class ShortestPath{

	public static void main(String args[]){
         // test case: shortest path is 0 2 4 with lowest cost 1+1 = 2
			double [][]matrix = {
                              {0,1,1,0,0},
                              {0,0,0,2,5},
                              {0,0,0,2,1}, 
                              {0,0,0,0,1},
                              {0,0,0,0,0}};
         int [] path = FindShortestPath(matrix, 0, 4);
         if(path != null)
            for(int i=0; i<path.length; i++)
             System.out.printf("%d ",path[i]);
         return;

	}
 // FindShortestPath
   // Pre-conditions:
   //    - adjecent matrix of a graph, start node, and end node
   // Post-conditions:
   //    - A new int[] is returned with a list of the nodes indicating 
   //      the shortest path. For example,the 0'th element is the start node
   //      of the path, and the last element is the end of the path.
	public static int[] FindShortestPath(double [][]adjMatrix, int startIdx, int endIdx){

		int numNodes = adjMatrix[0].length;

	  //the cost of an edge
      double[] cost = new double[numNodes];
	  // the predecessor of each node
      int[] node = new int[numNodes];
      
	  int[] result = new int[numNodes]; //result for return; a list of nodes

      for(int i = 0; i <= numNodes -1; i++){        
         cost[i] = java.lang.Double.POSITIVE_INFINITY;
         node[i] = -1; // -1 indicates no predecessor
      }
      
	  //
      cost[startIdx] = 0;
      int[] sort = topologicalSort(adjMatrix);
      if(sort == null){
		System.out.println("graph has cycle!");
		return null;
	  }
      for(int i: sort){
         for(int j= 0; j < numNodes; j++){
            if(adjMatrix[j][i] != 0){
			   // if an edge exist, find the lowest cost one, store the cost,
			   // then store the start node as the predecessor of end node.
               if((adjMatrix[j][i]+ cost[j]) < cost[i]){
                  cost[i] = adjMatrix[j][i] + cost[j];
                  node[i] = j;
               }
            }
         }
      }
     //backtrack
      int endN = endIdx;
	  
      Stack sNode = new Stack();
      int startN = node[endN];

      // back track get the path in reverse order, so store into a stack first
      while(startN != -1){
         sNode.push(endN);
         endN = startN;
		 startN = node[endN];
      }
	  sNode.push(endN); // add start node to stack

      int idx = 0;
      // transfer path from stack to result array, make it the right order
      while(!sNode.empty()){
         result[idx++] = (int)sNode.pop();
      }
      
      return Arrays.copyOfRange(result, 0, idx);


	}

 // topologicalSort
   // Pre-conditions:
   //    - adjecent matrix of a graph
   // Post-conditions:
   //    - A new int[] is returned with a topological sort of the nodes
   //      For example, the 0'th element of the returned array has no 
   //      incoming edges.  More generally, the node in the i'th element 
   //      has no incoming edges from nodes in the i+1'th or later elements
   //  time complexity: O(n^2)
 public static int[] topologicalSort(double [][]adjMatrix) {
   
	  int numNodes = adjMatrix[0].length;
      //in-Degree: number of incomming edges for a node.
      int[] inDegrees = new int[numNodes];
      for(int i=0; i<numNodes; i++)
         for(int j=0; j<numNodes; j++)
            if(adjMatrix[i][j] != 0)
               inDegrees[j]++;

      int[] result = new int[numNodes];
      int index = 0;
      
	  //queue store nodes that has no incomming edges besides edges from nodes in result.
      Queue<Integer> newTopSort = new LinkedList<Integer>();
      for(int i = 0; i < numNodes; i++){
         if(inDegrees[i] == 0){
            newTopSort.add(i);
         }
      }
      
      while(!newTopSort.isEmpty()){
         int n = newTopSort.remove();
         result[index++] = n; 
         for(int i = 0; i < numNodes; i++){
            if(adjMatrix[n][i] != 0){
               inDegrees[i]--;
               if (inDegrees[i] == 0){
                  newTopSort.add(i);
               }
            }
         }
      }
      
      int sum = Arrays.stream(inDegrees).sum();
      if(sum > 0){
         return null; //error,has cycle, shouldn't happen
      }else{
         return result;
      }      
   }



}
