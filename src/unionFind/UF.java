package unionFind;

public class UF {

    // 节点 x 的节点是 parent[x]
    private int[] parents;
    //记录连通分量个数
    private int count;
    //记录树的"重量"
    private int[] size;

    /* 构造函数，n 为图的节点总数 */
    public UF(int n) {
        parents = new int[n];
        size = new int[n];
        count = n;
        // 父节点指针初始指向自己
        // 重量应该初始化 1
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    //用size数组记录树的重量（节点个数），目的是让union后的树拥有平衡性，防止树结构退化为链表，影响操作效率
    public void union(int k1, int k2) {
        int k1p = find(k1);
        int k2p = find(k2);

        if (k1p == k2p)
            return;
        // 小树接到大树下面，较平衡
        if (size[k1p] > size[k2p]) {
            parents[k2p] = k1;
            size[k1p] += size[k2p];
        } else {
            parents[k1p] = k2p;
            size[k2p] += size[k1p];
        }
        // 两个连通分量合并成一个连通分量
        count--;
    }

    //我们能不能进一步压缩每棵树的高度，使树高始终保持为常数？
    //这样find就能以 O(1) 的时间找到某一节点的根节点，相应的，connected和union复杂度都下降为 O(1)。
    public int find(int k) {
        while (k != parents[k]) {
            // 进行路径压缩
            parents[k] = parents[parents[k]];
            k = parents[k];
        }
        return k;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    public int count() {
        return count;
    }

}
