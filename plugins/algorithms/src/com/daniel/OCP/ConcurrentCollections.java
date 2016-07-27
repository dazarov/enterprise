package com.daniel.OCP;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollections {
	public static void main(String...strings){
		List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(2,5,6,7));
		System.out.println("First time");
		int i = 40;
		for(Integer item : list){
			System.out.print(item+" ");
			list.add(i++);
		}
		System.out.println("Second time");
		list.forEach(System.out::println);
		
	}
}
