package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu
 * <p>
 * reference:
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/Graph.java
 * 范型类简单解释：http://jingyan.baidu.com/article/454316ab76711df7a7c03aa0.html
 */
public class Graph<T> {  // 范型类

    private List<Edge<T>> allEdges;
    private Map<Long, Vertex<T>> allVertex;

    boolean isDirected = false;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        allEdges = new ArrayList<Edge<T>>();
        allVertex = new HashMap<Long, Vertex<T>>();
    }

    public void addEdge(long id1, long id2) {
        addEdge(id1, id2, 0);
    }

    public void addEdge(long id1, long id2, int weight) {
        Vertex<T> vertex1 = null;
        if (allVertex.containsKey(id1))
            vertex1 = allVertex.get(id1);
        else {
            vertex1 = new Vertex<T>(id1);
            allVertex.put(id1, vertex1);
        }
        Vertex<T> vertex2 = null;
        if (allVertex.containsKey(id2))
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

    public Collection<Vertex<T>> getAllVertex() {
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Edge<T> e : allEdges) {
            stringBuilder.append(e.getVertex1() + " -> " + e.getVertex2() + ", " + e.getWeight());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}


	
