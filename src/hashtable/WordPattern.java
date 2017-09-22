package hashtable;

/**
 * 
 * https://leetcode.com/problems/word-pattern/#/description
 * 
 * https://discuss.leetcode.com/topic/26339/8-lines-simple-java
 */
 
import java.util.HashMap;
import java.util.Map;




public class WordPattern {

	public static void main(String[] args) {
		

	}
	static  boolean solution (String  pattern, String str){
		
		Map<Object, Integer> map = new HashMap<>();
		
		String[] strings = str.split(" ");

		if (strings.length != pattern.length())
			return false;
		
		for (Integer i=0; i<strings.length; i++) {
			
			/* 
			 * go through the pattern letters and words in parallel and compare the indexes where they last appeared. 
		     */         
			if (map.put(strings[i], i) != map.put(pattern.charAt(i), i) ){
				return false;
			}
		}
		
		return true;
	}

}
