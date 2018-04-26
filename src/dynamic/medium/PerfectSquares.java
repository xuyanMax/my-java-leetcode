package dynamic.medium;
//279. Perfect Squares

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.min;

public class PerfectSquares {

    // static dynamic solution
    static List<Integer> res = new ArrayList<>();

    public int numSquares(int n) {
        if (res.size() == 0)
            res.add(0);

        while (res.size() <= n) {
            int m = res.size();
            int min = Integer.MAX_VALUE;
            for (int j=1; j*j<= m; j++)
                min = min(min, res.get(m - j*j) + 1);
            res.add(min);
        }
        return res.get(n);
    }

    public int numSquaressol2(int n){
        int[] dp = new int[n+1];// dynamic[i]: least number of squares to sum to i;
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i=1; i<=n; i++){
            for (int j=1; j*j<=i; j++) {
                dp[i] = min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[n+1];
    }
}
