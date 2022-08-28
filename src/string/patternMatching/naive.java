package string.patternMatching;

public class naive {

	public static void main(String[] args) {
		

	}
	/**
	 * Time complexity: O(n(n-m+1)) in worst case
	 *
	 * @param text
	 * @param pattern
	 * @return
	 *
	 */
	public int naiveSearch(char[] text, char[] pattern) {

		int i=0, j=0;
		for  (;i<=text.length-pattern.length;i++) {

			for (;j<pattern.length;j++) {

				if (text[ i + j ]!=pattern[ j ])
					break;

				if (j == pattern.length-1)
					System.out.println("Pattern found at index " + i);
			}
		}
		return -1;
	}

}
