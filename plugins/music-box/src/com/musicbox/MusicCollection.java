package com.musicbox;

import static com.musicbox.MusicBox.out;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
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
	
	public void addSong(Writer logFile, String fileName, String artist, String title){
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
	
	public void printAll(Writer songList){
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
	
	public int getNumberOFArtists(){
		return artists.size();
	}
	
	Map<String, FileInfo> filesInfo = new HashMap<>();
	
	public void collectInfo(Writer log, Path filePath) throws IOException{
		Map<String, Object> attributes = Files.readAttributes(filePath, "creationTime");
		FileTime creationTime = (FileTime)attributes.get("creationTime");
		
		filesInfo.put(filePath.getFileName().toString(),
				new FileInfo(
					filePath.getFileName().toString(),
					filePath.getParent().toString(),
					creationTime
				));
	}
	
	public void checkInfo(Writer log, Path root, Path filePath) throws IOException{
		Map<String, Object> attributes = Files.readAttributes(filePath, "creationTime");
		FileTime creationTime = (FileTime)attributes.get("creationTime");
		FileInfo fileInfo = filesInfo.get(filePath.getFileName().toString());
		if(fileInfo != null){
			FileTime otherTime = fileInfo.creationTime;
			if(creationTime.compareTo(otherTime) > 0){
				// Copy file from mobile device
				out(log, "Copy file "+filePath.getFileName()+" from mobile device...");
			}
			filesInfo.remove(filePath.getFileName().toString());
		}else{
			// Copy file from mobile device
			out(log, "Copy file "+filePath.getFileName()+" from mobile device...");
		}
	}
	
	public void synchronizeRoots(Writer log, Path root, Path otherRoot) throws IOException{
		for(String fileName : filesInfo.keySet()){
			FileInfo info = filesInfo.get(fileName);
			out(log, "Copy file "+fileName+" to mobile device...");
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
	String fileName;
	String folderName;
	FileTime creationTime;
	
	public FileInfo(String fileName, String folderName, FileTime creationTime){
		this.fileName = fileName;
		this.folderName = folderName;
		this.creationTime = creationTime;
	}
}
