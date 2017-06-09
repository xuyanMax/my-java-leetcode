package collections;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.crypto.NullCipher;

/**
 * 
 * @author xu
 * @date 2017/02/16
 */

public class sortMountains {

	LinkedList<Mountain> mtn = new LinkedList<Mountain>();
	
	public static void main(String[] args) {
		
		new sortMountains().go();

	}
	public void go() {
		mtn.add(new Mountain("Long", 14255));
		mtn.add(new Mountain("Elbert", 14433));
		mtn.add(new Mountain("Maroon", 14156));
		mtn.add(new Mountain("Castle", 14265));
		
		System.out.println(mtn);
		
		nameCompare nCompare = new nameCompare();
		heightCompare hCompare = new heightCompare();
		
		Collections.sort(mtn, nCompare);
		System.out.println("By name:");
		System.out.println(mtn);
		
		Collections.sort(mtn,hCompare);
		System.out.println("By height:");
		System.out.println(mtn);
	}
	class Mountain{
		private String name;
		private int height;
		
		Mountain(String s, int h){
			name = s;
			height = h;
		}
		
		public String toString(){
			return name + " " + height;
			
		}
	}
	class nameCompare implements Comparator<Mountain>{
		public int compare(Mountain a, Mountain b){
			return a.name.compareTo(b.name); // alphabetical order
		}
	}
	class heightCompare implements Comparator<Mountain> {
		public int compare(Mountain a, Mountain b) {
			return a.height - b.height; // number ascending order;
		}
	}

}
