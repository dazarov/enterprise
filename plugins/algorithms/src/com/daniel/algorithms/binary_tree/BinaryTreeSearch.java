package com.daniel.algorithms.binary_tree;

import java.util.ArrayDeque;

@SuppressWarnings("rawtypes")
public class BinaryTreeSearch {
	
	
	public void visit(BinaryTreeNode node){
		// do the check
		System.out.print(node.data);
	}
	
	// Depth-first search
	// ------------------
	
	public void preorder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		visit(node);
		preorder(node.left);
		preorder(node.right);
	}
	
	public void iterativePreorder(BinaryTreeNode node){
		ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();
		if(node == null){
			return;
		}
		stack.push(node);
		while(!stack.isEmpty()){
			node = stack.pop();
			visit(node);
			if(node.right != null){
				stack.push(node.right);
			}
			if(node.left != null){
				stack.push(node.left);
			}
		}
	}
	
	public void inorder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		inorder(node.left);
		visit(node);
		inorder(node.right);
	}
	
	public void iterativeInorder(BinaryTreeNode node){
		ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();
		while(!stack.isEmpty() || node != null){
			if(node != null){
				stack.push(node);
				node = node.left;
			}else{
				node = stack.pop();
				visit(node);
				node = node.right;
			}
		}
	}
	
	public void postorder(BinaryTreeNode node){
		if(node == null){
			return;
		}
		inorder(node.left);
		inorder(node.right);
		visit(node);
	}
	
	public void iterativePostorder(BinaryTreeNode node){
		ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();
		BinaryTreeNode lastVisitedNode = null;
		while(!stack.isEmpty() || node != null){
			if(node != null){
				stack.push(node);
				node = node.left;
			}else{
				BinaryTreeNode peekNode = stack.peek();
				if(peekNode.right != null && lastVisitedNode != peekNode.right){
					node = peekNode.right;
				}else{
					visit(peekNode);
					lastVisitedNode = stack.pop();
				}
			}
		}
	}
	
	// Breadth-first search
	public void levelorder(BinaryTreeNode root){
		ArrayDeque<BinaryTreeNode> queue = new ArrayDeque<>();
		queue.add(root);
		while(!queue.isEmpty()){
			BinaryTreeNode node = queue.remove();
			visit(node);
			if(node.left != null){
				queue.add(node.left);
			}
			if(node.right != null){
				queue.add(node.right);
			}
		}
	}
}
