package greedy;

//解析 http://yucoding.blogspot.jp/2013/02/leetcode-question-123-wildcard-matching.html
/**
 * 
 * @author xu
 * Analysis:

	For each element in s
	If *s==*p or *p == ? which means this is a match, then goes to next element s++ p++.
	If p=='*', this is also a match, but one or many chars may be available, 
	so let us save this *'s position and the matched s position.
	If not match, then we check if there is a * previously showed up,
	       if there is no *,  return false;
	       if there is an *,  we set current p to the next element of *, and set current s to the next saved s position.
	
	e.g.
	
	abed
	?b*d**
	
	a=?, go on, b=b, go on,
	e=*, save * position star=3, save s position ss = 3, p++
	e!=d,  check if there was a *, yes, ss++, s=ss; p=star+1
	d=d, go on, meet the end.
	check the rest element in p, if all are *, true, else false;

 */
public class WildcardMatch {

	public static void main(String[] args) {
		
	}
	/**
	 * match: stores the saved s pointer  
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static boolean isMath(String str, String pattern){
		
		int s = 0, p = 0, match = 0, starIndex = -1;
		while (s < str.length()) { 
			// advance two pointers when both characters match of '?' is found in pattern
			// and make sure that p is within the length 
			if (p < pattern.length() && (pattern.charAt(p) == '?' ||(str.charAt(s) == pattern.charAt(p)))) {
				p++;
				s++;
				continue;
			}
			// advance only p pointer 
			// '*' found in pattern, trace index of '*' and index of s
			if (p < pattern.length() && (pattern.charAt(p) == '*')) {
				starIndex = p;
				match = s;
				
				p++;
				continue;
			}
			// if two characters don't match under three cases above (== | '?'| '*').
			// but '*' found on last p pointer
			// we set p to the next of '*' found on last p pointer
			// we set s to the next of saved s positoin
			if (starIndex != -1) {
				p = starIndex + 1;
				match++;
				s = match;
				continue;
			}
			
			return false;
				
		}
		// abc vs. ab?***
		// check the rest of pattern string, if they are all '*' return true;
		// 
		while (p < pattern.length() && pattern.charAt(p) == '*') 
			p++;
		
		return p == pattern.length();
	}
	public boolean isMatch_2d_method(String s, String p) {
		int m = s.length(), n = p.length();
		
		// dynamic[i][j] denotes: string s(:i) matches p(:j) or not
		boolean[][] dp = new boolean[m+1][n+1];
		
		dp[0][0] = true;// empty strings match each other perfectly
		for (int i=1; i<=m; i++) // pattern empty matches string s 
			dp[i][0] = false;
		
		for(int j=1; j<=n; j++) // string s empty matches pattern p must be "*", "**", ... 
			if(p.charAt(j-1)=='*')// once current char p[j-1]!= '*' all the dynamic[0][j] afterwards are false
				dp[0][j] = true;
			 else 
				break;		
		
		/**
		 * IF current char of pattern p is "*" for ex. abcc vs abc*
		 *    dynamic[i][j] = dynamic[i-1][j] | dynamic[i][j-1] ( dynamic = s(:i-1)--p(:j) | s(:i)--p(:j-1) )
		 * NOT
		 *    dynamic[i][j] = ( dynamic[i-1][j-1]  &&( (p[j-1]=='?') | (s[i-1]==p[j-1])) )
		 * 	
		 * 
		 */
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if (p.charAt(j-1)!='*') {
					dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?');
				} else {
					dp[i][j] = dp[i-1][j] || dp[i][j-1];
				}
			}
		}
		return dp[m][n];
	}
	
}
