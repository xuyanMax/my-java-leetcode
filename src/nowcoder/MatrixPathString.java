package nowcoder;

/**
 * @Author: xyx
 * @Date: 2018-12-01 13:11
 * @Version 1.0
 */
public class MatrixPathString {
    private int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        // 回溯算法
        if (matrix == null || matrix.length == 0 || str == null || str.length == 0) return false;
        boolean[] visited = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(matrix, rows, cols, i, j, str, 0, visited))
                    return true;
            }
        }
        return false;

    }

    public boolean dfs(char[] matrix, int rows, int cols, int i, int j, char[] str, int index, boolean[] visited) {
        if (index == str.length) return true;
        // 映射二位matrix到一纬
        int indMatrix = i * cols + j;
        //边界判断+字符判断+是否访问
        if (i < 0 || i >= rows || j < 0 || j >= cols || str[index] != matrix[indMatrix] || visited[indMatrix])
            return false;
        //回溯
        visited[indMatrix] = true;
        for (int n = 0; n < dir.length; n++) {
            int[] d = dir[n];
            if (dfs(matrix, rows, cols, i + d[0], j + d[1], str, index + 1, visited))
                return true;
        }
        // unmake it
        visited[indMatrix] = false;
        return false;
    }
}
