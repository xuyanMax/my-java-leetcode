package unionFind;

public class _130 {
    void solve(int[][] matrix) {
        //二维数组转换为一维数组： 用x*n + y来表示(x,y)
        int m = matrix.length, n = matrix[0].length;
        UF uf = new UF(+1);
        int dummy = m * n; //

        //union 首列与末列的'O'
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 'O')
                uf.union(dummy, i * n);
            if (matrix[i][n - 1] == 'O')
                uf.union(dummy, i * n + n - 1);
        }

        //union 首行与末行的'O'
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 'O') {
                uf.union(dummy, j);
            }
            if (matrix[m - 1][j] == 'O') {
                uf.union(dummy, (m - 1) * n + j);
            }
        }

        //扫描
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 'O') {
                    uf.union(i * n + j, dummy);
                    for (int k = 0; k < 4; k++) {
                        int x = i + dirs[k][0];
                        int y = j + dirs[k][1];
                        if (matrix[x][y] == 'O') {
                            uf.union(dummy, x + n + y);
                        }
                    }
                }
            }
        }
        // 所有不和dummy联通的O都需要被替换为X
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 'O' && (uf.find(i * n + j) == uf.find(dummy))) {
                    matrix[i][j] = 'X';
                }
            }
        }
    }
}