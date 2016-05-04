import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashMap;
public class groupAnagram{
   public static void main(String [] args){
      boolean bb = isAnagram1("aa", "bb");
      groupAnagram obj = new groupAnagram();
      List<List<String>> lst = obj.groupAnagrams2(new String []{"eat", "tea", "tan", "ate", "nat", "bat"});
      System.out.println(lst);
   }
   public List<List<String>> groupAnagrams1(String[] strs){
      if(strs == null||strs.length == 0)
         return null;
      //Arrays.sort(strs);
      HashMap<String, List<String>> hm = new HashMap<String,List<String>>();
      for(int i=0; i<strs.length; i++){
         char [] temp = strs[i].toCharArray();
         Arrays.sort(temp);
         String orderStr = new String(temp);
         List<String> ls = hm.get(orderStr);
         if(ls == null){
            ls = new LinkedList<String>();
         }
         ls.add(strs[i]);
         hm.put(orderStr, ls);//updata when key exist, otherwise add (key,value) pair
      }
      for(String s: hm.keySet()){
          Collections.sort(hm.get(s));
      }
      return new ArrayList<List<String>>(hm.values());
   }
   //Method 2: works but has O(n^2) time complexity
   public List<List<String>> groupAnagrams2(String[] strs) {
        if(strs == null || strs.length == 0)
            return null;
        Arrays.sort(strs);
        int len = strs.length;
        List<List<String>> ret = new LinkedList<List<String>>();
        for(int i=0; i<len; i++){
            if(strs[i] == null)
                continue;
            List<String> row = new LinkedList<String>();
            row.add(strs[i]);
            for(int j=i+1; j<len; j++){
                if(strs[j] != null && isAnagram(strs[i],strs[j])){
                    row.add(strs[j]);
                    strs[j] = null;
                }
            }
            ret.add(row);
        }
        return ret;
    }
    boolean isAnagram(String s1, String s2){
        if(s1.length() != s2.length())
            return false;
        int len = s1.length();
        int i=0;
        int [] count = new int [26];
        while(i<len){
            count[s1.charAt(i)-'a']++;
            count[s2.charAt(i)-'a']--;
            i++;
        }
        for(int j=0; j<26; j++){
            if(count[j] != 0)
                return false;
        }
        return true;
    }
   //this doesn't work
   // because val will be 0 when s1 is anagram with s2, but val is 0 doesn't mean s1 is anagram with s2.
   // e.g.    val is 0  when s1 = "aid", s2 = "dog", also, s1 ="aa" s2="bb"
   static boolean isAnagram1(String s1, String s2){
        if(s1.length() != s2.length())
            return false;
        int len = s1.length();
        int i=0, val =0;
        while(i<len){
            val ^= (int)Math.pow(s1.charAt(i),2);
            val ^= (int)Math.pow(s2.charAt(i),2);
            i++;
        }
        if(val == 0)
            return true;
        else
            return false;
    }
    

}