public class wildcardMatch{

   public static void main(String [] args){
      wildcardMatch obj = new wildcardMatch();
      //String s1 = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba";
      //String s2 = "a*******b";
      String s1 = "aa";//"ababababbabbabababbbbabbaabaaaaaaababbbbbaabbaaabbbaa";
      String s2 = "*";//"*****ba*";
      System.out.println(obj.isMatch2(s1,s2));
   }
    public boolean isMatch(String s, String p){
       if(s == null || p == null)
             return false;//error
       return isMatch_h(s.toCharArray(),0,s.length()-1,p.toCharArray(),0,p.length()-1);
    }
    public boolean isMatch_h(char[] sArr, int fs, int es, char[] pArr, int fp, int ep) {
        int i = fs, j = fp;
        while (i <= es && j <= ep && sArr[i] != '*' && pArr[j] != '*'){
            if(sArr[i] == '?' || pArr[j] == '?' || sArr[i] == pArr[j]){
               i++;
               j++;
            }
            else
               return false;
        }
        if(i > es && j > ep){
            return true;
        }
        else if(i > es){
            while(j <= ep){
                if(pArr[j] != '*' && pArr[j] != '?')
                    return false;
                j++;
            }
            return true;
        }
        else if(j > ep){
            while(i<=es){
                if(sArr[i] != '*' && sArr[i] != '?')
                    return false;
                i++;
            }
            return true;
        }
        else{
            if(sArr[i] == '*'){
               while (i<es && sArr[i+1] == '*')
                   i++;
               if(i == es)
                   return true;
               for(int k=ep; k>=j; k--){
                  if(isMatch_h(sArr,i+1, es, pArr, k, ep))
                     return true;
               }
          }
            else{
               while (j<ep && sArr[j+1] == '*')
                   j++;
               if(j == ep)
                   return true;
               for(int k=es; k>=i; k--){
                  if(isMatch_h(sArr, k, es, pArr, j+1, ep))
                     return true;
               }
          }
          return false;
        }
    }
    //method 2: dynamic programming
   public boolean isMatch2(String s, String p){
      if(s == null || p == null)
            return false;//error
      int slen = s.length(), plen = p.length();
      boolean [] match = new boolean [slen+1];
      match[0] = true;
      for(int i = 0; i < plen; i++){
         if(p.charAt(i) == '*'){
            for(int j = 1; j < slen+1; j++){
               match[j] = match[j] || match[j-1];
            }
         }
         else{
            for(int j = slen; j >= 1; j--){
               match[j] = (match[j-1] && (p.charAt(i) == s.charAt(j-1) || p.charAt(i) == '?'));
            }
            match[0] = false;
         }
      }
      return match[slen];
   }
   
}