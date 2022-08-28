package array.divide_conquer;

public class Search2DMatrix2 {
    public static void main(String[] args) {

    }

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

    // https://discuss.leetcode.com/topic/33240/java-an-easy-to-understand-divide-and-conquer-method
	/*
	 * First, we divide the matrix into four quarters as shown below:

		  zone 1      zone 2
		*  *  *  * | *  *  *  *
		*  *  *  * | *  *  *  *
		*  *  *  * | *  *  *  *
		*  *  *  * | *  *  *  *
		-----------------------
		*  *  *  * | *  *  *  *
		*  *  *  * | *  *  *  *
		*  *  *  * | *  *  *  *
		*  *  *  * | *  *  *  *
		  zone 3      zone 4
	    
	    We then compare the element in the center of the matrix with the target. There are three possibilities:

			center < target. In this case, we discard zone 1 because all elements in zone 1 are less than target.
		
			center > target. In this case, we discard zone 4.
		
			center == target. return true.
		

	 */
    public static boolean searchMatrixDivideAndConquer(int[][] matrix, int target) {


        return false;
    }
}
