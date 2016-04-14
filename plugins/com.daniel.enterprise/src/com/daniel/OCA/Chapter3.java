package com.daniel.OCA;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

public class Chapter3 {
	void date(){
		// immutable
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDateTime dateTime = LocalDateTime.now();
		
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
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		
		//date.p
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
	}
}
