 package graph;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author xu
 * references
 * DFS:
 * http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
 * http://www.geeksforgeeks.org/applications-of-depth-first-search/
 * 
 * BFS:
 *  http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 */
public class GraphTraversals {

	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<Integer>(true);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 6);
		graph.addEdge(6, 5);
		graph.addEdge(5, 3);
		GraphTraversals gTraversals = new GraphTraversals();
//		gTraversals.DFS(graph);
		
		gTraversals.BFS(graph);
				

	}
	
	// A function used by DFS
    public void DFSUtil(Vertex<Integer> v, Set<Long> visited){
    	visited.add(v.getID());// marked as visited
    	System.out.print(v.getID()+" ");
    	for (Vertex<Integer> vet : v.getAdjacentVertex()) {
    		if (!visited.contains(vet.getID()))
    			DFSUtil(vet, visited);
    	}
    	
    }
    
    
 // The function to do DFS traversal. It uses recursive DFSUtil()
	public void DFS(Graph<Integer> graph) {
		Set<Long> visited = new HashSet<Long>();
		
		 // Call the recursive helper function to print DFS traversal
        // starting from all vertices one by one
        
		for (Vertex<Integer> v : graph.getAllVertex()) 
        	if (!visited.contains(v.getID()))
        		DFSUtil(v, visited);

	}
	
	public void BFS(Graph<Integer> graph) {
		Set<Long> visited = new HashSet<Long>();
		Deque<Vertex<Integer>> queue = new LinkedList<Vertex<Integer>>();
		
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			if(!visited.contains(vertex.getID())) {
				queue.addLast(vertex);
				visited.add(vertex.getID());
				
				while (!queue.isEmpty()) {
					Vertex<Integer> tmp = queue.poll();// remove and retrieve the head of queue
					System.out.print(tmp.getID()+" ");
					for (Vertex<Integer> vet : vertex.getAdjacentVertex()) {
						if (!visited.contains(vet.getID())) {
							queue.addLast(vet);
							visited.add(vet.getID());
						}
					}
							
				}
				System.out.println("\n\n");
			}
				
		}
		
	}

}
