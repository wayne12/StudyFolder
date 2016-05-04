/*
   Implement next permutation, 
   which rearranges numbers into the lexicographically next greater permutation of numbers.
   
   If such arrangement is not possible, 
   it must rearrange it as the lowest possible order (ie, sorted in ascending order).
   
   The replacement must be in-place, do not allocate extra memory.
   
   Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
   
   1,2,3 -> 1,3,2
   3,2,1 -> 1,2,3
   1,1,5 -> 1,5,1
*/

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1)
            return;
        int s = nums.length-1;
        
        while(s > 0){
            if(nums[s] > nums[s-1]){
                //swap nums[s-1] with the next greater element in range of [s, nums.length-1]
                
                int idx = nums.length-1;
                //find the next greater value
                while(nums[idx] <= nums[s-1]) 
                    idx--;
                //swap
                int temp = nums[s-1];
                nums[s-1] = nums[idx];
                nums[idx] = temp;
                break;
            }
            s--;
        }
        
        int e = nums.length-1;
        //reverse order of values in range of [s, nums.length-1]
        while(s < e && nums[s] > nums[e]){
            int temp = nums[s];
            nums[s++] = nums[e];
            nums[e--] = temp;
        }
        
    }
}