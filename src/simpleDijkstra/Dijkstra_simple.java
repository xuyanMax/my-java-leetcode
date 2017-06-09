package simpleDijkstra;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author xu
 * simple Dijkstra implementation with out using any performance optimization, e.g.,  priority queue for unsettled vertices
 * references:
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 */
public class Dijkstra_simple {
	
	private  List<Vertex> nodes;
	private  List<Edge> edges;
	private Set<Vertex> settledNodes;
	private Set<Vertex> unSettledNodes;
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> distance;
	
	public Dijkstra_simple (Graph graph) {
		
		this.nodes = new ArrayList<Vertex>(graph.getVertices());
		this.edges = new ArrayList<Edge>(graph.getEdges());
		
	}

	public void execute(Vertex src) {
		
		initialize(src);
		
        while (unSettledNodes.size()>0) {
        	Vertex current = getMin(unSettledNodes);
        	settledNodes.add(current);	// open list
        	unSettledNodes.remove(current);// closed list
        	
        	List<Vertex> adjacentNodes = getNeighbors(current);
        	for (Vertex v: adjacentNodes) {
        			Relax(current, v);
        	}
        }
        
	}
 	public void initialize(Vertex current) {
 		for (Vertex v: nodes) 
 			distance.put(v, Integer.MAX_VALUE);
 		
 		distance.put(current, 0);
 		settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();        
        unSettledNodes.add(current);
        
 	}
 	public void Relax(Vertex u, Vertex v) {
		int tmp = distance.get(u) + getEdgeWeight(u, v);
		if (distance.get(v) > tmp ) {
			distance.put(v, tmp);
			predecessors.put(v, u);
			unSettledNodes.add(v);// like add v to openList to be examined
		}
		
	}
 	
 	public int getEdgeWeight (Vertex u, Vertex v) {
 		for (Edge e: edges) {
 			if (e.getSrc().equals(u) && e.getDst().equals(v)) 
 				return 	e.getWeight();
 		}
		throw new RuntimeException("Shouldn't happen!");
 	}
	public List<Vertex> getNeighbors (Vertex current) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge e : edges)
			if (e.getSrc().equals(current) && !settledNodes.contains(e.getDst()))
				neighbors.add(e.getDst());
		
		return neighbors;
	}
	public Vertex getMin(Set<Vertex> vertices) {
		
		Vertex vertMinVal = null;
		for (Vertex vert : vertices) {
			if(vertMinVal == null)
				vertMinVal = vert;
			else {
				Integer tmpInt1 = distance.get(vertMinVal);
				Integer tmpInt2 = distance.get(vert);

				if (tmpInt1 > tmpInt2)
					vertMinVal = vert;	
			} 
				
		}
		return vertMinVal;
	}
	
	public LinkedList<Vertex> getPath(Vertex dst) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		
		path.add(dst);
		Vertex trace = dst;
		while (predecessors.get(trace) != null) {
			trace = predecessors.get(trace);
			path.add(trace);
			
		}
		return path;	
	}
	
}
