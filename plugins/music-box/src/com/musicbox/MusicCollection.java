package com.musicbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MusicCollection {
	private HashMap<String, List<String>> songs = new HashMap<String, List<String>>();
	
	public void addSong(String fileName, String artist, String title){
		if(artist != null && !artist.isEmpty()){
			List<String> songList = songs.get(artist.toLowerCase());
			if(songList == null){
				songList = new ArrayList<String>();
				songs.put(artist.toLowerCase(), songList);
			}
			//Song song = new Song(fullPath, artist, title, albom, year, length, bitRate);
			if(songList.contains(title.toLowerCase())){
				System.out.println(fileName+" #################### There is alreade song with this name!");
			}else{
				songList.add(title.toLowerCase());
			}
		}
	}
	
	public int getNumberOFArtists(){
		return songs.size();
	}
	
//	static class Song{
//		private String fullPath;
//		private String artist;
//		private String title;
//		private String albom;
//		private String year;
//		private int length;
//		private int bitRate;
//		
//		public Song(String fullPath, String artist, String title, String albom, String year, int length, int bitRate){
//			this.fullPath = fullPath;
//			this.artist = artist;
//			this.title = title;
//			this.albom = albom;
//			this.year = year;
//			this.length = length;
//			this.bitRate = bitRate;
//		}
//		
//		public String getFullPath(){
//			return fullPath;
//		}
//		
//		public String getArtist(){
//			return artist;
//		}
//		
//		public String getTitle(){
//			return title;
//		}
//		
//		public String getAlbom(){
//			return albom;
//		}
//		
//		public String getYear(){
//			return year;
//		}
//		
//		public int getLength(){
//			return length;
//		}
//		
//		public int getBitRate(){
//			return bitRate;
//		}
//	}
}
