import java.lang.Math;

/**
- One edit distance
	uestion: Given two strings, determine if they are different in one edit distance.
	("abc", "abc") -> false
	("abcd", "a") -> false
	("abc", "ac") -> true
	("abc", "ab") -> true
        ("abc", "adc") -> ture

// the normal way is using dynamic programming with recursion to get minimum edit distance of two strings 
**/

//import java.util;

public class EditDistance{

public static void main(String args[]){
    
     boolean r = OneDis("abcd", "ebcde");
     System.out.println(r);
     System.out.println(EditDis("abcd","abec"));
  }

//this is another solution just for edit distance of 1
public static boolean OneDis(String str1, String str2){
    if(str1 == null || str1.isEmpty()) return false;
    if(str2 == null || str2.isEmpty()) return false;
    int len1 = str1.length();
    int len2 = str2.length();
    int dis = 0;
    if(len1 == len2){
      for(int i=0; i<len1; i++){
         if(str1.charAt(i) != str2.charAt(i))
            dis++;
      }
      if(dis == 1)
        return true;
      return false;
    }
    else if( Math.abs(len1 - len2) == 1){  // one more char in one string
      String lstring = str1;
      String sstring = str2;
      int l = len1, s = len2;
      if ( len2 > len1){
         lstring = str2;
         sstring = str1;
         l = len2;
         s = len1;
      }
      int j = 0;
      for(int i=0; i<l; i++){
         if(lstring.charAt(i) == sstring.charAt(j))
            j++;
         if(j == s)
            return true;
      }
      return false;

    }
    else
       return false;


  }
//this is dynamic programming with recursion solution
public static int EditDis(String str1, String str2){

    int len1 = str1.length();
    int len2 = str2.length();
    if(len1 == 0)
      return len2;

    if(len2 == 0)
	   return len1;
     
    if(str1.charAt(len1-1) == str2.charAt(len2-1))
	   return EditDis(str1.substring(0,len1-1), str2.substring(0,len2-1));
    
    return 1 + Math.min (Math.min(EditDis(str1.substring(0,len1-1), str2.substring(0,len2-1)),
                    EditDis(str1.substring(0,len1-1), str2.substring(0,len2))),
                    EditDis(str1.substring(0,len1), str2.substring(0,len2-1))
                   );


  }


}
