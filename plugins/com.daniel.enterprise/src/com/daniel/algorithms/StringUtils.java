package com.daniel.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringUtils {
	public static List<String> getMostPopularStrings(List<String> strings, int number){
		HashMap<String, Integer> counters = new HashMap<String, Integer>();
		
		for(String str : strings){
			Integer counter = counters.get(str);
			if(counter == null){
				counters.put(str, 1);
			}else{
				counters.replace(str, counter+1);
			}
		}
		
		ArrayList<String> result = new ArrayList<String>();
		
		List<Map.Entry<String, Integer>> entries = counters
				.entrySet()
				.stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.limit(number)
				.collect(Collectors.toList());
		
		for(Map.Entry<String, Integer> entry : entries){
			result.add(entry.getKey());
		}
		
		
		return result;
	}
	
	public static void main(String[] args){
		ArrayList<String> text = new ArrayList<String>();
		
		text.add("Second");
		text.add("Third");
		text.add("Most");
		text.add("Some");
		text.add("Second");
		text.add("Most");
		text.add("Third");
		text.add("Second");
		text.add("Ame");
		text.add("Most");
		text.add("More");
		text.add("Bla bla");
		text.add("Most");
		
		List<String> result = getMostPopularStrings(text, 3);
		
		for(String str : result){
			System.out.println(str);
		}
	}
}
