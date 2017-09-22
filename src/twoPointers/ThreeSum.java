package twoPointers;

import java.util.*;

/**
 * Created by xu on 20/07/2017.
 */
public class ThreeSum {
    public List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);//对arrays 排序

        for (int i=0; i<nums.length-2; i++) {
            if (i>0 && nums[i] == nums[i-1])
                continue;//避免出现重复，因为第一次将-nums[i]作为target, i之后的所有可能组合都被找到，因此如果相同，必会出现重复
            int target = - nums[i];
            int left = i + 1, right = nums.length-1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    while (left < right) if (nums[left] == nums[left - 1]) left++;//跳过相同的结果
                    while (left < right) if (nums[right] == nums[right + 1]) right--;//跳过相同的结果
                }
                else if (nums[left] + nums[right] > target)
                    right--;
                else left++;
            }
        }
        return res;
    }

    // HashSet
    public List<List<Integer>> solution2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            int partSum = - nums[i];
            for (int j= i+1; j<nums.length; j++) {
                partSum -= nums[j];
                if (set.contains(partSum))
                    res.add(Arrays.asList(nums[i], nums[j], partSum));
            }
            set.add(nums[i]);
        }
        return res;
    }
}
