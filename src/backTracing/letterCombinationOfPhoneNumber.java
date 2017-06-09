package BackTracing;

import java.util.LinkedList;
import java.util.List;


/**
 * 
 * @author xu
 *
 * Given a digit string, return all possible letter combinations that the number could represent.

 * Input:Digit string "23"
  
   Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 */

public class letterCombinationOfPhoneNumber {

	public static void main(String[] args) {
		
		// test case from "1", "2" all the way to "99"
		List<String> result = letterCombinations("");
		result.forEach(ele->System.out.print(ele+" "));

	}
	/* FIFO LinkedList implements Queue and Stack */
	public static List<String> letterCombinations (String digits) {
		String[] mapping = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		
		LinkedList<String> result  =  new LinkedList<String>();
		
		result.add("");
		
		for (int i=0; i < digits.length(); i++) {
			int a = Character.getNumericValue(digits.charAt(i));
			while (result.peek().length() == i) {
				String tmp = result.remove();
				for (char c : mapping[a].toCharArray()) 
					result.add(tmp + c);
			}
		}
		
		return result;
		
	}
	

}
