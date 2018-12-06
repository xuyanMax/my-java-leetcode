package sortAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Bucket sort is mainly useful when input is uniformly distributed over a range
 * <p>
 * unstable
 * not in-place sorting algorithm
 * <p>
 * best and average O(n), worst O(n^2)
 * <p>
 * why O(n^2) in worst case
 * i.e. when all elements at allocated to the same bucket. Since individual buckets are sorted using another algorithm,
 * if only a single bucket needs to be sorted, bucket sort will take on the complexity of the inner sorting algorithm.
 * This is why bucket sort is only useful when the input is uniformly distributed in a range.
 * This way they will not end up in the same bucket.
 * <p>
 * space: O(n)
 * http://javarevisited.blogspot.com/2017/01/bucket-sort-in-java-with-example.html
 */
public class BucketSort {
    public static void bucketSort(int[] input) {
        final int[] hash = hash(input);

        List<Integer>[] buckets = new ArrayList[hash[1]];
        for (int i = 0; i < buckets.length; i++)
            buckets[i] = new ArrayList<Integer>();

        //distribute key into buckets
        for (int num : input)
            buckets[hash(num, hash)].add(num);

        // sort each buckets
        for (List<Integer> bucket : buckets)
            Collections.sort(bucket);

        // merge the buckets O(n)
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket)
                input[index++] = num;
        }
    }

    public static int hash(int num, int[] hash) {
        return (num / hash[0] * (hash[1] - 1));
    }

    public static int[] hash(int[] input) {
        int max = input[0];
        for (int i = 1; i < input.length; i++)
            max = Math.max(max, input[i]);
        return new int[]{max, (int) Math.sqrt(input.length)};
    }

}
