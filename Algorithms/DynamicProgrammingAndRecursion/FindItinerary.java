/*
   Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], 
   reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
   
   Note:
   If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
   For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
   All airports are represented by three capital letters (IATA code).
   You may assume all tickets form at least one valid itinerary.
   Example 1:
   tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
   Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
   Example 2:
   tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
   Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
   Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
*/
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.PriorityQueue;
public class FindItinerary { 

   public static void main(String [] args){
      FindItinerary obj = new FindItinerary();
      List<String> ret = obj.findItinerary2(new String [][]{{"AXA","EZE"},{"EZE","AUA"},{"ADL","JFK"},{"ADL","TIA"},{"AUA","AXA"},{"EZE","TIA"},{"EZE","TIA"},{"AXA","EZE"},{"EZE","ADL"},{"ANU","EZE"},{"TIA","EZE"},{"JFK","ADL"},{"AUA","JFK"},{"JFK","EZE"},{"EZE","ANU"},{"ADL","AUA"},{"ANU","AXA"},{"AXA","ADL"},{"AUA","JFK"},{"EZE","ADL"},{"ANU","TIA"},{"AUA","JFK"},{"TIA","JFK"},{"EZE","AUA"},{"AXA","EZE"},{"AUA","ANU"},{"ADL","AXA"},{"EZE","ADL"},{"AUA","ANU"},{"AXA","EZE"},{"TIA","AUA"},{"AXA","EZE"},{"AUA","SYD"},{"ADL","JFK"},{"EZE","AUA"},{"ADL","ANU"},{"AUA","TIA"},{"ADL","EZE"},{"TIA","JFK"},{"AXA","ANU"},{"JFK","AXA"},{"JFK","ADL"},{"ADL","EZE"},{"AXA","TIA"},{"JFK","AUA"},{"ADL","EZE"},{"JFK","ADL"},{"ADL","AXA"},{"TIA","AUA"},{"AXA","JFK"},{"ADL","AUA"},{"TIA","JFK"},{"JFK","ADL"},{"JFK","ADL"},{"ANU","AXA"},{"TIA","AXA"},{"EZE","JFK"},{"EZE","AXA"},{"ADL","TIA"},{"JFK","AUA"},{"TIA","EZE"},{"EZE","ADL"},{"JFK","ANU"},{"TIA","AUA"},{"EZE","ADL"},{"ADL","JFK"},{"ANU","AXA"},{"AUA","AXA"},{"ANU","EZE"},{"ADL","AXA"},{"ANU","AXA"},{"TIA","ADL"},{"JFK","ADL"},{"JFK","TIA"},{"AUA","ADL"},{"AUA","TIA"},{"TIA","JFK"},{"EZE","JFK"},{"AUA","ADL"},{"ADL","AUA"},{"EZE","ANU"},{"ADL","ANU"},{"AUA","AXA"},{"AXA","TIA"},{"AXA","TIA"},{"ADL","AXA"},{"EZE","AXA"},{"AXA","JFK"},{"JFK","AUA"},{"ANU","ADL"},{"AXA","TIA"},{"ANU","AUA"},{"JFK","EZE"},{"AXA","ADL"},{"TIA","EZE"},{"JFK","AXA"},{"AXA","ADL"},{"EZE","AUA"},{"AXA","ANU"},{"ADL","EZE"},{"AUA","EZE"}});
      
      //List<String> ret = obj.findItinerary2(new String [][]{{"JFK", "MUC"}});
      System.out.println(ret);
   }

