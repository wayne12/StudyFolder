import java.util.Stack;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

public class SurroundRegions{

//this is the second	version of algorithm	for this	problem
//using Breadth first search
//seach 'O'	in	boundary, then	applying	BFS
public void solve2(char[][]   board) {
     if(board == null || board.length < 3||board[0].length < 3)
         return;
     boolean [][] visited = new boolean [board.length][board[0].length];
     Queue<position> path = new LinkedList<position>();
     //search left and right boundary,label 'O's that connect to boundary
     for(int i=0; i < board.length; i++){
         if(board[i][0] == 'O' && !visited[i][0]){
               visited[i][0] =   true;
               path.add(new position(i,0));
               breadthFirstTravesal(board, visited, path);
         }
         if(board[i][board[0].length-1] == 'O' && !visited[i][board[0].length-1]){
               visited[i][board[0].length-1] =  true;
               path.add(new position(i,board[0].length-1));
               breadthFirstTravesal(board, visited, path);
         }   
     }
     //search up and bottom boundary, label 'O's that connect to boundary
     for(int i=1; i < board[0].length-1; i++){
         if(board[0][i] == 'O' && !visited[0][i]){
               visited[0][i] =   true;
               path.add(new position(0,i));
               breadthFirstTravesal(board, visited, path);
         }
         if(board[board.length-1][i] == 'O' && !visited[board.length-1][i]){
               visited[board.length-1][i] =  true;
               path.add(new position(board.length-1,i));
               breadthFirstTravesal(board, visited, path);
         }   
     }
     //set unlabeled 'O' to 'X'
     for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == 'O' && !visited[i][j])
                {
                     board[i][j] = 'X';
                }
            }
     }
}
//using Queue implement breadth first search
public void breadthFirstTravesal(char[][] board,boolean [][]visited, Queue<position> path){     
      while(!path.isEmpty()){
         position pos = path.remove();
         if(pos.x + 1 < board.length && board[pos.x+1][pos.y] == 'O'&& !visited[pos.x+1][pos.y]){
             path.add(new  position(pos.x+1, pos.y));
             visited[pos.x+1][pos.y] = true;
         }
         if(pos.x -1 >= 0 && board[pos.x-1][pos.y] == 'O'&& !visited[pos.x-1][pos.y]){
             path.add(new  position(pos.x-1, pos.y));
             visited[pos.x-1][pos.y] = true;
         }
         if(pos.y+1 < board[0].length && board[pos.x][pos.y+1] == 'O'&& !visited[pos.x][pos.y+1]){
             path.add(new  position(pos.x, pos.y+1));
             visited[pos.x][pos.y+1] = true;
         }
         if(pos.y-1 >= 0 && board[pos.x][pos.y-1] == 'O'&& !visited[pos.x][pos.y-1]){
             path.add(new  position(pos.x, pos.y-1));
             visited[pos.x][pos.y-1] = true;
         }
     }
     return;

}
//	this is the	first	version of algorithm
	 class position{
		  int	x;
		  int	y;
		  position(int	a,	int b)
		  {
				x = a;
				y = b;
		  }
	 }
	 public void solve(char[][] board) {
		  if(board == null || board.length < 3||board[0].length < 3)
				return;
		  boolean [][]	visited = new boolean [board.length][board[0].length];
		  Stack<position>	path = new Stack<position>();
		  List<position> saves = new LinkedList<position>();
		  for(int i=0;	i<board.length; i++){
				for(int j=0; j<board[0].length; j++){
					 if(board[i][j] == 'O' && !visited[i][j])
					 {
						  path.push(new position(i,j));
						  visited[i][j] =	true;
						  if(depthFirstTravesal(board, visited, path, saves)){
								for(position pos:	saves){
									 board[pos.x][pos.y]	= 'X';
								}
						  }
						  saves.clear();
					 }
				}
		  }

	 }
	 public boolean depthFirstTravesal(char[][] board,boolean [][]visited, Stack<position>path, List<position> saves){
		  boolean ret = true;
		  while(!path.isEmpty()){
				position	pos =	path.pop();
				saves.add(pos);
				if(pos.x	==	0 || pos.x == board.length-1 || pos.y == 0 || pos.y == board[0].length-1)
					 ret = false;
				if(pos.x	+ 1 <	board.length && board[pos.x+1][pos.y] == 'O'&& !visited[pos.x+1][pos.y]){
					 path.push(new	position(pos.x+1,	pos.y));
					 visited[pos.x+1][pos.y] =	true;
				}
				if(pos.x	-1	>=	0 && board[pos.x-1][pos.y]	==	'O'&&	!visited[pos.x-1][pos.y]){
					 path.push(new	position(pos.x-1,	pos.y));
					 visited[pos.x-1][pos.y] =	true;
				}
				if(pos.y+1 < board[0].length && board[pos.x][pos.y+1]	==	'O'&&	!visited[pos.x][pos.y+1]){
					 path.push(new	position(pos.x, pos.y+1));
					 visited[pos.x][pos.y+1] =	true;
				}
				if(pos.y-1 >= 0 && board[pos.x][pos.y-1] == 'O'&& !visited[pos.x][pos.y-1]){
					 path.push(new	position(pos.x, pos.y-1));
					 visited[pos.x][pos.y-1] =	true;
				}
		  }
		  return	ret;
	 }
}