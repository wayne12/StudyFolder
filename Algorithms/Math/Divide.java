/*
   Divide two integers without using multiplication, division and mod operator.
   
   If it is overflow, return MAX_INT.
*/

public class Divide {
   public static void main(String [] args){
      Divide obj = new Divide();
      try{
         int ret = obj.divide(-2147483648,2);
         System.out.println(ret);
      }
         catch(Exception ex){
      }
   }
   /*
   
   if log and pow can be used. then dvs * 2^n = dvd  ==> n = l0g2(dvd) - log2(dvs) ==> 2^n = 2^(log2(dvd) - log2(dvs))
   if do not use log and pow, then using bit manipulation
   the idea is x/y = (x1 + x2 + ..)/y  = (y*2^n1 + y*2^n2+ y*2^n3 + ...)/y
   so, find 2^n1, then find 2^n2 , .... and add them together. ( 2^n1 = 1<<n1 )
   
   */
    public int divide(int dividend, int divisor) {
        if(divisor == 0)
            return Integer.MAX_VALUE;
        if(dividend == divisor)
            return 1;
        if(dividend == 0 || divisor == Integer.MIN_VALUE)
            return 0;
        if(divisor == 1)
            return dividend;
        if(divisor == -1)
            return dividend == Integer.MIN_VALUE? Integer.MAX_VALUE: -dividend;
        
        int sign = (dividend <= 0 && divisor <= 0 || dividend >= 0&& divisor >= 0)? 1:-1;
        int shift = 0;
        int x = dividend;
        int y = divisor < 0? -divisor : divisor;
        if(x < 0){
            if(x == Integer.MIN_VALUE){
                x = x+y; //shift y
                shift = sign;
            }
            x = 0-x;// x = -x;
        }
        if(x < y)
            return shift;
        
        long sum = y;
        
        int count = 0;
        int pre;
        int res = 0;
        while(sum <= x){
            pre = (int)sum;
            sum <<= 1;
            if(sum > x){
                x = x - pre;
                sum = y;
                res += (1<<count);
                count = 0;
            }
            else
                count++;
        }
        
        return sign<0? -(res+shift): (res+shift);
    }
}

/* comment

small trick

while( x+b < y){
   x += b;
}
instead of

int pre;
while( x < y){
   pre = x;
   x += b;
   if(x >= y)
      ...
}


*/