public class sumProblems{

   public int threeSumClosest(int[] nums, int target) {
        if(nums == null)
            return -1;
        int diff = Integer.MAX_VALUE;
        int len = nums.length;
        int result = Integer.MAX_VALUE;
        for(int i=0; i<len-2; i++){
            for(int j=i+1; j<len-1; j++){
                for(int k = j+1; k<len; k++){
                    if(Math.abs(nums[i]+nums[j]+nums[k] - target) < diff){
                        result = nums[i]+nums[j]+nums[k];
                        diff = Math.abs(result - target);
                    }
                }
            }
        }
        return result;
    }
    public List<List<Integer>> threeSum(int[] nums) {
         Hashtable<Integer,Integer> ht= new Hashtable<Integer,Integer>();
         List<List<Integer>> ret = new LinkedList<List<Integer>>();
         for(int i=0; i<nums.length-2; i++){
            for(int j=i+1; j<nums.length-1; j++){
               if(ht.containsKey(nums[j]){
                  List<Integer> lst = new LinkedList<Integer>();
                  lst.add(nums[i]);
                  lst.add(0-nums[i]-nums[j]);
                  lst.add(nums[j]);
                  ret.add(lst);
               }
               else{
                  ht.put(0-nums[i]-nums[j]);
               }
            }
            ht.clear();
         }
    }
    public int[] twoSum(int[] nums, int target){
      
        Hashtable<Integer,int []> countTable= new Hashtable<Integer,int []>();
        int [] ret = new int [2];
        int len = nums.length;
        for(int i = 0; i<len; i++){
            if(countTable.contains(nums[i])){
                countTable.get(nums[i])[1] = i;
            }
            else{
                int [] index = new int [2];
                index[0] = i;
                countTable.put(nums[i],index);
            }
        }
        for(int key : countTable.keySet()){
            if(countTable.containsKey(target - key)){
                if(target - key == key ){
                    int [] lst = countTable.get(key);
                    if(lst[1] != 0){                       
                        return lst;                    
                    }
                }
                else{
                    ret[0] = countTable.get(key)[0];
                    ret[1] = countTable.get(target - key)[0];
                    return ret;
                }
            }
        }


        return ret;
    }
 /*
    Given an array S of n integers, are there elements a, b, c, and d in S 
    such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 */
   public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length < 4)
            return new LinkedList<List<Integer>>();
        Arrays.sort(nums);
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        
        int f,e;
        for(int i=0; i<nums.length-3; i++){
            if(i > 0 && nums[i] == nums[i-1])
               continue;
            for(int j=i+1; j<nums.length-2; j++){
                if(j > i+1 && nums[j] == nums[j-1])
                    continue;
                f = j+1;
                e = nums.length-1;
                while(f<e){
                    if((e< nums.length-1 && nums[e] == nums[e+1])
                        ||nums[e]+nums[f] > target-nums[i]-nums[j])
                        e--;
                    else if((f> j+1 && nums[f] == nums[f-1])
                        ||nums[e]+nums[f] < target-nums[i]-nums[j])
                        f++;                    
                    else{
                        List<Integer> lst = new LinkedList<Integer>();
                        lst.add(nums[i]);
                        lst.add(nums[j]);
                        lst.add(nums[f]);
                        lst.add(nums[e]);
                        ret.add(lst);
                    }
                }
            }
        }
        return ret;
    }
    //this solution exceed time limit
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        if(nums.length < 4)
            return new LinkedList<List<Integer>>();
        Arrays.sort(nums);
        HashSet<Integer> ht= new HashSet<Integer>();
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        
        for(int i=0; i<nums.length-3; i++){
            if(i > 0 && nums[i] == nums[i-1])
                  continue;
            for(int j=i+1; j<nums.length-2; j++){
                if(j > i+1 && nums[j] == nums[j-1])
                    continue;
                for(int k=j+1; k<nums.length; k++){
                    if(k > j+1 && nums[k] == nums[k-1])
                        continue;
                    if(ht.contains(nums[k])){
                        List<Integer> lst = new LinkedList<Integer>();
                        lst.add(nums[i]);
                        lst.add(nums[j]);
                        lst.add(target-nums[i]-nums[j]-nums[k]);
                        lst.add(nums[k]);
                        //Collections.sort(lst);
                        ret.add(lst);
                    }
                    else{
                      ht.add(target-nums[i]-nums[j]-nums[k]);
                    }
                }
                ht.clear();
            }
        }
        return ret;
    }

}