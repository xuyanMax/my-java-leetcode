package graph;

import java.util.Arrays;
import java.util.Comparator;

// this version we use simple val structure to implement the Kruskals' algorithm


// http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
public class KruskalMST2 {

    public static void main(String[] args) {

        // store result mst
        int V = 4;  // Number of vertices in mst2
        int E = 5;  // Number of edges in mst2
        KruskalMST2 mst2 = new KruskalMST2(V, E);
        mst2.edges[0].src = 0;
        mst2.edges[0].dst = 1;
        mst2.edges[0].weight = 10;

        // add edges 0-2
        mst2.edges[1].src = 0;
        mst2.edges[1].dst = 2;
        mst2.edges[1].weight = 6;

        // add edges 0-3
        mst2.edges[2].src = 0;
        mst2.edges[2].dst = 3;
        mst2.edges[2].weight = 5;

        // add edges 1-3
        mst2.edges[3].src = 1;
        mst2.edges[3].dst = 3;
        mst2.edges[3].weight = 15;

        // add edges 2-3
        mst2.edges[4].src = 2;
        mst2.edges[4].dst = 3;
        mst2.edges[4].weight = 4;

        mst2.getMST();

    }
    int V, E; // #vertices, #edges
    Edge[] edges;

    // create a mst2 with v vertices and e edges
    public KruskalMST2(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];

        for (int i=0; i<E; i++)
            edges[i] = new Edge();

    }
    // Find set of an element i and use path compression as well
    public int find(disJoint[] ds, int i) {
        if (ds[i].parent != i)
            ds[i].parent = find(ds, ds[i].parent); // path compression

        return ds[i].parent;
    }
    // do union to two sets of x and y using union by rank
    public void union(disJoint[]ds, int x, int y) {
        int xroot = ds[x].parent;
        int yroot = ds[y].parent;

        if (ds[xroot].rank <= ds[yroot].rank) {
            ds[xroot].parent = yroot;
            ds[yroot].rank = (ds[xroot].rank == ds[yroot].rank) ? ds[yroot].rank + 1 : ds[yroot].rank;
        }
        else
            ds[yroot].parent = xroot;

    }
    public void getMST() {
        // 最小权重生成树

        Edge[] mst = new Edge[V];
        for (int i=0; i<V; i++)
            mst[i] = new Edge();

        int count = 0;//An index variable, used for mst[]
        int i = 0; // used to index the sorted edges

        // sort all edges in non descending order
        // if we are not allowed to change the given mst2, we can create a copy of the given edges
        Arrays.sort(edges, new Edge());

        // create as many disjoint sets as the number of vertices
        // set its rank 0; set it parent itself
        disJoint[] ds = new disJoint[V];

        // make #V set
        for (i=0; i<V; i++) {
            ds[i] = new disJoint();
            ds[i].rank = 0;
            ds[i].parent = i;
        }

        // minimum spanning tree contains at most V-1 edges
        i = 0; // index used to pick next edge

        while (count < V - 1) {// E == V - 1是最少边
            //依次挑选最小的边
            Edge curEdge = new Edge();
            curEdge = edges[i++];

            int xroot = find(ds, curEdge.src);
            int yroot = find(ds, curEdge.dst);

            if (xroot != yroot) {// if including this edges does not introduce cycle, add it; otherwise go to next edges

                mst[count++] = curEdge;

                union(ds, xroot, yroot);
            }
        }
        int total_weight = 0;

        for (i=0; i<count; i++) {//输出 count 个 边
            Edge edge = mst[i];
            System.out.println(edge.src + " -> " + edge.dst + " : " + edge.weight);
            total_weight += edge.weight;
        }
        System.out.println("Minimum weight: "+total_weight);
    }


    class Edge implements Comparator<Edge>{

        int src, dst, weight;
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.weight-e2.weight;
        }

    }
    class disJoint{
        int parent, rank;

    }


}
