package nowcoder;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/72a5a919508a4251859fb2cfb987a0e6?orderByHotValue=1&questionTypes=000100&page=4&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]矩形覆盖
 * 热度指数：224641时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class RectangularOverlap {
    public int RectCover(int target) {
        if (target <= 1) return 1;
        if (target == 2) return 2;
        int[] dp = new int[target + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= target; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[target];


    }
}
