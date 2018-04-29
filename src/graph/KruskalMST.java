package graph;

import UnionFind.DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author xu
 * 
 *  Time complexity - O(ElogE)
 *  Space complexity - O(E + V)
 *  
 *  
 * http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
 * http://stackoverflow.com/questions/1195872/kruskal-vs-prim
 * 
 * 
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
        for (Edge<Integer> edge : result)  {
        	System.out.println(edge.getVertex1().getID() + " -> " +
					edge.getVertex2().getID() + " : " + edge.getWeight());
        	sum += edge.getWeight();
        }
        System.out.println("Minimum spanning tree weight " + sum);
	}
	public class EdgeComparator implements Comparator<Edge<Integer>>{
		@Override
		public int compare(Edge edge1, Edge edge2) {
			if (edge1.getWeight() <= edge2.getWeight()) 
				return -1;
			else 
				return 1;
		}
	}
	public List<Edge<Integer>> getMST(Graph<Integer> graph) {
		List<Edge<Integer>> allEdges = graph.getAllEdges();
		
		// sort all edges in non-descending order
		Collections.sort(allEdges, new EdgeComparator());
		DisjointSet dSet = new DisjointSet();
		
		// create as many sets as the number of vertices
		
		for (Vertex<Integer> vertex : graph.getAllVertex()) 
			dSet.makeSet(vertex.getID()); 
		
		List<Edge<Integer>> mst = new ArrayList<Edge<Integer>> ();

		for (Edge<Integer> e : allEdges) {
			long root1 = dSet.findSet(e.getVertex1().getID());
			long root2 = dSet.findSet(e.getVertex2().getID());
			if (root1 == root2)
				continue;
			else {
				mst.add(e);
				dSet.union(root1, root2);
			}
			
		}
		return mst;
		
	}
	
	

}
