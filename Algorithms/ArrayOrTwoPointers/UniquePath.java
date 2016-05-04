/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the

 bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

*/

public class UniquePath {

    public int uniquePaths(int m, int n) {
        if(m < 1 || n < 1)
            return -1;
        int [][] grid = new int [m][n];
        grid[0][0] = 1;
        for(int j = 1; j < n; j++)
            grid[0][j] = 1;
        for(int i=1; i<m; i++){
            grid[i][0] = 1;
            for(int j=1; j<n; j++){
                grid[i][j] = grid[i-1][j] + grid[i][j-1];
            }
        }
        return grid[m-1][n-1];
    }
    
   //follow up problem
/*
   Follow up for "Unique Paths":

   Now consider if some obstacles are added to the grids. How many unique paths would there be?

   An obstacle and empty space is marked as 1 and 0 respectively in the grid.

   For example,
   There is one obstacle in the middle of a 3x3 grid as illustrated below.

      [
        [0,0,0],
        [0,1,0],
        [0,0,0]
      ]
   The total number of unique paths is 2.

*/

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid[0][0] == 1)
            return 0;
        // if m == 0, n == 0, return 0; should also be included
        
        obstacleGrid[0][0] = 1;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        
        int value = 1;
        
        for(int j=1; j<n; j++){
            if(obstacleGrid[0][j] == 1){
                obstacleGrid[0][j] = 0;
                value = 0;
            }
            else
                obstacleGrid[0][j] = value;
        }
        value = 1;
        //if it is 1 then set to 0, if it is 0, set to 1
        for(int i = 1; i < m; i++){
            if(obstacleGrid[i][0] == 1){
                obstacleGrid[i][0] = 0;
                value = 0;;
            }
            else
                obstacleGrid[i][0] = value;
            
        }
            
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else
                    obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
            }
        }
        return obstacleGrid[m-1][n-1];
    }
}