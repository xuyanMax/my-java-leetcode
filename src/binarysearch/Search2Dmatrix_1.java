package binarysearch;

/**
 * Created by xu on 2017/6/21.
 */
public class Search2Dmatrix_1 {
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties:
     * <p>
     * Integers in each row are SORTED FROM LEFT TO RIGHT.
     * The FIRST integer of each row is greater than the LAST integer of the previous row.
     * For example,
     * <p>
     * Consider the following matrix:
     * <p>
     * [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     */

    //Don't treat it as a 2D matrix, just treat it as a sorted list
    public boolean searchMatrix(int[][] mat, int target) {
        if (mat == null || mat.length == 0)
            return false;

        int col = mat[0].length;
        int left = 0, right = mat.length * col - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (mat[mid / col][mid % col] > target)
                right = mid - 1;
            else if (mat[mid / col][mid % col] < target)
                left = mid + 1;
            else
                return true;
        }
        return false;
    }

}
