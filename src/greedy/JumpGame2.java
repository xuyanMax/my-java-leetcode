package greedy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your MAXIMUM jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Example:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 * <p>
 * You can assume that you can always reach the last index.
 */
public class JumpGame2 {

    public static void main(String[] args) {
        JumpGame2 jg = new JumpGame2();
//        jg.jump2(new int[]{1, 2, 3, 1, 1});
        System.out.println(jg.jump(new int[]{1, 2, 3, 1, 1, 2, 1}));
    }

    // Your goal is to reach the last index in the minimum number of jumps.
    // for-loop inside while-loop iteratively find out the longest distance reachable in current position
    // and then take the longest reachable one in current position as the next bounding edge of the next iteration
    public int jump(int[] nums) {
        int next_dst_sofar = 0;
        int lng_dst_sofar = 0;

        int currPos = 0;
        int min_step = 0;
        //every while iteration is worth of 1 step.
        while (lng_dst_sofar < nums.length - 1) {
            // longest reachable one as the bounding edge
            // i:current position
            for (; currPos <= lng_dst_sofar; currPos++)
                next_dst_sofar = Math.max(currPos + nums[currPos], next_dst_sofar);


            if (next_dst_sofar > lng_dst_sofar) {
                lng_dst_sofar = next_dst_sofar;
                min_step++;
            } else // unable to reach beyond the longest distance so far
                return -1;
        }

        return min_step;
    }

    // record indices of jumps
    public int jump2(int[] nums) {
        int last_jump_lgst = 0; // last longest distance having reached这一跳能走的最远距离
        int step = 0; // minimum steps
        int curr_jump_lgst = 0; // longest distance that can be reached with 1 more step //下一跳能走的最远距离

        List<Integer> index = new ArrayList<>();
        int ind = 0;
        for (int i = 0; i < nums.length; i++) {

            if (i > last_jump_lgst) { // i < last_lgst : 所有尝试走的步数都在当前jump的范围内
                step++;
                last_jump_lgst = curr_jump_lgst;

                index.add(ind);
                ind = i + 1;

                if (curr_jump_lgst > nums.length - 1)
                    break;
            }
            // curr_jump_lgst = Math.max(curr_jump_lgst, i + nums[i]); // 当i<last_lgst， 即当前位置在下一jump范围内时，寻找最远距离。
            //一旦超出下一jump距离，更新最远距离，并增加步数1
            if (curr_jump_lgst < i + nums[i]) {
                ind = i;
                curr_jump_lgst = i + nums[i];
            }

        }

        index.add(nums.length - 1);
        for (int n : index)
            System.out.println(n);

        System.out.println(step);
        return step;
    }

    /**
     * Cannot pass the last test case in that it is too big a data
     *
     * @param nums
     * @return
     */
    public int jumpBFS(int[] nums) {
        // store index of number
        if (nums.length == 1) {
            return 0;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        boolean[] visited = new boolean[nums.length];
        int cnt = 0;
        boolean found = false;
        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                visited[curr] = true;
                for (int i = 1; i <= nums[curr] && i + curr < nums.length && !visited[i + curr]; i++) {
                    queue.add(i + curr);
                    if (i + curr == nums.length - 1) {
                        found = true;
                        break;
                    }
                }
            }
            cnt++;
        }
        return cnt;
    }
}
