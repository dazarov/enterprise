package com.daniel.algorithms;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {
	public static String swapReverse(String input) {
		char[] temparray = input.toCharArray();
		int left, right = 0;
		right = temparray.length - 1;
		for (left = 0; left < right; left++, right--) {
			char temp = temparray[left];
			temparray[left] = temparray[right];
			temparray[right] = temp;
		}
		return new String(temparray);
	}

	public static String simpleReverse(String input) {
		char[] array = input.toCharArray();
		char[] temparray = new char[array.length];

		int index = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			temparray[index++] = array[i];
		}

		return new String(temparray);
	}

	public static String stringBuilderReverse(String input) {
		StringBuilder builder = new StringBuilder();

		builder.append(input);
		builder.reverse();

		return builder.toString();
	}

	public static String collectionsReverse(String input) {
		char[] hello = input.toCharArray();
		List<Character> list = new LinkedList<Character>();
		for (char c : hello) {
			list.add(c);
		}
		Collections.reverse(list);
		
		StringBuilder builder = new StringBuilder();
		
		Iterator<Character> it = list.iterator();
		while(it.hasNext()){
			builder.append(it.next().charValue());
		}
		
		return builder.toString();
	}

	public static void main(String[] args) {
		final String input = "Convert the input string into character!";

		System.out.println("+++++++++++ SWAP REVERSE +++++++++++++++++++++");
		System.out.println("Original String - " + input);

		String reversedString = swapReverse(input);

		System.out.println("Reversed string - " + reversedString);

		String againString = swapReverse(reversedString);

		System.out.println("Reversed again string - " + againString);
		
		System.out.println("+++++++++++ SIMPLE REVERSE +++++++++++++++++++++");
		System.out.println("Original String - " + input);

		reversedString = simpleReverse(input);

		System.out.println("Reversed string - " + reversedString);

		againString = simpleReverse(reversedString);

		System.out.println("Reversed again string - " + againString);
		
		System.out.println("+++++++++++ STRING BUILDER REVERSE +++++++++++++++++++++");
		System.out.println("Original String - " + input);

		reversedString = stringBuilderReverse(input);

		System.out.println("Reversed string - " + reversedString);

		againString = stringBuilderReverse(reversedString);

		System.out.println("Reversed again string - " + againString);

		System.out.println("+++++++++++ COLLECTIONS REVERSE +++++++++++++++++++++");
		System.out.println("Original String - " + input);

		reversedString = collectionsReverse(input);

		System.out.println("Reversed string - " + reversedString);

		againString = collectionsReverse(reversedString);

		System.out.println("Reversed again string - " + againString);
	}
}
