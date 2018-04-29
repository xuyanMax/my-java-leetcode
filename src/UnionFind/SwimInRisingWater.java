package UnionFind;
//778. Swim in Rising Water
public class SwimInRisingWater {
    class UF {
        int[] id;
        public UF(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
        }
        public int root(int i) {
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }
        public boolean isConnected(int p, int q) {
            return root(p)==root(q);
        }
        public void union(int p, int q) {
            if (isConnected(p, q)) return;
            id[root(p)] = root(q);
        }
    }
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        UF uf = new UF(N*N);
        int time = 0;
        while(!uf.isConnected(0, N*N-1)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] > time) continue;
                    if (i < N-1 && grid[i+1][j]<=time)
                        uf.union(i*N+j, i*N+j+N);
                    if (j < N-1 && grid[i][j+1]<=time)
                        uf.union(i*N+j, i*N+j+1);
                }
            }
            time++;
        }
        return time - 1;
    }
}
