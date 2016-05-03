package com.daniel.OCP;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateAndTime {
	// Time zone offset - +02:00, GMT+2, UTC+2 - all the same
	
	public static void main(String[] args){
		ZonedDateTime zdt = ZonedDateTime.now();
		System.out.println("It is "+zdt+" now");
		String str = zdt.toString();
		
		ZonedDateTime fromTheString;
		
		fromTheString = ZonedDateTime.parse(str);
		
		System.out.println("Parsed Zoned Date and Time - "+fromTheString);
		
		//LocalDateTime dateTime;
		
		// dateTime = LocalDateTime.parse(str); could not be parsed
		
		//System.out.println("Parsed Date and Time - "+dateTime);
		
		System.out.println("All available time zone ids:");
		ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
		System.out.println("Total: "+ZoneId.getAvailableZoneIds().stream().count());
		
		LocalDate date1 = LocalDate.now();
		System.out.println("It is "+date1+" now");
		
		long fromEpoch = date1.toEpochDay();
		System.out.println("Long - "+fromEpoch);
		
		LocalDate date2 = LocalDate.ofEpochDay(fromEpoch);
		LocalTime time1 = LocalTime.now();
		
		System.out.println("Date from long - "+date2);
		
		String periodStr = Period.of(1, 2, 3).toString();
		
		System.out.println("Period - "+periodStr);
		
		Period period = Period.parse(periodStr);
		
		System.out.println("parsed Period - "+period);
		
		ZoneId timeZone = ZoneId.of("Europe/London");
		
	}
}
