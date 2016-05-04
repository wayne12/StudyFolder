/*
*  water trapping program
*     Given n non-negative integers representing an elevation map where the width of each bar is 1, 
*     compute how much water it is able to trap after raining.
*     For example, 
*     Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6
*  3/21/16
*/

import java.util.Stack;
public class TrappingWater {
   public static void main(String [] args){
      TrappingWater obj = new TrappingWater();
      int ret = obj.trap(new int [] {0,1,0,2,1,0,1,3,2,1,2,1});
      System.out.println(ret);
   }
    public int trap(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        int len = height.length;
        int sum = 0;
        //find tallest one
        int max = height[0];
        int index = 0;
        for(int i=1; i<len; i++){
            if(max < height[i]){
                   max = height[i];
                   index = i;
                }
        }
        int pointer = 0, curMax = 0;
        //check left side
        while(pointer < index){
            if(height[pointer] < curMax){
                sum += curMax-height[pointer];
                pointer++;
            }
            else{
               curMax = height[pointer++];
            }
        }
        //check right side
        pointer = len -1;
        curMax = 0;
        while(pointer > index){
            if(height[pointer] < curMax){
                sum += curMax-height[pointer];
                pointer--;
            }
            else{
               curMax = height[pointer--];
            }
        }
        return sum;
    }
    
    //another method is using stack
    //from https://leetcode.com/discuss/15634/a-stack-based-solution-for-reference-inspired-by-histogram
    public int trap2(int[] height) {
        if (height==null) return 0;
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < height.length){
            if (s.isEmpty() || height[i]<=height[s.peek()]){
                s.push(i++);
            }
            else {
                int bot = s.pop();
                maxBotWater = s.isEmpty()? // empty means no il
                0:(Math.min(height[s.peek()],height[i])-height[bot])*(i-s.peek()-1);
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }
}