package array.binarysearch;

/**
 * Created by xu on 2017/6/21.
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 */
public class Search2DMatrix_2 {

    /*Divide and conquer -> search2dMatrix*/
    // we start search the matrix from either top-right corner or bottom-left corner.
    // it works as if it has two binary search graph.tree.
    // if the target larger than matrix[bottom][left], then the column index is added.
    // otherwise, row index --, it must be in the upper rows since the current row is sorted.
    // in sum, we search it by two directions, column and row.
    // time-complexity: O(N+M)
    // start from top-right corner
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        int col = 0, row = matrix.length - 1;

        while (row >= 0 && col <= matrix[0].length - 1) {
            if (matrix[row][col] < target) {
                col++;
            } else if (matrix[row][col] > target) {
                row--;
            } else
                return true;
        }
        return false;

    }
}
