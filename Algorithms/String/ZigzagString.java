import java.util.*;

public class ZigzagString {
     public static void main(String [] args){
         ZigzagString obj = new ZigzagString();
         System.out.println(obj.convert("abcdefgfdsa", 3));
     }
   public String convert(String s, int numRows) {
        if(s == null)
            return null;
        int len = s.length();
        if(len == 0 || numRows == 1)
            return s;
        StringBuilder [] retS = new StringBuilder[numRows];
        for(int i=0; i<numRows; i++)
            retS[i] = new StringBuilder();
        char [][] cArr = new char[numRows][len];
        int index = 0;
        //write to array column by column
        outerloop:
        for(int j = 0; j<len; j++)
            for(int i=0; i<numRows; i++){
                if(index >= len)
                    break outerloop;
                if(j%(numRows-1) == 0 ||((i+j)%(numRows-1) == 0)){
                    retS[i].append(s.charAt(index));
                    index++;
                }
            }
        //read from array row by row
        for(int i=1; i<numRows; i++){
            retS[0].append(retS[i]);
        }
        return retS[0].toString();
    }
}