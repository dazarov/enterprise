package com.daniel.OCP;

import java.nio.file.Path;
import java.nio.file.Paths;

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
	}
}
