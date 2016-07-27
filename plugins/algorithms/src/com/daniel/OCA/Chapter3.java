package com.daniel.OCA;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Chapter3 {
	void date(){
		// immutable
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDateTime dateTime = LocalDateTime.now();
		
		//LocalDate d = LocalDate.of(2015, 5);
		
		date.minusMonths(4);
		
		// Month starts with 1
		Month month = Month.APRIL;
		
		date.isAfter(LocalDate.of(1970, 6, 12));
		
		// isBefore
		// isEqual
		// isLeapYear()
		
		// mutable
		Period period = Period.of(1, 2, 3);
		Duration duration = Duration.ofSeconds(20);

		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		
		// shows time only
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		
		//date.p
	}
	
	void stringWork(){
		String a = "";
		
		a += 2;
		a += 'd';
		a += false;
		
		String[] names = {"aaa", "bbb", "ccc"};
		//names.asList();
	}
	
	void old_way(){
		// mutable
		Date d = new Date(); // current date
		
		Calendar c = Calendar.getInstance();
		
		// Month starts with 0
		c.set(2015, 0, 1);
		
		int month = Calendar.JANUARY;
		
		Date jan = c.getTime();
	}
	
	public static void main(String... args){
		Chapter3 chapter = new Chapter3();
		
		chapter.date();
		
		List<String> hex = Arrays.asList("30", "8", "3A", "FF");
		Collections.sort(hex);
		int x = Collections.binarySearch(hex, "8");
		int y = Collections.binarySearch(hex, "3A");
		int z = Collections.binarySearch(hex, "4F");
		
		System.out.println(hex);
		
		System.out.println(x + " "+ y + " " + z);
	}
}
