public class CountBits {


   //O(n) time complexity solution
    public int[] countBits(int num) {
        if(num < 0)
            throw new IllegalArgumentException();
        int [] counts = new int[num+1];
        if(num == 0)
            return counts;
        else{
            counts[1] = 1;
            if(num == 1){
                return counts;
            }
        }
        int i = 2;//next index
        int len = 1; //pre length bits(1,2,4,8,16,...)
        while(true){
            for(int j = i; j<i+len; j++){
                counts[j] = counts[j-len];
                if(j == num)
                    return counts;
            }
            for(int k = i+len; k<i+2*len; k++){
                counts[k] = counts[k-len] + 1;
                if(k == num)
                    return counts;
            }
            i = i+2*len;
            len *= 2;
        }
    }
    //O(n *sizeof(bits)) solution
   public int[] countBits2(int num) {
      if(num < 0)
         throw new IllegalArgumentException();
      int [] counts = new int [num+1];
      int temp = 1;
      for(int i=1; i<= num; i++){
         temp = i;
         while(temp != 0){
            if(temp % 2 != 0)
               counts[i]++;
            temp >>= 1;
         }
      }
      return counts;
   }
}