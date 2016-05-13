package com.daniel.OCP;

public class ReadFileThread extends Thread {

	@Override
	public void run() {
		System.out.println("Reading file...");
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Done.");
	}

}
