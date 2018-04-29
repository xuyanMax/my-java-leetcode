package bfs.easy;

import java.util.Arrays;

/**
 * 
 * @author xu
 *
 * The program is for adjacency matrix representation of the graph
 * 9 nodes, consist of an adjacent list graph[i][j] = '0' means unreachable from i to j. 
 * 
 */
public class Dijkstra_adj_naive {

	public static void main(String[] args) {
		
		
		int[][] graph = new int[][] {
			{0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}};
            Dijkstra_adj_naive shortestPath = new Dijkstra_adj_naive();
            int src = 0;
            shortestPath.dijkstra(graph, src);
	}
	
	// main function that  implements single source
	// shortest path algorithm for adjacent list represented graph
	public void dijkstra(int[][] graph, int src){
		int N = graph.length;

		// 两个存储结构
		int[] dist = new int[graph.length];
		boolean[] visited = new boolean[graph.length];
		// 初始化：距离初始无穷大，
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		
		// source setting
		dist[src] = 0;

		for (int i=0; i<N-1; i++) {
		//pick the min distance from any adjacent nodes of src 
			int u = getMinDistNode(dist, visited);
			
		// set u visited
			visited[u] = true;
			// updateHighestHeightBtwLR neighbors' distance of u
			for (int j=0; j<N; j++) {
				// updateHighestHeightBtwLR the neighbor v only if it is not visited, there is an edge from u to v, and total weight
				// of path from src to v through u is smaller than current value of dist[v] (relaxing)
				if (visited[j] == false && graph[u][j]!=0 && (dist[u] + graph[u][j]) < dist[j]) { // 0 indicates INFINITY 
					dist[j] = dist[u] + graph[u][j];
				}
			}
			
			
		}
		printSolution(dist);
	}
	public int getMinDistNode(int[] dist, boolean[] visited){
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i=0; i<dist.length; i++) {
			if (visited[i] == false && dist[i] <= min)  {
				min = dist[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	public void printSolution(int[] dist){
		System.out.println("Vertex\t\tDistance from source");
		for (int i=0; i<dist.length; i++){
			
			System.out.println(i + "\t\t" + dist[i]);
		}
	}

	

}
