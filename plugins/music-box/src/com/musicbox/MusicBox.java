package com.musicbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class MusicBox {
	private static final int MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER = 4;
	private static final String LINUX_PATH = "/home/daniel/Music/Music";
	private static final String TEST_PATH = "/home/daniel/Music/Test";
	private static final String WINDOWS_PATH = "C:/Users/Daniil/Music";
	
	private int fileNumbers = 0;
	private long totalLength = 0;
	private ArrayList<String> artistFolders = new ArrayList<>();
	private HashMap<String, List<Path>> nonFolderArtists = new HashMap<>();
	private MusicCollection collection = new MusicCollection();
	//private Console console;
	
	private Path root;
	
	public static void main(String[] args){
		MusicBox mb = new MusicBox();
		mb.performe();
	}
	
	public void performe(){
		System.out.println("-------------------- BEGIN --------------------------");
		
		//console = System.console();
		//if(console == null){
		//	throw new RuntimeException("Console not available");
		//}
		
		root = Paths.get(LINUX_PATH);
		//root = new File(TEST_PATH);
		if(!Files.exists(root)){
			root = Paths.get(WINDOWS_PATH);
		}
		if(Files.isDirectory(root)){
			try {
				Files.walk(root).filter(p -> Files.isDirectory(p)).forEach(this::scanDirectory);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				Files.walk(root).filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".mp3")).forEach(this::processFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		moveToSeparateFolder();
		
		System.out.println("-------------------- END --------------------------");
		
		collection.printAll();
		
		
		System.out.println("Files processed - "+fileNumbers);
		long hours = totalLength/(60*60);
		long min = (totalLength%(60*60))/60;
		long sec = (totalLength%(60*60))%60;
		
		System.out.println(hours+":"+min+":"+sec);
		
		System.out.println("Artists: "+collection.getNumberOFArtists());
	}
	
	private void moveToSeparateFolder(){
		Collection<List<Path>> collection = nonFolderArtists.values();
		for(List<Path> list : collection){
			if(list.size() > MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER){
				Path firstFile = list.get(0);
				Information fileNameInfo = parseFromFileName(firstFile.getFileName().toString());
				String newFolderName = fileNameInfo.artist;
				System.out.println("Creating folder for new artist - "+newFolderName);
				Path dir = firstFile.getParent().getParent();
				Path newDir = Paths.get(dir.toString(), newFolderName);
				System.out.println("Path for new folder - "+newDir.toString());
				if(!Files.exists(newDir)){
					try {
						Files.createDirectory(newDir);
						System.out.println("Folder successfully created!");
					} catch (IOException e1) {
						System.out.println("Error while creating a folder!");
						e1.printStackTrace();
					}
					
					for(Path file : list){
						Path newFile = Paths.get(newDir.toString(), file.getFileName().toString());
						System.out.println("Moving file into new filder - "+file.getFileName());
						try {
							Files.move(file, newFile);
							System.out.println("File successfully moved!");
						} catch (IOException e) {
							System.out.println("Error while moving the file!");
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	private void scanDirectory(Path directory){
		String artistFolder = null;
		if(!directory.getFileName().startsWith("All")){
			artistFolder = directory.getFileName().toString();
			if(!artistFolders.contains(artistFolder.toLowerCase())){
				artistFolders.add(artistFolder.toLowerCase());
			}
		}
	}
	
	private Information parseFromFileName(String fileName){
		Information info = new Information();
		
		int delimeter = fileName.indexOf(" - ");
		int mp3Position = fileName.indexOf(".mp3");
		
		if(delimeter < 0 || mp3Position < 0){
			System.out.println(fileName+" Delimiter or .mp3 not found!");
			return null;
		}
		
		info.artist = fileName.substring(0, delimeter);
		info.multiArtist = info.artist.indexOf(" & ") >= 0 || info.artist.indexOf(" и ") >= 0; 
		info.title = fileName.substring(delimeter+3, mp3Position);
		
		if(info.artist.isEmpty()){
			System.out.println(fileName+" Artist is empty!");
			return null;
		}
		
		if(info.title.isEmpty()){
			System.out.println(fileName+" Title is empty!");
			return null;
		}
		
		return info;
	}
	
	private Path findArtistFilder(Path rootDir, String folderName){
		try {
			Optional<Path> result = Files.find(rootDir, 10, (p,a)->p.toString().toLowerCase().equals(folderName)).findFirst();
			if(result.isPresent()){
				return result.get();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void processFile(Path file){
		fileNumbers++;
		
		String folderArtist = file.getParent().getFileName().toString();
		if(folderArtist.startsWith("All")){
			folderArtist = null;
		}
		
		String fileName = file.getFileName().toString();
		
		if(!Files.isWritable(file)){
			System.out.println(fileName+" File is read-only!");
			return;
		}
		
		Information fileNameInfo = parseFromFileName(fileName);
		if(fileNameInfo == null){
			collection.addSong(fileName, null, null);
			return;
		}
		
		collection.addSong(fileName, fileNameInfo.artist, fileNameInfo.title);
		
		try {
			AudioFile audioFile = AudioFileIO.read(file.toFile());
			Tag tag = audioFile.getTag();
			if(tag == null){
				System.out.println(fileName+" TAG is null! Creating a new one...");
				tag = audioFile.createDefaultTag();
				audioFile.setTag(tag);
				tag.setField(FieldKey.ARTIST, fileNameInfo.artist.trim());
				tag.setField(FieldKey.TITLE, fileNameInfo.title.trim());
				
				try {
					AudioFileIO.write(audioFile);
				} catch (CannotWriteException e) {
					e.printStackTrace();
				}
				return;
			}
			
			AudioHeader header = audioFile.getAudioHeader();
			if(header == null){
				System.out.println(fileName+" HEADER is null!");
			}else{
				int trackLength = header.getTrackLength();
				totalLength += trackLength;
			}
			
			Information tagInfo = new Information(tag.getFirst(FieldKey.ARTIST), tag.getFirst(FieldKey.TITLE));
			
			if(folderArtist == null){
				if(artistFolders.contains(fileNameInfo.artist.toLowerCase())){
					System.out.println(fileName+" Song in All forder, but there is a specific folder for this artist! Moving...");
					Path dir = findArtistFilder(root, fileNameInfo.artist.toLowerCase());
					if(dir != null){
						Path newFile = Paths.get(dir.toString(), fileNameInfo.artist.trim()+" - "+fileNameInfo.title.trim()+".mp3");
						Files.move(file, newFile);
						System.out.println("File successfully moved!");
						//System.out.println("Error while moving the file!");
					}else{
						System.out.println("Internal error - folder not found!!!!");
					}
				}
				if(nonFolderArtists.containsKey(fileNameInfo.artist.toLowerCase())){
					List<Path> files = nonFolderArtists.get(fileNameInfo.artist.toLowerCase());
					files.add(file);
					if(files.size() > MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER){
						System.out.println(fileName+" Song in All forder, but there are more then 4 songs of this artist!");
					}
				}else{
					List<Path> files = new ArrayList<>();
					files.add(file);
					nonFolderArtists.put(fileNameInfo.artist.toLowerCase(), files);
				}
				
			}
			
			if(!tagInfo.artist.equals(fileNameInfo.artist) || !tagInfo.title.equals(fileNameInfo.title) || (folderArtist != null && ((fileNameInfo.multiArtist && fileNameInfo.artist.indexOf(folderArtist) < 0) || (!fileNameInfo.multiArtist && !fileNameInfo.artist.equals(folderArtist)) ))){
				System.out.println("Which one is correct?");
				if((folderArtist != null && ((fileNameInfo.multiArtist && fileNameInfo.artist.indexOf(folderArtist) < 0) || (!fileNameInfo.multiArtist && !fileNameInfo.artist.equals(folderArtist)) ))){
					System.out.println("1. Folder name - "+folderArtist);
				}
				System.out.println("2. File name - "+fileName);
				System.out.println("3. Tags name - "+tagInfo.artist+" - "+tagInfo.title+".mp3");
				System.out.println("4. Skip");
				System.out.println("0. Exit");
				String value = readLine("Enter:");
				
				for(char ch : value.toCharArray()){
					if(!Character.isDigit(ch)){
						value = "0";
						break;
					}
				}
				
				int input = Integer.parseInt(value);

				System.out.println("Input - "+input);
				
				if(input == 1){
					fileNameInfo.artist = folderArtist;
					tagInfo.artist = folderArtist;
					tag.setField(FieldKey.ARTIST,fileNameInfo.artist.trim());
					try {
						AudioFileIO.write(audioFile);
					} catch (CannotWriteException e) {
						e.printStackTrace();
					}
					Path newFile = Paths.get(file.getParent().toString(), tagInfo.artist.trim()+" - "+tagInfo.title.trim()+".mp3");
					Files.move(file, newFile);
				}else if(input == 2){
					//tag = audioFile.createDefaultTag();
					//audioFile.setTag(tag);
					tag.setField(FieldKey.ARTIST,fileNameInfo.artist.trim());
					tag.setField(FieldKey.TITLE,fileNameInfo.title.trim());
					
					try {
						AudioFileIO.write(audioFile);
					} catch (CannotWriteException e) {
						e.printStackTrace();
					}
				}else if(input == 3){
					Path newFile = Paths.get(file.getParent().toString(), tagInfo.artist.trim()+" - "+tagInfo.title.trim()+".mp3");
					Files.move(file, newFile);
				}else if(input == 0){
					System.exit(0);
				}
			}
			
			
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			e.printStackTrace();
		}
	}
	
	private String readLine(String format, Object... args) throws IOException {
	    if (System.console() != null) {
	    	System.console().flush();
	        return System.console().readLine(format, args);
	    }
	    System.out.print(String.format(format, args));
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));
	    return reader.readLine();
	}
	
	static class Information{
		String artist;
		String title;
		boolean multiArtist;
		
		public Information(String artist, String title){
			this.artist = artist;
			this.title = title;
		}
		
		public Information(){
			
		}
	}
}
