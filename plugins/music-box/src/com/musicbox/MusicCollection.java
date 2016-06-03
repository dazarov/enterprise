package com.musicbox;

import static com.musicbox.MusicBox.out;
import static com.musicbox.MusicBox.waitForCommand;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class MusicCollection {
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
	
	void collectInfo(Writer log, Path filePath) throws IOException{
		//System.out.println("collectInfo "+filePath);
		if(filesInfo.containsKey(filePath.getFileName().toString())){
			out(log, "File: "+filePath+" already in the list! You should do normalizarion before synchronizing!");
		}else{
			filesInfo.put(filePath.getFileName().toString(),
				new FileInfo(
					filePath,
					Files.size(filePath)
				));
		}
	}
	
	void checkInfo(Writer log, Path root, Path otherRoot, Path filePath) throws IOException{
		//System.out.println("checkInfo "+filePath);
		long size = Files.size(filePath);
		FileInfo fileInfo = filesInfo.get(filePath.getFileName().toString());
		if(fileInfo != null){
			if(size != fileInfo.size){
				System.out.println("File: "+filePath);
				System.out.println("File size is different from the file size in the main repository!");
				System.out.println("1 - Copy file to mobile device");
				System.out.println("2 - Copy file to the main repository");
				System.out.println("3 - Skip");
				System.out.println("0 - Exit");
				int command = waitForCommand("Input:");
				if(command == 1){ // Copy file to mobile device
					out(log, "Copy file "+filePath.getFileName()+" --------> to mobile device...");
					Path copyToPath = otherRoot.resolve(root.relativize(fileInfo.filePath.getParent()));
					if(!Files.exists(copyToPath)){
						Files.createDirectories(copyToPath);
					}
					Files.copy(fileInfo.filePath, copyToPath.resolve(fileInfo.filePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
					out(log, "File successfully copied!");
				}else if(command == 2){ // Copy file to the main repository
					out(log, "Copy file "+filePath.getFileName()+" <------ to the main repository...");
					Path copyToPath = root.resolve(otherRoot.relativize(filePath.getParent()));
					if(!Files.exists(copyToPath)){
						Files.createDirectories(copyToPath);
					}
					Files.copy(filePath, copyToPath.resolve(filePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
					out(log, "File successfully copied!");
				}
			}
			filesInfo.remove(filePath.getFileName().toString());
		}else{
			System.out.println("File: "+filePath);
			System.out.println("File is not found in the main repository!");
			System.out.println("1 - Copy file to the main repository");
			System.out.println("2 - Delete local file");
			System.out.println("3 - Skip");
			System.out.println("0 - Exit");
			int command = waitForCommand("Input:");
			if(command == 1){  // Copy file from mobile device
				out(log, "Copy file "+filePath.getFileName()+" <------ to the main repository...");
				Path copyToPath = root.resolve(otherRoot.relativize(filePath.getParent()));
				if(!Files.exists(copyToPath)){
					Files.createDirectories(copyToPath);
				}
				Files.copy(filePath, copyToPath.resolve(filePath.getFileName())/*, StandardCopyOption.COPY_ATTRIBUTES*/); // Operation not supported
				out(log, "Folder successfully copied!");
			}else if(command == 2){ // Delete the file
				out(log, "Deleting the file "+filePath);
				Files.delete(filePath);
				out(log, "File successfully deleted!");
			}else if(command == 0){
				System.exit(0);
			}
			
		}
	}
	
	void synchronizeRoots(Writer log, Path root, Path otherRoot) throws IOException{
		System.out.println("synchronizeRoots... ");
		for(String fileName : filesInfo.keySet()){
			FileInfo info = filesInfo.get(fileName);
			out(log, "Copy file "+info.filePath+" ------> to mobile device...");
			Path copyToPath = otherRoot.resolve(root.relativize(info.filePath.getParent()));
			System.out.println("Copy To Path - "+copyToPath);
			if(!Files.exists(copyToPath)){
				Files.createDirectories(copyToPath);
			}
			Files.copy(info.filePath, copyToPath.resolve(info.filePath.getFileName())/*, StandardCopyOption.COPY_ATTRIBUTES*/); // Operation not supported
			out(log, "File successfully copied!");
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
