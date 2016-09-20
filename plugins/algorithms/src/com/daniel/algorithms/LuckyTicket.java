package com.daniel.algorithms;

import java.util.stream.*;

public class LuckyTicket {
	public static boolean isMyTicketLucky(String number){
		if(number.length() != 6){
			throw new RuntimeException("String should be 6 characters long");
		}
		int[] array = new int[6];
		for(int index = 0; index < number.length(); index++){
			array[index] = Integer.parseInt(""+number.charAt(index));
		}
		int left = IntStream.of(array).limit(3).sum();
		int right = IntStream.of(array).skip(3).sum();

		return left == right;
	}
	
	public static void main(String[] args){
		String str1 = "123321";
		
		System.out.println("Ticket - "+str1+" lucky - "+isMyTicketLucky(str1));
		
		String str2 = "123456";
		
		System.out.println("Ticket - "+str2+" lucky - "+isMyTicketLucky(str2));
		
	}
	
	
}
