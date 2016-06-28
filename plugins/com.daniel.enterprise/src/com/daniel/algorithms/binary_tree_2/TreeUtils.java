package com.daniel.algorithms.binary_tree_2;


public class TreeUtils {
	
	// time complexity  - O(h), h - height of the tree
	// space complexity - O(1)
	public static BinaryTreeNode findLowestCommonAncestor(BinaryTreeNode node1, BinaryTreeNode node2){
		int depth1 = getDepth(node1);
		int depth2 = getDepth(node2);
		if(depth2 > depth1){
			BinaryTreeNode t = node1;
			node1 = node2;
			node2 = t;
		}
		
		int depthDiff = Math.abs(depth2-depth1);
		while(depthDiff-- > 0){
			node1 = node1.parent;
		}
		
		while(node1 != node2){
			node1 = node1.parent;
			node2 = node2.parent;
		}
		
		return node1;
	}
	
	public static int getDepth(BinaryTreeNode node){
		int depth = 0;
		while(node.parent != null){
			depth++;
			node = node.parent;
		}
		return depth;
	}
	
	public static void main(String[] args){
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(0, null);
		
		BinaryTreeNode<Integer> n1 = new BinaryTreeNode<Integer>(1, root);
		BinaryTreeNode<Integer> n2 = new BinaryTreeNode<Integer>(2, root);
		root.left = n1;
		root.right = n2;
		
		BinaryTreeNode<Integer> n3 = new BinaryTreeNode<Integer>(3, n1);
		BinaryTreeNode<Integer> n4 = new BinaryTreeNode<Integer>(4, n1);
		
		n1.left = n3;
		n1.right = n4;
		
		BinaryTreeNode<Integer> n5 = new BinaryTreeNode<Integer>(5, n4);
		
		n4.left = n5;
		
		BinaryTreeNode<Integer> n6 = new BinaryTreeNode<Integer>(6, n3);
		
		n3.left = n6;
		
		BinaryTreeNode<Integer> commonAncestor = findLowestCommonAncestor(n6, n5);
		System.out.println("Lowest Common Ancestor - "+commonAncestor.data);
		
		commonAncestor = findLowestCommonAncestor(n6, n2);
		System.out.println("Lowest Common Ancestor - "+commonAncestor.data);
		
	}
}
