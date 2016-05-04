/*
 *  Author: Hongwei Lu
 *  Description:
 *     Given a binary tree and two node, find the 
 *     lowest common ancestor of these two nodes
*/



public class LCA{
	
	public class Node{
		int data;
		Node lchild;
		Node rchild;
		Node nextRight;
	}

	//public static void main(String [] args)

	public Node Lca(Node root, Node x, Node y, int indicator){
		if(root == null)
			return null;
		Node temp;
		if(temp = PostOrderSearch(root.left) != null)
			return temp;
		if(temp = PostOrderSearch(root.right) != null)
			return temp;

		if(indicator < 4){
			if(root == x){
					indicator = (indicator << 1) + 1;
			}
			else if(root == y){
					indicator = (indicator << 1) + 2;
			}
		}
		if(indicator == 5)
			if(SearchNode(root, x))
				return root;
		else if(indicator == 4)
			if(SearchNode(root, y))
				return root;
		}
		return null;

	}
	boolean SearchNode(Node root, Node n){
		if(root == null)
			return false;
		if(root == n)
			return true;
		if(SearchNode(root.lchild, n))
			return true;
		return SearchNode(root.rchild, n);
	}

}
