package array;

/**
 * Created by xu on 2017/6/25.
 */
/*
Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/
public class SpiralMatrix_2 {
/*

The only tricky part is that when I traverse left or up I have to check whether
the row or col still exists to prevent duplicates.

*/

    public int[][] spiral(int n) {
        int[][] mat = new int[n][n];
        if (n < 1)
            return mat;

        int top = 0, bottom = n-1, left = 0, right = n-1;
        int num = 1;
        while (top<=bottom && left<=right) {
            for (int i=left; i<=right; i++)
                mat[top][i] = num++;
            top++;

            for (int i=top; i<=bottom; i++)
                mat[i][right] = num++;
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    mat[bottom][i] = num++;
            }
            bottom--;

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    mat[i][left] = num++;
            }
            left++;

        }
        return mat;

    }
}
