package sortAlgorithms;

import java.util.Arrays;

/**
 * What if the elements are in range from 1 to n^2?
 * We can’t use counting sort because counting sort will take O(n^2) which is worse than comparison based sorting algorithms.
 * <p>
 * Can we sort such an arr in linear time?
 * -Radix Sort is the answer. The idea of Radix Sort is to do digit by digit sort starting from least significant digit to
 * most significant digit.
 * -Radix sort uses counting sort as a subroutine to sort.
 * <p>
 * <p>
 * http://www.geeksforgeeks.org/radix-sort/
 */
public class RadixSort {//基数排序

    public static void main(String[] args) {
        int[] arr = new int[]{170, 45, 75, 90, 802, 24, 2, 66};
        printArr(arr);
        radixSort(arr);

    }

    static int getMax(int[] arr) {
        int max = arr[0];
        for (int n : arr) {
            if (n > max)
                max = n;
        }
        return max;
    }

    static void countSort(int[] arr, int level) {

        /* the output arr that will store the sorted arr*/
        int n = arr.length;
        int[] output = new int[n];

        /* a count arr to store the count of individual digits from 0-9*/
        int[] count = new int[10];
        Arrays.fill(count, 0);

        int i;

        /* count the occurrences of digits on level digit*/
        for (i = 0; i < n; i++)
            count[(arr[i] / level) % 10]++;

        /* change count[] so that count[i] contains the actual position of this digit in arr[] */
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        /* build the output arr from back to forth so that the rank of numbers with the same digit does not change*/
        for (i = n - 1; i >= 0; i--) {
            int index = (arr[i] / level) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        /* copy the output arr to arr[], now arr[] contains the sorted numbers according to the current digit*/
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    static void radixSort(int[] arr) {

        int max = getMax(arr);

        /* do counting sort for every digit. Note that, instead of passing digit,
         * level is passed. level is 10^i where i is current digit, starting from the LSB.*/
        for (int level = 1; max / level > 0; level *= 10)
            countSort(arr, level);

        printArr(arr);

    }

    static void printArr(int[] arr) {
        for (int n : arr)
            System.out.print(n + " ");
        System.out.println("");
    }

}
