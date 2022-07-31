package a_OA;

public class JumpLast {
    public int sol(int[] nums) {
        int curr = 0;
        int nextstep_longest = 0;
        int sofar_longest = 0;
        int min_steps = 0;
        while (sofar_longest < nums.length - 1) {

            for (; curr + nums[curr] <= sofar_longest; curr++)
                nextstep_longest = Math.max(nextstep_longest, curr + nums[curr]);

            if (nextstep_longest > sofar_longest)
                min_steps += 1;
            else
                return -1;//fail to jump to the last
        }
        return min_steps;
    }
}
