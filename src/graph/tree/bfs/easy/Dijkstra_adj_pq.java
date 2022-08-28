package graph.tree.bfs.easy;

import java.util.*;

/**
 * @author xu
 * <p>
 * http://www.sanfoundry.com/java-program-mplement-dijkstras-algorithm-using-priority_queue/
 * O(VlogV + E) using Fabonacci Heap
 * input:
 * 5
 * <p>
 * 0 9 6 5 3
 * 0 0 0 0 0
 * 0 2 0 4 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * Enter the source
 * 1
 * <p>
 * output:
 * <p>
 * The Shorted Path to all nodes are
 * 1 to 1 is 0
 * 1 to 2 is 8
 * 1 to 3 is 6
 * 1 to 4 is 5
 * 1 to 5 is 3
 */
public class Dijkstra_adj_pq {

    private int dist[];
    private Set<Integer> visited;
    private PriorityQueue<Node> pQueue = new PriorityQueue<>((a, b) -> (a.wgt - b.wgt));
    private int num_vert;
    private int[][] adj;

    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);
        int[][] adjacent;

        System.out.println("Enter the number of vertices");
        int num_vert = cin.nextInt();
        adjacent = new int[num_vert + 1][num_vert + 1];


        System.out.println("Enter the Weighted Matrix for the graph");
        for (int i = 1; i <= num_vert; i++) {
            for (int j = 1; j <= num_vert; j++) {
                adjacent[i][j] = cin.nextInt();
                if (i == j) {
                    adjacent[i][j] = 0;
                    continue;
                }
                // if the input is 0, meaning infinity
                if (adjacent[i][j] == 0) {
                    adjacent[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        // input the source Node
        int src = cin.nextInt();
        Dijkstra_adj_pq dijkstra_AJ = new Dijkstra_adj_pq(num_vert);
        dijkstra_AJ.dijkstra(adjacent, src, 5);

        // print shortest distance from src to all other nodes
        System.out.println("The Shorted Path to all nodes are ");
        for (int i = 1; i <= num_vert; i++) {
            System.out.println(src + " to " + i + " is " + dijkstra_AJ.dist[i]);
        }
        cin.close();
    }

    public Dijkstra_adj_pq(int num) {
        this.num_vert = num;
        dist = new int[num_vert + 1];
        visited = new HashSet<Integer>();
        pQueue = new PriorityQueue<Node>(num_vert, Comparator.comparingInt(Node::getWgt));
        adj = new int[num_vert + 1][num_vert + 1];
        System.out.println("Constructor done");

    }

    public int dijkstra(int[][] adjacent, int src, int target) {

        // initialization
        for (int i = 1; i <= num_vert; i++)
            dist[i] = Integer.MAX_VALUE;

        // copy adjacent list
        for (int i = 1; i <= num_vert; i++)
            for (int j = 1; j <= num_vert; j++)
                adj[i][j] = adjacent[i][j];

        // add source to priority queue
        pQueue.add(new Node(src, 0));
        dist[src] = 0;

        Node u;
        while (!pQueue.isEmpty()) {
            u = pQueue.poll();
            visited.add(u.val);

            if (u.val == target)
                return dist[u.val];

            LinkedList<Node> neighbors = getNeighbor(u);

            for (Node v : neighbors) {
                int edge = adj[u.val][v.val];
                int newDistance = edge + dist[u.val];
                if (!visited.contains(v.val) && newDistance < dist[v.val])
                    dist[v.val] = newDistance;
                pQueue.add(new Node(v.val, dist[v.val]));
            }

        }
        return -1;

    }

    public LinkedList<Node> getNeighbor(Node u) {

        LinkedList<Node> neighbors = new LinkedList<>();

        for (int i = 1; i <= num_vert; i++) {
            // 临近节点需要排除已经visited的点
            if (!visited.contains(i) && adj[u.val][i] != Integer.MAX_VALUE) {
                neighbors.add(new Node(i, adj[u.val][i]));// 这里的weight不重要
            }
        }
        return neighbors;

    }

    class Node implements Comparator<Node> {
        int val;
        int wgt;

        public Node() {

        }

        public Node(int n, int weight) {
            val = n;
            wgt = weight;
        }

        public int getWgt() {
            return wgt;
        }

        @Override
        public int compare(Node A, Node B) {
            return Integer.compare(A.wgt, B.wgt); // return the minimum
        }
    }


}
