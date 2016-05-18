package com.daniel.OCP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MapsTraining {
	public static void main(String...strings){
		Map<Integer, String> map = new HashMap<>();
		
		map.put(1, "Aaaa");
		map.put(2, "Bbbb");
		map.put(3, "Cccc");
		map.put(4, "Dddd");
		map.put(5, "Eeee");
		map.put(6, "Ffff");
		
		System.out.println("Map - "+map);
		
		map.compute(3, (k,v) -> v == null ? "_UP_"+k : v.toUpperCase()+k);
		map.compute(8, (k,v) -> v == null ? "_UP_"+k : v.toUpperCase()+k);
		
		System.out.println("after compute Map - "+map);
		
		map.merge(3, "_addition", (s1, s2) -> s1.concat(s2));
		map.merge(7, "_addition", (s1, s2) -> s1.concat(s2));
		
		System.out.println("after merge Map - "+map);
		
		map.computeIfPresent(1, (k,v) -> v.toUpperCase()+k);
		map.computeIfPresent(9, (k,v) -> v.toUpperCase()); // do nothing
		
		System.out.println("after computeIfPresent Map - "+map);
		
		map.computeIfAbsent(2, (v) -> "_UP_"); // do nothing
		map.computeIfAbsent(10, (v) -> "_UP_");
		
		System.out.println("after computeIfAbsent Map - "+map);
		
		//Properties prop = new Properties();
		
		//prop.keySet().s
		
		Path path = Paths.get("/aaa");
		
		try {
			Files.lines(path)
			.flatMap(p -> Stream.of(p.split(",")))
			.map(s -> s.length())
			.collect(Collectors.toList())
			.forEach(System.out::print);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String a="as";
		//a.com
		
		Arrays.asList(0,1,2)
		//.stream()
		.forEach(System.out::print);
		
		Stream<String> sss = Stream.of("1", "2");
		
		IntStream is = sss.mapToInt(Integer::parseInt);
	}
	
	public static void add(List list){
		list.add("sd");
		list.add(.123);
		
		//UnaryOperator<LocalDate> u = 1 -> 1;
	}
}
