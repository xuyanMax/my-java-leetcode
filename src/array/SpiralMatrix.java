package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/6/21.
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();

        if (matrix.length == 0) {
            return ret;
        }

        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse Right
            for (int j = left; j <= right; j ++) {
                ret.add(matrix[top][j]);
            }
            top++;

            // Traverse Down
            for (int j = top; j <= bottom; j ++) {
                ret.add(matrix[j][right]);
            }
            right--;

            if (top <= bottom) {
                // Traverse Left
                for (int j = right; j >= left; j --) {
                    ret.add(matrix[bottom][j]);
                }
            }
            bottom--;

            if (left <= right) {
                // Traver Up
                for (int j = bottom; j >= top; j --) {
                    ret.add(matrix[j][left]);
                }
            }
            left ++;
        }
        return ret;
    }
}
