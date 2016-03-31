package com.musicbox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
	static int fileNumbers = 0;
	static long totalLength = 0;
	static ArrayList<String> artistFolders = new ArrayList<String>();
	static HashMap<String, Integer> nonFolderArtists = new HashMap<String, Integer>();
	
	public static void main(String[] args){
		String path = "/home/daniel/Music/Music";
		
		System.out.println("-------------------- BEGIN --------------------------");
		
		File file = new File(path);
		if(file.isDirectory()){
			scanDirectory(file);
			
			processDirectory(file);
		}
		
		System.out.println("-------------------- END --------------------------");
		System.out.println("Files processed - "+fileNumbers);
		long hours = totalLength/(60*60);
		long min = (totalLength%(60*60))/60;
		long sec = (totalLength%(60*60))%60;
		
		System.out.println(hours+":"+min+":"+sec);
	}
	
	private static void scanDirectory(File directory){
		String artistFolder = null;
		if(!"All".equals(directory.getName())){
			artistFolder = directory.getName();
			if(!artistFolders.contains(artistFolder)){
				artistFolders.add(artistFolder);
			}
		}
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				scanDirectory(file);
			}
		}
	}
	
	
	private static void processDirectory(File directory){
		String artistFolder = null;
		if(!"All".equals(directory.getName())){
			artistFolder = directory.getName();
		}
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				processDirectory(file);
			}else if(file.getName().endsWith(".mp3")){
				processFile(file, artistFolder);
			}else{
				System.out.println(file.getName()+" ############################## Non mp3 file!");
			}
		}
	}
	
	private static void processFile(File file, String folderArtist){
		fileNumbers++;
		
		String fileName = file.getName();
		
		if(!file.canWrite()){
			System.out.println(fileName+" ################# File is read-only!");
		}
		
		int delimeter = fileName.indexOf(" - ");
		int mp3Position = fileName.indexOf(".mp3");
		
		if(delimeter < 0 || mp3Position < 0){
			System.out.println(fileName+" ################### Delimiter or .mp3 not found!");
			return;
		}
		
		String fileArtist = fileName.substring(0, delimeter);
		boolean multiArtist = fileArtist.indexOf(" & ") >= 0 || fileArtist.indexOf(" и ") >= 0; 
		String fileTitle = fileName.substring(delimeter+3, mp3Position);
		if(fileArtist.isEmpty()){
			System.out.println(fileName+" ################### Artist is empty!");
			return;
		}
		if(fileTitle.isEmpty()){
			System.out.println(fileName+" ################### Title is empty!");
			return;
		}
		
		try {
			AudioFile audioFile = AudioFileIO.read(file);
			Tag tag = audioFile.getTag();
			if(tag == null){
				System.out.println(fileName+" ################### TAG is null!");
				tag = audioFile.createDefaultTag();
				audioFile.setTag(tag);
				tag.setField(FieldKey.ARTIST,fileArtist.trim());
				tag.setField(FieldKey.TITLE,fileTitle.trim());
				
				try {
					AudioFileIO.write(audioFile);
				} catch (CannotWriteException e) {
					e.printStackTrace();
				}
				return;
			}
			
			AudioHeader header = audioFile.getAudioHeader();
			if(header == null){
				System.out.println(fileName+" +++++++++++++++++++ HEADER is null!");
			}else{
				int trackLength = header.getTrackLength();
				totalLength += trackLength;
			}
			
			String tagArtist = tag.getFirst(FieldKey.ARTIST);
			String tagTitle = tag.getFirst(FieldKey.TITLE);
			
			if(folderArtist == null){
				if(artistFolders.contains(fileArtist)){
					System.out.println(fileName+" ##################### song in All forder, but there is a specific folder for this artist!");
				}
				if(nonFolderArtists.containsKey(fileArtist)){
					Integer counter = nonFolderArtists.get(fileArtist);
					nonFolderArtists.replace(fileArtist, counter+1);
					if(counter > 4){
						System.out.println(fileName+" ##################### song in All forder, but there are more then 4 songs of this artist!");
					}
				}else{
					nonFolderArtists.put(fileArtist, 1);
				}
				
			}
			
			
			if(!tagArtist.equals(fileArtist) || !tagTitle.equals(fileTitle) || (folderArtist != null && ((multiArtist && fileArtist.indexOf(folderArtist) < 0) || (!multiArtist && !fileArtist.equals(folderArtist)) ))){
				System.out.println("Which one is correct?");
				if((folderArtist != null && ((multiArtist && fileArtist.indexOf(folderArtist) < 0) || (!multiArtist && !fileArtist.equals(folderArtist)) ))){
					System.out.println("1. Folder name - "+folderArtist);
				}
				System.out.println("2. File name - "+fileName);
				System.out.println("3. Tags name - "+tagArtist+" - "+tagTitle+".mp3");
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
					fileArtist = folderArtist;
					tagArtist = folderArtist;
					//tag.deleteField(FieldKey.ARTIST);
					tag.setField(FieldKey.ARTIST,fileArtist.trim());
					try {
						AudioFileIO.write(audioFile);
					} catch (CannotWriteException e) {
						e.printStackTrace();
					}
					File newFile = new File(file.getParent(), tagArtist.trim()+" - "+tagTitle.trim()+".mp3");
					file.renameTo(newFile);
				}else if(input == 2){
					//tag.deleteField(FieldKey.ARTIST);
					//tag.deleteField(FieldKey.TITLE);
					//tag = audioFile.createDefaultTag();
					//audioFile.setTag(tag);
					tag.setField(FieldKey.ARTIST,fileArtist.trim());
					tag.setField(FieldKey.TITLE,fileTitle.trim());
					
					try {
						AudioFileIO.write(audioFile);
					} catch (CannotWriteException e) {
						e.printStackTrace();
					}
				}else if(input == 3){
					File newFile = new File(file.getParent(), tagArtist.trim()+" - "+tagTitle.trim()+".mp3");
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
}