    //variant of depth first seach using stack (greedy algorithm is a more appropriat description) 
    public List<String> findItinerary(String[][] tickets) {
        //using hash map and priority queue 
        if(tickets == null || tickets.length == 0 || tickets[0].length == 0)
            return new LinkedList<String>();
            
        List<String> res = new LinkedList<String>();
        HashMap<String, PriorityQueue<String>> hm = new HashMap<String, PriorityQueue<String>>();
        
        for(int i=0; i<tickets.length; i++){
            if(hm.containsKey(tickets[i][0])){//already exist
                //add to same value (the priority queue) in hm
                hm.get(tickets[i][0]).add(tickets[i][1]);
            }
            else{//create new queue
                PriorityQueue<String> que = new PriorityQueue<String>();
                que.add(tickets[i][1]);
                hm.put(tickets[i][0], que);
            }
        }
        Stack<String> st = new Stack<String>();
        st.add("JFK");
 
        while(!st.empty()){
            String curStr = st.peek();
            PriorityQueue<String> que = hm.get(curStr);
            if(que != null && !que.isEmpty())
               st.push(que.poll());
            else{
               st.pop();
               res.addFirst(0,curStr); //write to result in backward direction
            }
        }
        return res;
    }
    //variant of depth first seach using recursion (greedy algorithm is a more appropriat description) 
    public List<String> findItinerary(String[][] tickets) {
        //using hash map and priority queue 
        if(tickets == null || tickets.length == 0 || tickets[0].length == 0)
            return new LinkedList<String>();
            
        List<String> res = new LinkedList<String>();
        HashMap<String, PriorityQueue<String>> hm = new HashMap<String, PriorityQueue<String>>();
        
        for(int i=0; i<tickets.length; i++){
            if(hm.containsKey(tickets[i][0])){//already exist
                //add to same value (the priority queue) in hm
                hm.get(tickets[i][0]).add(tickets[i][1]);
            }
            else{//create new queue
                PriorityQueue<String> que = new PriorityQueue<String>();
                que.add(tickets[i][1]);
                hm.put(tickets[i][0], que);
            }
        }
 
        help(hm, res, "JFK");
        return res;
    }
    public void help(HashMap<String, PriorityQueue<String>> hm,List<String> res, String curStr) {
         PriorityQueue<String> que = hm.get(curStr);
         while(que != null && !que.isEmpy()){
            help(hm, res, que.poll())
         }
         res.addFirst(0, curStr);
    }

   //solution 2: search all posibble path until find to right one
    public List<String> findItinerary2(String[][] tickets) {
        //using hash map and priority queue 
        if(tickets == null || tickets.length == 0 || tickets[0].length == 0)
            return new LinkedList<String>();
        
        TreeSet<String> ts = new TreeSet<String>();
        for(int i=0; i<tickets.length; i++){
            if(!ts.contains(tickets[i][0])){
                ts.add(tickets[i][0]);
            }
            if(!ts.contains(tickets[i][1])){
                ts.add(tickets[i][1]);
            }
        }
        // keys and values are all sorted in treemap
        TreeMap<String, Integer> hm = new TreeMap<String, Integer>();
        int index = 0;
        for(String str: ts){
            hm.put(str,index++);
        }
        
        int totalCount = 0;
        //neighboring matrix
        int [][] neighborArr = new int[index][index];
        for(int i=0; i<tickets.length; i++){
               neighborArr[hm.get(tickets[i][0])][hm.get(tickets[i][1])]++;
        }

        List<String> res = findItinerary_help(neighborArr, hm.get("JFK"), ts.toArray(new String[index]), tickets.length);    
        res.add(0,"JFK");
        return res;
    }
    public List<String> findItinerary_help(int [][] neighbMatrix, int start, String []strs, int remainCnt){
        if(remainCnt == 0)
            return new LinkedList<String>();
        for(int i=0; i<neighbMatrix[0].length; i++){
            if(neighbMatrix[start][i] != 0){
                neighbMatrix[start][i]--;
                //System.out.println(remainCnt);//("***"+strs[i]);
                List<String> res = findItinerary_help(neighbMatrix, i, strs, remainCnt-1);
                if(remainCnt-1 == res.size())
                {
                    //System.out.println("########"+strs[i]);

                    res.add(0,strs[i]);
                    return res;
                }
                neighbMatrix[start][i]++;
            }
        }
        return new LinkedList<String>();
    }
    
    
    
    
            boolean [] visited = new boolean[index];
        visited[hm.get("JFK")] = true;
        
        unVisit.add(hm.get("JFK"));
        while(!unVisit.empty()){
            int cur = visited.pop();
            for(int i=0; i<neighbMatrix[0].length; i++){
                if(neighbMatrix[cur][i] != 0){
                    visited.push(i);
                }
            }
        }

}

/*

Generally speaking, it is a graph traversal problem.

The hard point of this problem is to understand the problem. 

The problem is to find itinerary  which uses all tickets onece and in a smallest lexical order.
There are maybe several same tickets given. 

Another hard point for method of using DFS is finding terminal (destination). The destination is
the node which doesn't have any outgoing edge( it may be given like this, or its outgoing edges have been visited).

Summary: Depth first search has variants. Be familiar with DFS and paractice to use it in difference situations.

*/