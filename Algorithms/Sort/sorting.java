//sorting algorithms : heap sort, quick sort, merge sort, selection sort insertion sort, bubble sort
//                     bucket sort, acconting sort, radix sort

public class sorting{

   //Merge sort
   // it is based on the divide-and-conquer paradigm. It involves the following three steps:
   //   Divide the array into two (or more) subarrays
   //   Sort each subarray (Conquer)
   //   Merge them into one (in a smart way!)
   public void mergeSort(int []arr) throws Exception{
      if(arr == null || arr.length == 0)
         throw new Exception();
      int len = arr.length;
      helpSort(arr, 0, len-1);   
   }
    public void helpSort(int []arr, int s, int end){
      if(s >= end)
         return;

      int mid = (s+end)/2;
      helpSort(arr, s, mid);
      helpSort(arr, mid+1, end);
      
      //merge
      int[] temp = new int [end-s+1];
      int idx = 0, m=s, n=mid+1;
      while(m <= mid && n <= end){
         if(arr[m] < arr[n]){
            temp[idx++] = arr[m++];
         }
         else
            temp[idx++] = arr[n++];
      }
      while(m <= mid)
            temp[idx++] = arr[m++];
      while(n <= end)
            temp[idx++] = arr[n++];
            
      return;
         
    }


   //insertion sort
   // To sort unordered list of elements, we remove its entries one at a time 
   // and then insert each of them into a sorted part (initially empty):
   public void insertionSort(int arr[])throws Exception{
      if(arr == null || arr.length == 0)
         throw new Exception();
      int len = arr.length;   
      for(int i = 1; i < len; i++){
         int value = arr[i];
         int j;
         for(j = i-1; j >= 0 && arr[j] > value; j--){
               arr[j+1] = arr[j];
         }
         arr[j+1] = value;
      }
      return ;
   }
   
 
   //selection sort
   // The algorithm works by selecting the smallest unsorted item and then swapping it
   // with the item in the next position to be filled.The selection sort works as follows: 
   // you look through the entire array for the smallest element, once you find it you 
   // swap it (the smallest element) with the first element of the array. Then you look for 
   // the smallest element in the remaining array (an array without the first element) and 
   // swap it with the second element. Then you look for the smallest element in the 
   // remaining array (an array without first and second elements) and swap it with 
   // the third element, and so on.
   public void selectionSort(int [] arr)throws Exception{
      if(arr == null || arr.length == 0)
         throw new Exception();
      int len = arr.length;
      int min;
      for(int i=0; i < len-1; i++){
      //asume i is the index of min value, then compare others with the value of arr[min]
      // change index min when another value is smaller then it.
         min = i;
         for(int j = i; j < len; j++){
            if(arr[j] < arr[min])
               min = j;
         } 
         int temp = arr[i];
         arr[i] = arr[min];
         arr[min] = temp;
      }
   }
   //get smallest index in a array: assume a min index first
   
   
   //bubble Sort
   // The algorithm works by comparing each item in the list with the item next to it,
   // and swapping them if required. In other words, the largest element has bubbled to 
   // the top of the array. The algorithm repeats this process until it makes a pass all 
   // the way through the list without swapping any items.
   public void bubbleSort(int[] arr){
		if(arr == null || arr.length == 0)
			return ;
		int len = arr.length;
		for(int i = len - 1 ; i >= 1; i--){
			for(int j = 0; j < i; j ++){
				if(arr[j] > arr[j+1])
					swap(arr, j, j+1);
			}
		}
      return ;
	}
	//swap two element in a array
	public void swap(int [] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return;
	}

}