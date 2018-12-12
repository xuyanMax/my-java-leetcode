package dp.hard;

import java.util.Arrays;

public class MaximumRectangle {

    /**
     * height means from top to this position, there are how many ‘1’
     * left means at current position, what is the index of left bound of the rectangle with height[j].
     * 0 means at this position, no rectangle. (现在这个矩形的左边的下标)
     * right means the right bound index of this rectangle. ‘n’ means no rectangle.
     * <p>
     * height[row][col]记录的是(row, col)这个坐标为底座的直方图柱子的高度, 如果这个点是'0', 那么高度当然是0了
     * left[row][col]记录的是(row, col)这个坐标点对应的height可以延申到的最左边的位置
     * right[row][col]记录的是(row, col)这个坐标点对应的height可以延申到的最右边的位置+1
     *
     * @param matrix
     * @return
     */
    public int sol(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix[0].length, m = matrix.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(right, n);

        int[] height = new int[n];

        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            int curr_left = 0, curr_right = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curr_left);
                } else {
                    left[j] = curr_left;
                    curr_left = j + 1;
                }
            }

            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curr_right);
                } else {
                    right[j] = n;
                    curr_right = j;
                }
            }
            for (int j = 0; j < n; j++)
                maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
        }
        return maxArea;
    }
    /* if(matrix.empty()) return 0;
    const int m = matrix.size();
    const int n = matrix[0].size();
        int left[n], right[n], height[n];
        fill_n(left,n,0); fill_n(right,n,n); fill_n(height,n,0);
        int maxA = 0;
        for(int i=0; i<m; i++) {
            int cur_left=0, cur_right=n;
            for(int j=0; j<n; j++) { // compute height (can do this from either side)
                if(matrix[i][j]=='1') height[j]++;
                else height[j]=0;
            }
            for(int j=0; j<n; j++) { // compute left (from left to right)
                if(matrix[i][j]=='1') left[j]=max(left[j],cur_left);
                else {left[j]=0; cur_left=j+1;}
            }
            // compute right (from right to left)
            for(int j=n-1; j>=0; j--) {
                if(matrix[i][j]=='1') right[j]=min(right[j],cur_right);
                else {right[j]=n; cur_right=j;}
            }
            // compute the area of rectangle (can do this from either side)
            for(int j=0; j<n; j++)
                maxA = max(maxA,(right[j]-left[j])*height[j]);
        }
        return maxA;*/
}
