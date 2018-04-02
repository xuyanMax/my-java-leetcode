package collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;


/**
 * 
 * @author xu
 * @TreeSet is similar to HashSet in that it prevents duplicates. But
 * it also keeps the list sorted. by non-arg (Comparable) or ArtistCompare (Comparator)
 */
public class jukeBox8 {
	
	private ArrayList<song> songList = new ArrayList<song>();
	
	class ArtistCompare implements Comparator<song>{
		public int compare(song a, song b) {
			return a.getArtist().compareTo(b.getArtist());
		}
	}
	class TitleCompare implements Comparator<song>{
		public int compare(song a, song b) {
			return a.getTitle().compareTo(b.getTitle());
		}
	}
	
	
	public static void main(String[] args) {
			
		new jukeBox8().go();
		
	}
	public void go() {
		getSongs();// WE DID not change it
		System.out.println(songList);
		
		ArtistCompare ac = new ArtistCompare();
		Collections.sort(songList, ac);
		System.out.println("By artisit(title:artist):");
		System.out.println(songList);
		
		//Calling the non-arg TreeSet constructor means the set will use the song object's compareTo() method (song must implements Comparable) 
		// for the sort.
		
//		TreeSet<song> songSet = new TreeSet<song>();
		
		// OR WE COULD HAVE PASSED THE COMPARATOR
		TreeSet<song> songSet = new TreeSet<song>(ac);
		songSet.addAll(songList);
	
		System.out.println(songSet);
		
	}
	public void getSongs(){
		
		File file = new File("/Users/apple/Documents/workspace/run/src/collections//SongListMore2.txt");
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
			return title+": "+artist; 
		}
		
		@Override
		/**
		 * same idea here, the String class has an overridden hashCode(), so you can 
		 * just return the result of calling hashCode()	 on the title.
		 *  NOTICE: how hashCode() and equals are using the SAME instance variable
		 */
		public int hashCode() {
			return title.hashCode();
			
		}
		/**
		 * GREAT news is that title is a STRING and Strings have an overridden equals() method
		 * so what we have to do is to ask one title if it's equal to the other song
		 */
		public boolean equals(Object aSong) {
			song s = (song) aSong;
			return this.getTitle().equals(s.getTitle());
		}

		@Override
		public int compareTo(song o) {
			
			return this.getTitle().compareTo(o.getTitle());
		}
		

		
	}
	
	
	

}
