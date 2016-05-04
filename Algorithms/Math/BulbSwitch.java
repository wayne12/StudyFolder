/*
There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.
*/


public class BulbSwitch {
    public int bulbSwitch(int n) {
        int [] counts = new int [n+1]; //time of changes
        int s = (int)Math.sqrt(n);
        for(int i = 1; i <= s; i++){ // n rounds
            counts[i*i]++;
            for(int j=i+1; j<=n/i; j++){
                counts[i*j] += 2;
            }
        }
        int count = 0;
        for(int i=1; i<=n; i++){
            if(counts[i]%2 != 0)
                count++;
        }
        return count;
    }
}
/* *** Comment ****
   This is a solution exceed the time limit. However, I'm pretty pround of it. It is very close to the best solution. When I saw a best solution,
   I already knew the reason.
   
   It's actually a math problem. The solution I wrote above is counting the changing times of each bulb. In order to be more efficiency, 
   
   I draw a table to see how can I eliminate the work in first for loop.
   
   The table is:
   
   1  2  3  4  5  6  7  8  9  10
   
   2  4  6  8  10 12 14 16 18 20
   
   3  6  9  12 15 18 21 24 27 30
   
   4  8  12 16 20 24 28 32 36 40
   
   5  10 15 20 25 30 35 40 45 10
   
   6  12 18 24 30 36 42 48 54 60
   
   7  14 21 28 35 42 49 56 63 70
   
   8  16 24 32 40 48 56 64 72 80
   
   9  18 27 36 45 54 63 72 81 90
   
   10 20 30 40 50 60 70 80 90 100
   
   From the table I find out it is symantric. That means counts[i*j] = counts[j*i]. So I don't have to count twice.
   Another thing is for diagonal numbers which are counts[i*i], they only count once.
   The last thing I notice is I don't have to loop from 1 to n. I only need loop to squart root of n, because the numbers after
   sqrt(n) are already count. e.g. for i<j  when count counts[i*j], I count  counts[j*i] too, which is a number after sqrt(n).
   
   
   */