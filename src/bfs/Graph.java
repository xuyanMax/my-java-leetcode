package bfs;

import java.util.*;

/**
 * 
 * @author xu
 * 
 * reference:
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/Graph.java
 * 范型类简单解释：http://jingyan.baidu.com/article/454316ab76711df7a7c03aa0.html
 *
 */
public class Graph<T> {  // 范型类

	private List<Edge<T>> allEdges;
	private Map<Long, Vertex<T>> allVertex;
	
	boolean isDirected = false;
	
	public Graph (boolean isDirected) {
		this.isDirected = isDirected;
		allEdges = new ArrayList<Edge<T>>();
		allVertex = new HashMap<Long, Vertex<T>>();
	}
	
	public void addEdge(long id1, long id2) {
		addEdge(id1, id2, 0);
	}
	
	public void addEdge(long id1, long id2, int weight) {
		Vertex<T> vertex1 = null;
		if(allVertex.containsKey(id1))
			vertex1 = allVertex.get(id1);
		else {
			vertex1 = new Vertex<T>(id1);
			allVertex.put(id1, vertex1);
		}
		///////////////////////////////////
		Vertex<T> vertex2 = null;
		if(allVertex.containsKey(id2))
			vertex2 = allVertex.get(id2);
		else {
			vertex2 = new Vertex<T>(id2);
			allVertex.put(id2, vertex2);
		}
		
		Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
		
		allEdges.add(edge);
		vertex1.addAdjacentVertex(edge, vertex2);
		
		if (!isDirected)
			vertex2.addAdjacentVertex(edge, vertex1);
	}
	
	public List<Edge<T>> getAllEdges() {
		return allEdges;
	}
	public Collection <Vertex<T>> getAllVertex() {
		return allVertex.values();// .values() returns a Collection of values of the map
	}
	public Vertex<T> getVertex(long id) {
		return allVertex.get(id);
				
	} 
	public void setNewDataForVertex(long id, T data) {
		if (allVertex.containsKey(id)) {
			Vertex<T> tmp = allVertex.get(id);
			tmp.setData(data);
			
		}
	}
	/**
	 * 
	 */
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		for (Edge<T> e: allEdges) {
			stringBuilder.append(e.getVertex1()+" -> "+e.getVertex2()+", "+e.getWeight());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	
}

class Vertex<T> {
    long id;
    private T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<Vertex<T>> adjacentVertex = new ArrayList<>();

    Vertex (long id) {
    this.id = id;
    }

    public long getID() {
        return id;
    }

    public T getData() {
        return data;
    }
    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setData (T data) {
        this.data = data;
    }

    public void addAdjacentVertex (Edge<T> e, Vertex<T> v) {
        edges.add(e);
        adjacentVertex.add(v);
    }
    public List<Vertex<T>> getAdjacentVertex() {
        return adjacentVertex;
    }
    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public int getDegree() {
        return edges.size();
    }
}

/**
 *
 * Edge
 *
 *
 */
class Edge<T> {
    private boolean isDirected = false;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private int weight;

    Edge(Vertex<T> v1, Vertex<T> v2) {
        this.vertex1 = v1;
        this.vertex2 = v2;
    }

    Edge (Vertex<T> v1, Vertex<T> v2, boolean isDirected, int weight) {
        this.isDirected =isDirected;
        this.vertex1 = v1;
        this.vertex2 = v2;
        this.weight = weight;

    }
    Vertex<T> getVertex1(){
        return vertex1;
    }

    Vertex<T> getVertex2(){
        return vertex2;
    }
    public boolean isDirected() {
        return isDirected;
    }

    public int getWeight() {
        return weight;
    }

    @Override

    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge<T> other = (Edge<T>) obj;
        if (vertex1 == null) {
            if (other.vertex1 != null)
                return false;
        } else if (!vertex1.equals(other.vertex1))
            return false;
        if (vertex2 == null) {
            if (other.vertex2 != null)
                return false;
        } else if (!vertex2.equals(other.vertex2))
            return false;
        return true;

    }

    @Override

    public String toString () {
        return "Edge[isDirected=" +isDirected + ", vertex1=" + vertex1 +", "
                + "vertex2=" + vertex2+", weight="+weight+"]";
    }


}