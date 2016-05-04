/*
   Given a string S, find the longest palindromic substring in S. 
   You may assume that the maximum length of S is 1000, 
   and there exists one unique longest palindromic substring.
*/
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if(s.length() <= 1)
            return s;
        char [] chs = s.toCharArray();
        int front = 0, end = 0;
        for(int i=1; i<chs.length; i++){
            if(chs[i-1] == chs[i])
            {
                int j = 1;
                while(i-1-j >=0 && i+j < chs.length){
                    if(chs[i-1-j] != chs[i+j])
                        break;
                    j++;
                }
                if((i+j - (i-1-j) -1) > end - front + 1)
                {
                    front = i-1-j+1;
                    end = i+j-1;
                }
                if(end - front + 1 >= (chs.length - i)*2)
                    break;
            }
            if(i < chs.length-1 && chs[i-1] == chs[i+1]){
                int j = 1;
                while(i-1-j >= 0 && i+1+j < chs.length){
                    if(chs[i-1-j] != chs[i+1+j])
                        break;
                    j++;
                }
                if((i+1+j - (i-1-j) -1) > end - front + 1)
                {
                    front = i-1-j+1;
                    end = i+1+j-1;
                }
                if(end - front + 1 >= (chs.length - i)*2)
                    break;
            }
            
        }
        return s.substring(front,end+1);
    }
}