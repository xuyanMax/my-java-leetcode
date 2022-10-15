package array.presum;

import org.junit.Test;

/**
 * Created by xu on 2022/9/20 09:30.
 * 304. 二维区域和检索 - 矩阵不可变
 * 计算二维矩阵中子矩阵的元素之和：
 */
public class NumMatrix {
    // 构造前缀和矩阵

    public int sumRange(int[][] matrix, int x1, int y1, int x2, int y2) {
        // 返回矩阵【x1,y1,x2,y2】的元素和
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) return -1;
        //presum[i][j]记录矩阵[0,0,i,j] 的元素和
        int[][] presum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                presum[i][j] = presum[i][j - 1] + presum[i - 1][j] + matrix[i - 1][j - 1] - presum[i - 1][j - 1];

        return presum[x2 + 1][y2 + 1] - presum[x2 + 1][y1] - presum[x1][y2 + 1] + presum[x1][y1];
    }


    @Test
    public void test() {
        int[][] matrix = new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};

        System.out.println(sumRange(matrix, 0, 0, 1, 2));
        System.out.println(sumRange(matrix, 2, 1, 4, 3)); //[4,3]-[2,1] = [4,3]-[4,0]-[1,3] + [1,0]
        System.out.println(sumRange(matrix, 2, 1, 4, 2));
        System.out.println(sumRange(matrix, 0, 0, 1, 0));
    }
}

