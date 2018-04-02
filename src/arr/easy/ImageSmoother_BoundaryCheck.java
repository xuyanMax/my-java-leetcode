package arr.easy;

/**
 * Created by xu on 16/09/2017.
 */
public class ImageSmoother_BoundaryCheck {
    private int[][] dir = new int[][]{{-1,-1},{-1,0},{0,-1},{0,1},{1,1},{1,0}};

    public int[][] imageSmoother(int[][] M) {

        if (M == null) return null;
        int rows = M.length;
        if (rows == 0) return new int[0][];
        int cols = M[0].length;

        int result[][] = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int count = 0;
                int sum = 0;

                for (int incR : new int[]{-1, 0, 1}) {
                    for (int incC : new int[]{-1, 0, 1}) {
                        if (isValid(row + incR, col + incC, rows, cols)) {
                            count++;
                            sum += M[row + incR][col + incC];
                        }
                    }
                }
                result[row][col] = sum / count;
            }
        }

        return result;

    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
    private void test(int[][] nums){
        int[][] ret = new int[nums.length][nums[0].length];
        for (int i=0; i<nums.length; i++) {
            for (int j=0; j<nums.length; j++) {
                int sum = 0;
                int count = 0;
                for (int[] d : dir) {
                    int r = d[0] + i;
                    int c = d[1] + j;
                    if (r >= 0 || r < nums.length || c >= 0 || c < nums[0].length) {
                        count++;
                        sum += nums[r][c];
                    }
                }
                ret[i][j] = sum/count;
            }
        }
    }
}
