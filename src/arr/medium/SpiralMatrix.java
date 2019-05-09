package arr.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/6/21.
 * 54. Spiral Matrix
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 * <p>
 * For example,
 * Given the following matrix:
 * <p>
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();

        if (matrix.length == 0)
            return ret;

        // four pointers
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse Right
            for (int p = left; p <= right; p++) {
                ret.add(matrix[top][p]);
            }
            top++;

            // Traverse Down
            for (int j = top; j <= bottom; j++) {
                ret.add(matrix[j][right]);
            }
            right--;

            if (top <= bottom) {
                // Traverse Left
                for (int j = right; j >= left; j--) {
                    ret.add(matrix[bottom][j]);
                }
            }
            bottom--;

            if (left <= right) {
                // Traver Up
                for (int j = bottom; j >= top; j--) {
                    ret.add(matrix[j][left]);
                }
            }
            left++;
        }
        return ret;
    }
}
