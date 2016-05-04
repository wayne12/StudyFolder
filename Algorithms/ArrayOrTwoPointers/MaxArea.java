/*
   Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
   n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
   which together with x-axis forms a container, such that the container contains the most water.
   
   Note: You may not slant the container.
*/

public class MaxArea {
    public int maxArea(int[] height) {
        if(height.length < 2)
            return 0;
        int max = 0;
        for(int i=0; i<height.length-1; i++){
            if(i> 0 && height[i] <= height[i-1])
                continue;
            for(int j=height.length-1; j>=i+1; j--){
                int h = height[i] > height[j] ?  height[j]:height[i];
                if(max < (j-i)*h)
                    max = (j-i)*h;
                    if(h >= height[i])
                        break;
            }
        }
        return max;
    }
    
    //using two pointers
    public int maxArea2(int[] height) {
        if(height.length < 2)
            return 0;
        int max = 0;
        int h=0;
        int w=0;
        int front = 0, end = height.length-1;
        while(front < end){
            w = end - front;
            if(height[front] < height[end]){ // max area is decided when left line is shorter
               h = height[front++];
            }
            else if(height[front] > height[end]){ // max area is decided when right line is shorter
               h = height[end--];
            }
            else{
               h = height[front++];
               end--;
            }
            if( max < w*h)
                  max = w*h;   
        }
        return max;
   }
}