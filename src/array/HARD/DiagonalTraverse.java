package array.HARD;

/*
* 498. Diagonal Traverse
DescriptionHintsSubmissionsDiscussSolution
Given a matrix of M x N elements (M rows, N columns),
return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]


*/
public class DiagonalTraverse {
    // step: d
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;

        int[] result = new int[m * n];
        int row = 0, col = 0, d = 1;

        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row -= d;
            col += d;

            // Order of "if " is key here…
            // row col 同时"出界"

            // 以下两个if内部无序
            // [2,1]->[3,0]->[2,2]
            if (row >= m) { row = m - 1; col += 2; d = -d;}
            // 3*3 matrix 比如 [0,2] -> [-1,3] -> [1,2]的过程
            if (col >= n) { col = n - 1; row += 2; d = -d;}

            // 下列两个if内部无序
            if (col < 0)  { col = 0; d = -d;}
            if (row < 0)  { row = 0; d = -d;}
        }

        return result;
    }
}
