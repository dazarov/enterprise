package com.daniel.algorithms;

import java.util.stream.IntStream;

public class LuckyTicket {
	public static boolean isMyTicketLucky(String string){
		if(string.length() != 6){
			throw new RuntimeException("String should be 6 characters long");
		}
		//int[] numbers = new int[6];
		//for(int index = 0; index < string.length(); index++){
		//	numbers[index] = Integer.parseInt(""+string.charAt(index));
		//}
		
		// Stream.of(string.toCharArray()) - returns stream of arrays, not stream of chars 
		// string.chars() - returns IntStream;
		
		//int[] numbers = string.chars().map(i -> (char)i).map(c -> Integer.parseInt(""+c)).toArray();
		int[] numbers = string.chars().map(i -> Integer.parseInt(""+(char)i)).toArray();
		
		int left = IntStream.of(numbers).limit(3).sum();
		int right = IntStream.of(numbers).skip(3).sum();

		return left == right;
	}
	
	public static void main(String[] args){
		String str1 = "123321";
		
		System.out.println("Ticket - "+str1+" lucky - "+isMyTicketLucky(str1));
		
		String str2 = "123456";
		
		System.out.println("Ticket - "+str2+" lucky - "+isMyTicketLucky(str2));
		
	}
	
	
}
