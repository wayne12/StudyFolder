import java.util.Arrays;

public class validBST{
   class TreeNode{
      int val;
      TreeNode left;
      TreeNode right;
   }
   public static void main(String [] args){
      validBST obj = new validBST();
      TreeNode root = obj.buildTree(new int []{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
      boolean ret = obj.isValidBST2((validBST.TreeNode)root);
      System.out.println(ret);
   }
   //method: using max value and min value
    public boolean isValidBST(TreeNode root) {
        // set max and min to null to deal with corner case
        // it actually means no lower limit and upper limit values 
        // for the minimum value and maximum value of the tree
        return isValidBST_h(root, null, null); 
    }
    public boolean isValidBST_h(TreeNode root, Integer max, Integer min){
        if(root == null)
            return true;
        if((max != null && root.val >= max.intValue()) || (min != null && root.val <= min.intValue()))
            return false;
        boolean left = isValidBST_h(root.left, new Integer(root.val), min);
        boolean right = isValidBST_h(root.right, max, new Integer(root.val));
        return left&&right;
    }
    class pNode{
      TreeNode node;
    }
    // method 2:  using in-order search
    public boolean isValidBST2(TreeNode root) {
        // set max and min to null to deal with corner case
        // it actually means no lower limit and upper limit values 
        // for the minimum value and maximum value of the tree
        return isValidBST_h2(root, new pNode()); 
    }
    public boolean isValidBST_h2(TreeNode root, pNode pnode){
       if(root == null)
         return true;
       boolean left = isValidBST_h2(root.left, pnode);
       if(!left || (pnode.node != null && pnode.node.val >= root.val))
         return false;
       pnode.node = root;
       return isValidBST_h2(root.right, pnode);
    }
    
    //another method would be find max value of left tree and min value of right tree(leftmost of right tree)
    //...
    
    
    
    //build tree
    public TreeNode buildTree(int [] vals){
      if(vals == null || vals.length == 0)
         return null;
        
      int len = vals.length;
      int mid = len/2;
      
      TreeNode root = new TreeNode();
      root.val = vals[mid];
      root.left = buildTree(Arrays.copyOfRange(vals, 0, mid));
      root.right = buildTree(Arrays.copyOfRange(vals, mid+1, len));
      
      return root;
      
   }
}