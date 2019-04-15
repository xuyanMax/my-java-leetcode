package arr.medium;

/**
 * @Author: xyx
 * @Date: 2019-01-20 20:29
 * @Version 1.0
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 */
public class SetMatrixZeros {

    public void setZeroes(int[][] matrix) {
        boolean isZeroCol = false;
        boolean isZeroRow = false;
        for (int i = 0; i < matrix.length; i++) { //check the first column
            if (matrix[i][0] == 0) {
                isZeroCol = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) { //check the first row
            if (matrix[0][i] == 0) {
                isZeroRow = true;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) { //check except the first row and column
            for (int j = 1; j < matrix[0].length; j++)
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
        }
        for (int i = 1; i < matrix.length; i++) { //process except the first row and column
            for (int j = 1; j < matrix[0].length; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
        }
        if (isZeroCol) { //handle the first column
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }
        if (isZeroRow) { //handle the first row
            for (int i = 0; i < matrix[0].length; i++)
                matrix[0][i] = 0;
        }
    }

    void setZeroesOpt(int[][] matrix) {
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
        }
    }
}
