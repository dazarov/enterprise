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

import com.musicbox.MusicBoxValidator.SongFileInformation;

public class MusicBoxCollection {
	private Map<String, Map<String, SongFileInformation>> artists = new TreeMap<>();
	private Set<String> nonArtists = new TreeSet<>();
	public Map<String, List<Path>> nonFolderArtists = new HashMap<>();
	private Map<String, FileInfo> filesInfo = new HashMap<>();
	
	void addSong(Writer logFile, SongFileInformation song) throws IOException{
		if(song.getAllArtists() == null || song.getTitle() == null || song.getAllArtists().isEmpty() || song.getTitle().isEmpty()){
			nonArtists.add(song.getFileName());
		} else {
			for(String artist : song.getArtists()) {
			
				String artistId = getId(artist);
				String titleId = getId(song.getTitle());
				
				Map<String, SongFileInformation> songs = artists.get(artistId);
				if(songs == null){
					songs = new TreeMap<String, SongFileInformation>();
					songs.put(titleId, song);
					artists.put(artistId, songs);
				}else{
					if(songs.containsKey(titleId)){
						SongFileInformation existing = songs.get(titleId);
						out(logFile, song.getFileName()+" Song with similar name already exist - "+existing.getFileName()+"!");
						System.out.println("1 - Delete file - "+existing.getFileName());
						System.out.println("2 - Delete file - "+song.getFileName());
						System.out.println("3 - Skip");
						System.out.println("0 - Exit");
						int command = waitForCommand("Input:");
						if(command == 1){
							out(logFile, "Deleting the file "+existing.getFileName());
							Files.delete(Paths.get(existing.getFileName()));
							out(logFile, "File successfully deleted!");
						}else if(command == 2){
							out(logFile, "Deleting the file "+song.getFileName());
							Files.delete(Paths.get(song.getFileName()));
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
	
	void validateArtists(Writer logFile){
		for(String artistId : artists.keySet()){
			String artistName = null;
			Map<String, SongFileInformation> songs = artists.get(artistId);
			for(String title : songs.keySet()){
				boolean found = false;
				SongFileInformation song = songs.get(title);
				for(String artist : song.getArtists()) {
					if(artistName == null && getId(artist).equals(artistId)){
						found = true;
						artistName = artist;
					}else if(getId(artist).equals(artistId)){
						if(!artistName.equals(artist)){
							error(logFile, "Wrong Artist Name " + artist + " or " + artistName);
						}else{
							found = true;
						}
					}
					
				}
				if(!found) {
					error(logFile, "Song in a wrong folder " + song.getFileName());
				}
			}
		}
	}
	
	void printAll(Writer songList){
		AtomicInteger total = new AtomicInteger(1);
		int artCount = 1;
		for(String artist : artists.keySet()){
			Map<String, SongFileInformation> songs = artists.get(artist);
			int count = 1;
			boolean firstSong = true;
			for(String title : songs.keySet()){
				SongFileInformation song = songs.get(title);
				if(firstSong){
					out(songList, "-------   "+artCount+". "+song.getAllArtists().toUpperCase()+"   --------");
					firstSong = false;
				}
				out(songList, total+". "+count+". "+song.getTitle());
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

//class Song{
//	String fileName;
//	String artist;
//	String title;
//	
//	public Song(String fileName, String artist, String title){
//		this.fileName = fileName;
//		this.artist = artist;
//		this.title = title;
//	}
//}

class FileInfo{
	Path filePath;
	long size;
	
	public FileInfo(Path filePath, long size){
		this.filePath = filePath;
		this.size = size;
	}
}
