package com.daniel.java;

public class LazySingleton {
	
	private static class LazySingletonHolder {
		public static LazySingleton instance = new LazySingleton();
	}

	public static LazySingleton getInstance() {
		return LazySingletonHolder.instance;
	}

	private LazySingleton() {

	}

}
