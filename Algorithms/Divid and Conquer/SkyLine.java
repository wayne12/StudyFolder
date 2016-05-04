/*
   skyline problem
   
   reference: https://leetcode.com/problems/the-skyline-problem/

*/

import java.util.*;

public class SkyLine {
   public static void main(String [] args){
      SkyLine obj = new SkyLine();
      List<int[]> lst = obj.getSkyline(new int [][]{{1,2,1},{1,2,2},{1,2,3}});
      for(int[] arr: lst){
            System.out.print(Arrays.toString(arr));
      }
   }
    public List<int[]> getSkyline(int[][] buildings) {

        List<int[]> front = new LinkedList<int[]>();
        List<int[]> end = new LinkedList<int[]>();
        Comparator <int[]> comparator = new arrComparator();
        HashMap<int[], Integer> indice = new HashMap<int[], Integer> ();


        boolean bAddL = true;
        boolean bAddR = true;
        int [] preF = null;
        for(int i=0; i<buildings.length; i++){
            int rightH = 0;
            bAddL = true;
            bAddR = true;
            ListIterator<int []> iter = end.listIterator();
            while(iter.hasNext()){
                int [] pre = iter.next();
                int h = indice.get(pre);
                if(buildings[i][2] < h){
                    //check the left point of current rectangle
                    if(buildings[i][0] <= pre[0]){
                        bAddL = false;
                    }
                    //check the right point of current rectangle
                    if(buildings[i][1] <= pre[0])
                        bAddR = false;
                    //check if this rectange hides previous right points
                    else if(!bAddL && pre[1] < buildings[i][2]){
                        pre[1] = buildings[i][2];
                    }
                }
                else if(buildings[i][2] == h)
                {
                     if(buildings[i][0] <= pre[0]){
                        bAddL = false;
                    }
                    //check the right point of current rectangle
                    if(buildings[i][1] <= pre[0])
                        bAddR = false;
                    else if(!bAddL){
                        iter.remove();
                        indice.remove(pre);
                    }
                }
                else{
                    if(preF != null && buildings[i][0] == preF[0])
                        front.remove(preF);
                    //check the right point of current rectangle
                    if(bAddR && buildings[i][1] < pre[0] && rightH < h)
                        rightH = h;
                    //check if this rectange hides previous right points
                    if(buildings[i][0] <= pre[0] && buildings[i][1] >= pre[0]){
                        iter.remove();
                        indice.remove(pre);
                    }
                }
            }
            if(bAddL){
                int [] temp = new int[]{buildings[i][0],buildings[i][2]};
                front.add(temp);
                preF = temp;
            }
            if(bAddR){
                int [] temp = new int[]{buildings[i][1],rightH};
                end.add(temp);
                indice.put(temp, buildings[i][2]);
            }    
        }
        List<int[]> ret= new LinkedList<int[]>(front);
        if(end.size() > 0)
            ret.addAll(end);
        Collections.sort(ret, comparator);
        return ret;
    }
    //comment: other comparator usage:
    /*
    Collections.sort(ret, new Comparator<int[]>(){
        public int compare(int[] first, int[] second){
            return first[0] - second[0];
        }
    }
    );
    */
    public class arrComparator implements Comparator<int[]>{
        @Override
        public int compare(int[] first, int[] second){
            return first[0] - second[0];
        }
    }}

//other method:
//       divide and conquer (divide to left half buildings and right half buildings using recursion, then merge)
//              reference: https://leetcode.com/discuss/40963/share-my-divide-and-conquer-java-solution-464-ms
//       check all start points and end points using sorting (priority queue, collection.sort,  and tree map)
//              reference: https://leetcode.com/discuss/54201/short-java-solution