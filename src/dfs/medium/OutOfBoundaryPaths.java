package dfs.medium;

import java.util.Arrays;

/**
 * Created by xu on 21/08/2017.
 */
public class OutOfBoundaryPaths {

    public int findPaths_bruteForce(int m, int n, int N, int i, int j) {
        //到达边界外，返回1种方法
        if (i == m || j == n || i < 0 || j < 0)
            return 1;
        //仍在边界内，N==0，返回0
        if (N == 0)
            return 0;
        //向四个方向探索
        return
                findPaths_bruteForce(m, n, N - 1, i - 1, j)
                        + findPaths_bruteForce(m, n, N - 1, i, j - 1)
                        + findPaths_bruteForce(m, n, N - 1, i + 1, j)
                        + findPaths_bruteForce(m, n, N - 1, i, j + 1);
    }

    // with memoize
    //memo[i][j][k] 来存储在节点[i][j]with剩余步数k时到达把边界存在的可能

    private final int M = 1000000007;

    public int findPaths_bruteForce_memoize(int m, int n, int N, int i, int j) {
        int[][][] memo = new int[m][n][N + 1];

        for (int[][] arr_2d : memo) {
            for (int[] arr_1d : arr_2d)
                Arrays.fill(arr_1d, -1);
        }
        return findPaths_bruteForce_memoize(m, n, N, i, j, memo);
    }

    public int findPaths_bruteForce_memoize(int m, int n, int N, int i, int j, int[][][] memo) {

        if (i == m || j == n || i < 0 || j < 0)
            return 1;
        // left steps check
        if (N == 0)
            return 0;

        if (memo[i][j][N] >= 0)
            return memo[i][j][N];

        memo[i][j][N] =
                ((findPaths_bruteForce_memoize(m, n, N - 1, i - 1, j, memo)
                        + findPaths_bruteForce_memoize(m, n, N - 1, i + 1, j, memo)) % M

                        + (findPaths_bruteForce_memoize(m, n, N - 1, i, j + 1, memo)
                        + findPaths_bruteForce_memoize(m, n, N - 1, i, j - 1, memo)) % M
                ) % M;
        return memo[i][j][N];
    }
}
