package dfs;

import java.util.LinkedList;

/**
 * @author xu
 * http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
 */
public class Dfs {

    public static void main(String[] args) {
        Dfs instance = new Dfs();
        graph g = instance.new graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.addEdge(3, 1);
        g.DFS();

    }

    class graph {
        private int N; //No of vertices
        private LinkedList<Integer>[] adj; //adjacency list

        graph(int n) {// constructor
            N = n;
            //初始化
            adj = new LinkedList[N];
            for (int i = 0; i < N; i++)
                adj[i] = new LinkedList<Integer>();
        }

        // function to add an edge into the graph
        void addEdge(int u, int v) {
            adj[u].add(v);
        }

        // DFS traversal starting from src vertex
        void DFS() {

            // mark all vertices as visited false, as default
            // or use HashSet to check
            boolean[] visited = new boolean[N];

            // Create a ##queue## for BFS
//			LinkedList<Integer> queue = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                if (!visited[i])
                    DFSUtil(i, visited);
            }

        }

        void DFSUtil(int u, boolean[] visited) {
            visited[u] = true;
            System.out.print(u + " ");
            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    DFSUtil(v, visited);
                }
            }
        }
    }

}
