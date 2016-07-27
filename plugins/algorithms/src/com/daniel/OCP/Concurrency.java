package com.daniel.OCP;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Concurrency {
	public static void main(String...strings){
		System.out.println("Available processors: "+Runtime.getRuntime().availableProcessors());
		
		new Thread(()->System.out.println("Hello world")).start();
		
		new ReadFileThread().start();
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		service.execute(() -> System.out.println("..."));
		
		service.shutdown();
		
		ScheduledExecutorService service2 = Executors.newSingleThreadScheduledExecutor();
		
		service2.scheduleWithFixedDelay(() -> System.out.println("working..."), 0, 10, TimeUnit.SECONDS);
	}
}
