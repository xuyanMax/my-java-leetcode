package nowcoder;
import java.util.ArrayList;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/9b4c81a02cd34f76be2659fa0d54342a?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]顺时针打印矩阵
 * 热度指数：352108时间限制：1秒空间限制：32768K
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class ClockwisePrintMatrix {

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new ArrayList<>();

        ArrayList<Integer> res = new ArrayList<>();
        int rowBegin = 0, colBegin = 0, rowEnd = matrix.length - 1, colEnd = matrix[0].length-1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++)
                res.add(matrix[rowBegin][i]);
            rowBegin++;
            for (int j = rowBegin; j <= rowEnd; j++)
                res.add(matrix[j][colEnd]);
            colEnd--;
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; i--)
                    res.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            if (colBegin <= colEnd) {
                for (int j = rowEnd; j >= rowBegin; j--)
                    res.add(matrix[j][colBegin]);
            }
            colBegin++;
        }
        return res;
    }
}
