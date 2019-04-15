package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author xu
 * <p>
 * Find the kth largest element in an unsorted arr. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ arr
 */
public class KthLargestElementInArr {

    //一定参考：https://discuss.leetcode.com/topic/14597/solution-explained/50
    // use built-in sort algorithm and return the kth
    // use any sort algorithm sorting in descending order.
    // nlgn
    public int findKthLargestSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargestSort2(int[] arr, int K) {
        int index = quickSort(arr, 0, arr.length - 1, K);
        return arr[index];
    }

    public int findKthLargestSort3(int[] arr, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int val : arr) {
            pq.offer(val);//offer or add: add to pq

            if (pq.size() > k) {
                pq.poll();// retrieve and remove;
            }
        }
        return pq.peek();//retrieve only
    }

    // 返回index
    public int quickSort(int[] arr, int left, int right, int K) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            if (pivot - left + 1 == K)
                return pivot;
            else if (pivot - left + 1 < K)
                return quickSort(arr, pivot + 1, right, K - (pivot - left + 1));
            else
                return quickSort(arr, left, pivot - 1, K);
        }
        return -1;
    }

    // 使用基于快速排序中的partition()方法的简单选择排序，是比较高效的算法
    // best case O(n)/worst case O(n^2)
    public int findKthLargestSort4(int[] arr, int k) {

        int left = 0, right = arr.length - 1;
        while (left < right) {
            final int pivot = partition(arr, left, right);

            if (pivot > k - 1)
                right = pivot - 1;
            else if (pivot < k - 1)
                left = pivot + 1;
            else
                return arr[pivot];
        }

        return arr[left];
    }

    public int partition(int[] arr, int left, int right) {

        int pivotKey = arr[left];

        while (left < right) {
            while (right > left && arr[right] <= pivotKey)
                right--;
            swap(arr[right], pivotKey);

            while (right > left && arr[left] >= pivotKey)
                left++;
            swap(arr[left], pivotKey);

        }
        return left;
    }

    //如果想要保证O(n)需要对数组进行洗牌
    public void shuffle(int[] nums) {
        final Random random = new Random();
        for (int i = 1; i < nums.length; i++) {
            final int r = random.nextInt(i + 1);
            swap(nums[r], nums[i]);
        }
    }

    public void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

}

