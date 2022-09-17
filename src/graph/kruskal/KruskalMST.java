package graph.kruskal;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import unionFind.DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xu
 * <p>
 * Time complexity - O(ElogE)
 * Space complexity - O(E + V)
 * <p>
 * <p>
 * http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
 * http://stackoverflow.com/questions/1195872/kruskal-vs-prim
 */
public class KruskalMST {//最小生成树

    public static void main(String[] args) {

        Graph graph = new Graph<Integer>(false);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 3);
        graph.addEdge(2, 4, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 4, 3);
        graph.addEdge(4, 7, 2);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 7, 8);

        KruskalMST mst = new KruskalMST();
        List<Edge<Integer>> result = mst.getMST(graph);
        int sum = 0;
        for (Edge<Integer> edge : result) {
            System.out.println(edge.getVertex1().getID() + " -> " +
                    edge.getVertex2().getID() + " : " + edge.getWeight());
            sum += edge.getWeight();
        }
        System.out.println("Minimum spanning graph.tree weight " + sum);
    }

    public class EdgeComparator implements Comparator<Edge<Integer>> {
        @Override
        public int compare(Edge edge1, Edge edge2) {
            if (edge1.getWeight() <= edge2.getWeight())
                return -1;
            else
                return 1;
        }
    }

    /**
     * 将所有边按照权重从小到大排序，从权重最小的边开始遍历，如果这条边和mst中的其它边不会形成环，则这条边是最小生成树的一部分，
     * 将它加入mst集合；否则，这条边不是最小生成树的一部分，不要把它加入mst集合。
     *
     * @param graph
     * @return
     */
    public List<Edge<Integer>> getMST(Graph<Integer> graph) {
        List<Edge<Integer>> allEdges = graph.getAllEdges();

        // sort all edges in non-descending order
        Collections.sort(allEdges, Comparator.comparingInt(Edge::getWeight));
        DisjointSet dSet = new DisjointSet();

        // create as many sets as the number of vertices
        for (Vertex<Integer> vertex : graph.getAllVertex())
            dSet.makeSet(vertex.getID());

        List<Edge<Integer>> res_mst = new ArrayList<Edge<Integer>>();

        for (Edge<Integer> e : allEdges) {

            if (dSet.connected(e.getVertex1().getID(), e.getVertex2().getID()))
                continue;
            else {
                // add to mst
                res_mst.add(e);
                //union
                dSet.union(dSet.findSet(e.getVertex1().getID()), dSet.findSet(e.getVertex2().getID()));
            }
        }
        return res_mst;

    }


}
