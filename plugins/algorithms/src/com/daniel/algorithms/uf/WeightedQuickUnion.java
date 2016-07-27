package com.daniel.algorithms.uf;

public class WeightedQuickUnion extends QuickUnion {
	protected int[] sz;

	public WeightedQuickUnion(int N) {
		super(N);
		sz = new int[N];
		for(int i = 0; i < N; i++){
			sz[i] = 1;
		}
	}

	@Override
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		
		if(i == j){
			return;
		}
		
		if(sz[i] < sz[j]){
			id[i] = j;
			sz[j] += sz[i];
		}else{
			id[j] = i;
			sz[i] += sz[j];
		}
		count--;
	}

}
