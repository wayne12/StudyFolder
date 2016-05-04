/*
* Author: Hongwei Lu
* Description: solving Climbing Stairs problem 
* Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
* 3/5/16
*/
import java.lang.*;
import java.util.*;
import java.io.*;

/*
* notes: take 1 or 2 steps to get nth steps from (n-2)th step or (n-1)th step
*        so, countWays(n) = countWays(n-2) + countWays(n-1)
*/
public class climbStairs{
   public static void main(String [] args){
      climbStairs obj = new climbStairs();
      try{
         int ret;
         int steps = 2;
         
         //test for dp
         ret = obj.countWays(steps);
         System.out.println(ret);
         
         //test for recursion
         ret = obj.countWays_Recursion(steps);
         System.out.println(ret);
         
      }
      catch(Exception e){
         System.out.println("no result!");
      }
   }
   
   // countWays
   // pre-conditions:
   //          an integer n
   // post-conditions:
   //          return number of ways clibming to the  nth stairs
   // notes:
   //          using dynamic programing
   public int countWays(int n)throws Exception{
      if(n < 0)
         throw new Exception();
      if(n <= 2)
         return n;
         
      int first = 1;
      int second = 2;
      
      int curCount = -1;
      int i = 3;
      while(i++ <= n){
         curCount = first + second;
         first = second;
         second = curCount;
      }
      return curCount;
   }
   // countWays
   // pre-conditions:
   //          an integer n
   // post-conditions:
   //          return number of ways clibming to the  nth stairs
   // notes:
   //          using recursion
   public int countWays_Recursion(int n)throws Exception{
      if(n < 0)
         throw new Exception();
      if(n <= 2)
         return n;
      return countWays_Recursion(n-1) + countWays_Recursion(n-2);
   }
}