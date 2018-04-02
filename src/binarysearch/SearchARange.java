package binarysearch;

/**
 * Created by xu on 23/07/2017.
 */
/*
Given an arr of integers SORTED in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the arr, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

*/
public class SearchARange {
    //[2,2] 3
    //[1] 1
    //[] 0

    //需要找到左右边界并返回，对于左边界直接寻找target，对于右边界，寻找target+1然后得到的索引减1即可
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length==0)
            return new int[]{-1,-1};
        int start = findFirstGreaterEqual(nums, target);
        //判断start返回值
        //如果是-1 那么序列最小值大于target，不存在该target
        //如果是nums.length, 那么序列最大值小于target，不存在target
        //如果返回的值不是target且大于target且存在序列中，通过则使用nums[start] != target可以判断出
        if (start == -1 ||  start == nums.length || nums[start] != target)
            return new int[]{-1,-1};
        int end = findFirstGreaterEqual(nums, target + 1);//寻找target+1， 索引再减1即可
        return new int[]{start, end-1};

    }
    public int findFirstGreaterEqual(int[] nums, int target) {
        //如果target小于nums[0], 返回-1
        if (nums[0] > target)
            return -1;
        //如果target大于nums[nums.length-1]返回nums.length
        if (nums[nums.length-1] < target)
            return nums.length;

        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = left + (right-left)/2;
            // left <= mid < right
            if (nums[mid] < target)
                left = mid + 1;
            else
                //should not be mid -1 when nums[mid] = target
                //could be mid when nums[mid]>target, because right > mid
                right = mid;
        }
        return left;
    }

// ANOTHER APPROACH
    public int[] solution2(int[] nums, int target) {
        int left = left(nums, target);
        if (left == -1)
            return new int[]{-1,-1};
        int right = right(nums, target);
        return new int[] {left, right};
    }


    public int left(int[] nums, int target){
        if (target < nums[0])
            return -1;
        int left = 0, right = nums.length-1;
        while (left < right) {
            //  mid is always biased towards the left
            int mid = left + (right - left)/2;
            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return nums[left]==target?left:-1;
    }
    // 找到存在重复的序列中最后一次出现的位置
    public int right(int[] nums, int target){
        int left = 0, right = nums.length-1;
        while (left < right) {
            // on purpose biased the mid to the right
            int mid = left + (right-left+1)/2;
            if (nums[mid] > target)
                right = mid - 1;
            else
                // when nums[mid] == target left should not be mid + 1
                // when nums[mid] < target, left < mid, left could be mid
                left = mid;
        }
        return right;
    }

}

/*
####
If the elements of the whole arr is the same as the target, can we do it in a O(logn) time?

- ANSWER

Absolutely. You can choose two double numbers T-0.5 and T+0.5 for target T, and then binary search positions for
the two double numbers in the integer arr(suppose the position are a and b respectively), then the answer is [a, b-1].
For example, for input [5,7,7,8,8,10], you can search position for number 7.5 and 8.5 using binary-search,
and the result is 3 and 5, by which the answer [3,4] is easily obtained.
*/