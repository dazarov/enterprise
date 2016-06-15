package com.musicbox;

import static com.musicbox.Utils.format;
import static com.musicbox.Utils.out;
import static com.musicbox.Utils.waitForCommand;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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


public class MusicBoxValidator {
	private static final int MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER = 4;
	private static final String SONG_LIST_FILE = "SongList.txt";

	private final MusicBoxCollection collection;
	
	private int fileNumbers = 0;
	private long totalLength = 0;
	private List<String> artistFolders = new ArrayList<>();
	
	MusicBoxValidator(MusicBoxCollection collection){
		this.collection = collection;
	}
	
	MusicBoxCollection getMusicCollection(){
		return collection;
	}
	
	void performeValidation(Writer log, Path root) throws IOException {
		LocalDateTime startDateTime = LocalDateTime.now();
		out(log, "\n\n################################    "+format(startDateTime)+"    ################################");
		out(log, "Validation...");
		
		if(Files.isDirectory(root)){
			//Files.walk(root).filter(p -> Files.isDirectory(p)).forEach(this::scanDirectory);
			Files.find(root, 20, (p,a) -> a.isDirectory()).forEach(this::scanDirectory);

			//Files.walk(root).filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".mp3")).forEach(p -> processFile(log, p));
			Files.find(root, 20, (p,a) -> a.isRegularFile() && p.toString().endsWith(".mp3")).forEach(p -> processFile(log, root, p));
		}else{
			out(log, root+" is not a folder!");
		}
		
		moveToSeparateFolder(log);
		
		createSongListFile(format(startDateTime));
		
		out(log, "Files processed - "+fileNumbers);
		long hours = totalLength/(60*60);
		long min = (totalLength%(60*60))/60;
		long sec = (totalLength%(60*60))%60;
		
		out(log, hours+":"+min+":"+sec);
		
		out(log, "Artists: "+collection.getNumberOFArtists());
		Duration duration = Duration.between(startDateTime, LocalDateTime.now());
		out(log, "Processing time: "+format(duration));
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
	
	private void createSongListFile(String dateString){
		try(BufferedWriter song_list = Files.newBufferedWriter(Paths.get(SONG_LIST_FILE))){
			out(song_list, dateString);
			collection.printAll(song_list);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void moveToSeparateFolder(Writer logFile) throws IOException{
		Collection<List<Path>> collection = this.collection.nonFolderArtists.values();
		for(List<Path> list : collection){
			if(list.size() > MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER){
				Path firstFile = list.get(0);
				Information fileNameInfo = parseFromFileName(logFile, firstFile.getFileName().toString());
				String newFolderName = fileNameInfo.artist;
				out(logFile, "Creating folder for new artist - "+newFolderName);
				Path dir = firstFile.getParent().getParent();
				Path newDir = Paths.get(dir.toString(), newFolderName);
				out(logFile, "Path for new folder - "+newDir.toString());
				if(!Files.exists(newDir)){
					Files.createDirectory(newDir);
					out(logFile, "Folder successfully created!");
					
					for(Path file : list){
						Path newFile = Paths.get(newDir.toString(), file.getFileName().toString());
						out(logFile, "Moving file into new filder - "+file.getFileName());
						Files.move(file, newFile);
						out(logFile, "File successfully moved!");
					}
				}
			}
		}
	}
	
	private void processFile(Writer logFile, Path root, Path file){
		fileNumbers++;
		
		String folderArtist = file.getParent().getFileName().toString();
		if(folderArtist.startsWith("All")){
			folderArtist = null;
		}
		
		String fileName = file.getFileName().toString();
		try {
		
			if(!Files.isWritable(file)){
				out(logFile, fileName+" File is read-only!");
				return;
			}
			
			Information fileNameInfo = parseFromFileName(logFile, fileName);
			if(fileNameInfo == null){
				collection.addSong(logFile, fileName, null, null);
				return;
			}
		
			collection.addSong(logFile, fileName, fileNameInfo.artist, fileNameInfo.title);
		
			AudioFile audioFile = AudioFileIO.read(file.toFile());
			Tag tag = audioFile.getTag();
			if(tag == null){
				out(logFile, fileName+" TAG is null! Creating a new one...");
				tag = audioFile.createDefaultTag();
				audioFile.setTag(tag);
				tag.setField(FieldKey.ARTIST, fileNameInfo.artist.trim());
				tag.setField(FieldKey.TITLE, fileNameInfo.title.trim());
				
				try {
					AudioFileIO.write(audioFile);
				} catch (CannotWriteException e) {
					out(logFile, e.getMessage());
					e.printStackTrace();
				}
				return;
			}
			
			AudioHeader header = audioFile.getAudioHeader();
			if(header == null){
				out(logFile, fileName+" HEADER is null!");
			}else{
				int trackLength = header.getTrackLength();
				totalLength += trackLength;
			}
			
			Information tagInfo = new Information(tag.getFirst(FieldKey.ARTIST), tag.getFirst(FieldKey.TITLE));
			
			if(!tagInfo.artist.equals(fileNameInfo.artist) || !tagInfo.title.equals(fileNameInfo.title) || (folderArtist != null && ((fileNameInfo.multiArtist && fileNameInfo.artist.indexOf(folderArtist) < 0) || (!fileNameInfo.multiArtist && !fileNameInfo.artist.equals(folderArtist)) ))){
				System.out.println("Which one is correct?");
				if((folderArtist != null && ((fileNameInfo.multiArtist && fileNameInfo.artist.indexOf(folderArtist) < 0) || (!fileNameInfo.multiArtist && !fileNameInfo.artist.equals(folderArtist)) ))){
					System.out.println("1. Folder name - "+folderArtist);
				}
				System.out.println("2. File name - "+fileName);
				System.out.println("3. Tags name - "+tagInfo.artist+" - "+tagInfo.title+".mp3");
				System.out.println("4. Skip");
				System.out.println("0. Exit");
				
				int input = waitForCommand("Enter:");
				
				System.out.println("Input - "+input);
				
				if(input == 1){
					fileNameInfo.artist = folderArtist;
					tagInfo.artist = folderArtist;
					tag.setField(FieldKey.ARTIST,fileNameInfo.artist.trim());
					try {
						AudioFileIO.write(audioFile);
					} catch (CannotWriteException e) {
						out(logFile, e.getMessage());
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
						out(logFile, e.getMessage());
						e.printStackTrace();
					}
				}else if(input == 3){
					Path newFile = Paths.get(file.getParent().toString(), tagInfo.artist.trim()+" - "+tagInfo.title.trim()+".mp3");
					Files.move(file, newFile);
				}else if(input == 0){
					System.exit(0);
				}
			}
			
			if(folderArtist == null){
				if(artistFolders.contains(fileNameInfo.artist.toLowerCase())){
					out(logFile, fileName+" Song in All forder, but there is a specific folder for this artist! Moving...");
					Path dir = findArtistFolder(root, fileNameInfo.artist.toLowerCase());
					if(dir != null){
						Path newFile = Paths.get(dir.toString(), fileNameInfo.artist.trim()+" - "+fileNameInfo.title.trim()+".mp3");
						Files.move(file, newFile);
						out(logFile, "File successfully moved!");
					}else{
						out(logFile, "Internal error - folder not found!!!!");
					}
				}
				if(collection.nonFolderArtists.containsKey(fileNameInfo.artist.toLowerCase())){
					List<Path> files = collection.nonFolderArtists.get(fileNameInfo.artist.toLowerCase());
					files.add(file);
					if(files.size() > MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER){
						out(logFile, fileName+" Song in All forder, but there are more then 4 songs of this artist!");
					}
				}else{
					List<Path> files = new ArrayList<>();
					files.add(file);
					collection.nonFolderArtists.put(fileNameInfo.artist.toLowerCase(), files);
				}
				
			}
			
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			out(logFile, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Information parseFromFileName(Writer logFile, String fileName){
		Information info = new Information();
		
		int delimeter = fileName.indexOf(" - ");
		int mp3Position = fileName.indexOf(".mp3");
		
		if(delimeter < 0 || mp3Position < 0){
			out(logFile, fileName+" Delimiter or .mp3 not found!");
			return null;
		}
		
		info.artist = fileName.substring(0, delimeter);
		info.multiArtist = info.artist.indexOf(" & ") >= 0 || info.artist.indexOf(" и ") >= 0; 
		info.title = fileName.substring(delimeter+3, mp3Position);
		
		if(info.artist.isEmpty()){
			out(logFile, fileName+" Artist is empty!");
			return null;
		}
		
		if(info.title.isEmpty()){
			out(logFile, fileName+" Title is empty!");
			return null;
		}
		
		return info;
	}
	
	private Path findArtistFolder(Path rootDir, String folderName){
		try {
			Optional<Path> result = Files.find(rootDir, 20, (p, a) -> p.getFileName().toString().toLowerCase().equals(folderName)).findFirst();
			if(result.isPresent()){
				return result.get();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
