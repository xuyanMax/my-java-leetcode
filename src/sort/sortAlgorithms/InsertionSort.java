package sort.sortAlgorithms;

import java.util.Arrays;

/**
 * @author xu
 * <p>
 * time comlexity - O(n^2) worst case when arr is sorted in reverse order ;
 * O(n) is arr is sorted as best case.
 * <p>
 * stable
 * <p>
 * sorting in place
 * <p>
 * Auxiliary space - O(1)
 * Uses: Insertion sort is used when number of elements is small.
 * It can also be useful when input arr is almost sorted, only few elements are misplaced in complete big arr
 * <p>
 * Binary Insertion Sort (BIS)
 * We can use BIS to reduce the number of comparisons in normal insertion sort. BIS use binary search to find the proper location to insert
 * the selected item at each iteration by O(lgn). While the algorithm as a whole takes O(n^2) because of the series of swaps required for each insertion.
 * <p>
 * Implement Insertion Sort in Linked List
 * <p>
 * http://quiz.geeksforgeeks.org/insertion-sort/
 */
public class InsertionSort {


    public void InsertionSorting(int[] arr) {

        int i, j;
        for (i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                int flag = arr[i];
                // arr[j] > flag && j>=0 does not work...
                for (j = i - 1; j >= 0 && arr[j] > flag; j--)
                    arr[j + 1] = arr[j];
                arr[j + 1] = flag;
            }
        }

    }

    public void binaryInsertSort(int[] arr) {
        int i, j, index;
        int flag;
        // start from index 1
        for (i = 1; i < arr.length; i++) {
            flag = arr[i];
            if (arr[i] < arr[i - 1]) {
                index = Arrays.binarySearch(arr, 0, i, arr[i]);
                index = index < 0 ? ~index : index;
                for (j = i; j > index; j--)
                    arr[j] = arr[j - 1];
                arr[index] = flag;
            }
        }
    }

    public void swap(int[] arr, int a, int b) {

    }
}
