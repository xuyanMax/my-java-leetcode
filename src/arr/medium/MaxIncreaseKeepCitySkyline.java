package arr.medium;

//807. Max Increase to Keep City Skyline
//https://leetcode.com/problems/max-increase-to-keep-city-skyline/description
public class MaxIncreaseKeepCitySkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid[0].length == 0) return 0;
        int[] rows = new int[grid.length];// max element in each row
        int[] cols = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                rows[i] = Math.max(rows[i], grid[i][j]);
                cols[j] = Math.max(cols[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                res += Math.min(rows[i], cols[j]) - grid[i][j];// smaller than the smaller of (rows[i], cols[j])

        return res;
    }
}
