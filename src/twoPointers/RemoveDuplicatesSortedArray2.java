package twoPointers;

/**
 * 80. Remove Duplicates from Sorted Array II
 * Follow up for "Remove Duplicates":
	What if duplicates are allowed at most twice?
	
	For example,
	Given sorted arr nums = [1,1,1,2,2,3],
	
	Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
	
	It doesn't matter what you leave beyond the new length.
	
	https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/#/description
	
 */

public class RemoveDuplicatesSortedArray2 {

	// when duplicates are allowed at k times, we remain the first k terms as it is
    // and start from the k-th index and check if the element at the CURRENT-K position [index-k] is the
    // same as the new arriving element [i], if the same, skip to the next element,
    // if not, update the CURRENT [index] position
    // as the new arriving one [i]
	public int solution (int[] arr, int k) {
		int left = k;

		for (int i = k; i<arr.length; i++)
			if (arr[i] > arr[left-k] ) // or !=
				arr[left++] = arr[i];

		return left;
	
	}
	public int sol2(int[] arr, int k) {
        int i = 0;

        for (int n:arr)
            if (i < k || n > arr[i - k])
                arr[i++] = n;

        return i;
    }

}
