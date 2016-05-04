/*
   A message containing letters from A-Z is being encoded to numbers using the following mapping:
   
   'A' -> 1
   'B' -> 2
   ...
   'Z' -> 26
   Given an encoded message containing digits, determine the total number of ways to decode it.
   
   For example,
   Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
   
   The number of ways decoding "12" is 2.
*/


// a dynamic programming problem


public class NumDecode {
    public int numDecodings(String s) {
        if(s.isEmpty() || s.charAt(0) == '0')
            return 0;
        int count = 1;
        int preCount = 1;
        byte pre = Byte.decode(s.substring(0,1));
        for(int i=1; i<s.length(); i++){
            byte cur = Byte.decode(s.substring(i,i+1));
            if(pre == 0 && cur == 0){
                return 0;
            }
            else if(cur == 0){
                if(pre > 2){
                    return 0;
                }
                else
                {
                    count = preCount;
                }
            }
            else if(pre != 0 && pre*10+ cur <=26){
                    int temp = preCount;
                    preCount = count;
                    count += temp;
            }
            else
                preCount = count;
            pre = cur;
        }
        return count;
    }
    
    //more clear dynamic programming solution
    public int numDecodings2(String s) {
        if(s.isEmpty() || s.charAt(0) == '0')
            return 0;
        int len = s.length();
        int [] count = new int[len + 1];
        count[0] = 1;
        count[1] = s.charAt(0) == '0'? 0:1;

        byte pre = Byte.decode(s.substring(0,1));
        for(int i=1; i<s.length(); i++){
            byte cur = Byte.decode(s.substring(i,i+1));
            int twoDigt = pre * 10 + cur;
            if(cur != 0){
                count[i+1] += count[i]; 
            }
            if(twoDigt >= 10 && twoDigt < 27){
                count[i+1] += count[i-1];
            }
            pre = cur;
        }
        return count[len];
    }
}