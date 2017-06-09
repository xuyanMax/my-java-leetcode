package simpleDijkstra;

import java.util.List;
/**
 * 
 * @author xu
 * A graph consists of vertices and edges. These are represented by the Vertex & Edge models.
 *
 */
public class Graph {
	private final List<Vertex> vertices;
	private final List<Edge> edges;
	
	public Graph (List<Vertex> vert, List<Edge> edges) {
		this.vertices = vert;
		this.edges = edges;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}
	
}
