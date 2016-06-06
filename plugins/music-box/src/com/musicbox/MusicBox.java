package com.musicbox;

import static com.musicbox.Utils.waitForCommand;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class MusicBox {
	private static final String LINUX_PATH = "/home/daniel/Music/Music";
	private static final String WINDOWS_PATH = "C:/Users/Daniil/Music";
	private static final String UNIX_MOBILE_ROOT_PATH = "/run/user/1000/gvfs";
	//private static final String WINDOWS_MOBILE_ROOT_PATH = "This PC/Nexus 6/Internal storage";
	private static final String SD_CARD_PATH = "E:/Music";
	
	private static final String LOG_FILE = "MusicBox.log";
	
	private MusicBoxCollection collection = new MusicBoxCollection();
	private MusicBoxSynchronizer synchronizer = new MusicBoxSynchronizer(collection);
	private MusicBoxValidator validator = new MusicBoxValidator(collection);
	
	public static void main(String[] args){
		try(BufferedWriter log = Files.newBufferedWriter(Paths.get(LOG_FILE), StandardOpenOption.APPEND, StandardOpenOption.CREATE)){
			
			Path mobilePath = findMobilePath(log, UNIX_MOBILE_ROOT_PATH);
//			if(mobilePath == null){
//				mobilePath = findMobilePath(log, WINDOWS_MOBILE_ROOT_PATH);
//			}
			
			Path root = Paths.get(LINUX_PATH);
			if(!Files.exists(root)){
				root = Paths.get(WINDOWS_PATH);
			}
				
			MusicBox mb = new MusicBox();
			System.out.println("1. Validate Main Music Repository "+root);
			if(mobilePath != null){
				System.out.println("2. Validate Mobile Device Folder "+mobilePath);
				System.out.println("3. Sysnchronize Main Music Ripository with "+mobilePath);
			}
			System.out.println("4. Validate Mobile Device Folder "+SD_CARD_PATH);
			System.out.println("5. Sysnchronize Music Library with "+SD_CARD_PATH);
			System.out.println("0. Exit");
			
			int input = waitForCommand("Enter:");
			
			System.out.println("Input - "+input);
			if(input == 1){
				mb.validator.performeValidation(log, root);
			}else if(input == 2){
				mb.validator.performeValidation(log, mobilePath);
			}else if(input == 3){
				mb.synchronizer.performeSynchronization(log, root, mobilePath);
			}else if(input == 4){
				mb.validator.performeValidation(log, Paths.get(SD_CARD_PATH));
			}else if(input == 5){
				mb.synchronizer.performeSynchronization(log, root, Paths.get(SD_CARD_PATH));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static Path findMobilePath(Writer log, String pathString){
		try {
			Path path = Paths.get(pathString);
			if(Files.exists(path)){
				Optional<Path> result = Files.find(path, 20, (p, a) -> p.getFileName().toString().equals("Music")).findFirst();
				if(result.isPresent()){
					return result.get();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
