package com.daniel.algorithms.binary_tree_2;

public class BinaryTreeNode<T> {
	T data;
	BinaryTreeNode<T> right, left;
	BinaryTreeNode<T> parent;
	
	public BinaryTreeNode(T data, BinaryTreeNode<T> parent){
		this.data = data;
		this.parent = parent;
	}
}
