import java.util.List;

public class MinimumPath {
   //method 1: top down
    public int minimumTotal(List<List<Integer>> triangle) {
        //minimum path is able to use dynamic programming
        //if already know minimum path to all nodes in n-1 level
        //how to find minimum path to nodes in n level
        if(triangle == null || triangle.size() == 0)
            return -1;//indicate an error
        int numRow = triangle.size();
        int [] sums = new int [numRow];
        int temp;
        for(int i=0; i<numRow; i++){
            int len = triangle.get(i).size();
            for(int j=len-1; j>=0; j--){
                temp =  triangle.get(i).get(j);
                if(j==0)
                    sums[j] = temp +sums[j];
                else if(j == len-1)
                    sums[j] = temp + sums[j-1];
                else
                    sums[j] = Math.min(sums[j-1],sums[j]) + temp;
            }
        }
        int ret = Integer.MAX_VALUE;
        for(int i=0; i<triangle.get(numRow-1).size(); i++){
            if(sums[i] < ret)
                ret = sums[i];
        }
        return ret;
        
    }
    ///method 2: using bottom up
    public int minimumTotal2(List<List<Integer>> triangle) {
        //minimum path is able to use dynamic programming
        //suppose start from bottom and try to find a path
        // to the top. this will be a same problem but different direction.
        if(triangle == null || triangle.size() == 0)
            return -1;//indicate an error
        int numRow = triangle.size();
        int [] sums = new int [numRow+1];
        for(int i=numRow-1; i>=0; i--){
            int len = triangle.get(i).size();
            for(int j=0; j < len; j++){
                sums[j] = Math.min(sums[j+1],sums[j]) + triangle.get(i).get(j);
            }
        }
        return sums[0];        
    }
}