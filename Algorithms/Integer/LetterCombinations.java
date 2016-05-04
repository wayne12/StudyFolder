import java.util.*;
public class LetterCombinations {
    private char [][] chMap;
    
    public static void main(String [] args){
      LetterCombinations ss = new LetterCombinations();
      List<String> ret = ss.letterCombinations("23");
      System.out.println(ret);
   }
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0 || digits.contains("1")||digits.contains("0"))
            return new LinkedList<String>();
        
        char [] chs = digits.toCharArray();
        
        chMap = new char[10][3];
        buildMap(chMap);
        List<StringBuilder> ret = help_letCom(chs, chs.length-1);
        List<String> lst = new LinkedList<String>();
        for(StringBuilder strb: ret){
            lst.add(strb.toString());
        }
        return lst;

    }
    
    public List<StringBuilder> help_letCom(char [] digits, int index) {
        int num = Character.digit(digits[index],10);
        if(index == 0){
            List<StringBuilder> lst = new ArrayList<StringBuilder>();
            for(int i=0; i<3; i++){
                lst.add(new StringBuilder(String.valueOf(chMap[num][i])));
            }
            return lst;
        }
        //recursion
        List<StringBuilder> lst = help_letCom(digits, index-1);
        ListIterator<StringBuilder> lsIter = lst.listIterator();
        while(lsIter.hasNext()){
            StringBuilder str = lsIter.next();// lst){
            StringBuilder str1 = new StringBuilder(str.toString());
            StringBuilder str2 = new StringBuilder(str.toString());
            str.append(chMap[num][0]);
            str1.append(chMap[num][1]);
            str2.append(chMap[num][2]);
            lsIter.add(str1);
            lsIter.add(str2);
        }
       return lst;
    }
    public void buildMap(char [][] chs){
        char ch = 'a';
        for(int i=2; i<=9; i++){
            for(int j=0; j<3; j++){
                chs[i][j] = ch;
                ch++;
            }
        }
    }
}