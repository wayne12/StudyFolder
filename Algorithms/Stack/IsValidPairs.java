

import java.util.Stack;
public class IsValidPairs {
   public static void main(String [] args){
      IsValidPairs obj = new IsValidPairs();
      boolean ret = obj.isValid2("()([{}]{})()");
      System.out.println(ret);
   }
    public boolean isValid(String s) {
        //note: typical stack problem
        //also, could use recursion for this problem
        if(s.isEmpty())
            return true;
        Stack<Character> st = new Stack<Character>();
        char [] chs = s.toCharArray();
        int len = chs.length;
        for(int i=0; i<len; i++){
            if(chs[i] == ')'||chs[i] == '}' ||chs[i] ==']')
            {
                if(st.empty() || st.pop() != pair(chs[i]))
                    return false;
            }
            else if(chs[i] == '('||chs[i] == '{' ||chs[i] =='[')
                st.push(chs[i]);
        }
        if(st.empty())
            return true;
        return false;
    }
    char pair(char c){
        switch (c){
            case ')':
                return '(';
            case '}':
                return '{';
            case ']':
                return '[';
            default:
                return ' ';
        }
    }
    //solution 2: using recursion
    public boolean isValid2(String s) {
        //note: typical stack problem
        //also, could use recursion for this problem
        if(s.isEmpty())
            return true;
        char [] chs = s.toCharArray();
        int len = chs.length;
        return isValid_help(chs, 0, len-1);
    }
    public boolean isValid_help(char [] chs, int s, int e){
      if(s > e) //empty
         return true;
      if(s == e)
         return false;
         
      if(e == s+1){
         if (chs[s] == pair(chs[e]))
            return true;
         else
            return false;
       }
      
      int start = s;
      if(chs[s] != '('&&chs[s] != '{' && chs[s] !='[')
         return false;
      char endPair = pair2(chs[s]);;
      //recursion
      int count=0;
      for(int i=s+1; i<=e; i++){
         if(chs[i] == chs[s])
         {
            count++;
         }
         else if(chs[i] == endPair){
            if(count == 0){
               if(!isValid_help(chs, s+1, i-1))
                  return false;
               if(!isValid_help(chs,i+1, e))
                  return false;
            }
            else
               count--;
         }
         
      }
      if(count > 0)
         return false;
      return true;
    }
    char pair2(char c){
        switch (c){
            case '(':
                return ')';
            case '{':
                return '}';
            case '[':
                return ']';
            default:
                return ' ';
        }
    }


}