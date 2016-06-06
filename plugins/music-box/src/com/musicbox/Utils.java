package com.musicbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class Utils {
	static final int WAITER_ID_SYNCHRONIZE_ROOTS = 1;
	
	static void out(Writer logFile, String message){
		System.out.println(message);
		try {
			logFile.write(message+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static String readLine(String format, Object... args) throws IOException {
	    if (System.console() != null) {
	    	System.console().flush();
	        return System.console().readLine(format, args);
	    }
	    System.out.print(String.format(format, args));
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));
	    return reader.readLine();
	}

	static int waitForCommand(String prompt) throws IOException {
		String value;
		WHILE_LOOP:
		while(true){
			value = readLine("Enter:");
			for(char ch : value.toCharArray()){
				if(!Character.isDigit(ch)){
					continue WHILE_LOOP;
				}
			}
			break WHILE_LOOP;
		}
		return Integer.parseInt(value);
	}
	
	static int lastCommand = -1;
	static int lastID = -1;
	
	static int getCommand(String prompt, int waiterID) throws IOException {
		if(lastID == waiterID && lastID != -1){
			return lastCommand;
		}else{
			lastCommand = -1;
			lastID = -1;
			int command = waitForCommand(prompt);
			if(command%2 != 0){
				lastCommand = command-1;
				lastID = waiterID;
				return lastCommand;
			}else{
				return command;
			}
		}
	}
	
	/**
	 * Moves file within the same root folder path
	 * 
	 * @param root
	 * @param source
	 * @param newRelativePath
	 * @throws IOException
	 */
	static void move(Path root, Path source, Path newRelativePath) throws IOException{
		Path folderPath = root.resolve(newRelativePath);
		if(!Files.exists(folderPath)){
			Files.createDirectories(folderPath);
		}
		Files.move(source, folderPath);
		
	}
	
	static void copy(Path sourceRoot, Path sourceFile, Path targetRoot, boolean replace) throws IOException{
		if(!sourceFile.isAbsolute()){
			sourceFile = sourceRoot.resolve(sourceFile);
		}
		Path copyToPath = targetRoot.resolve(sourceRoot.relativize(sourceFile));
		Path folderPath = copyToPath.getParent();
		if(!Files.exists(folderPath)){
			Files.createDirectories(folderPath);
		}
		if(replace){
			Files.copy(sourceFile, copyToPath, StandardCopyOption.REPLACE_EXISTING);
		}else{
			Files.copy(sourceFile, copyToPath);
		}

	}
	
	static String format(Duration duration){
		long seconds = duration.getSeconds();
        long hours = seconds / (60 * 60);
        int minutes = (int) ((seconds % (60 * 60)) / 60);
        int secs = (int) (seconds % 60);
        return ""+hours+":"+minutes+":"+secs;
	}

}
