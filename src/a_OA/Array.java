package a_OA;

import java.util.HashMap;

public class Array {
    /**
     * 给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
     * 1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
     * 2.假设 nums[-1] = nums[n] = -\infty−∞
     * 3.对于所有有效的 i 都有 nums[i] != nums[i + 1]
     * 4.你可以使用O(logN)的时间复杂度实现此问题吗？
     * <p>
     * 数据范围：
     * 1 \le nums.length \le 2\times 10^5 \1≤nums.length≤2×10
     * 5
     * <p>
     * -2^{31}<= nums[i] <= 2^{31} - 1−2
     * 31
     * <=nums[i]<=2
     * 31
     * −1
     * <p>
     * 如输入[2,4,1,2,7,8,4]时，会形成两个山峰，一个是索引为1，峰值为4的山峰，另一个是索引为5，峰值为8的山峰，如下图所示：
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        //二分法
        while (left < right) {
            int mid = left + (right - left) / 2;
            //右边往下走，不一定有波峰
            if (nums[mid] > nums[mid + 1])
                right = mid;
            else
                left = mid + 1;
        }
        //其中一个波峰
        return left;
        //另一个
//        return right
    }

    public int binary_search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else
                return mid;
        }
        return -1;
    }

    /**
     * 有一个长度为 n 的非降序数组，比如[1,2,3,4,5]，将它进行旋转，即把一个数组最开始的若干个元素搬到数组的末尾，变成一个旋转数组，
     * 比如变成了[3,4,5,1,2]，或者[4,5,1,2,3]这样的两段式(最小值在每一段的段首)。请问，给定这样一个旋转数组，求数组中的最小值。
     * <p>
     * 数据范围：1 \le n \le 100001≤n≤10000，数组中任意元素的值: 0 \le val \le 100000≤val≤10000
     * 要求：空间复杂度：O(1)O(1) ，时间复杂度：O(logn)O(logn)
     */
    public int minNumberInRotateArray(int[] rotatedArray) {

        int left = 0, right = rotatedArray.length - 1;
        while (left < right) {
            int mid = left + (right - left) >> 2;
            if (rotatedArray[mid] > rotatedArray[right]) {
                left = mid + 1;
            } else if (rotatedArray[mid] < rotatedArray[right]) {
                right = mid;
            } else if (rotatedArray[mid] == rotatedArray[right]) {
                right--;
            }
        }
        return rotatedArray[left];
    }

    //最长无重复子数组
    public int maxLength (int[] arr) {
        //哈希表记录窗口内非重复的数字
        HashMap<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        //设置窗口左右边界
        for(int left = 0, right = 0; right < arr.length; right++){
            //窗口右移进入哈希表统计出现次数
            if(mp.containsKey(arr[right]))
                mp.put(arr[right],mp.get(arr[right])+1);
            else
                mp.put(arr[right],1);
            //出现次数大于1，则窗口内有重复
            while(mp.get(arr[right]) > 1)
                //窗口左移，同时减去该数字的出现次数
                mp.put(arr[left],mp.get(arr[left++])-1);
            //维护子数组长度最大值
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}