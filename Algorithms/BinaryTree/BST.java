/*
* Author: Hongwei Lu
* Description: Binary Search Tree Operations and Algorithms
* 3/4/16
*/
import java.lang.*;
import java.util.*;
import java.io.*;

public class BST{

   private class Node{
      int data;
      Node left;
      Node right;
   }
   private class NodeM{
      Node node;
   }



   public static void main(String [] args){
      BST bst = new BST();
      Node root = bst.buildTree(new int []{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
      validBST obj = new validBST();
      obj.isValidBST2((validBST.TreeNode)root);
      int val = 14;
      
      try{
         //using in-order traversal to find previous value
         int preVal = bst.preNode(root, val);
         System.out.println(preVal);
         
         //using binary search to find previous value
         int preVal2 = bst.preNode2(root, val);
         System.out.println(preVal2);
      }
      catch(Exception e){
         System.out.println("No result!");
      }
   }
   
   // preNode
   // pre-condition:
   //          root of a binary search tree
   //          a value of a node
   // post-condition:
   //          find the previous value of the given value in sorting order
   public int preNode(Node root, int val)throws Exception{
      NodeM pre = new NodeM();
      int ret = preNodeRecursion(root, pre, val);
      
      if(ret == -1)
         throw new Exception();
         
      return ret;

   }
   public int preNodeRecursion(Node root, NodeM preN, int val)throws Exception{
      if(root == null)
         return -1;
         

      int ret = preNodeRecursion(root.left, preN, val);
      if(ret != -1)
         return ret;        
         
      if(root.data == val){
         if(preN.node == null)
            throw new Exception();
         return preN.node.data;
      }
      else{
         preN.node = root;
      }
      
      ret = preNodeRecursion(root.right, preN, val);
      if(ret != -1)
         return ret;
         
      return -1;
   }
   
   
   // preNode
   // pre-condition:
   //          root of a binary search tree
   //          a value of a node
   // post-condition:
   //          the previous value of the given value in sorting order
   public int preNode2(Node root, int val) throws Exception{
      Node pre = null;
      Node cur = root;
      while (cur != null){
         if(val > cur.data){
            pre = cur;
            cur = cur.right;
         }
         else if(val < cur.data){
            cur = cur.left;
         }
         else{
            if(cur.left != null)
               return rightMost(cur.left).data;
            if(pre == null)
               throw new Exception();
            return pre.data;
         }
      }
      throw new Exception();
   }
   
   // rightMost
   // pre-condition:
   //          root of a binary tree
   // post-condition:
   //          return the right most node in a binary tree
   Node rightMost(Node root){
      if(root.right == null)
         return root;
      return rightMost(root.right);
   }
   
   //buildTree: to build a binary search tree from an array
   //pre-condition:
   //          a sorted array in ascending order
   //post-condition:
   //          the root of a binary search tree
   public Node buildTree(int [] vals){
      if(vals == null || vals.length == 0)
         return null;
        
      int len = vals.length;
      int mid = len/2;
      
      Node root = new Node();
      root.data = vals[mid];
      root.left = buildTree(Arrays.copyOfRange(vals, 0, mid));
      root.right = buildTree(Arrays.copyOfRange(vals, mid+1, len));
      
      return root;
      
   }
}