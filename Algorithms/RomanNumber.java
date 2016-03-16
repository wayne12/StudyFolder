/*
*   any of the letters representing numbers in the Roman numerical system: 
*   I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1,000. 
*   In this system, a letter placed after another of greater value adds (thus XVI or xvi is 16),
*   whereas a letter placed before another of greater value subtracts (thus XC or xc is 90).
*   assum numbers are in range of 0 to 3999
*/


public class RomanNumber{

   public static void main(String [] args){
      RomanNumber romNum = new RomanNumber();
      String S = "MMCDXLVII";
      try{
      int ret = romNum.romanToInteger1(S);
      int ret2 = romNum.romanToInteger2(S);
      String ret3 = romNum.IntegerToRoman(2447);
      System.out.println(ret+" "+ret2+" "+ret3);
      }
      catch(Exception ex){
      }
   }

   //two solutions for roman numerical string to integer
   ////1. check letters in backward
   public int romanToInteger1(String s)throws Exception{
      if(s == null || s.isEmpty())
         throw new Exception();
         
      int sum = 0;
      int pre = Integer.MIN_VALUE; // mininum value
      int len = s.length();
      
      for(int i = len-1; i >= 0; i--){
         int val = romanMap(s.charAt(i));
         if(val >= pre)
            sum += val;
         else
            sum -= val;
         pre = val;
      }
      return sum;
   }
   ////2. check letters from front to end
   public int romanToInteger2(String s)throws Exception{
      if(s == null || s.isEmpty())
         throw new Exception();
         
      int sum = 0;
      int pre = Integer.MAX_VALUE; // maximum value
      int len = s.length();
      
      for(int i = 0; i < len; i++){
         int val = romanMap(s.charAt(i));
         sum += val;
         if(val > pre)
            sum -= pre*2;
         pre = val;
      }
      return sum;
   }
   
   //Integer to roman string
   public String IntegerToRoman(int n)throws Exception{
      if(n<0||n>3999)
         throw new Exception();
      StringBuilder strBuilder = new StringBuilder();
      int temp = n;
      while (temp >= 1000){
         strBuilder.append(romanMap2(1000));
         temp -= 1000;
      }
      for(int i=1; i<=100; i*=10){
         if(temp >= 900/i){
            strBuilder.append(romanMap2(100/i));
            strBuilder.append(romanMap2(1000/i));
            temp -= 900/i;
         }
         if(temp >= 500/i){
            strBuilder.append(romanMap2(500/i));
            temp -= 500/i;
         }
         if(temp >= 400/i){
            strBuilder.append(romanMap2(100/i));
            strBuilder.append(romanMap2(500/i));
            temp -= 400/i;
         }
         while(temp >= 100/i){
            strBuilder.append(romanMap2(100/i));
            temp -= 100/i;
         }
      }
      return strBuilder.toString();
   }
   
   
   
   
   
   public int romanMap(char c)throws Exception{
      switch(c){
         case 'I':
            return 1;
         case 'V':
            return 5;
         case 'X':
            return 10;
         case 'L':
            return 50;
         case 'C':
            return 100;
         case 'D':
            return 500;
         case 'M':
            return 1000;
         default:
            throw new Exception();
      }
   }
    public char romanMap2(int n)throws Exception{
      switch(n){
         case 1:
            return 'I';
         case 5:
            return 'V';
         case 10:
            return 'X';
         case 50:
            return 'L';
         case 100:
            return 'C';
         case 500:
            return 'D';
         case 1000:
            return 'M';
         default:
            throw new Exception();
      }
   }

}