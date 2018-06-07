package arr.medium;

/**
 * Created by xu on 15/09/2017.

A zero-indexed arr A consisting of N different integers is given.
The arr contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:

S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.

Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an arr A consisting of N integers,
return the size of the largest set S[K] for this arr.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation:
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of arr A is an integer within the range [0, N-1].


*/
public class ArrayNesting {

    // 时间复杂度O(n^2)
    // 空间O(1)
    // 容易造成重复计算，耗时
    public int arrayNesting_brute_force(int[] nums) {
        int res = 0;
        int count = 0;

        for (int i=0; i<nums.length; i++) {
            int start = nums[i];

            do {
                start = nums[start];
                count++;
            }
            while (start !=  nums[i]);
            res = Math.max(count, res);
        }
        return res;
    }
    //nums元素都在1-N-1之间，因此不存在越界的问题
    // 再一个，由于元素的唯一性，元素形成的circle是唯一的，即，没有两个元素能够同时跳到K，
    // 因此不存在nums[a] == nums[b] == k 当 a != b时
    // 如果按照上一种方法，尝试每一个元素作为起始点，
    // 则会造成多余的运算，因为同一个circle里的任意一个元素作为起点，都会形成同样一个circle
    // 因此使用一个visited数组来记录哪些元素已经走过
    public int arrayNesting_visited(int[] nums) {
        int res = 0;
        boolean[] visited = new boolean[nums.length];
        for (int i=0; i<nums.length; i++) {
            if (!visited[i]) {
                int start = nums[i];
                int count = 0;
                do {
                    visited[start] = true;
                    start = nums[start];
                    count++;
                }while (start != nums[i]);
                res = Math.max(res, count);
            }
        }
        return res;
    }
    public int AN_visited_improved(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int max = 0;
        for (int num:nums){
            if (!visited[num]){
                int count = 0;
                while (!visited[num]){
                    visited[num] = true;
                    num = nums[num];
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
    // 时间O(n)
    // 空间O(1)
    // 利用原有的array[]将访问过的元素设置为一个大于20000的数值即可
    public static int arrayNesting_no_visited(int[] nums) {
        int max = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i];
                int count = 0;
                while (nums[start] != Integer.MAX_VALUE) {
                    int tmp = start;
                    start = nums[start];
                    nums[tmp] = Integer.MAX_VALUE;
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        return max;

    }
     public static void main(String[] args){
         System.out.println(arrayNesting_no_visited(new int[]{5,4,0,3,1,6,2}));
     }

    /**
     *
     * @author xu
     *
     * 64. Minimum Path Sum
     * dp solution
     * Given a m x n grid filled with non-negative numbers,
     * find a path from top left to bottom right which minimizes the sum of
     * all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     *
     */
    public static class MinimumSumPath {

        public static int solution(int[][] grid) {

            int col = grid[0].length;
            int row = grid.length;
            int[][] dp = new int[row][col];

            dp[0][0] = grid[0][0];

            for (int i=1; i<col; i++)
                dp[0][i] += dp[0][i-1];
            for (int i=1; i<col; i++)
                dp[i][0] += dp[i][0];

            for (int i=1; i<row; i++)
                for (int j=1; j<col; j++)
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];

            return dp[row - 1][col - 1];
        }

        public static int solution2(int[][] grid) {
            int col = grid[0].length;
            int[] dp = new int[col];

            dp[0] = grid[0][0];
            for (int i=1; i<col; i++)
                dp[i] += dp[i-1];

            for (int[] row : grid) {
                for (int i=0; i<col; i++) {
                    if (i == 0)
                        dp[i] = dp[i] + row[i];
                    else
                        dp[i] = Math.min(dp[i-1], dp[i]) + row[i];
                }
            }

            return dp[col - 1];
        }

    }
}
