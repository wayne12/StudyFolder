/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/


public class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null || word == null || board.length == 0|| board[0].length == 0)
            return false;
        
        char first = word.charAt(0);
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if(board[i][j] == first){
                    if(existRe(board, word.substring(1), i, j))
                        return true;
                }
            }
        }
        return false;
            
    }
    boolean existRe(char [][] board, String word, int posX, int posY){
        if(word.length() == 0)
            return true;
        board[posX][posY] ^= 256;
        if(posX - 1 >= 0 && board[posX-1][posY] == word.charAt(0)){
            if(existRe(board, word.substring(1), posX-1, posY))
                return true;
        }
        if(posX + 1 < board.length && board[posX+1][posY] == word.charAt(0)){
            if(existRe(board,word.substring(1),posX+1,posY))
                return true;
        }
        if(posY-1 >= 0 && board[posX][posY-1]== word.charAt(0)){
            if(existRe(board,word.substring(1),posX,posY-1))
                return true;
        }
        if(posY+1 <= board[0].length && board[posX][posY+1]== word.charAt(0)){
            if(existRe(board, word.substring(1),posX,posY+1)){
                return true;
            }
        }
        board[posX][posY] ^= 256;
        return false;
    }
}
