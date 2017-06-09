package graph;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author xu
 * 
 * http://www.sanfoundry.com/java-program-mplement-dijkstras-algorithm-using-priority_queue/
 * O(VlogV + E) using Fabonacci Heap
 * input:
 * 5
 * 
 *  0 9 6 5 3 
	0 0 0 0 0
	0 2 0 4 0
	0 0 0 0 0
	0 0 0 0 0
 *  Enter the source 
	1
 * 
 * output:

	The Shorted Path to all nodes are 
	1 to 1 is 0
	1 to 2 is 8
	1 to 3 is 6
	1 to 4 is 5
	1 to 5 is 3
 */
public class Dijkstra_Adj_pq {

	private int distances[];
	private Set<Integer> visited;
	private PriorityQueue<Node> pQueue;
	private int num_vert;
	private int[][] adj;
	public static void main(String[] args) {
	
		int[][] adjacent;
		int num_vert;
		
		Scanner cin = new Scanner(System.in);
		
		System.out.println("Enter the number of vertices");
		num_vert = cin.nextInt();
		adjacent = new int[num_vert + 1][num_vert + 1];
		
		
        System.out.println("Enter the Weighted Matrix for the graph");
		for (int i=1; i<=num_vert; i++) {
			for (int j=1; j<=num_vert; j++) {
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
		 System.out.println("Enter the source ");
		int src = cin.nextInt();
		Dijkstra_Adj_pq dijkstra_AJ = new Dijkstra_Adj_pq(num_vert);
		dijkstra_AJ.dijkstra(adjacent, src, 5);
		
		// print shortest distance from src to all other nodes
		 System.out.println("The Shorted Path to all nodes are ");
		for (int i=1;i<=num_vert;i++) {
			System.out.println(src + " to " + i + " is " + dijkstra_AJ.distances[i]);
		}
		cin.close();
	}
	
	public Dijkstra_Adj_pq(int num){
		this.num_vert = num;
		distances = new int[num_vert + 1];
		visited = new HashSet<Integer>();
		pQueue = new PriorityQueue<Node>(num_vert, new Node());
		adj = new int[num_vert + 1][num_vert + 1];
		System.out.println("C done");
		
	}
	public void dijkstra(int[][] adjacent, int src, int target) {
		
		// initialization
		for (int i=1; i<=num_vert; i++) 
			distances[i] = Integer.MAX_VALUE;
		
		// copy adjacent list
		for (int i=1; i<=num_vert; i++) {
			for (int j=1; j<=num_vert; j++) {
				adj[i][j] = adjacent[i][j];
			}
		}
		// add source to priority queue
		pQueue.add(new Node(src, 0));
		distances[src] = 0;
		
		Node curr;
		while (!pQueue.isEmpty()) {
			curr = pQueue.poll();
			
			if (curr.node == target)
				return;//////////////////////////
			
			visited.add(curr.node);
//			distances[curr.node] = curr.wgt;
			LinkedList<Node> neighbors = getNeighor(curr);
			
			for (Node vNode : neighbors) {
				
					int edge = adj[curr.node][vNode.node];
					int newDistance = edge + distances[curr.node];
					if (newDistance < distances[vNode.node]){
						distances[vNode.node] = newDistance;
					}
					
					pQueue.add(new Node(vNode.node, distances[vNode.node]));
				
			}
					
		}
		
	}
	public LinkedList<Node> getNeighor(Node u) {
		
		LinkedList<Node> neighbors = new LinkedList<>();
		for (int i=1; i<=num_vert; i++) {
			if (!visited.contains(i)) {
				if (adj[u.node][i] != Integer.MAX_VALUE) { // adjacent
					neighbors.add(new Node(i, adj[u.node][i]));
				}
			}
		}
		return neighbors;
		
	}
	class Node implements Comparator<Node>{
		int node;
		int wgt;
		public Node() {
		
		}
		public Node(int n, int weight){
			node = n;
			wgt = weight;
		}
		@Override
		public int compare(Node A, Node B) {
			return Integer.compare(A.wgt, B.wgt); // return the minimum
		}
	}
	

}
