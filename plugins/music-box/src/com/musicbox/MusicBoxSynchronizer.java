package com.musicbox;

import static com.musicbox.Utils.out;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MusicBoxSynchronizer {
	private final MusicBoxCollection collection;
	
	MusicBoxSynchronizer(MusicBoxCollection collection){
		this.collection = collection;
	}
	
	MusicBoxCollection getMusicCollection(){
		return collection;
	}
	
	void performeSynchronization(Writer log, Path root, Path otherRoot) throws IOException{
		LocalDateTime startDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM);
		out(log, formatter.format(startDateTime));
		out(log, "Synchronization...");
		
		if(!Files.exists(otherRoot)){
			out(log, "Mobile path does not exist!");
			return;
		}
		
		out(log, "\nReading the main repository...");
		if(Files.isDirectory(root)){
			Files.find(root, 20, (p,a) -> a.isRegularFile() && p.toString().endsWith(".mp3")).forEach(p -> collectInfo(log, root, p));
		}
		
		out(log, "\nReading mobile device...");
		if(Files.isDirectory(otherRoot)){
			Files.find(otherRoot, 20, (p,a) -> a.isRegularFile() && p.toString().endsWith(".mp3")).forEach(p -> checkInfo(log, root, otherRoot, p));
		}
		
		out(log, "\nSynchronizing Roots...");
		synchronizeRoots(log, root, otherRoot);
		
		Duration duration = Duration.between(startDateTime, LocalDateTime.now());
		out(log, "Processing time: "+duration);
	}
	
	private void synchronizeRoots(Writer log, Path root, Path otherRoot){
		try {
			collection.synchronizeRoots(log, root, otherRoot);
		} catch (IOException e) {
			out(log, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void collectInfo(Writer log, Path root, Path filePath){
		try {
			collection.collectInfo(log, root, filePath);
		} catch (IOException e) {
			out(log, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void checkInfo(Writer log, Path root, Path otherRoot, Path filePath){
		try {
			collection.checkInfo(log, root, otherRoot, filePath);
		} catch (IOException e) {
			out(log, e.getMessage());
			e.printStackTrace();
		}
	}

}
