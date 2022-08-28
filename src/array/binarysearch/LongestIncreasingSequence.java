package array.binarysearch;

/**
 * Created by xu on 13/07/2017.
 */
public class LongestIncreasingSequence {
    /**
     * http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
     * <p>
     * nlogn solution
     * <p>
     * 1. If A[i] is smallest among all end
     * candidates of active lists, we will start
     * new active list of length 1.
     * 2. If A[i] is largest among all end candidates of
     * active lists, we will clone the largest active
     * list, and extend it by A[i].
     * <p>
     * 3. If A[i] is in between, we will find a list with
     * largest end element that is smaller than A[i].
     * Clone and extend this list by A[i]. We will discard all
     * other lists of same length as that of this modified list.
     */

    public static void main(String[] args) {
        LongestIncreasingSequence instance = new LongestIncreasingSequence();
        System.out.println(instance.lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9}));
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int[] tailTable = new int[n];
        int len = 1;
        tailTable[0] = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] < tailTable[0]) // case 1
                tailTable[0] = nums[i];
            else if (nums[i] > tailTable[len - 1])
                tailTable[len++] = nums[i]; // case 2
            else // case 3
                tailTable[bs(tailTable, nums[i], len)] = nums[i];
        }
        return len;
    }

    //返回刚刚大于target的元素索引
    public int bs(int[] table, int target, int size) {

        int left = 0, right = size - 1;
        if (table[right] < target)
            return right;
        if (table[left] > target)
            return 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (table[mid] < target) {
                left = mid + 1;
            } else if (table[mid] > target) {
                if (table[mid - 1] < target)
                    return mid;
                right = mid - 1;
            } else // 需要处理duplicate元素 如[4,10,4,3,8,9]
                return mid;
        }
        return left;
    }

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    //下列算法未搞懂
    // Binary search (note boundaries in the caller)
    // A[] is ceilIndex in the caller
    static int CeilIndex(int A[], int l, int r, int key) {
        while (r - l > 1) {
            int m = l + (r - l) / 2;
            if (A[m] >= key)
                r = m;
            else
                l = m;
        }

        return r;
    }

    static int LongestIncreasingSubSequenceLength(int A[], int size) {
        // Add boundary case, when arr size is one

        int[] tailTable = new int[size];
        int len; // always points empty slot

        tailTable[0] = A[0];
        len = 1;
        for (int i = 1; i < size; i++) {
            if (A[i] < tailTable[0])
                // new smallest value
                tailTable[0] = A[i];

            else if (A[i] > tailTable[len - 1])
                // A[i] wants to extend largest subsequence
                tailTable[len++] = A[i];

            else
                // A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                tailTable[CeilIndex(tailTable, -1, len - 1, A[i])] = A[i];
        }

        return len;
    }

}
