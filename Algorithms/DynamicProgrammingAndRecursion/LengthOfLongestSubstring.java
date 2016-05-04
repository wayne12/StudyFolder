/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3.
 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/



public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()<=1)
            return s.length();
        char [] chs = s.toCharArray();
        HashMap<Character, Integer> subStr = new HashMap<Character, Integer>();
        //LinkedList<Character> subStr = new LinkedList<Character>();
        int front = 0, len = 0;
;
        int i= 0;
        for(i=0; i<chs.length; i++){
            if(subStr.containsKey(chs[i])){
                if(i-front > len)
                    len = i-front;
                for(int j=front; j<subStr.get(chs[i]); j++){
                    subStr.remove(chs[j]);
                }
                front = subStr.get(chs[i]) + 1;
            }

            subStr.put(chs[i],i); // if not contains in the hash table add it, otherwise, update it

        }
        if(i - front > len)
            len = i-front;
        return len;
    }
    
   public int lengthOfLongestSubstring2(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }
}


