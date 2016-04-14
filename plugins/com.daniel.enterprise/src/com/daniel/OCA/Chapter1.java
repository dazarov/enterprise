package com.daniel.OCA;

public class Chapter1 {
	//int 23abc;
	
	int i;
	String name;
	
	
	float f;
	//double public;
	double Public = 3f;
	int _field;
	long $field = 123_456_789_012L;
	
	
	{
		//int a = 1234L; Type mismatch: cannot convert from long to int

		i = 1_________000_000;
	}
	
	// Command line:
	//  cd /home/daniel/git/enterprise/plugins/com.daniel.enterprise/src
	//  javac ./com/daniel/oracle/Chapter1.java
	//  java com.daniel.oracle.Chapter1
	//  java com.daniel.oracle.Chapter1 "One argument" another


	
	public void Chapter1(){
		
	}
	
	public Chapter1(){
		i = name.length();
	}
	
	public static void main(String[] args){
		Chapter1 chapter = new Chapter1();
		System.out.println("Chapter name - "+chapter.name);
		System.out.println("i - "+chapter.i);
		
		String[] array = new String[]{"First", "Second", "Third"};
		
		chapter.m1(array);
		chapter.m2(array);
		chapter.m3(array);
		
		//chapter.
		//long x = 10;
		//int y = 2 * x;
		int x = 5;
		System.out.println(x > 2 ? x < 4 ? 10 : 8 : 7);
		System.out.println("8-bit byte min - "+Byte.MIN_VALUE); 	// -128
		System.out.println("8-bit byte max - "+Byte.MAX_VALUE);		// 127
		
		System.out.println("16-bit short min - "+Short.MIN_VALUE);  // -32_768
		System.out.println("16-bit short max - "+Short.MAX_VALUE);	// 32_767
		
		System.out.println("32-bit int min - "+Integer.MIN_VALUE);	// -2_147_483_648 ~ 2E9
		System.out.println("32-bit int max - "+Integer.MAX_VALUE);	// 2_147_483_647
		
		System.out.println("64-bit long min - "+Long.MIN_VALUE);	// -9_223_372_036_854_775_808 ~ 9E18
		System.out.println("64-bit long max - "+Long.MAX_VALUE);	// 9_223_372_036_854_775_807
		
		System.out.println("32-bit float min - "+Float.MIN_VALUE);	// 1.4E-45
		System.out.println("32-bit float max - "+Float.MAX_VALUE);	// 3.4028235E38
		
		System.out.println("64-bit double min - "+Double.MIN_VALUE);// 4.9E-324
		System.out.println("64-bit double max - "+Double.MAX_VALUE);// 1.7976931348623157E308
		
		//System.out.println("16-bit char min - "+Character.MIN_VALUE);
		//System.out.println("16-bit char max - "+Character.MAX_VALUE);
	}
	
	void m1(String[] args){
		System.out.println("m1 args - "+args.length);
	}
	
	void m2(String args[]){
		System.out.println("m2 args - "+args.length);
	}
	
	void m3(String... args){
		System.out.println("m3 args - "+args.length);
	}
	
	void process(){
		byte b1 = 1;
		byte b2 = 2;
		// byte b3 = b1 + b2; - Type mismatch: cannot convert from int to byte
		byte b3 = (byte)(b1 + b2);
		//b3 = b3 + 1; - Type mismatch: cannot convert from int to byte
		b3 = (byte)(b3 + 1);
		b3 += 1;
		
		
		short s1 = 1;
		short s2 = 2;
		//short s3 = s1 + s2; -	Type mismatch: cannot convert from int to short
		short s3 = (short)(s1 + s2);
		//s3 = s3 + 2; -	Type mismatch: cannot convert from int to short
		s3 = (short)(s3 + 2);
		s3 += 2;
	}
																																										{name="Name"; }
}
