public class NumTrees {
      //method1: with recursion
    public int numTrees(int n) {
        if(n < 0)
            throw new IllegalArgumentException();
        if(n <= 2)
            return n;
        int count = 0;
        for(int i=1; i <= n; i++){
            //check for each number is on the root
            if(i == 1 || i == n)
                count += numTrees(i-1) + numTrees(n-i); // recursion for left subtree and right subtree
            else
                count += numTrees(i-1) * numTrees(n-i);
        }
        return count;
    }
    
    //method2: dynamic programming
    public int numTrees2(int n) {
        if(n < 0)
            throw new IllegalArgumentException();
        if(n <= 2)
            return n;
        int [] res = new int [n+1];
        for(int i = 0; i<3; i++){
            res[i] = i;
        }
        //using dynamic programming
        for(int k=3; k <= n; k++){
            //check for each number is on the root
            for(int i=1; i<=k; i++){
                if(i == 1 || i == k)
                    res[k] += res[i-1] + res[k-i]; // recursion for left subtree and right subtree
                else
                    res[k] += res[i-1] * res[k-i];
            }
        }
        return res[n];
    }
    //add-on:   in order to prevent return value, which is int, overflow,
    //          add some protection, such as:
    /*
                int maxInt = Integer.MAX_VALUE;
                for(int k=3; k <= n; k++){
                  //check for each number is on the root
                  for(int i=1; i<=k; i++){
                      if(i == 1 || i == k)
                          res[k] += res[i-1] + res[k-i]; // recursion for left subtree and right subtree
                      else
                          res[k] += res[i-1] * res[k-i];
                      if(Integer.longValue(res[k]) > maxInt)
                           return -1;
            }
        }

    */         
}