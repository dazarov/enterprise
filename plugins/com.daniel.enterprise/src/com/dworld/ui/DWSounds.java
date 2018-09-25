package com.dworld.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.dworld.core.DWConfiguration;

public enum DWSounds {
	SHOT("resources/sounds/shot.wav"),
	CANNON_SHOT("resources/sounds/cannon_shot.wav"),
	ROCKET_LAUNCH("resources/sounds/rocket_launch.wav"),
	STEP("resources/sounds/step.wav"),
	DDOR("resources/sounds/door.wav"),
	EXPLOSION("resources/sounds/explosion.wav");
	
	private final int BUFFER_SIZE = 128000;
	
	private String fileName;
	
	private Thread thread;

	
	DWSounds(String fileName){
		this.fileName = fileName;
	}
	
	public void playSound(){
		if(DWConfiguration.getInstance().isSoundEnabled()){
			if(thread == null || thread.getState() == Thread.State.TERMINATED){
				thread = new Thread(new RunnablePlayer(), "Sound Player " + name());
				thread.start();
			}
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
			AudioInputStream audioStream = null;
	        try {
	            audioStream = getAudioInputStream();
	        } catch (Exception e){
	            e.printStackTrace();
	            System.exit(1);
	        }

	        AudioFormat audioFormat = audioStream.getFormat();

	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	        SourceDataLine sourceLine = null;
	        try {
	            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
	            sourceLine.open(audioFormat);
	        } catch (LineUnavailableException e) {
	            e.printStackTrace();
	            System.exit(1);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }

	        sourceLine.start();

	        int nBytesRead = 0;
	        byte[] abData = new byte[BUFFER_SIZE];
	        while (nBytesRead != -1) {
	            try {
	                nBytesRead = audioStream.read(abData, 0, abData.length);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            if (nBytesRead >= 0) {
	                @SuppressWarnings("unused")
	                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
	            }
	        }

	        sourceLine.drain();
	        sourceLine.close();
		}
		
	}
}
