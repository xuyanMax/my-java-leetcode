package sortAlgorithms;

//Java implementation of Counting Sort

import java.util.Arrays;

/**
 * time complexity - O(n+k)
 * where n is the NUMBER of elements in input arr and k is the RANGE of input.
 * <p>
 * points to be noted:
 * 1.Counting sort is efficient if the range of input key is not significantly greater than the number of objects to be sorted.
 * Consider the situation where the input sequence is between range 1 to 10K and the key is 10, 5, 10K, 5K.
 * 2. It is not a comparison based sorting. It running time complexity is O(n) with space proportional to the range of key.
 * 3. It is often used as a sub-routine to another sorting algorithm like radix sort.
 * 4. Counting sort uses a partial hashing to count the occurrence of the key object in O(1).
 * 5. Counting sort can be extended to work for negative inputs also.
 */
public class CountSort {
    void sort(char arr[]) {
        int n = arr.length;

        // The output character arr that will have sorted arr
        char output[] = new char[n];

        // Create a count arr to store count of individual
        // characters and initialize count arr as 0
        int count[] = new int[256];
        Arrays.fill(count, 0);

        // store count of each character
        for (int i = 0; i < n; ++i)
            ++count[arr[i]];

        // Change count[i] so that count[i] now contains actual
        // position of this character in output arr
        for (int i = 1; i <= 255; ++i)
            count[i] += count[i - 1];

        // Build the output character arr
        for (int i = n - 1; i >= 0; --i) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        // Copy the output arr to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i)
            arr[i] = output[i];
    }

    // Driver method
    public static void main(String args[]) {
        CountSort ob = new CountSort();
        char arr[] = {'g', 'e', 'e', 'k', 's', 'f', 'o',
                'r', 'g', 'e', 'e', 'k', 's', '1', '2', '3'
        };

        ob.sort(arr);

        System.out.print("Sorted character arr is ");
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i]);
    }
}
