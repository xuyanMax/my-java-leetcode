package graph;

/**
 * 
 * @author xu
 * 
 * http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
 * time complextiy O(V^2)
 */
public class PrimMST2 {

	private static int V;
	public static void main(String[] args) {
	      /* Let us create the following graph
		        2    3
		     (0)--(1)--(2)
		     |    / \   |
		     6| 8/   \5 |7
		     | /      \ |
		     (3)-------(4)
		          9          */
		int[][] graph = new int[][]{{0,2,0,6,0},{2,0,3,8,5},{0,3,0,0,7},{6,8,0,0,9},{0,5,7,9,0}};
		PrimMST2 primMST2 = new PrimMST2(graph.length);
		primMST2.primMST(graph);

	}
	public PrimMST2(int size) {
		V = size;
	}
	public int minKey(int[] key, Boolean[] mstSet) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		
		// find the vertex with minimum key and not included in MST
		for (int v = 0; v<V; v++) {
			if (key[v] < min && mstSet[v] == false) {
				min = key[v];
				minIndex = v;
				
			}
		}
		return minIndex;
	}
	
	// construct and print mst for a graph represented by adjacency list
	public void primMST (int[][] graph) {
		//存储 已经建成的mst
		int[] parent = new int[V];
		//代表  key value 是否已经包含在mst内
		Boolean[] mstSet = new Boolean[V];

		// 最小权重边
		int[] key = new int[V];
		
		// initialize all keys as infinity， unvisited
		for (int v=0; v<V; v++)
			key[v] = Integer.MAX_VALUE;
		
		// always include first vertex in mst 
		key[0] = 0;// make key zero so that this vertex is picked first
		parent[0] = -1; // set its parent null or -1;
		
		
		// mst should have V-1 edges left
		for (int i = 0; i<V; i++) {
			//所有的key[]中最短的边
			int u = minKey(key, mstSet);
			
			// add the picked vertex to mstSet
			mstSet[u] = true;
			
			// consider u's adjacent nodes, update their parents and key value
			// only those not included in mst set && its edge value non-zero && its edge value < key value
			for (int v=0; v<V; v++){
				// 0 indicates impassable
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
					key[v] = graph[u][v];
					parent[v] = u;
				}
			}
		}
		print(parent, graph, V);
	}
	
	// print the constructed mst stored in parent
	public void print(int[] parent, int[][] graph, int V) {
		System.out.println("Edge   Weight");
		for (int i=1; i<V; i++) {
			System.out.println(parent[i] + " - " + i + "  " + graph[parent[i]][i]);
		}
	}

}
