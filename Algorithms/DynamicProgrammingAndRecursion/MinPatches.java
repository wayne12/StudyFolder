/*
Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that 
any number in range [1, n] inclusive can be formed by the sum of some elements in the array.
 Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0

*/

public class MinPatches {
    public static void main(String [] args){
      MinPatches ss = new MinPatches();
      int [] arr = new int []{1,3};
      int ret = ss.minPatches(arr,6);
      System.out.println(ret);
   }

    public int minPatches(int[] nums, int n) {
        
        if (n <= 0)
            return 0;

        int count = 0;
        long preMax = 0;
        long preSum = 0;
        int curIdx = 0;
        long i=1;
        while(i<=n){
            if(i > preSum){
                if(curIdx >= nums.length || nums[curIdx] > i){
                    count++;
                    preSum += i;
                }
                else{
                    preSum += nums[curIdx];
                    curIdx++;
                }
                if(preSum >= n)
                    return count;
            }
            else{
               i = preSum+1;
            }
        }
        return count;
    }
    
    //without using sum, and changing i every time after patching one element or checking one existed element.
    //e.g.  [1,3]:  i = 1(initial), 2(after checking 1), 4(after patching 2), 7(after checking 3)
    public int minPatches2(int[] nums, int n) {
        if (n <= 0)
            return 0;

        int count = 0;
        int curIdx = 0;
        long i = 1;
        
        while(i <= n)
        {
            if(curIdx >= nums.length || nums[curIdx] > i){
                count++;
                i += i;
            }
            else{
                i += nums[curIdx];
                curIdx++;
            }
        }
        return count;
    }
}

///Question analysis:
/*
   the goal of this question is to patching an array with minimum number of elements to make 
   the array elements are able to sum up to 1....n, n and the array are given.
   
   1. suppose the given array is empty, what will be the result?
      in this case, the goal is to come up with an array, whose element can be added up to eaual any number in [1 n];
      
           n       minimum patching
           
      (1), 1 ----> patch 1 element
      (2), 2 ----> patch 2 element (1, 2)
      (3), 3-----> ***do not patch more
      (4), 4-----> patch 3 element (1, 2, 3) or (1, 2, 4)
      (5), 5-----> ***do not patch more
      (6), 6-----> ***do not patch more
      (7), 7-----> **do not patch more for (1,2,4), patch 1 more for (1,2,3) to (1,2,3,4) or (1,2,3,5) or ...
      (8), 8-----> patch 4 element (1,2,3,4) or (1,2,3,5) or (1,2,4,5) or (1,2,4,6) .... (1,2,4,8)
      ...
      On the basis of the analysis, the patching of (1), (1,2), (1,2,4), (1,2,4,8), (1,2,4,8,16) ... are optimum patching.
      
      One solution is, for any positive number n, find x for 2^0 + 2^1 + 2^2+ ... + 2^(x-1) < n <= 2^0 + 2^1 + 2^2+ ... + 2^x
                 (better way to write this   2^x  <= n   < 2^(x+1) since  2^x = 2^0 + ... + 2^(x-1) + 1 )
      
         then x+1 will be the minimum patching number.
      
         The program can be write as loop x and compare the sum with n
         
      Idea: every time patched a number, tha patched part of array will be able to cover several numbers 
            e.g. [1,2] covers 1,2,3;     [1,2,4] covers 1,2,3,4,5,6,7
            That is: by patching 4 to the array, the array is able to cover four more numbers (4,5,6,7)
           
         
  2. if the given array is not empty, how should we find the minimum patching number?
     The basic idea of patching is similar with the above one except some element already given.
     Calculateing minimum patching for 1, then 2, ... until n (define this viraible as i).
     
     During this process, comparing i with the sum of patched numbers.( e.g   [1, 3]  i=4, sum = 1+2+3 )
        
        if i less or equal the sum of patched numbers, do not patch more, just check next i , which is sum + 1.
            since 1 to sum have already been covered by the patched numbers. e.g. [1,2,4] covers 1...8
       
        if i greater than the sum, may need to patch one element to the array deciding by whether the existing element in the array is
           greater than the one should be patched (which is sum + 1 or i), 
           if existing elment is less or equal than the one should be patched,
           do not patch. otherwise, patch one element, which is i
           
      loop through i until it reaches n

     in order to make program more efficient , changing i according to the sum of patched number instead of add 1. e.g. i = sum + 1 instead of
     
     i++;
          
         
*/
