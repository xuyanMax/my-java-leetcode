package dynamic;

/**
 * Created by xu on 11/08/2017.
 */
/*
70.

You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.
*/
public class ClimbStairs {

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i=3; i<=n; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n-1];
    }

    // fibonacci
    // _origin_     _1_(1)     _2_(2)     _3_(3)
    // 1 stair:  1 way
    // 2 stairs: 2 ways
    // 3 stairs: 3 ways
    public int climbStairs_fibonacci(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n < 1)
            return 0;
        int one_step_before = 1;// 一个stairs之前的位置（stair 2）到当前位置（stair 3）需要1步
        int two_steps_before = 2;//两个stair之前的位置（stair 1） 到当前位置（stair 3）需要2步
        int all_ways = 0;

        //从存在第三个stairs开始计算
        for (int i=2; i<n; i++) {
            all_ways = one_step_before + two_steps_before;
            two_steps_before = one_step_before;//两个stairs前的位置，现在仅一步之遥
            one_step_before = all_ways;
        }
        return all_ways;

    }
}

