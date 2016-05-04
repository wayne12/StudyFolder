//max value using recursion

public int maxV(int arr[], int start){
   if(arr.length - start == 1)
      return arr[start];
   else{
      return Math.max(arr[start], maxV(arr, start+1));
   }
}