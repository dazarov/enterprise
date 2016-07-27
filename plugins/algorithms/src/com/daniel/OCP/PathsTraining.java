package com.daniel.OCP;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathsTraining {
	public static void main(String...strings){
		Path path1 = Paths.get("/root/first/second/thirdt/file_one.ext");
		Path path2 = Paths.get("/root/one/../file_two.ext");
		
		System.out.println("Path 1 -"+path1);
		System.out.println("Path 2 -"+path2);
		
		path1 = path1.normalize();
		path2 = path2.normalize();
		
		System.out.println("Normalized Path 1 -"+path1);
		System.out.println("Normalized Path 1 -"+path2);
		
		System.out.println("Relative path from 1 to 2 - "+path1.relativize(path2));
		
		System.out.println("Relative path from 2 to 1- "+path2.relativize(path1));
		
		
		Path path3 = Paths.get("folder/file.ext");
		
		System.out.println("Path 3 -"+path3);
		
		path3 = path3.toAbsolutePath();
		
		System.out.println("Absolute Path 3 -"+path3);
		
		// 8
//		Path path = Paths.get("bear/polar/./evironment").normalize().getRoot();
//		System.out.println(Files.list(path)
//				.filter(p -> !Files.isDirectory(p))
//				.map(p -> p)
//				.collect(Collectors.toSet()))
//				.size());
		
//		Queue<Integer> q = new LinkedList<>();
//		q.add(new Integer(6));
//		q.add(new Integer(6));
//		System.out.println(q.size()+" "+q.contains(6L));
//		
		Stream<String> s = Stream.empty();
		Stream<String> s2 = Stream.empty();
		Predicate<String> condition = b -> b.startsWith("c");
		Map<Boolean, List<String>> p = s.collect(
				Collectors.partitioningBy(condition));
		Map<Boolean, List<String>> g = s2.collect(
				Collectors.groupingBy(b -> b.startsWith("c")));
		System.out.println("empty partitioningBy - "+p+" empty groupingBy - "+g);
		
		Path p1 = Paths.get("/lemur/habitat/./party.txt");
		System.out.println("SUB PATH - "+p1.subpath(1, 4));
		Path p2 = p1.subpath(1, 4).toAbsolutePath();
		System.out.println("PATH - "+p2);
		
		Path root1 = Paths.get("/usr/var/Root1");
		Path root2 = Paths.get("/usr/var/Root2");
		
		Path file1 = Paths.get("/usr/var/Root1/folder1/folder2/file.ext");
		System.out.println("File 1 - "+file1);
		
		Path file2 = root2.resolve(root1.relativize(file1));
		System.out.println("File 2 - "+file2);
	}
}
