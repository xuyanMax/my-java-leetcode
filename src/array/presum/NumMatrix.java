package array.presum;

/**
 * Created by xu on 2022/9/20 09:30.
 * 304. 二维区域和检索 - 矩阵不可变
 * 计算二维矩阵中子矩阵的元素之和：
 */
public class NumMatrix {
    // 构造前缀和矩阵
    //presum[i][j]记录矩阵[0,0,i,j] 的元素和
    int[][] presum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) return;
        presum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                presum[i][j] = presum[i][j - 1] + presum[i - 1][j] + matrix[i][j] - presum[i - 1][j - 1];
    }

    // 返回矩阵【x1,y1,x2,y2】的元素和
    public int sumRange(int x1, int x2, int y1, int y2) {
        return presum[x2][y2] - presum[x2][y1] - presum[x1][y2] + presum[x1][y1];
    }
}
