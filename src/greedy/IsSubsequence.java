package greedy;

/*
 * https://leetcode.com/problems/is-subsequence/#/description
 * 
 * Given a string s and a string t, check if s is subsequence of t.
 * 
 */
public class IsSubsequence {

	public static void main(String[] args) {
		System.out.println(solution2("axc", "ahbgdc"));
		
//System.out.println(solution2("leeeeetcode", "yyyyylyyyyyyyyyyyyyyyyyyyyyyeyyyyyyyyyyyyyyyyyyyeyeyeyeyyyyyyyyyyycy0ydyeyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"));
//"", "avb"
//"asd", ""
//"", "asda"
	}
	
	public static boolean solution(String s, String t) {
		int ss = 0, tt= 0;
		if (s.length() == 0)
			return true;
		
		if (t.length() < s.length())
		    return false;
		while (ss < s.length() && tt<t.length()) {
			if (s.charAt(ss) == t.charAt(tt)){
				ss++;
				tt++;
			}else {
				tt++;
			}
		}
		
		return ss == s.length();
	}
	// Memory Limit Exceeded
	public static boolean solution2(String s, String t) {
		boolean[][] dp = new boolean[s.length()+1][t.length()+1];
		
		dp[0][0] = true; // "","" true
		for (int i=1; i<=t.length(); i++)
			dp[0][i] = true;
		
		for (int i=1; i<=s.length(); i++)
			for (int j=1; j<=t.length(); j++) {
				
				if (j >= i){
				    //
					dp[i][j] = dp[i-1][j] || (dp[i-1][j-1] && s.charAt(i-1)==t.charAt(j-1));
					continue;
				}
				else
					dp[i][j] = false;

				
			}
		
		diplay2dArray(dp);
		return dp[s.length()][t.length()];
	}

	public static void diplay2dArray(boolean[][] data) {
		
		for (boolean[] row:data) {
			for (boolean t:row){
				System.out.print(t+" ");
			}
			System.out.println("");
		}
			
	}
	// indexOf
	 public boolean isSubsequence(String s, String t) 
	    {
	        if(t.length() < s.length()) 
	        	return false;
	        int prev = 0;
	        for(int i = 0; i < s.length();i++)
	        {
	            char tempChar = s.charAt(i);
	            prev = t.indexOf(tempChar, prev);
	            
	            if(prev == -1) 
	            	return false;
	            
	            prev++;
	        }
	        
	        return true;
	    }

}
