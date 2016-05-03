package com.daniel.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StringUtils {
	public static List<String> getMostPopularStringsJava7(List<String> strings, int number){
		HashMap<String, Integer> stringMap = new HashMap<String, Integer>();
		
		for(String str : strings){
			Integer counter = stringMap.get(str);
			if(counter == null){
				stringMap.put(str, 1);
			}else{
				stringMap.replace(str, counter+1);
			}
		}
		
		HashMap<Integer, List<String>> countMap = new HashMap<Integer, List<String>>();
		
		for(String str : stringMap.keySet()){
			Integer count = stringMap.get(str);
			
			if(!countMap.containsKey(count)){
				ArrayList<String> list = new ArrayList<String>();
				list.add(str);
				countMap.put(count, list);
			}else{
				List<String> list = countMap.get(count);
				list.add(str);
			}
		}
		
		Integer[] counts = countMap.keySet().toArray(new Integer[]{});
		
		Arrays.sort(counts);
		
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = counts.length - 1; i >= 0; i--){
			List<String> list = countMap.get(counts[i]);
			for(String str : list){
				result.add(str);
				if(result.size() == number){
					return result;
				}
			}
		}
		
		return result;
	}
	
	public static List<String> getMostPopularStringsJava8_old(List<String> strings, int number){
		ArrayList<String> result = new ArrayList<String>(number);
		
		Set<String> set = strings.stream()
	            .collect(Collectors.toSet());
		
		Map<String, Integer> map = set.stream()
	            .collect(Collectors.toMap(str -> str,
	                    str -> Collections.frequency(strings, str)));
		
		map
			.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			.limit(number)
			.forEach(s -> result.add(s.getKey()));
		
		return result;
	}
	
	public static List<String> getMostPopularStringsJava8_old2(List<String> strings, int number){
		ArrayList<String> result = new ArrayList<String>(number);
		
		strings.stream()
	        .collect(Collectors.toMap(str -> str, str -> Collections.frequency(strings, str), (s1, s2) -> s1))
			.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			.limit(number)
			.forEach(s -> result.add(s.getKey()));
		
		return result;
	}
	
	public static List<String> getMostPopularStringsJava8_old3(List<String> strings, int number){
		ArrayList<String> result = new ArrayList<String>(number);
		
		strings.stream()
			.distinct()
	        .collect(Collectors.toMap(str -> str, str -> Collections.frequency(strings, str)))
			.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			.limit(number)
			.forEach(s -> result.add(s.getKey()));
		
		return result;
	}

	public static List<String> getMostPopularStringsJava8(List<String> strings, int number){
		
		 List<String> result = strings.stream()
		    .distinct()
			.sorted((s1, s2) -> Collections.frequency(strings, s2) - Collections.frequency(strings, s1))
			.limit(number)
			.collect(Collectors.toList());
		
		return result;
	}
	
	
	public static void main(String[] args){
		ArrayList<String> text = new ArrayList<String>();
		// Most - 4
		// Second - 3
		// Third - 2
		
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

		ArrayList<String> text2 = new ArrayList<String>();
		// Most - 2
		// Second - 2
		// Third - 2
		
		text2.add("Third");
		text2.add("Some");
		text2.add("Second");
		text2.add("Third");
		text2.add("Second");
		text2.add("Ame");
		text2.add("Most");
		text2.add("More");
		text2.add("Bla bla");
		text2.add("Most");
		
		System.out.println("\nJava7 test 1");
		
		List<String> result = getMostPopularStringsJava7(text, 3);
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava7 test 2");
		
		result = getMostPopularStringsJava7(text2, 3);
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 test 1");

		result = getMostPopularStringsJava8(text, 3);
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 test 2");
		
		result = getMostPopularStringsJava8(text2, 3);
		
		for(String str : result){
			System.out.println(str);
		}


	}
}
