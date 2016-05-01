package com.daniel.OCP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Streams {
	public static void main(String[] args){
		Streams instance = new Streams();
		
		instance.creatingStreams();
		
		instance.commonOperations();
		
		instance.primitiveStreams();
		
		
		//Stream<String> stream = Stream.of("Cccc", "Slon", "aaa", "Aaaa", "Bbbb",  "vvv");
		//stream.filter(x -> Character.isUpperCase(x.charAt(0))).forEach(System.out::println);
		
		// sorted
		
		List<String> list = Stream.of("34", "67hn", "Ccccccccc", "Slon", "aaa", "Aaaa", "Bbbb",  "vvv", "567")
				.distinct() // remove duplicated elements
				//.filter(s -> Character.isUpperCase(s.charAt(0)))
				.filter(s -> Character.isLetter(s.charAt(0)))
				//.sorted()
				.sorted((s1, s2) -> s2.length() - s1.length())
				.collect(Collectors.toList());
		
		System.out.println(list);
	}
	
	void creatingStreams(){
		// Creating stream source
		//-----------------------
		Stream<String> empty = Stream.empty();
		Stream<Integer> singleElement = Stream.of(34);
		Stream<Integer> fromArray = Stream.of(23,65,78,500);
		
		List<String> stringList = Arrays.asList("first", "second", "third");
		Stream<String> fromList = stringList.stream();
		
		System.out.println("\nRandom stream:");
		// Stream<Double> randoms = 
		Stream.generate(Math::random).limit(10).forEach(System.out::println);
		
		System.out.println("\nOdd number stream:");
		//Stream<Integer> oddNumbers = 
		Stream.iterate(1, n -> n+2).skip(5).limit(10).forEach(System.out::println);

		System.out.println("\nEven number stream:");
		//Stream<Integer> evenNumbers = 
		Stream.iterate(2, n -> n+2).skip(3).limit(10).forEach(System.out::println);
	}

	void commonOperations(){
		// Using common terminal operations
		
		// long count()
		Stream<String> streamForCount = Stream.of("a", "b", "c", "d", "e");
		System.out.println("Counting stream elements - "+streamForCount.peek(System.out::println).count());
		
		// Optional<T> min(Comparator<? super T> comparator) and Optional<T> max(Comparator<? super T> comparator)
		Stream<String> streamForMax = Stream.of("a", "b", "c", "d", "e");
		Optional<String> max = streamForMax.max((s1, s2) -> s1.compareTo(s2));
		System.out.println("Max string:");
		max.ifPresent(System.out::println);

		Stream<String> streamForMin = Stream.of("a", "b", "c", "d", "e");
		Optional<String> min = streamForMin.min((s1, s2) -> s1.compareTo(s2));
		System.out.println("Min string:");
		min.ifPresent(System.out::println);
		
		// Optional<T> findAny() and Optional<T> findFirst()
		
		Stream<String> infStream = Stream.generate(() -> "Infinite string");
		infStream.findAny().ifPresent(System.out::println);
		
		// boolean allMatch(Predicate<? super T> predicate),  boolean anyMatch(Predicate<? super T> predicate) and boolean noneMatch(Predicate<? super T> predicate)
		
		boolean allMatch = Stream.of("1", "2", "v", "T").allMatch(s -> Character.isDigit(s.charAt(0)));
		System.out.println("All Match - "+allMatch);
		boolean anyMatch = Stream.of("1", "2", "v", "T").anyMatch(s -> Character.isDigit(s.charAt(0)));
		System.out.println("Any Match - "+anyMatch);
		
		// T reduce(T identity, BinaryOperator<T> accumulator);
		
		String concatString = Stream.of("S", "t", "r", "e", "a", "m")
				//.reduce("", (s1, s2) -> s1 + s2 );
				.reduce("", String::concat );
		System.out.println("Reduce str - "+concatString);

		int sum = Stream.of(2, 4, 6, 8, 4, 0)
				.reduce(0, (s1, s2) -> s1 + s2 );
		System.out.println("Reduce int - "+sum);
		
		// <R> R collect(Supplier<R> supplier, BiConsumer<R ? super T> accumulator, BiConsumer<R, R> combiner)
		// <R, A> R collect(Collector<? super T, A, R> collector)
		
		StringBuilder sb = Stream.of("S", "t", "r", "e", "a", "m")
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		System.out.println("Collect StringBuilder - "+sb);

		ArrayList<String> arrayList = Stream.of("S", "t", "r", "e", "a", "m")
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		System.out.println("Collect ArrayList - "+arrayList);
		
		TreeSet<String> treeSet = Stream.of("S", "t", "r", "e", "a", "m")
				.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
		System.out.println("Collect TreeSet - "+treeSet);
		
		arrayList = Stream.of("S", "t", "r", "e", "a", "m")
				.collect(Collectors.toCollection(ArrayList::new)); // toList toSet toMap
		System.out.println("Collect more ArrayList - "+arrayList);
		
		// <R> Stream<R> map(Function<? super T, ? extends R> mapper)
		
		System.out.println("Stream of Strings to Stream of ints");
		Stream.of("ab", "abcde", "rest")
			.map(String::length).forEach(System.out::println);
		
		System.out.println("Stream of ints to Stream of Strings");
		Stream.of(3, 5, 56)
			.map(i -> "_"+i+"_").forEach(System.out::println);
		
		// flatMap
		
		List<String> l1 = Arrays.asList();
		List<String> l2 = Arrays.asList("one", "tree");
		List<String> l3 = Arrays.asList("one", "tree", "two", "four");
		List<String> l4 = Arrays.asList("five", "tree", "two", "four", "six");
		
		Stream<List<String>> listStream = Stream.of(l1, l2, l3, l4);
		
		System.out.println("Stream from Streams of lists");
		listStream.flatMap(l -> l.stream())
			.distinct() // remove duplicates
			.forEach(System.out::println);

	}
	
	void primitiveStreams(){
		IntStream intStream = IntStream.of(1,3,7,8,7,8);
		System.out.println("Int average - ");
		intStream.distinct().average().ifPresent(System.out::println);
		
		IntStream rangeStream = IntStream.range(3, 11); // same as IntStream.rangeClosed(3,10);
		
		//System.out.println("Range int stream sum - "+range.sum());
		
		LongStream longStream = rangeStream.mapToLong(i -> i);
		
		System.out.println("Long range average - ");
		longStream.average().ifPresent(System.out::print);
	}
}
