import java.util.*;

public class SearchRange {
   public static void main(String [] args){
      SearchRange obj = new SearchRange();
      try{
         int ret = obj.searchRange(new int []{1}, 0);
         System.out.println(ret);
      }
         catch(Exception ex){
      }
   }
    // binary search first. then search range (which cause time complexity may greater than O(lgn) )
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || (nums.length == 1 && nums[0] != target))
            return new int [] {-1, -1};
        int s = 0, end = nums.length-1;
        int pos = 0, mid;
        if(nums[s] == target){
                pos = s;
                s = end;
        }
        else if(nums[end] == target){
               pos = end;
               end = s;
        }
        while(s+1 < end){ // using s+1 instead of s to prevent infinit loop
            mid = s + (end-s)/2; //instead of mid = (s+end)/2 to prevent overflow
            if(nums[mid] == target){
                pos = mid;
                break;
            }
            else if(nums[mid] > target)
                end = mid;
            else
                s = mid;
        }
        int cnt = 0;
        if(nums[pos] == target){
            cnt++;
        }
        int rcnt = 0, lcnt = 0;
        while(pos-1-lcnt >= 0 && nums[pos-1-lcnt] == target){
            lcnt++;
        }
        while(pos+1+rcnt < nums.length && nums[pos+1+rcnt] == target){
            rcnt++;
        }
        return cnt==0? new int []{-1,-1}: new int []{pos-lcnt, pos+lcnt};
    }
    
    //this is another method using binary search, which improved time complexity in method 1
    public int[] searchRange2(int[] nums, int target) {
        if(nums == null || (nums.length == 1 && nums[0] != target))
            return new int [] {-1, -1};
        int s = 0, e = nums.length-1;
        int mid;
        
        if(nums[s] != target){ // find lower bound
            while(s < e){
               mid = s + (e-s)/2; 
               if(nums[mid] >= target)
                   e = mid;
               else
                   s = mid+1;
           }
        }
        int low = s;
        e = nums.length-1;
        if(nums[e] != target){ // find upper bound
           while(s < e){ 
               mid = s + (e-s+1)/2;  // same as ceiling of (s + (e-s)/2); using it to prevent infinit loop
               if(nums[mid] <= target)
                   s = mid;
               else
                   e = mid-1;
           }
        }

        return s >= nums.length || nums[s] != target ? new int []{-1,-1} : new int []{s,e};
    } 
    
    //comment: 
    /*
      1.
         (s+e)/2 is actually  Math.floor((s+e)/2);
         (s+e+1)/2 is actually Math.ceil((s+e)/2);
    
      2.  time complexity in Method1 is actually not O(lgn), it is (number of target)+O(lgn); So the worest case could be O(n) 
            (e.g. [2,2,2,2,...] 2)
    
    */
}