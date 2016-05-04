/*
   Problem 1:
      Given a sorted array, remove the duplicates in place such that each element appear only once 
      and return the new length.
      
      Do not allocate extra space for another array, you must do this in place with constant memory.
      
      For example,
      Given input array nums = [1,1,2],
      
      Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
      It doesn't matter what you leave beyond the new length.

*/


public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0)
            return 0;
        int nextIndex = 1;
        for(int i=1; i<nums.length; i++){
            if(nums[i] > nums[i-1]){
                if(i != nextIndex){
                    nums[nextIndex] = nums[i];
                }
                nextIndex++;
            }
        }
        return nextIndex;
    }

/*
   Problem 2:
      Given an array and a value, remove all instances of that value in place and return the new length.
      
      Do not allocate extra space for another array, you must do this in place with constant memory.
      
      The order of elements can be changed. It doesn't matter what you leave beyond the new length.
      
      Example:
      Given input array nums = [3,2,2,3], val = 3
      
      Your function should return length = 2, with the first two elements of nums being 2.
*/

    public int removeElement(int[] nums, int val) {
        if(nums.length == 0)
            return 0;
        int idx = 0;
        for(int n: nums){
            if(n != val){
                nums[idx++] = n;
            }
        }
        return idx;
    }
    
}