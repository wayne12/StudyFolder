/*
* Author: Hongwei Lu
* Bubble sort for asscending order
* 3/4/16
*/


public class BubbleSort{

	public static void main(String [] args){
      BubbleSort bs = new BubbleSort();
      int [] ar = new int []{6,3,2,4,8};
      int [] rar = bs.bubbleSort(ar);
      for(int i = 0; i < ar.length; i++)
         System.out.println(rar[i]);
      return;
	}

	int [] bubbleSort(int[] arr){
		if(arr == null || arr.length == 0)
			return null;
		int len = arr.length;
		for(int i = len - 1 ; i > 1; i--){
			for(int j = 0; j < i; j ++){
				if(arr[j] > arr[j+1])
					swap(arr, j, j+1);
			}
		}
      return arr;
	}
	
	public void swap(int [] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return;
	}


}
