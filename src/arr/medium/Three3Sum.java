package arr.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xu on 20/07/2017.
 *
 * 15. 3Sum
 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 Find all unique triplets in the array which gives the sum of zero.

 Note: The solution set must not contain duplicate triplets.

 For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 *
 */
public class Three3Sum {
/*
 run through all indices of a possible first element of a triplet.
 For each possible first element, we make a standard bi-directional 2Sum sweep of the remaining part of the arr.
 Also we want to skip equal elements to avoid duplicates in the answer without making a set or smth like that.
*/
    public List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);//对arrays 排序

        for (int i=0; i<nums.length-2; i++) {
            if (i>0 && nums[i] == nums[i-1])
                continue;//避免出现重复，因为第一次将-nums[i]作为target, i之后的所有可能组合都被找到，因此如果相同，必会出现重复
            int target = - nums[i];

            //two pointers
            int left = i + 1, right = nums.length-1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {

                    res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));

                    while (left < right && nums[left] == nums[left - 1])
                        left++;//跳过相同的结果
                    while (left < right && nums[right] == nums[right + 1])
                        right--;//跳过相同的结果

                    left++;
                    right--;
                }
                else if (nums[left] + nums[right] > target) right--;
                else left++;
            }
        }
        return res;
    }
}
