package graph.tree.dfs.easy;

/**
 * Created by xu on 03/08/2017.
 * 547. Friend Circles
 * There are N students in a class. Some of them are friends, while some are not.
 * Their friendship is transitive in nature. For example, if A is a direct friend of B,
 * and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of
 * students who are direct or indirect friends.
 * <p>
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1,
 * then the ith and jth students are direct friends with each other, otherwise not. And you have to output the
 * total number of friend circles among all the students.
 * <p>
 * Example 1:
 * Input:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * <p>
 * Output: 2
 * <p>
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * <p>
 * Output: 1
 * <p>
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * <p>
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 */
public class FriendCircles {// UNION FOUND

    public int findCircleNum(int[][] M) {

        boolean[] visited = new boolean[M.length];
        int circles = 0;
        for (int row = 0; row < M.length; row++) {
            if (!visited[row]) {
                dfs(M, visited, row);
                circles++;
            }
        }
        return circles;
    }

    public void dfs(int[][] M, boolean[] visited, int row) {
        for (int col = 0; col < M.length; col++) {
            //如果 【row】与【col】是直接朋友，那么看【col】与哪些是直接朋友，因此dfs【col】
            //并且 【col】之前未参与过count的计数
            if (row == col) {
                visited[col] = true;
                continue;
            }
            if (M[row][col] == 1 && !visited[col]) {
                visited[col] = true;
                dfs(M, visited, col);
            }
        }
    }
}
