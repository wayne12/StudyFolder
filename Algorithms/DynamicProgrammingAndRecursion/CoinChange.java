/* Problem description:
   You are given coins of different denominations and a total amount of money amount. 
   Write a function to compute the fewest number of coins that you need to make up that amount. 
   If that amount of money cannot be made up by any combination of the coins, return -1.
   Example 1:
   coins = [1, 2, 5], amount = 11
   return 3 (11 = 5 + 5 + 1)
   
   Example 2:
   coins = [2], amount = 3
   return -1.
*/
// dynamic programming analysis
// base case counts[0] = 0;// if amount = 0, coin number is 0;
// then for amount is k, its coin number is coin number of (k-coins[j]) + 1, 
//     where i is the index of a coin in the coins array.
// find the smallest coin number for amount k.
// the way to think this problem is :
//   how to find the smallest coin number of an amount k from previous amounts.
public class CoinChange {

        //solution1: dynamic programming.
    public int coinChange(int[] coins, int amount) {
        if(coins == null)
            throw new IllegalArgumentException();
        int [] counts = new int [amount+1];
        //leave counts[0]=0, set other to max value
        for(int i=1; i<= amount; i++){
            counts[i] = Integer.MAX_VALUE;  
        }
        for(int i=1; i<=amount; i++){
            for(int j=0; j<coins.length; j++){
                if(i >= coins[j] && counts[i-coins[j]] != Integer.MAX_VALUE){
                    counts[i] = Math.min(counts[i],counts[i-coins[j]]+1);
                }
            }
        }
        if(counts[amount] == Integer.MAX_VALUE && amount != Integer.MAX_VALUE)
            return -1;
        return counts[amount];
    }
    // solution 2: recursion
    public int coinChange2(int[] coins, int amount){
      if(coins == null)
           throw new IllegalArgumentException();
      int min = Integer.MAX_VALUE;
      if(amount == 0)
         return 0;
      if(amount < 0)
         return -1;
      int ret = 0;
      for(int i=0; i<coins.length; i++){
         if(amount > coins[i]){
            ret = coinChange2(coins, amount-coins[i])+1;
            if(ret < min) 
               min = ret;
         }
      }
      if(min == Integer.MAX_VALUE && amount != Integer.MAX_VALUE)
         return -1;
      return min;
    }
}