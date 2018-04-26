package arr.easy;

import java.util.ArrayList;
import java.util.List;

//228. Summary Ranges
/*Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:
Input: [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Example 2:
Input: [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
*/
public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int i=0;
        List<String> res = new ArrayList<>();
        int start = nums[0], end = nums[0];
        if (nums.length == 1){
            res.add(String.valueOf(start));
            return res;
        }

        while (i<nums.length-1) {
            if (nums[i] +1 == nums[i+1]){
                end = nums[i+1];
            }else if (start!=end){
                res.add(String.valueOf(start +"->"+end));
                start = nums[i+1];
                end = nums[i+1];
            }else {
                res.add(String.valueOf(start));
                start = nums[i+1];
                end = nums[i+1];
            }
            i++;
        }
        // 1 2 3 4 6 [7 8 9]
        if (start != end)
            res.add(String.valueOf(start+"->"+end));
        // 1 2 3 4 5 6 8 9 14
        if (nums.length > 1 && nums[nums.length-2] +1 != nums[nums.length-1])
            res.add(String.valueOf(nums[i]));


        return res;
    }
    public List<String> summaryRanges2(int[]nums) {
        if (nums==null || nums.length == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        if (nums.length == 1) {
            res.add("" + nums[0]);
            return res;
        }
        int i = 0;
        while (i < nums.length){
            int start = nums[i];
            while (i < nums.length-1 && nums[i] + 1 == nums[i+1])
                i++;
            if (start != nums[i]){
                res.add(start + "->" + nums[i]);
            }else {
                res.add(start+"");
            }
            i++;

        }
        return res;
    }

}
