package collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class jukeBox3 {
	
	// change to AL of Song objects instead of String
	private ArrayList<song> songList = new ArrayList<song>();
	
	public static void main(String[] args) {
			
		new jukeBox3().go();
		}
	public void go() {// start loading the file and print the content of the songList
		getSongs();
		System.out.println(songList);
		
		// (sort()) it worked fine when passing ArrayList of String, but
		// failed as soon as we tried to sort an ArrayList<song>
		
		// sort can only take lists of COMPARABLE objects
		// since song is not a subtype of COMPARABLE, you can sort a list of songs.
		// we want to sort by title/bpm/, so we make song class implements Comparable<song> 
		// and override the compareTo() method
		Collections.sort(songList);
		System.out.println(songList);
		
	}
	public void getSongs(){
		
		File file = new File("/Users/apple/Documents/workspace/run/src/collections//SongListMore.txt");
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line=br.readLine())!=null) 
				addSong(line);
				
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void addSong(String lineToParse) {
		
		String[] tokens = lineToParse.split("/");
		song aSong = new song(tokens[0], tokens[1], tokens[2], tokens[3]);
		songList.add(aSong);// create a song using the four tokens, and add the aSong to the list.
	}
	
	class song implements Comparable<song>{
		private String title;
		private String artist;
		private String rating;
		private String bpm;
		
		public song (String t, String a, String r, String b) {
			title = t;
			artist = a;
			rating = r;
			bpm = b;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getArtist() {
			return artist;
		}

		public void setArtist(String artist) {
			this.artist = artist;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public String getBpm() {
			return bpm;
		}

		public void setBpm(String bpm) {
			this.bpm = bpm;
		}
		// we override the toString because, 
		// when we do a System.out.println(aSongObject), we want to see the title.
		// When you do a System.out.println(aListOfSongs), it calls the toString() method 
		// of EACH element in the list.
		public String toString() {
			return title; 
		}

		@Override
		public int compareTo(song o) {
			// the sort() method sends a song to compareTo() to see
			// how that song o compares to the song on which the method was invoked.
			// we pass the work on to the title STRING objects, since we know STRING has a compareTo() method
			return this.title.compareTo(o.getTitle());
		}
		
	}
	

}
