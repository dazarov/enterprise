package com.daniel.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StringUtils {
	public static List<String> getMostPopularStringsJava7(List<String> strings, int number){
		Map<String, Integer> stringMap = new HashMap<>();
		
		for(String str : strings){
			Integer counter = stringMap.get(str);
			if(counter == null){
				stringMap.put(str, 1);
			}else{
				stringMap.replace(str, counter+1);
			}
		}
		
		Map<Integer, List<String>> countMap = new TreeMap<>(Collections.reverseOrder());
		
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
		
		List<String> result = new ArrayList<>();
		
		for(Integer count : countMap.keySet()){
			List<String> list = countMap.get(count);
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
		List<String> result = new ArrayList<>(number);
		
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
		List<String> result = new ArrayList<String>(number);
		
		strings.stream()
	        .collect(Collectors.toMap(str -> str, str -> Collections.frequency(strings, str), (s1, s2) -> s1))
			.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			.limit(number)
			.forEach(s -> result.add(s.getKey()));
		
		return result;
	}
	
	public static List<String> getMostPopularStringsJava8ByMapping(List<String> strings, int number){
		List<String> result = new ArrayList<String>(number);
		
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

	public static List<String> getMostPopularStringsJava8ByMappingParallel(List<String> strings, int number){
		List<String> result = new ArrayList<String>(number);
		
		strings.stream().unordered().parallel()
			.distinct()
	        .collect(Collectors.toConcurrentMap(str -> str, str -> Collections.frequency(strings, str)))
			.entrySet()
			.parallelStream()
			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			.limit(number)
			.forEach(s -> result.add(s.getKey()));
		
		return result;
	}

	public static List<String> getMostPopularStringsBySortintgJava8(List<String> strings, int number){
		
		 List<String> result = strings.stream()
		    .distinct()
			.sorted((s1, s2) -> Collections.frequency(strings, s2) - Collections.frequency(strings, s1))
			.limit(number)
			.collect(Collectors.toList());
		
		return result;
	}

	public static List<String> getMostPopularStringsJava8Parallel(List<String> strings, int number){
		//List<String> list = Collections.synchronizedList(strings);
		
		 List<String> result = strings.stream().unordered().parallel()
		    .distinct()
			.sorted((s1, s2) -> Collections.frequency(strings, s2) - Collections.frequency(strings, s1))
			.limit(number)
			.collect(Collectors.toList());
		
		return result;
	}
	
	public static String intToString(int x){
		StringBuilder sb = new StringBuilder();
		boolean isNegative = false;
		if(x < 0){
			x = -x;
			isNegative = true;
		}
		
		do{
			char c = (char)('0'+ x % 10);
			sb.append(c);
			x /= 10;
		}while(x > 0);
		
		if(isNegative){
			sb.append("-");
		}
		
		return sb.reverse().toString();
	}
	
	public static int stringToInt(String s){
		char[] chars = s.toCharArray();
		
		boolean isNegative = chars[0] == '-';
		int result = 0;
		
		for(int i = chars[0] == '-' ? 1 : 0; i < chars.length; i++){
			int digit = chars[i] - '0';
			result = result * 10 + digit;
		}
		
		return isNegative ? -result : result;
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
		text.add("Seco");
		text.add("S");
		text.add("Sed");
		text.add("Seco");
		text.add("d");
		text.add("a");
		text.add("AAAAAAAAAAAAAAAAAAA");
		text.add("bbbbbbbbbbbbbbbbbb");
		text.add("cccccccccccccccccccccccccccccc");
		text.add("dd3dddddddddddddddddddddd");
		text.add("dddd4dddddddddddddddddddd");
		text.add("ddddd4ddddddddddddddddddd");
		text.add("dddddd5dddddddddddddddddd");
		text.add("ddddddd5ddddddddddddddddd");
		text.add("dddddddd5dddddddddddddddd");
		text.add("ddddddddd5ddddddddddddddd");
		text.add("dddddddddd5dddddddddddddd");
		text.add("ddddddddddd5ddddddddddddd");
		text.add("dddddddddddd5dddddddddddd");
		text.add("ddddddddddddd5ddddddddddd");
		text.add("dddddddddddddd5dddddddddd");
		text.add("ddddddddddddddd5ddddddddd");
		text.add("dddddddddddddddd5dddddddd");
		text.add("ddddddddddddddddd5ddddddd");
		text.add("dddddddddddddddddd5dddddd");
		text.add("ddddddddddddddddddd5ddddd");
		text.add("dddddddddddddddddddd5dddd");
		text.add("ddddddddddddddddddddd5ddd");
		text.add("dddddddddddddddddddddd5dd");
		text.add("ddddddddddddddddddddddd5d");
		text.add("ddddddddddddddddfdddddddd");
		text.add("dddddddddddddddddfddddd5dd");
		text.add("dddddddddddddddfdddddddd5dd");
		text.add("ddddddddddddddddfddddddd5dd");
		text.add("ddddtddddddddddddfddddddd5dd");
		text.add("ddddddddrdddddddddfddddd5dd");
		text.add("ddddfdddddddddddddddddd5dd");
		text.add("dddddfddddddddddddddddd5dd");
		text.add("ddddddfdddddddddddddddd5dd");
		text.add("dddddddfddddddddddddddd5dd");
		text.add("ddddddddfdddddddddddddd5dd");
		text.add("dddddddddfddddddddddddd5dd");
		text.add("ddddddddddfdddddddddddd5dd");
		text.add("dddddddddddfddddddddddddd");
		text.add("ddddddddddddfdddddddddddd");
		

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
		text2.add("Seco");
		text2.add("S");
		text2.add("Sed");
		text2.add("Seco");
		text2.add("d");
		text2.add("a");
		text2.add("AAAAAAAAAAAAAAAAAAA");
		text2.add("bbbbbbbbbbbbbbbbbb");
		text2.add("cccccccccccccccccccccccccccccc");
		text2.add("dd3dddddddddddddddddddddd");
		text2.add("dddd4dddddddddddddddddddd");
		text2.add("ddddd4ddddddddddddddddddd");
		text2.add("dddddd5dddddddddddddddddd");
		text2.add("ddddddd5ddddddddddddddddd");
		text2.add("dddddddd5dddddddddddddddd");
		text2.add("ddddddddd5ddddddddddddddd");
		text2.add("dddddddddd5dddddddddddddd");
		text2.add("ddddddddddd5ddddddddddddd");
		text2.add("dddddddddddd5dddddddddddd");
		text2.add("ddddddddddddd5ddddddddddd");
		text2.add("dddddddddddddd5dddddddddd");
		text2.add("ddddddddddddddd5ddddddddd");
		text2.add("dddddddddddddddd5dddddddd");
		text2.add("ddddddddddddddddd5ddddddd");
		text2.add("dddddddddddddddddd5dddddd");
		text2.add("ddddddddddddddddddd5ddddd");
		text2.add("dddddddddddddddddddd5dddd");
		text2.add("ddddddddddddddddddddd5ddd");
		text2.add("dddddddddddddddddddddd5dd");
		text2.add("ddddddddddddddddddddddd5d");
		text2.add("ddddddddddddddddfdddddddd");
		text2.add("dddddddddddddddddfddddd5dd");
		text2.add("dddddddddddddddfdddddddd5dd");
		text2.add("ddddhddddddddddddfddddddd5dd");
		text2.add("ddddddddddddddddfddddddd5dd");
		text2.add("ddddddddrdddddddddfddddd5dd");
		text2.add("ddddfdddddddddddddddddd5dd");
		text2.add("dddddfddddddddddddddddd5dd");
		text2.add("ddddddfdddddddddddddddd5dd");
		text2.add("dddddddfddddddddddddddd5dd");
		text2.add("ddddddddfdddddddddddddd5dd");
		text2.add("dddddddddfddddddddddddd5dd");
		text2.add("ddddddddddfdddddddddddd5dd");
		text2.add("dddddddddddfddddddddddddd");
		text2.add("ddddddddddddfdddddddddddd");
		
		System.out.println("\nJava7 test 1");
		long start = System.currentTimeMillis();
		List<String> result = getMostPopularStringsJava7(text, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava7 test 2");
		
		start = System.currentTimeMillis();
		result = getMostPopularStringsJava7(text2, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 Sorting test 1");

		start = System.currentTimeMillis();
		result = getMostPopularStringsBySortintgJava8(text, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 Sorting test 2");
		
		start = System.currentTimeMillis();
		result = getMostPopularStringsBySortintgJava8(text2, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 Mapping test 1");

		start = System.currentTimeMillis();
		result = getMostPopularStringsJava8ByMapping(text, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 Mapping test 2");
		
		start = System.currentTimeMillis();
		result = getMostPopularStringsJava8ByMapping(text2, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}

		System.out.println("\nJava8 Parallel Sorting test 1");

		start = System.currentTimeMillis();
		result = getMostPopularStringsJava8Parallel(text, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 Parallel Sorting test 2");
		
		start = System.currentTimeMillis();
		result = getMostPopularStringsJava8Parallel(text2, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 Parallel Mapping test 1");

		start = System.currentTimeMillis();
		result = getMostPopularStringsJava8ByMappingParallel(text, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("\nJava8 Parallel Mapping test 2");
		
		start = System.currentTimeMillis();
		result = getMostPopularStringsJava8ByMappingParallel(text2, 3);
		System.out.println("Time - "+(System.currentTimeMillis()-start));
		
		for(String str : result){
			System.out.println(str);
		}
		
		System.out.println("intToString 1 - <"+intToString(123456)+">");
		System.out.println("intToString 2 - <"+intToString(-7523)+">");
		
		System.out.println("stringToInt 1 - <"+stringToInt("123456")+">");
		System.out.println("stringToInt 2 - <"+stringToInt("-7523")+">");

	}
}
