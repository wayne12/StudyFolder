import java.util.*;

public class subSet{
   //test
   public static void main(String [] args){
      subSet ss = new subSet();
      int [] arr = new int []{1,2,3};
      List<List<Integer>> retList = ss.subsetOf(arr);
      System.out.println(retList);
      List<List<Integer>> retList2 = ss.subsetOf2(arr);
      System.out.println(retList2);
   }
   
   //get subset of a set, using dynamic programming
   public List<List<Integer>> subsetOf(int [] nums){
      if(nums == null)
         return null;
      List<List<Integer>> retList = new LinkedList<List<Integer>>();
      retList.add(new LinkedList<Integer>()); //add empty set to result
      int len = nums.length;
      for(int i=0; i<len; i++){    
         ListIterator<List<Integer>> liter = retList.listIterator();    
         while(liter.hasNext()){
            List<Integer> newSet = new LinkedList<Integer>(liter.next());
            newSet.add(nums[i]);
            liter.add(newSet);
         }
      }
      return retList;
   }
   //get subset of a set, using recursion
   public List<List<Integer>> subsetOf2(int [] nums){
      if(nums == null)
         return null;
      return subsetOf_help2(nums, 0, nums.length-1);
   }
   public List<List<Integer>> subsetOf_help2(int [] nums, int s, int e){   
      int len = e-s+1;
      if(len == 0){
         LinkedList<List<Integer>> ret = new LinkedList<List<Integer>>();
         ret.add(new LinkedList<Integer>());
         return ret; //add empty set to result
      }
      else{
         List<List<Integer>> ret = subsetOf_help2(nums, s, e-1);
         ListIterator<List<Integer>> liter = ret.listIterator();    
         while(liter.hasNext()){
            List<Integer> newSet = new LinkedList<Integer>(liter.next());
            newSet.add(nums[e]);
            liter.add(newSet);
         }
         return ret;
      }
   }
}