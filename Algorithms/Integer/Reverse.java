public class Reverse {
   public static void main(String [] args){
      Reverse obj = new Reverse();
      int ret = obj.reverse2(-2147483648);
      System.out.println(ret);
   }
    public int reverse(int x) {
        int reverse = 0;
        final int maxlen = 10;
        int [] digits = new int [maxlen+1];
        int count = 1;
        int temp = x;
        while(count <= maxlen){
            digits[count] = temp - (temp/10)*10; // same with temp % 10
            temp = temp/10;
            if(temp == 0)
               break;
            count++;

        }
        int val = 1;
        for(int i=count; i>1; i--){
            reverse += digits[i]*val;
            val *= 10;
        }
        long ll = new Long((long)(reverse + digits[1] * (long)val));
        if(ll > Integer.MAX_VALUE ||  ll < Integer.MIN_VALUE)
            return 0;
        else 
            return reverse + digits[1] * val;
    }
    
    //from https://leetcode.com/discuss/18785/my-accepted-15-lines-of-code-for-java
    public int reverse2(int x)
   {
       int result = 0;
   
       while (x != 0)
       {
           int tail = x % 10;
           int newresult = result * 10 + tail;
           if ((newResult - tail) / 10 != result)
           {
               return 0; 
           }
           result = newResult;
           x = x / 10;
       }
   
       return result;
   }
}