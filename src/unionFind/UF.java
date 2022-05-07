package unionFind;

public class UF {

    //记录连通分量个数
    private int[] parents;
    //存储若干树
    private int count;
    //记录树的"重量"
    private int[] size;

    public UF(int n) {
        parents = new int[n];
        size = new int[n];
        count = n;

    }

    //用size数组记录树的重量（节点个数），目的是让union后的树拥有平衡性，防止树结构退化为链表，影响操作效率
    public void union(int k1, int k2) {
        int k1p = find(k1);
        int k2p = find(k2);

        if (k1p == k2p)
            return;
        if (size[k1p] > size[k2p]) {
            parents[k2p] = k1;
            size[k1p] += size[k2p];
        } else {
            parents[k1p] = k2p;
            size[k2p] += size[k1p];
        }
        count--;
    }

    public int find(int k) {
        while (k != parents[k]) {
            parents[k] = parents[parents[k]];
            k = parents[k];
        }
        return k;
    }

    public int count() {
        return count;
    }

}
