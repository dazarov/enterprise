package com.musicbox;

import static com.musicbox.Utils.WAITER_ID_CHECK_INFO_1;
import static com.musicbox.Utils.WAITER_ID_CHECK_INFO_2;
import static com.musicbox.Utils.WAITER_ID_SYNCHRONIZE_ROOTS;
import static com.musicbox.Utils.copy;
import static com.musicbox.Utils.format;
import static com.musicbox.Utils.getCommand;
import static com.musicbox.Utils.move;
import static com.musicbox.Utils.out;
import static com.musicbox.Utils.printErrors;
import static com.musicbox.Utils.waitForCommand;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

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
		out(log, "\n\n################################    "+format(startDateTime)+"    ################################");
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
		try {
			Map<String, FileInfo> filesInfo = collection.getFilesInfo();
			synchronizeRoots(log, filesInfo, root, otherRoot);
		} catch (IOException e) {
			out(log, e.getMessage());
			e.printStackTrace();
		}
		
		printErrors();
		
		Duration duration = Duration.between(startDateTime, LocalDateTime.now());
		out(log, "\nDone.");
		out(log, "Processing time: "+format(duration));
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
			Map<String, FileInfo> filesInfo = collection.getFilesInfo();
			checkInfo(log, filesInfo, root, otherRoot, filePath);
		} catch (IOException e) {
			out(log, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void checkInfo(Writer log, Map<String, FileInfo> filesInfo, Path root, Path mobileRoot, Path filePath) throws IOException{
		//System.out.println("checkInfo "+filePath);
		System.out.print(".");
		
		FileInfo fileInfo = filesInfo.get(filePath.getFileName().toString());
		if(fileInfo != null){
			long size = Files.size(filePath);
			Path relativePath = mobileRoot.relativize(filePath);
			if(!fileInfo.filePath.equals(relativePath)){
				if(size != fileInfo.size){
					// Ask what to do
					System.out.println("\nFile: "+filePath);
					System.out.println("File size is different from the file size in the main repository (MR)!");
					//System.out.println("File location is different from the file location in MR!");
					System.out.println("1 - Correct file in MR - Delete file on MD and Replace file on MD with file from MR");
					System.out.println("2 - Correct file and location on Mobile Device (MD) - Replace file in MR with file from MD");
					System.out.println("4 - Skip");
					System.out.println("0 - Exit");
					int command = waitForCommand("Input:");
					if (command == 0) {
                      System.exit(0);
                    } else if(command == 1){ // Correct file in MR - Delete local file and Copy file from MR to MD
						out(log, "Deleting the file "+filePath);
						Files.delete(filePath);
						out(log, "File successfully deleted!");
						
						out(log, "Copy file "+filePath.getFileName()+" --------> to mobile device...");
						copy(root, fileInfo.filePath, mobileRoot, false);
						out(log, "File successfully copied!");
					}else if(command == 2){ // Correct file and location on Mobile Device (MD) - Replace file in MR with file from MD
						Path p = root.resolve(fileInfo.filePath);
						out(log, "Deleting the file "+p);
						Files.delete(p);
						out(log, "File successfully deleted!");
						
						out(log, "Copy file "+filePath.getFileName()+" <------ to the main repository...");
						copy(mobileRoot, filePath, root, false);
						out(log, "File successfully copied!");
					}
				}else{
					// Ask to move
					System.out.println("\nFile: "+filePath);
					System.out.println("File location is different from the file location in MR!");
					System.out.println("1 - Move file to the correct location");
					System.out.println("2 - Move ALL such files to the correct location");
					System.out.println("3 - Delete file");
					System.out.println("4 - Delete ALL such files");
					System.out.println("5 - Skip");
					System.out.println("6 - Skip All");
					System.out.println("0 - Exit");
					int command = getCommand("Input:", WAITER_ID_CHECK_INFO_1);
					System.out.println("Command - "+command);
					if(command == 0) {
					  System.exit(0);
					} else if(command == 1) { // Move file to the correct location
						out(log, "Move file "+filePath.getFileName()+" to the other folder");
						move(mobileRoot, filePath, fileInfo.filePath.getParent());
						out(log, "File successfully moved!");
					} else if(command == 3) { // Delete file
						out(log, "Deleting file "+filePath.getFileName());
						Files.delete(filePath);
						out(log, "File successfully deleted!");
					}
				}
			}else if(size != fileInfo.size){
				System.out.println("\nFile: "+filePath);
				System.out.println("File size is different from the file size in the main repository!");
				System.out.println("1 - Copy file to mobile device");
				System.out.println("2 - Copy file to the main repository");
				System.out.println("3 - Skip");
				System.out.println("0 - Exit");
				int command = waitForCommand("Input:");
				if(command == 0) {
                  System.exit(0);
                } else if(command == 1){ // Copy file to mobile device
					out(log, "Copy file "+filePath.getFileName()+" --------> to mobile device...");
					
					copy(root, fileInfo.filePath, mobileRoot, true);
					
					out(log, "File successfully copied!");
				}else if(command == 2){ // Copy file to the main repository
					out(log, "Copy file "+filePath.getFileName()+" <------ to the main repository...");
					
					copy(mobileRoot, filePath, root, true);
					
					out(log, "File successfully copied!");
				}
			}
			
			filesInfo.remove(filePath.getFileName().toString());
		}else{
			System.out.println("\nFile: "+filePath);
			System.out.println("File is not found in the main repository!");
			System.out.println("1 - Copy file to the main repository");
			System.out.println("2 - Copy ALL such files to the main repository");
			System.out.println("3 - Delete local file");
			System.out.println("4 - Delete ALL such files");
			System.out.println("5 - Skip");
			System.out.println("6 - Skip ALL");
			System.out.println("0 - Exit");
			int command = getCommand("Input:", WAITER_ID_CHECK_INFO_2);
			System.out.println("Command - "+command);
			if(command == 0){
              System.exit(0);
            } else if(command == 1){  // Copy file from mobile device
				out(log, "Copy file "+filePath.getFileName()+" <------ to the main repository...");
				
				copy(mobileRoot, filePath, root, false);
				
				out(log, "Folder successfully copied!");
			}else if(command == 3){ // Delete the file
				out(log, "Deleting the file "+filePath);
				Files.delete(filePath);
				out(log, "File successfully deleted!");
			}
		}
	}
	
	void synchronizeRoots(Writer log, Map<String, FileInfo> filesInfo, Path root, Path mobileRoot) throws IOException{
		for(String fileName : filesInfo.keySet()){
			FileInfo info = filesInfo.get(fileName);
			System.out.println("File: "+info.filePath);
			System.out.println("File is not found on Mobile Device!");
			System.out.println("1 - Copy file to the Mobile Device");
			System.out.println("2 - Copy ALL such files to the Mobile Device");
			System.out.println("3 - Delete file from the Main Repository");
			System.out.println("4 - Delete All such files from the Main Repository");
			System.out.println("5 - Skip");
			System.out.println("6 - Skip ALL");
			System.out.println("0 - Exit");
			int command = getCommand("Input:", WAITER_ID_SYNCHRONIZE_ROOTS);
			System.out.println("Command - "+command);
			if (command == 0) {
              System.exit(0);
            } else if(command == 1){
				out(log, "Copy file "+info.filePath+" ------> to mobile device...");
				
				copy(root, info.filePath, mobileRoot, false);
				
				out(log, "File successfully copied!");
			}else if(command == 3){
				out(log, "Deleting the file "+info.filePath);
				Files.delete(info.filePath);
				out(log, "File successfully deleted!");
			} 
		}
	}

}
