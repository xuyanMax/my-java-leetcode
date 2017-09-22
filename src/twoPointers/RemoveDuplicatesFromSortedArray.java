package twoPointers;

/**
 * Follow up for "Remove Duplicates":
	What if duplicates are allowed at most twice?
	
	For example,
	Given sorted array nums = [1,1,1,2,2,3],
	
	Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
	
	It doesn't matter what you leave beyond the new length.
	
	https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/#/description
	
 */
import java.util.Scanner;

public class RemoveDuplicatesFromSortedArray {

	public static void main(String[] args) {

		solution();
	}
	
	// when duplicates are allowed at k times, we remain the first k terms as it is
    // and start from the k-th index and check if the element at the CURRENT-k position [index-k] is the
    // same as the new arriving element [i], if the same, skip to the next element, if not, update the CURRENT [index] position
    // as the new arriving one [i]
	public static void solution () {
	Scanner cin = new Scanner(System.in);
	int n = cin.nextInt();
	int[] arr = new int[n];
	int k = cin.nextInt();
	
	for (int i=0; i<n; i++) 
		arr[i] = cin.nextInt();
	int j = k;
	for (int i = k; i<n; i++) {
		if (arr[i] != arr[i-k] ) {
			arr[++j] = arr[i];
		}
	} 
	System.out.println(j);
	cin.close();
	
	}

}
