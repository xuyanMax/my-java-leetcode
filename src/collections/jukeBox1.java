package collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
/**
 * 
 * @author xu
 * @
 */
public class jukeBox1 {
	
	ArrayList<String> songList = new ArrayList<String>();// We'll store the song titles in an ArrayList
	
	public static void main(String[] args) {
		new jukeBox1().go();

	}
	public void go() {// start loading the file and print the content of the songList
		getSongs();
		System.out.println(songList);
		
		/**
		 *  @add Collections.sort() to the jubeBox
		 */
		
		Collections.sort(songList);
		System.out.println(songList);
		
	}
	public void getSongs(){
		
		File file = new File("/Users/apple/Documents/workspace/run/src/collections/SongList.txt");
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
		songList.add(tokens[0]);// we only want the song title, so we only add the first token to the List
	}
	
}
