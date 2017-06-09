package simpleDijkstra;

public class Edge {
	final private String id;
	final private Vertex src;
	final private Vertex dst;
	final private int weight;
	
	public Edge (String id, Vertex src, Vertex dst, int wgt) {
		this.id = id;
		this.src = src;
		this.dst = dst;
		this.weight = wgt;
	}

	public String getId() {
		return id;
	}

	public Vertex getSrc() {
		return src;
	}

	public Vertex getDst() {
		return dst;
	}

	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return 	this.src +" -> "+ this.dst;
		
	}
	
	

}
