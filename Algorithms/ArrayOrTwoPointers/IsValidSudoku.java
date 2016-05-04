public class IsValidSudoku {
   public static void main(String [] args){
      IsValidSudoku obj = new IsValidSudoku();
      boolean ret = obj.isValidSudoku(new char [][]{".87654321".toCharArray(),"2........".toCharArray(),
      "3........".toCharArray(),"4........".toCharArray(),"5........".toCharArray(),"6........".toCharArray()
      ,"7........".toCharArray(),"8........".toCharArray(),"9........".toCharArray()});
      System.out.println(ret);
   }
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9)
            return false;
        // this array works as hashtable, each number has three lines acting as row, column, block indicators.
        // e.g. [3][0][1] indicating if number 3 is included in row 1; 
        //      [3][1][7] indicating if number 3 is included in column 7;
        int [][][]numArr = new int [10][3][9];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == '.')
                    continue;
                int n = Character.digit(board[i][j],10);
                if(numArr[n][0][i] == 1)//rows
                    return false;
                else
                    numArr[n][0][i] = 1;
                    
                if(numArr[n][1][j] == 1)//colum
                    return false;
                else
                    numArr[n][1][j] = 1;
                //calculate block index: i-i%3 + j/3 or 3*(i/3) + j/3    
                if(numArr[n][2][i-i%3 + j/3] == 1)//block
                    return false;
                else
                    numArr[n][2][i-i%3 + j/3] = 1;
            }
        }
        return true;
    }
}