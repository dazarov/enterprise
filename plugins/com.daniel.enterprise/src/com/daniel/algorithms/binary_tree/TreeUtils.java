package com.daniel.algorithms.binary_tree;

public class TreeUtils {
	static class Status{
		int numTargetNodes;
		BinaryTreeNode ancestor;
		
		public Status(int numTargetNodes, BinaryTreeNode ancestor){
			this.numTargetNodes = numTargetNodes;
			this.ancestor = ancestor;
		}
	}
	
	// time complexity  - O(n)
	// space complexity - O(h), h - height of the tree
	static Status findLowestCommonAncestor(BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2){
		if(root == null){
			return new Status(0, null);
		}
		Status leftResult = findLowestCommonAncestor(root.left, node1, node2);
		if(leftResult.numTargetNodes == 2){
			return leftResult;
		}
		
		Status rightResult = findLowestCommonAncestor(root.right, node1, node2);
		if(rightResult.numTargetNodes == 2){
			return rightResult;
		}
		
		int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes;
		if(root == node1){
			numTargetNodes++;
		}
		if(root == node2){
			numTargetNodes++;
		}
		
		return new Status(numTargetNodes, numTargetNodes == 2 ? root : null);
	}
	
	// time complexity  - O(n)
	// space complexity - O(h), h - height of the tree
	static int getSumRootToLeaf(BinaryTreeNode<Integer> root){
		return getSumRootToLeaf(root, 0);
	}
	
	static int getSumRootToLeaf(BinaryTreeNode<Integer> root, int partialPathSum){
		if(root == null){
			return 0;
		}
		
		partialPathSum = partialPathSum/* *2 */ + root.data;
		if(root.left == null && root.right == null){ // Leaf
			return partialPathSum;
		}
		
		return getSumRootToLeaf(root.left, partialPathSum) + getSumRootToLeaf(root.right, partialPathSum);
	}
	
	// time complexity  - O(n)
	// space complexity - O(h), h - height of the tree
	static BinaryTreeNode<Integer> findNodeWithPathSum(BinaryTreeNode<Integer> root, int targetSum){
		return findNodeWithPathSum(root, 0, targetSum);
	}
	
	static BinaryTreeNode<Integer> findNodeWithPathSum(BinaryTreeNode<Integer> root, int partialPathSum, int targetSum){
		if(root == null){
			return null;
		}
		
		partialPathSum += root.data;
		if(root.left == null && root.right == null){ // Leaf
			if(partialPathSum == targetSum){
				return root;
			}else{
				return null;
			}
		}
		
		BinaryTreeNode<Integer> node = findNodeWithPathSum(root.left, partialPathSum, targetSum);
		if(node != null){
			return node;
		}
		node = findNodeWithPathSum(root.right, partialPathSum, targetSum);
		return node;
	}
	
	
	
	public static void main(String[] args){
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(0);
		
		BinaryTreeNode<Integer> n1 = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> n2 = new BinaryTreeNode<Integer>(2);
		root.left = n1;
		root.right = n2;
		
		BinaryTreeNode<Integer> n3 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> n4 = new BinaryTreeNode<Integer>(4);
		
		n1.left = n3;
		n1.right = n4;
		
		BinaryTreeNode<Integer> n5 = new BinaryTreeNode<Integer>(5);
		
		n4.left = n5;
		
		BinaryTreeNode<Integer> n6 = new BinaryTreeNode<Integer>(6);
		
		n3.left = n6;
		
		Status commonAncestor = findLowestCommonAncestor(root, n6, n5);
		System.out.println("Lowest Common Ancestor - "+commonAncestor.ancestor.data);
		
		commonAncestor = findLowestCommonAncestor(root, n6, n2);
		System.out.println("Lowest Common Ancestor - "+commonAncestor.ancestor.data);
		
		
		System.out.println("Root to Leaf Sum - "+getSumRootToLeaf(root));
	}
}
