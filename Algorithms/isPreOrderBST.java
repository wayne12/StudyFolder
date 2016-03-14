//given a array, determin if it is a pre-order traversal of a binary search tree
//average time complexity: O(nlgn)  (worest case: n^2)

public class isPreOrderBST{



   //solution 1:  compare root with right most of left subtree, and left most of right subtree
   public boolean isPreBST1(int [] arr){
      if(arr == null||arr.length == 0) 
         return false;
      return isPreBST_Help1(arr, 0, arr.length-1);
   }
   public boolean isPreBST_Help1(int []arr, int s, int e){
      if(s == e)
         return true;
         
      int idx = s+1;
      //find seperator position of left tree and right tree
      while(idx <= e && arr[idx] < arr[s])
         idx++;
         
      if((idx != s+1)// has left tree
         &&(arr[s] < rightMost(arr, s+1, idx-1))) // root is less than right most of left tree
         return false;
         
      if((idx != e+1)// has right tree
         &&(arr[s] > leftMost(arr, idx, e)))//root is greater than left most of right tree
         return false;
         
      boolean bleft = true, bright = true;
      
      if(idx != s+1)
         bleft = isPreBST_Help1(arr, s+1, idx-1); 
         
      if(idx != e+1)
         bright = isPreBST_Help1(arr, idx, e); 
         
      return bleft&&bright;
   }
   
   public int leftMost(int []arr,int s, int e){
      if(s == e)
         return arr[s];
      int idx = s+1;   
      while(idx <= e && arr[idx] < arr[s]){idx++;}
      if(idx  == s+1)//no left tree
         return arr[s];
      else{ // recursion
         return leftMost(arr, s+1, idx-1);
      }
      
   }
   public int rightMost(int []arr, int s, int e){
      if(s == e)
         return arr[s];
      int idx = s+1;         
      while(idx <= e && arr[idx] < arr[s]){idx++;}
      if(idx ==  e+1) // no right tree
         return arr[s];
      else // recursion
         return rightMost(arr, idx, e);
   }
   
   //solution 2: using maximum value and minimam value(range of a node value)
      public boolean isPreBST2(int [] arr){
      if(arr == null || arr.length == 0)
         return false;
      return isPreBST_Help2(arr, 0, arr.length-1, Integer.MAX_VALUE, Integer.MIN_VALUE);
   }
   public boolean isPreBST_Help2(int []arr, int s, int e, int max, int min){
      
      if(arr[s] > max || arr[s] < min)
            return false;
      if(s == e)
         return true;
         
      int idx = s+1;
      while(idx < e && arr[idx] < arr[s]){idx++;}
      
      boolean bleft = true, bright = true;
      if(idx != s+1) // has left tree
         bleft = isPreBST_Help2(arr, s+1, idx-1, arr[s], min);
      if(idx != e+1)// has right tree
         bright = isPreBST_Help2(arr, s+1, idx-1, max, arr[s]);
      return bleft && bright;
   }
   
   //solution 3: compare all left nodes and right nodes with root value
   public boolean isPreBST3(int [] arr){
      if(arr == null || arr.length == 0)
         return false;
      return isPreBST_Help3(arr, 0, arr.length-1);
   }
   public boolean isPreBST_Help3(int [] arr, int s, int e){
      if(s==e)
         return true;
      int idx = s+1;
      while(idx < e && arr[idx] < arr[s]){idx++;}
      
      boolean bleft = true, bright = true;
      if(idx != s+1){//has left tree
         for(int i=s+1; i <= idx-1; i++){
            if(arr[i] > arr[s])
               return false;
         }
         bleft = isPreBST_Help3(arr, s+1, idx-1);
      }
      if(idx != e+1){ // has right tree
         for(int i=idx; i<=e; i++){
            if(arr[i] < arr[s])
               return false;
         }
         bright = isPreBST_Help3(arr, idx, e);
      }
      return bleft && bright;
   }
}