import java.util.TreeMap;

public class NearbyAlmostDuplicate {
   public static void main(String [] args){
      NearbyAlmostDuplicate obj = new NearbyAlmostDuplicate();
      boolean ret = obj.containsNearbyAlmostDuplicate2(new int []{-1,2147483647}, 1 , 1);
      System.out.println(ret);
   }
/// using loop compare all elements
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums.length == 0 || k == 0)
            return false;
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<=i+k&&j<nums.length; j++){
                if(Math.abs(nums[j] - nums[i]) <= t)
                    return true;
            }
        }
        return false;
    }
    // using tree map  : doesn't work, unless it sorts by value. 
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if(nums.length <= 1 || k == 0 || t < 0)
            return false;
        TreeMap<Integer,Integer> tm = new TreeMap<Integer,Integer>();
        for(int i=0; i<nums.length && i<=k; i++){
            if(tm.containsKey(nums[i]))//contains same number, which indicating difference is 0, (less or equal than any difference)
                return true;
            tm.put(nums[i],i);
        }
        //compare neighbouring elements
        Integer pre = null;
        for(int el: tm.keySet()){
            if(pre != null
               && (long)el - pre.longValue() >= Integer.MIN_VALUE+1 
               && ((long)el - pre.longValue() <= Integer.MAX_VALUE)
               && Math.abs(el-pre)<=t)
                return true;
            pre = el;
        }
        int first = 0;
        for(int i=k+1; i<nums.length; i++){
            tm.remove(nums[first++]);
            if(tm.containsKey(nums[i]))
                return true;
            tm.put(nums[i],i);
            Integer next = tm.higherKey(nums[i]);
            Integer preKey = tm.lowerKey(nums[i]);
            if((next != null 
                  && (long)nums[i] - next.longValue() >= Integer.MIN_VALUE+1 
                  && ((long)nums[i] - next.longValue() <= Integer.MAX_VALUE) 
                  && (next - nums[i]) <= t)
              ||(preKey != null 
                  && (long)nums[i] - preKey.longValue() >= Integer.MIN_VALUE+1 
                  && ((long)nums[i] - preKey.longValue() <= Integer.MAX_VALUE)
                  && (preKey - nums[i]) <=t))
                return true;
        }
        return false;
    }}