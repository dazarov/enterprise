package com.musicbox;

import java.util.TreeMap;
import java.util.TreeSet;

public class MusicCollection {
	private TreeMap<String, TreeSet<String>> songs = new TreeMap<>();
	private TreeSet<String> nonArtists = new TreeSet<>();
	
	public void addSong(String fileName, String artist, String title){
		if(artist == null && title == null){
			nonArtists.add(fileName);
		} else if(artist != null && !artist.isEmpty()){
			TreeSet<String> songList = songs.get(artist.toLowerCase());
			if(songList == null){
				songList = new TreeSet<String>();
				songs.put(artist.toLowerCase(), songList);
			}
			title = processTitle(title);
			//Song song = new Song(fullPath, artist, title, albom, year, length, bitRate);
			if(songList.contains(title)){
				System.out.println(fileName+" Song with this name already exist!");
			}else{
				songList.add(title);
			}
		}
	}
	
	private String processTitle(String title){
		return title.toLowerCase()
				.replace(".", "")
				.replace(",", "")
				.replace("'", "")
				.replace("-", "")
				.replace("  ", " ")
				.replace("  ", " ");
	}
	
	public void printAll(){
		int total = 1;
		int artCount = 1;
		for(String artist : songs.keySet()){
			System.out.println("-------   "+artCount+". "+artist.toUpperCase()+"   --------");
			artCount++;
			TreeSet<String> list = songs.get(artist);
			int count = 1;
			for(String song : list){
				System.out.println(total+". "+count+". "+song);
				count++;
				total++;
			}
		}
		System.out.println("-------   Non Artists Songs   --------");
		for(String song : nonArtists){
			System.out.println(total+". "+song);
			total++;
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
