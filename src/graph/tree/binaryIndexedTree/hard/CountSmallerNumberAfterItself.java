package graph.tree.binaryIndexedTree.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 315. Count of Smaller Numbers After Self
 * <p>
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example:
 * <p>
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 *
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
public class CountSmallerNumberAfterItself {
    class Index {
        public int number;
        public int index;

        public Index(int number, int index) {
            this.number = number;
            this.index = index;
        }

        public Index(Index input) {
            this.index = input.index;
            this.number = input.number;
        }
    }

    public List<Integer> countSmaller(int[] nums) {

        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Index[] INDEX = new Index[nums.length];

        //记录原始数组nums的对应元素&索引信息
        for (int i = 0; i < nums.length; i++)
            INDEX[i] = new Index(nums[i], i);

        int[] smaller = new int[nums.length];

        //排序后Index[0:n]升序排列
        INDEX = sort(INDEX, smaller);

        res = Arrays.stream(smaller).mapToObj(Integer::new).collect(Collectors.toList());
        return res;
    }

    // INDEX[] 数组大小划分最小到1
    public Index[] sort(Index[] nums, int[] smaller) {
        int half = nums.length / 2;
        if (half > 0) {
            Index[] left = new Index[half];
            Index[] right = new Index[nums.length - half];

            for (int i = 0; i < half; i++)
                left[i] = new Index(nums[i]);

            for (int j = 0; j < right.length; j++)
                right[j] = new Index(nums[half + j]);

            Index[] retLeft = sort(left, smaller), retRight = sort(right, smaller);

            int n = retLeft.length, m = retRight.length, i = 0, j = 0;

            while (i < n || j < m) {
                if (j == m || i < n && left[i].number <= right[j].number) {
                    nums[i + j] = left[i];
                    smaller[left[i].index] += j;
                    i++;
                } else {
                    nums[i + j] = right[j];
                    j++;
                }
            }
        }
        return nums;
    }
}
