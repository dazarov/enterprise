package com.daniel.OCP;

import java.util.HashMap;
import java.util.Map;

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
		
		map.compute(3, (k,v) -> v == null ? "_UP_"+k : v.toUpperCase()+k);  // changes entry for key 3
		map.compute(8, (k,v) -> v == null ? "_UP_"+k : v.toUpperCase()+k);  // adds map entry
		
		System.out.println("after compute Map - "+map);
		
		map.merge(3, "_addition", (s1, s2) -> s1.concat(s2)); // changes entry
		map.merge(7, "_addition", (s1, s2) -> s1.concat(s2)); // adds entry
		
		System.out.println("after merge Map - "+map);
		
		map.computeIfPresent(1, (k,v) -> v.toUpperCase()+k);
		map.computeIfPresent(9, (k,v) -> v.toUpperCase()); // do nothing with missing key
		
		System.out.println("after computeIfPresent Map - "+map);
		
		map.computeIfAbsent(2, (v) -> "_UP_"); // do nothing with existing entry
		map.computeIfAbsent(10, (v) -> "_UP_");
		
		System.out.println("after computeIfAbsent Map - "+map);
		
		map = new HashMap<>();
		
		map.put(1, null);
		map.put(2, "Bbbb");
		map.put(3, null);
		
		System.out.println("Map (with null) - "+map);
		
		map.compute(1, (k,v) -> "NOT_NULL"); // changes entry
		map.compute(2, (k,v) -> null); // removes entry without null
		map.compute(3, (k,v) -> null); // removes entry with null
		
		System.out.println("after compute Map - "+map);

		map = new HashMap<>();
		
		map.put(1, null);
		map.put(2, "Bbbb");
		map.put(3, null);
		
		System.out.println("Map (with null) - "+map);
		
		map.merge(1, "MERGED", (s1, s2) -> "NOT_NULL");
		map.merge(2, "MERGED", (s1, s2) -> null); // removes entry
		map.merge(3, "MERGED", (s1, s2) -> null); // changes entry
		
		System.out.println("after merge Map - "+map);
		
		map = new HashMap<>();
		
		map.put(1, "Aaaa");
		map.put(2, "Bbbb");
		map.put(3, "Cccc");
		map.put(4, "Dddd");
		
		System.out.println("+++ Map (with null) - "+map);
		
		map.merge(1, "New", (s1, s2) -> s1+s2);
		map.merge(2, "New", (s1, s2) -> s1);
		map.merge(3, "New", (s1, s2) -> s2);
		map.merge(4, "New", (s1, s2) -> "_-_-_-");
		
		System.out.println("+++ after merge Map - "+map);
//		
//		map.computeIfPresent(1, (k,v) -> v.toUpperCase()+k);
//		map.computeIfPresent(9, (k,v) -> v.toUpperCase()); // do nothing
//		
//		System.out.println("after computeIfPresent Map - "+map);
//		
//		map.computeIfAbsent(2, (v) -> "_UP_"); // do nothing
//		map.computeIfAbsent(10, (v) -> "_UP_");
//		
//		System.out.println("after computeIfAbsent Map - "+map);
	}
	
}
