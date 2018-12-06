package math;

/**
 * Created by xu on 2017/6/19.
 */
public class UglyNumbers_2 {
    /*Write a program to find the n-th ugly number.*/

    // Heap
    // Dynamic Programming
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        int t2 = 0, t3 = 0, t5 = 0;

        if (n <= 0) return -1;
        if (n == 1) return 1;

        for (int i = 1; i < n; i++) {

            dp[i] = minOfThree(dp[t2] * 2, dp[t3] * 3, dp[t5] * 5); // find the minimum

            // updateHighestHeightBtwLR pointers to the next larger ugly number
            if (dp[i] == dp[t2] * 2) t2++;
            if (dp[i] == dp[t3] * 3) t3++;
            if (dp[i] == dp[t5] * 5) t5++;

        }
        return dp[n - 1];
    }

    public int minOfThree(int a, int b, int c) {
        int tmp = Math.min(a, b);
        return Math.min(tmp, c);
    }

    public int nthUglyNumber2(int n) {

        if (n <= 0) return -1;
        if (n == 1) return 1;

        int sequence2 = 2;
        int sequence3 = 3;
        int sequence5 = 5;

        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = minOfThree(sequence2, sequence3, sequence5);

            if (dp[i] == sequence2)
                sequence2 = 2 * dp[++index2];
            if (dp[i] == sequence3)
                sequence3 = 3 * dp[++index3];
            if (dp[i] == sequence5)
                sequence5 = 5 * dp[++index5];

        }
        return dp[n - 1];

    }

}
