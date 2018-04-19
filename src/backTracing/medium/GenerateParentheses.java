package backTracing.medium;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author xu
 *
 * #22
 * Given n pairs of parentheses, write a function to sol all combinations of well-formed parentheses.

	For example, given n = 3, a solution set is:
	
	[
	  "((()))",
	  "(()())",
	  "(())()",
	  "()(())",
	  "()()()"
	]
	
	https://leetcode.com/problems/generate-parentheses/#/description
 */

public class GenerateParentheses {

	public static void main(String[] args) {
	
		List<String> result = generateParenthese(3);
		result.forEach(System.out::print);
		
	}
	public static List<String> generateParenthese (int n) {
		List<String> res = new ArrayList<>();
		
		generateParenthesesUtil(res, new String(), 0, 0, n);
		
		return res;
	}
	public static void generateParenthesesUtil (List<String> result, String s, int open, int closed, int max) {
	
		if (s.length() == 2*max) {
			result.add(s);
			return;
		}
		if (open < max) {
			generateParenthesesUtil(result, s + "(", open + 1, closed, max);
		}
		if (closed < open) {
			generateParenthesesUtil(result, s + ")", open, closed + 1, max);
		}
	}





}
