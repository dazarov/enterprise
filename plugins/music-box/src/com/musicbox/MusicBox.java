package com.musicbox;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
	
	public static void main(String[] args){
		String path = "/home/daniel/Music/Music";
		
		System.out.println("-------------------- BEGIN --------------------------");
		
		File file = new File(path);
		if(file.isDirectory()){
			processDirectory(file);
		}
		
		System.out.println("-------------------- END --------------------------");
		System.out.println("Files processed - "+fileNumbers);
		//Time time = new Time(totalLength);
		long hours = totalLength/(60*60);
		//totalLength = totalLength%(60*60);
		long min = (totalLength%(60*60))/60;
		long sec = (totalLength%(60*60))%60;
		
		System.out.println(hours+":"+min+":"+sec);
	}
	
	private static void processDirectory(File directory){
		//System.out.println("Process directory - "+directory.getName());
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				processDirectory(file);
			}else if(file.getName().endsWith(".mp3")){
				processFile(file);
			}
		}
	}
	
	private static void processFile(File file){
		fileNumbers++;
		
		String fileName = file.getName();
		
		if(!file.canWrite()){
			System.out.println(fileName+" ################# File is read-only!");
		}
		
		//System.out.println("Process file - "+fileName);
		
		int delimeter = fileName.indexOf(" - ");
		int mp3Position = fileName.indexOf(".mp3");
		
		if(delimeter < 0 || mp3Position < 0){
			System.out.println(fileName+" ################### Delimiter or .mp3 not found!");
			return;
		}
		
		String fileArtist = fileName.substring(0, delimeter);
		String fileTitle = fileName.substring(delimeter+3, mp3Position);
		if(fileArtist.isEmpty()){
			System.out.println(fileName+" ################### Artist is empty!");
			return;
		}
		if(fileTitle.isEmpty()){
			System.out.println(fileName+" ################### Title is empty!");
			return;
		}
		
		//System.out.println("File Name Artist - "+fileArtist);
		//System.out.println("File Name Song title - "+fileTitle);
		
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
					//audioFile.commit();
					AudioFileIO.write(audioFile);
				} catch (CannotWriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			
			AudioHeader header = audioFile.getAudioHeader();
			if(header == null){
				System.out.println(fileName+" +++++++++++++++++++ HEADER is null!");
				//return;
			}else{
				int trackLength = header.getTrackLength();
				//int min = trackLength/60;
				//int sec = trackLength%60;
				totalLength += trackLength;
			}
			
			//System.out.println("Length - "+min+":"+sec);
			//System.out.println("BitRate - "+header.getBitRate());
			
			String tagArtist = tag.getFirst(FieldKey.ARTIST);
			String tagTitle = tag.getFirst(FieldKey.TITLE);
			
			//System.out.println("Tag Name Artist - "+tagArtist);
			//System.out.println("Tag Name Song title - "+tagTitle);
			
			boolean needCorrectTitle = false;
			boolean needCorrectArtist = false;
			
			if(!tagArtist.equals(fileArtist)){
				needCorrectArtist = true;
			}

			if(!tagTitle.equals(fileTitle)){
				needCorrectTitle = true;
			}
			
			if(needCorrectTitle || needCorrectArtist){
				System.out.println("Which one is correct?");
				System.out.println("1. File name - "+fileName);
				System.out.println("2. Tags name - "+tagArtist+" - "+tagTitle+".mp3");
				System.out.println("3. Skip");
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
					}else if((char)ch == '0'){
						input = 0;
					}
				}
				System.out.println("Input - "+input);
				
				if(input == 1){
					tag.setField(FieldKey.ARTIST,fileArtist.trim());
					tag.setField(FieldKey.TITLE,fileTitle.trim());
					
					try {
						AudioFileIO.write(audioFile);
					} catch (CannotWriteException e) {
						e.printStackTrace();
					}
				}else if(input == 2){
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
