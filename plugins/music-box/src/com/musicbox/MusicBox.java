package com.musicbox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
	private ArrayList<String> artistFolders = new ArrayList<String>();
	private HashMap<String, List<File>> nonFolderArtists = new HashMap<String, List<File>>();
	private MusicCollection collection = new MusicCollection();
	
	private File root;
	
	public static void main(String[] args){
		MusicBox mb = new MusicBox();
		mb.performe();
	}
	
	public void performe(){
		System.out.println("-------------------- BEGIN --------------------------");
		
		root = new File(LINUX_PATH);
		//root = new File(TEST_PATH);
		if(!root.exists()){
			root = new File(WINDOWS_PATH);
		}
		if(root.isDirectory()){
			scanDirectory(root);
			
			processDirectory(root);
		}
		
		moveToSeparateFolder();
		
		System.out.println("-------------------- END --------------------------");
		System.out.println("Files processed - "+fileNumbers);
		long hours = totalLength/(60*60);
		long min = (totalLength%(60*60))/60;
		long sec = (totalLength%(60*60))%60;
		
		System.out.println(hours+":"+min+":"+sec);
		
		System.out.println("Artists: "+collection.getNumberOFArtists());
	}
	
	private void moveToSeparateFolder(){
		Collection<List<File>> collection = nonFolderArtists.values();
		for(List<File> list : collection){
			if(list.size() > MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER){
				File firstFile = list.get(0);
				Information fileNameInfo = parseFromFileName(firstFile.getName());
				String newFolderName = fileNameInfo.artist;
				System.out.println("Creating folder for new artist - "+newFolderName);
				File dir = firstFile.getParentFile().getParentFile();
				File newDir = new File(dir.getPath()+"/"+newFolderName);
				System.out.println("Path for new folder - "+newDir.getPath());
				if(newDir.mkdir()){
					System.out.println("Folder successfully created!");
					for(File file : list){
						File newFile = new File(newDir, file.getName());
						System.out.println("Moving file into new filder - "+file.getName());
						if(file.renameTo(newFile)){
							System.out.println("File successfully moved!");
						}else{
							System.out.println("Error while moving the file!");
						}
					}
				}else{
					System.out.println("Error while creating a folder!");
				}
			}
		}
	}
	
	private void scanDirectory(File directory){
		String artistFolder = null;
		if(!directory.getName().startsWith("All")){
			artistFolder = directory.getName();
			if(!artistFolders.contains(artistFolder.toLowerCase())){
				artistFolders.add(artistFolder.toLowerCase());
			}
		}
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				scanDirectory(file);
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
	
	
	private void processDirectory(File directory){
		String artistFolder = null;
		if(!directory.getName().startsWith("All")){
			artistFolder = directory.getName();
		}
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				processDirectory(file);
			}else if(file.getName().endsWith(".mp3")){
				processFile(file, artistFolder);
			}else{
				System.out.println(file.getName()+" Non mp3 file!");
			}
		}
	}
	
	private File findArtistFilder(File rootDir, String folderName){
		File[] subDirs = rootDir.listFiles();
		for(File dir : subDirs){
			if(dir.isDirectory()){
				if(dir.getName().toLowerCase().equals(folderName.toLowerCase())){
					return dir;
				}
				File result = findArtistFilder(dir, folderName);
				if(result != null){
					return result;
				}
			}
		}
		return null;
	}
	
	private void processFile(File file, String folderArtist){
		fileNumbers++;
		
		String fileName = file.getName();
		
		if(!file.canWrite()){
			System.out.println(fileName+" File is read-only!");
			return;
		}
		
		Information fileNameInfo = parseFromFileName(fileName);
		if(fileNameInfo == null){
			return;
		}
		
		collection.addSong(fileName, fileNameInfo.artist, fileNameInfo.title);
		
		try {
			AudioFile audioFile = AudioFileIO.read(file);
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
					File dir = findArtistFilder(root, fileNameInfo.artist.toLowerCase());
					if(dir != null){
						File newFile = new File(dir, fileNameInfo.artist.trim()+" - "+fileNameInfo.title.trim()+".mp3");
						if(file.renameTo(newFile)){
							System.out.println("File successfully moved!");
						}else{
							System.out.println("Error while moving the file!");
						}
					}else{
						System.out.println("Internal error - folder not found!!!!");
					}
				}
				if(nonFolderArtists.containsKey(fileNameInfo.artist.toLowerCase())){
					List<File> files = nonFolderArtists.get(fileNameInfo.artist.toLowerCase());
					files.add(file);
					if(files.size() > MAX_NUMBER_OF_FILES_IN_COMMON_FOLDER){
						System.out.println(fileName+" Song in All forder, but there are more then 4 songs of this artist!");
					}
				}else{
					List<File> files = new ArrayList<File>();
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
				System.out.print("Enter:");
				
				int input = -1;
				int ch;
				while ((ch = System.in.read ()) != '\n'){
					if((char)ch == '1'){
						input = 1;
					}else if((char)ch == '2'){
						input = 2;
					}else if((char)ch == '3'){
						input = 3;
					}else if((char)ch == '4'){
						input = 4;
					}else if((char)ch == '5'){
						input = 5;
					}else if((char)ch == '0'){
						input = 0;
					}
				}
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
					File newFile = new File(file.getParent(), tagInfo.artist.trim()+" - "+tagInfo.title.trim()+".mp3");
					file.renameTo(newFile);
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
					File newFile = new File(file.getParent(), tagInfo.artist.trim()+" - "+tagInfo.title.trim()+".mp3");
					file.renameTo(newFile);
				}else if(input == 0){
					System.exit(0);
				}
			}
			
			
		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
				| InvalidAudioFrameException e) {
			e.printStackTrace();
		}
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
