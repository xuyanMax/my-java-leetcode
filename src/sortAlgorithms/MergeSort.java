package sortAlgorithms;


/**
 * @author xu
 * Like QuickSort, Merge Sort is a Divide and Conquer algorithm. It divides input arr in two halves,
 * calls itself for the two halves and then merges the two sorted halves.
 * <p>
 * time complexity: O(nlgn) in all 3 cases (best, worst, average) as merge sort always divides the arr in two halves and take
 * linear time to merge two halves.
 * <p>
 * T(n) = 2T(n/2) + O(n)
 * 2T(n/2) = 4(T/4) + O(2*n/2)
 * ....
 * T(n) = lgn * O(n) = O(nlgn)
 * <p>
 * Space complexity: O(n)
 */
public class MergeSort {

    /**
     * Main function that sorts arr[l..r] using
     * merge()
     */

    void sort(int[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(a, l, m);
            sort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    /**
     * Merges two subarrays of arr[].
     * First subarray is arr[l..m]
     * Second subarray is arr[m+1..r]
     */
    void merge(int[] nums, int low, int m, int high) {
        int n1 = m - low + 1;
        int n2 = high - m;

        /*create temp arrays*/
        int[] L = new int[n1];
        int[] R = new int[n2];

        /*copy key to temp key*/
        for (int i = 0; i < n1; i++)
            L[i] = nums[low + i];

        for (int j = 0; j < n2; j++)
            R[j] = nums[m + 1 + j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry arr
        int k = low;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) { //稳定性，两个元素相等，则将处在前面的序列的元素保存在结果序列的前面，这样保证了稳定性
                nums[k] = L[i];
                i++;

            } else {
                nums[k] = R[j];
                j++;
            }
            k++;
        }
        /*Copy remaining elements of L[i] if any*/
        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;
        }
        /*Copy remaining elements of R[i] if any*/
        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;
        }

    }
    /**
     *
     * merge sort non-recursive
     *
     */

//	public void mergeItr(int[] arr){
//
//		int k=0;
//		int[] TR = new int[100];
//		while (k < arr.length){
//			mergePass(arr, TR, k, arr.length-1);
//
//			if (k==0) k = (k+1)*2;
//			else k *=2;
//
//			mergePass(TR, arr, k, arr.length-1);
//			k *=2;
//		}
//
//	}
//	public void mergePass(int[]SR, int[]TR, int min, int max){
//		int i=0;
//		int j;
//		while(i <= max-2*min+1) {
//			merge(SR, TR, i, i+min-1, i+2*min-1);
//			i=i+2*min;
//
//		}
//		if(i<max-min+1)
//			merge(SR, TR, i, i+min-1, max);
//		else
//			for (j=i; j<=max; j++)
//				TR[j]=SR[j];
//	}


}
