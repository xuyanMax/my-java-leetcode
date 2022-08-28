package array.binarysearch;

/**
 * Created by xu on 2017/6/20.
 * <p>
 * Given an arr nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Note:
 * You must not modify the arr (assume the arr is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the arr, but it could be repeated more than once.
 */
public class FindDuplicate {

    /**
     * two pointers, fast & slow  2(a+b) = a + b + c + a => a = c
     * nums[0]永远是这个cycle的入口而且永远不回再回到nums[0]因为数组元素1到n之间
     * <p>
     * 1,2,3,4,5,4
     * 1,3,4,2,1
     * 5,2,1,3,5,7,6,4
     * 1 2 2 2 5 6 7 8 9 10 11
     */
    public static void main(String[] args) {
        FindDuplicate inst = new FindDuplicate();
        System.out.println(inst.findDuplicate(new int[]{1, 3, 4, 2, 1}));
    }

    public int findDuplicate(int[] nums) {
        if (nums.length < 2)
            return -1;
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]]; // two steps
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;

    }
}
