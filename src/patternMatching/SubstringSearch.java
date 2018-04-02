package patternMatching;

/**
 * 
 * @author xu
 *
 * Do pattern matching using KMP algorithm
 * The worst case complexity of naive algorithm is O(m(n-m+1)), while 
 * 
 * Time complexity KMP - O(n+m)
 * space complexity - O(n)
 * 
 * references:
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/string/SubstringSearch.java
 * http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
 *  
 */
public class SubstringSearch {

	public static void main(String[] args) {
		
		SubstringSearch sbSearch = new SubstringSearch();
		String text = "abcxabcdabcdabcy";
		String pattern = "abcdabcy";
		int index = sbSearch.KMP(text.toCharArray(), pattern.toCharArray());
		System.out.println(index);

//		sbSearch.naiveSearch(text.toCharArray(), pattern.toCharArray());
	}
	// we pre-process pattern pattern and prepare an arr lps[] that tell us count of characters to be skipped
	
	public int[] computeLSP(char[] pattern) {
		
		// lsp could be defined as the longest PROPER prefix which is also PROPER suffix
		// proper means that whole substring is NOT ALLOWED.
		
		int[] lsp = new int[pattern.length];
		int j = 0, i=1;
		
		while (i < lsp.length) {
			if (pattern[i] == pattern[j]) {
				lsp[i] = j+1;
				j++;
				i++;
			} else  {
				if (j!=0) 
					j = lsp[j-1];
				else {
					lsp[i]=0;
					i++;
				} 
					
			}		
		}
		return lsp;
	}
	/**
	 * KMP pattern matching
	 * @return true or false
	 */
	public int KMP(char[] text, char[] pattern) {
		int[] lsp = computeLSP(pattern);
		int i=0, j=0;
		while (i<text.length && j<pattern.length){
			if (text[i] == pattern[j]) {
				i++;j++;
			} else {
				if (j!=0) 
					j = lsp[j-1];
				 else
					i++;
			}
		}
		if (j == pattern.length)
			return i-j;
		else return -1;
	}

}
