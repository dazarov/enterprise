package com.musicbox;

import static com.musicbox.Utils.error;
import static com.musicbox.Utils.out;
import static com.musicbox.Utils.waitForCommand;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	private Map<String, FileInfo> filesInfo = new HashMap<>();
	
	void addSong(Writer logFile, String fileName, String artist, String title) throws IOException{
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
					Song existing = songs.get(titleId);
					out(logFile, fileName+" Song with similar name already exist - "+existing.fileName+"!");
					System.out.println("1 - Delete file - "+existing.fileName);
					System.out.println("2 - Delete file - "+fileName);
					System.out.println("3 - Skip");
					System.out.println("0 - Exit");
					int command = waitForCommand("Input:");
					if(command == 1){
						out(logFile, "Deleting the file "+existing.fileName);
						Files.delete(Paths.get(existing.fileName));
						out(logFile, "File successfully deleted!");
					}else if(command == 2){
						out(logFile, "Deleting the file "+fileName);
						Files.delete(Paths.get(fileName));
						out(logFile, "File successfully deleted!");
					}else if(command == 0){
						System.exit(0);
					}
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
	
	void collectInfo(Writer log, Path root, Path filePath) throws IOException{
		//System.out.println("collectInfo "+filePath);
		System.out.print(".");
		if(filesInfo.containsKey(filePath.getFileName().toString())){
			error(log, "\nFile: "+filePath+" already in the list! You should do normalizarion before synchronizing!");
		}else{
			filesInfo.put(filePath.getFileName().toString(),
				new FileInfo(
					root.relativize(filePath),
					Files.size(filePath)
				));
		}
	}
	
	Map<String, FileInfo> getFilesInfo(){
		return filesInfo;
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
