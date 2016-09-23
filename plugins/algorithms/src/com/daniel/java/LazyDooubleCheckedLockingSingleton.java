package com.daniel.java;

public class LazyDooubleCheckedLockingSingleton {
	private static volatile LazyDooubleCheckedLockingSingleton instance = null;

	// double-checked-locking
	public LazyDooubleCheckedLockingSingleton getInstance() {
		if (instance == null) {
			synchronized (this) {
				if (instance == null)
					instance = new LazyDooubleCheckedLockingSingleton();
			}
		}
		return instance;
	}

	private LazyDooubleCheckedLockingSingleton() {

	}
}
