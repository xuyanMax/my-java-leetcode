package sortAlgorithms;

//Java implementation of Counting Sort
/**
 * 
 * @author xu
 * 
 * time complexity - O(n+k) 
 * where n is the NUMBER of elements in input array and k is the RANGE of input.
 * 
 * points to be noted:
 * 1. Counting sort is efficient if the range of input data is not significantly greater than the number of objects to be sorted. 
 *    Consider the situation where the input sequence is between range 1 to 10K and the data is 10, 5, 10K, 5K.
 * 2. It is not a comparison based sorting. It running time complexity is O(n) with space proportional to the range of data.
 * 3. It is often used as a sub-routine to another sorting algorithm like radix sort.
 * 4. Counting sort uses a partial hashing to count the occurrence of the data object in O(1).	
 * 5. Counting sort can be extended to work for negative inputs also.
 */

public class CountSort
{
 void sort(char arr[])
 {
     int n = arr.length;

     // The output character array that will have sorted arr
     char output[] = new char[n];

     // Create a count array to store count of individual
     // characters and initialize count array as 0
     int count[] = new int[256];
     for (int i=0; i<256; ++i)
         count[i] = 0;

     // store count of each character
     for (int i=0; i<n; ++i)
         ++count[arr[i]];

     // Change count[i] so that count[i] now contains actual
     // position of this character in output array
     for (int i=1; i<=255; ++i)
         count[i] += count[i-1];

     // Build the output character array
     for (int i = 0; i<n; ++i)
     {
         output[count[arr[i]]-1] = arr[i];
         --count[arr[i]];
     }

     // Copy the output array to arr, so that arr now
     // contains sorted characters
     for (int i = 0; i<n; ++i)
         arr[i] = output[i];
 }

 // Driver method
 public static void main(String args[])
 {
     CountSort ob = new CountSort();
     char arr[] = {'g', 'e', 'e', 'k', 's', 'f', 'o',
                   'r', 'g', 'e', 'e', 'k', 's'
                  };

     ob.sort(arr);

     System.out.print("Sorted character array is ");
     for (int i=0; i<arr.length; ++i)
         System.out.print(arr[i]);
 }
}
/*This code is contributed by Rajat Mishra */