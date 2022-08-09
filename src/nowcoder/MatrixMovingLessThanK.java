package nowcoder;

/**
 * @Author: xyx
 * @Date: 2018-12-01 13:18
 * @Version 1.0
 * <p>
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class MatrixMovingLessThanK {

    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0) return 0;

        int count = 0;
        boolean[][] dp = new boolean[rows][cols];
        dp[0][0] = true;
        count++;//count统计包含原点，初始化第一行，第一列，以及中间部分
        //　初始化部分
        for (int i = 1; i < cols; i++)
            if (dp[0][i - 1] && count(i) <= threshold) {
                dp[0][i] = true;
                count++;
            } else dp[0][i] = false;

        for (int i = 1; i < rows; i++)
            if (dp[i - 1][0] && count(i) <= threshold) {
                dp[i][0] = true;
                count++;
            } else
                dp[i][0] = false;
        // 中间统计部分
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                if ((dp[row - 1][col] || dp[row][col - 1]) && merge(row, col) <= threshold) {
                    count++;
                    dp[row][col] = true;
                } else
                    dp[row][col] = false;
            }
        }
        return count;
    }

    public int merge(int col, int row) {
        return count(col) + count(row);
    }

    public int count(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
