package a_OA.nowcoder;

/**
 * https://blog.csdn.net/niushuai666/article/details/6662911
 * <p>
 * 某省调查城镇交通状况，得到现有城镇道路统计表，表中列出了每条道路直接连通的城镇。
 * 省政府“畅通工程”的目标是使全省任何两个城镇间都可以实现交通（但不一定有直接的道路相连，只要互相间接通过道路可达即可）。
 * 问最少还需要建设多少条道路？
 * <p>
 * <p>
 * Input
 * 测试输入包含若干测试用例。每个测试用例的第1行给出两个正整数，分别是城镇数目N ( < 1000 )和道路数目M；
 * 随后的M行对应M条道路，每行给出一对正整数，分别是该条道路直接连通的两个城镇的编号。为简单起见，城镇从1到N编号。
 * 注意:两个城市之间可以有多条道路相通,也就是说
 * 3 3
 * 1 2
 * 1 2
 * 2 1
 * <p>
 * 这种输入也是合法的
 * 当N为0时，输入结束，该用例不被处理。
 * <p>
 * <p>
 * Output
 * 对每个测试用例，在1行里输出最少还需要建设的道路数目。
 */
public class UnionFind {

    /**
     * @param mat
     * @return
     */
    public int sol(int[][] mat) {
        int[] parent = new int[mat.length + 1];
        int[] block = new int[mat.length + 1];

        for (int i = 1; i <= parent.length; i++)
            parent[i] = i;

        for (int i = 0; i < mat.length; i++) {
            int p1 = find(parent, mat[i][0]);
            int p2 = find(parent, mat[i][1]);
            if (p1 != p2) {
                parent[find(parent, p1)] = p2;
            }
        }
        for (int i = 1; i <= parent.length; i++) {
            block[find(parent, i)] = 1;
        }

        int cnt = 1;
        for (int i = 1; i <= parent.length; i++)
            if (block[i] == 1)
                cnt++;

        return cnt - 1;
    }

    public int find(int[] parent, int x) {
        int i = x;
        while (i != parent[i]) {
            i = parent[i];
        }

        while (x != parent[x]) {
            int j = parent[x];
            parent[x] = i;
            x = j;
        }
        return i;
    }
}
