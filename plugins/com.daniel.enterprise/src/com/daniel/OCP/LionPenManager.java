package com.daniel.OCP;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManager {
	private void removeAnimals(){
		System.out.println("Removing animals");
	}
	
	private void cleanPen(){
		System.out.println("Cleaning the pen");
	}
	
	private void addAminals(){
		System.out.println("Adding animals");
	}
	
	public void performTask(CyclicBarrier c1/*, CyclicBarrier c2*/){
		try{
			removeAnimals();
			c1.await();
			cleanPen();
			c1.await();
			addAminals();
		}catch(InterruptedException | BrokenBarrierException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ExecutorService service = null;
		try{
			service = Executors.newFixedThreadPool(4);
			LionPenManager manager = new LionPenManager();
			CyclicBarrier c1 = new CyclicBarrier(4, () -> System.out.println("*** Done!"));
			//CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));
			for(int i = 0; i < 4; i++){
				service.submit(() -> manager.performTask(c1));
			}
		}finally{
			if(service != null) service.shutdown();
		}
	}
}
