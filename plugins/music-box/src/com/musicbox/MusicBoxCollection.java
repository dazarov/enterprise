package com.musicbox;

import static com.musicbox.Utils.copy;
import static com.musicbox.Utils.move;
import static com.musicbox.Utils.out;
import static com.musicbox.Utils.waitForCommand;
import static com.musicbox.Utils.getCommand;
import static com.musicbox.Utils.WAITER_ID_SYNCHRONIZE_ROOTS;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class MusicBoxCollection {
	private Map<String, Map<String, Song>> artists = new TreeMap<>();
	private Set<String> nonArtists = new TreeSet<>();
	public Map<String, List<Path>> nonFolderArtists = new HashMap<>();
	
	void addSong(Writer logFile, String fileName, String artist, String title){
		if(artist == null || title == null || artist.isEmpty() || title.isEmpty()){
			nonArtists.add(fileName);
		} else {
			Song song = new Song(fileName, artist, title);
			
			String artistId = getId(artist);
			String titleId = getId(title);
			
			Map<String, Song> songs = artists.get(artistId);
			if(songs == null){
				songs = new TreeMap<String, Song>();
				songs.put(titleId, song);
				artists.put(artistId, songs);
			}else{
				if(songs.containsKey(titleId)){
					out(logFile, fileName+" Song with this name already exist!");
				}else{
					songs.put(titleId, song);
				}
			}
		}
	}
	
//	byte[] getHash(Path filePath) throws IOException, NoSuchAlgorithmException{
//		byte[] content = Files.readAllBytes(filePath);
//		MessageDigest md = MessageDigest.getInstance("MD2"); // MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
//		return md.digest(content);
//	}
	
	private String getId(String name){
		return name.toLowerCase()
				.replace(".", "")
				.replace(",", "")
				.replace("'", "")
				.replace("-", "")
				.replace("(", "")
				.replace(")", "")
				.replace("  ", " ")
				.replace("  ", " ");
	}
	
	void printAll(Writer songList){
		AtomicInteger total = new AtomicInteger(1);
		int artCount = 1;
		for(String artist : artists.keySet()){
			Map<String, Song> map = artists.get(artist);
			int count = 1;
			boolean firstSong = true;
			for(String title : map.keySet()){
				Song song = map.get(title);
				if(firstSong){
					out(songList, "-------   "+artCount+". "+song.artist.toUpperCase()+"   --------");
					firstSong = false;
				}
				out(songList, total+". "+count+". "+song.title);
				count++;
				total.incrementAndGet();
			}
			artCount++;
		}
		
		out(songList, "-------   Non Artists Songs   --------");
		nonArtists.forEach(s -> out(songList, total.getAndIncrement()+". "+s));
	}
	
	int getNumberOFArtists(){
		return artists.size();
	}
	
	private Map<String, FileInfo> filesInfo = new HashMap<>();
	
	void collectInfo(Writer log, Path root, Path filePath) throws IOException{
		//System.out.println("collectInfo "+filePath);
		System.out.print(".");
		if(filesInfo.containsKey(filePath.getFileName().toString())){
			out(log, "\nFile: "+filePath+" already in the list! You should do normalizarion before synchronizing!");
		}else{
			filesInfo.put(filePath.getFileName().toString(),
				new FileInfo(
					root.relativize(filePath),
					Files.size(filePath)
				));
		}
	}
	
	void checkInfo(Writer log, Path root, Path mobileRoot, Path filePath) throws IOException{
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
					System.out.println("File location is different from the file location in MR!");
					System.out.println("1 - Correct file in MR - Delete file on MD and Replace file on MD with file from MR");
					System.out.println("2 - Correct file and location on Mobile Device (MD) - Replace file in MR with file from MD");
					System.out.println("4 - Skip");
					System.out.println("0 - Exit");
					int command = waitForCommand("Input:");
					if(command == 1){ // Correct file in MR - Delete local file and Copy file from MR to MD
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
					}if(command == 0){
						System.exit(0);
					}
				}else{
					// Ask to move
					System.out.println("\nFile: "+filePath);
					System.out.println("File location is different from the file location in MR!");
					System.out.println("1 - Move file to the correct location");
					System.out.println("2 - Skip");
					System.out.println("0 - Exit");
					int command = waitForCommand("Input:");
					if(command == 1){ // Move file to the correct location
						out(log, "Move file "+filePath.getFileName()+" to the other folder");
						move(mobileRoot, filePath, fileInfo.filePath.getParent());
						out(log, "File successfully moved!");
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
				if(command == 1){ // Copy file to mobile device
					out(log, "Copy file "+filePath.getFileName()+" --------> to mobile device...");
					
					copy(root, fileInfo.filePath, mobileRoot, true);
					
					out(log, "File successfully copied!");
				}else if(command == 2){ // Copy file to the main repository
					out(log, "Copy file "+filePath.getFileName()+" <------ to the main repository...");
					
					copy(mobileRoot, filePath, root, true);
					
					out(log, "File successfully copied!");
				}else if(command == 0){
					System.exit(0);
				}
			}
			
			filesInfo.remove(filePath.getFileName().toString());
		}else{
			System.out.println("\nFile: "+filePath);
			System.out.println("File is not found in the main repository!");
			System.out.println("1 - Copy file to the main repository");
			System.out.println("2 - Delete local file");
			System.out.println("3 - Skip");
			System.out.println("0 - Exit");
			int command = waitForCommand("Input:");
			System.out.println("Command - "+command);
			if(command == 1){  // Copy file from mobile device
				out(log, "Copy file "+filePath.getFileName()+" <------ to the main repository...");
				
				//copy(mobileRoot, filePath, root, false);
				
				out(log, "Folder successfully copied!");
			}else if(command == 2){ // Delete the file
				out(log, "Deleting the file "+filePath);
				//Files.delete(filePath);
				out(log, "File successfully deleted!");
			}else if(command == 0){
				System.exit(0);
			}
			
		}
	}
	
	void synchronizeRoots(Writer log, Path root, Path mobileRoot) throws IOException{
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
			if(command == 1){
				out(log, "Copy file "+info.filePath+" ------> to mobile device...");
				
				copy(root, info.filePath, mobileRoot, false);
				
				out(log, "File successfully copied!");
			}else if(command == 3){
				out(log, "Deleting the file "+info.filePath);
				Files.delete(info.filePath);
				out(log, "File successfully deleted!");
			}else if(command == 0){
				System.exit(0);
			}
			
			
		}
	}
}

class Song{
	String fileName;
	String artist;
	String title;
	
	public Song(String fileName, String artist, String title){
		this.fileName = fileName;
		this.artist = artist;
		this.title = title;
	}
}

class FileInfo{
	Path filePath;
	long size;
	
	public FileInfo(Path filePath, long size){
		this.filePath = filePath;
		this.size = size;
	}
}
