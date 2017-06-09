package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

// 未完待续
public class primMST_pq {

	public static void main(String[] args) {

		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			
			primMST_pq ins = new primMST_pq();
			
			int n = cin.nextInt(); // number of vertices
			int m = cin.nextInt(); // number of edges
			
			int[][] edges = new int[m][3];
			// input format - src, dsr, weight
			for (int i=0; i<m; i++) {
				edges[i][0] = cin.nextInt();
				edges[i][1] = cin.nextInt();
				edges[i][2] = cin.nextInt();
			}
		
			Map<Integer, Node> allVertices = new HashMap<>();
			for (int[] row : edges) {
				allVertices.put(row[0], ins.new Node(row[0]));
				allVertices.put(row[1], ins.new Node(row[1]));
			}
			
			// initialize priority queue
			PriorityQueue<Node> pQueue = new PriorityQueue<>(allVertices.size(), ins.new Node());
			
			for (Node node : allVertices.values()) { // put all vertices into pQueue and set weight to INFINITY
				pQueue.add(node);
			}
			Node startVertex = allVertices.values().iterator().next();
			
			pQueue.remove(startVertex);
			startVertex.priority = 0;
			pQueue.add(startVertex);
			
			while (!pQueue.isEmpty()) {
				Node current = pQueue.poll();
				
				//for () // get adjacent nodes
			}
			
		}
		cin.close();
	}
	class Node implements Comparator<Node>{
		int id;
		int priority;
		Node parent;
		List<Edge> edges = new ArrayList<>();
		List<Node> adjacentVertex = new ArrayList<>();
		
		Node(int val) {
			id = val;
			priority = Integer.MAX_VALUE;
			parent = null;
		}
		Node(){
			
		}
		
		@Override
		public int compare(Node a, Node b) {
			return Integer.compare(a.priority, b.priority);
		}
	}
	class Edge {
		int weight;
		Node src;
		Node dst;
		
		public Edge(Node s, Node d, int w) {
			src = s;
			dst = d;
			weight = w;
		}
	}
	
	public List<List<String>> getMST() {
		
		List<List<String>> result = new ArrayList<>();
		
		
		return result;
		
	} 

}
