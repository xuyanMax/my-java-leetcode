package dag;
/**
 * 
 * @author xu
 *
 * @param <T>
 * Given a directed acyclic graph, do a topological sort on this graph.
 *
 * Do DFS by keeping visited. Put the vertex which are completely explored into a stack.
 * Pop from stack to get sorted order.
 *
 * Space and time complexity is O(n).-(visited)
 */
import graph.Graph;
import graph.Vertex;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class toposortdfs<T> {

	public static void main(String[] args) {
		
		 Graph<Integer> graph = new Graph<>(true);
	        graph.addEdge(1, 3);
	        graph.addEdge(1, 2);
	        graph.addEdge(3, 4);
	        graph.addEdge(5, 6);
	        graph.addEdge(6, 3);
	        graph.addEdge(3, 8);
	        graph.addEdge(8, 11);
	        
	        toposortdfs<Integer> sort = new toposortdfs<Integer>();
	        Deque<Vertex<Integer>> result = sort.TopoSort(graph);
	        while(!result.isEmpty()){
	            System.out.print(result.poll());
	        }
	    
	}
	
	public Deque<Vertex<T>> TopoSort(Graph<T> graph) {
	
		Set<Vertex<T>> visited = new HashSet<Vertex<T>>();
		
		Deque<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		for (Vertex<T> vet: graph.getAllVertex()) {
			if (!visited.contains(vet))
				TopoSortUtil(vet, queue, visited);
			else continue;
		}
		return queue;
	}
	
	
	public void TopoSortUtil(Vertex<T>vet, Deque<Vertex<T>>queue, Set<Vertex<T>> visited){
		
		visited.add(vet);
		System.out.print(vet.getID() + " ");
		queue.addLast(vet);
		for (Vertex<T>vertex : vet.getAdjacentVertex()) {
			if (!visited.contains(vertex))
				TopoSortUtil(vertex, queue, visited);
			else continue;
		}
		
	}

}
