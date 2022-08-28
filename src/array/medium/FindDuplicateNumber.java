package array.medium;

/**
 * 287. Find the Duplicate Number
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * <p>
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class FindDuplicateNumber {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        // 初始化fast,slow 就是一个2倍关系
        // the duplicate number must be the entry point of the circle
        // when visiting the array from nums[0]
        int fast = nums[nums[0]], slow = nums[0];
        while (fast != slow) {
            fast = nums[nums[fast]];
            slow = nums[slow];

        }

        // 从起点出发再次m行进a的距离即可定位到重复的数字，重复数字是cycle的entry point
        fast = 0;//数组需要再走完整个cycle的大小，因此起始为0
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;

    }
}
