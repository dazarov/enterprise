package com.daniel.algorithms.uf;

public abstract class UnionFind {
	protected int[] id; 
	protected int count; // number of components (connected to each other set of points)
	
	public UnionFind(int N){
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++){
			id[i] = i;
		}
	}
	
	public abstract void union(int p, int q);
	
	public abstract int find(int p);
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public int count(){
		return count;
	}
}
