/**
*  Author: Hongwei Lu
*  Description: 
*    Given a full binary tree, link all nodes  
*    to their next right neighbors at the same level
*
*
*/


public class LinkNextRight{

	public class Node{
		int data;
		Node lchild;
		Node rchild;
		Node nextRight;
	}

	public static void main(String [] args){


	}

	//this function only works for full binary tree
	public void linkNextRight(Node root){
		if(root == null)
			return;
 		if(root.lchild != null && root.rchild != null){
			root.lchild.nextRight = root.rchild;
			Node mostRight = root.lchild.rchild;
			Node mostLeft = root.rchild.lchild;
         //need a loop to link nodes between this two sub trees
			while(mostRight != null && mostLeft != null){
				mostLeft = mostRight.nextRight;
				mostLeft = mostLeft.lchild;
			}
         // do recursion to link nodes to right inside each sub tree
         linkNextRight(root.lchild);
			linkNextRight(root.rchild);
		}
		return;
	}




}


