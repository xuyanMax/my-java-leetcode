package array.medium;
//769. Max Chunks To Make Sorted
//https://leetcode.com/problems/max-chunks-to-make-sorted/description/

/**
 * The basic idea is to use max[] array to keep track of the max value
 * until the current position, and compare it to the sorted array (indexes from 0 to arr.length - 1).
 * If the max[i] equals the element at index i in the sorted array, then the final count++.
 * <p>
 * Update: As @AF8EJFE pointed out, the numbers range from 0 to arr.length - 1.
 * So there is no need to sort the arr, we can simply use the index for comparison.
 * Now this solution is even more straightforward with O(n) time complelxity.
 * <p>
 * For example,
 * <p>
 * original: 0, 2, 1, 4, 3, 5, 7, 6
 * max:      0, 2, 2, 4, 4, 5, 7, 7
 * sorted:   0, 1, 2, 3, 4, 5, 6, 7
 * index:    0, 1, 2, 3, 4, 5, 6, 7
 * The chunks are: 0 | 2, 1 | 4, 3 | 5 | 7, 6
 */
public class MaxChunksMakeSorted {
    public int maxChunksToSorted(int[] arr) {

        if (arr == null || arr.length == 0) return 0;
        int[] max = new int[arr.length]; // max value until arr[i]
        max[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max[i] = Math.max(max[i - 1], arr[i]);
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max[i] == i) {
                count++;
            }
        }

        return count;
    }

    // simplified one
    public int maxChunksToSorted2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int count = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                count++;
            }
        }

        return count;
    }
}
