/**
 * 链接：http://www.nowcoder.com/questionTerminal/22243d016f6b47f2a6928b4313c85387?orderByHotValue=1&questionTypes=000100&page=2&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]变态跳台阶
 * 热度指数：251234时间限制：1秒空间限制：32768K
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
package nowcoder;
public class BizareJumpStairs {
    /**
     * recursive
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {

        // 分析牛逼
        // f(n) = 1, n = 0, 1
        // f(n) = 2*f(n-1), n > 1
        if (target == 0) return 1;
        if (target == 1) return 1;
        return 2 * JumpFloorII(target - 1);
    }

    /**
     * dp solution
     * @param target
     * @return
     */
    public int JumpFloorII2(int target) {

        //分析牛逼
        // f(n) = 1, n = 0, 1
        // f(n) = 2*f(n-1), n > 1
        int[] dp = new int[target];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < target; i++)
            dp[i] = 2 * dp[i - 1];

        return dp[target - 1];
    }

}
