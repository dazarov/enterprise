package com.daniel.OCP;

import java.util.Optional;

public class OptionalUsage {
	public static Optional<Integer> findNumber(int a){
		if(a > 0){
			return Optional.of(a);
		}else{
			return Optional.empty();
		}
	}
	
	public static void main(String ... strings){
		
		Optional<Integer> result = findNumber(12);
		
		// use #1
		result.ifPresent(System.out::println);
		
		// use #2
		if(result.isPresent()){
			int value = result.get();
			System.out.println(value);
		}
		
		result = findNumber(-2);
		
		// use #3
		int a = result.orElse(200).intValue();
		System.out.println(a);
		
		// error handling
		try {
			result.orElseThrow(() -> new Exception("Negative value"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
