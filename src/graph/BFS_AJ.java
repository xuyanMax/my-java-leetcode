package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author xu
 * O(E+V)
 * http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 */
public class BFS_AJ {

	public static void main(String[] args) {
		BFS_AJ instance = new BFS_AJ();
		graph g = instance.new graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		g.addEdge(3, 1);
		g.BFS(2);

	}
	class graph {
		private int N; //No of vertices
		private LinkedList<Integer>[] adj; //adjacency list
		
		graph (int n) {// constructor
			N = n;
			adj = new LinkedList [N];
			for (int i=0; i<N; i++)
				adj[i] = new LinkedList<Integer>();
		}
		// function to add an edge into the graph
		void addEdge(int u, int v) {
			adj[u].add(v);
		}
		// BFS traversal starting from src vertex
		void BFS(int src) {
			
			// mark all vertices as visited false, as default
			// or use HashSet to check 
			boolean[] visited = new boolean[N];
			
			// Create a ##queue## for BFS
			Queue<Integer> queue = new LinkedList<>();
			
			visited[src] = true;
			queue.add(src); // add to the last 
			
			
			while( !queue.isEmpty() ) {
				int tmp = queue.poll(); // poll from the head 
					visited[tmp] = true;
					System.out.print(tmp +" ");
					
					for (int neigh : adj[tmp]) {
						if ( !visited[neigh] ) {
							queue.add(neigh);
							visited[neigh] = true;
						}
					}
				
			}
		}
	}
}
