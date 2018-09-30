package com.dworld.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.dworld.core.DWConfiguration;

public enum DWSounds {
	SHOT("resources/sounds/shot.wav"),
	CANNON_SHOT("resources/sounds/cannon_shot.wav"),
	ROCKET_LAUNCH("resources/sounds/rocket_launch.wav"),
	STEP("resources/sounds/step.wav"),
	DDOR("resources/sounds/door.wav"),
	EXPLOSION("resources/sounds/explosion.wav"),
	GRENADE("resources/sounds/grenade.wav");
	
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	private String fileName;
	
	DWSounds(String fileName){
		this.fileName = fileName;
	}
	
	public void playSound(){
		if(DWConfiguration.getInstance().isSoundEnabled()){
			executor.execute(new RunnablePlayer());
		}
    }
	
	protected AudioInputStream getAudioInputStream() throws UnsupportedAudioFileException, IOException {
		URL url = DWSounds.class.getClassLoader().getResource(fileName);
		if(url != null){
			return AudioSystem.getAudioInputStream(url);
		}else{
			return AudioSystem.getAudioInputStream(new File(fileName));
		}
	}
	
	class RunnablePlayer implements Runnable {

		@Override
		public void run() {
	        try {
	        	AudioInputStream audioStream = getAudioInputStream();
	            AudioFormat format = audioStream.getFormat();
		        DataLine.Info info = new DataLine.Info(Clip.class, format);
		        Clip clip = (Clip) AudioSystem.getLine(info);
		        clip.open(audioStream);
		        clip.start();
	        } catch (Exception e){
	            e.printStackTrace();
	            System.exit(1);
	        }

	        
		}
		
	}
}
