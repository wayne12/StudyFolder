/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. 
If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
You are responsible to gather all the input requirements up front.
*/


public class Atoi {



//handling cases:
   // discard beginning white spaces
   // handling overflow
   // sign of the number
   // empty string
   // handling non numberic character
    public int myAtoi(String str) {
        if(str.isEmpty())
            return 0;
        char [] chs = str.toCharArray();
        int len = chs.length;
        
        long result = 0;
        int signal = 1, start = 0;
        
        while(chs[start] == ' ')
            start++;
            
        if(chs[start] == '+')
            start++;
        else if(chs[start] == '-'){
            signal = -1;
            start++;
        }
        
        for(int i=start; i<len; i++){
            if(!Character.isDigit(chs[i]))
                return (int)result*signal;
            result = result * 10 + Character.getNumericValue(chs[i]);
            if(result*signal > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            if(result*signal < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return (int)result * signal;
    }
}