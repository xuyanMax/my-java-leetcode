package graph.dijkstra.simpleDijk;


import java.util.*;

/**
 * @author xu
 * simple Dijkstra implementation without using any performance optimization,
 * e.g.,  priority queue for unsettled vertices
 * references:
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 */
public class Dijkstra_simple {

    private List<Vertex> nodes;
    private List<Edge> edges;
    private Set<Vertex> visited;
    private Set<Vertex> unSettledNodes;
    // help build search path
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distFromStart;

    public Dijkstra_simple(Graph graph) {

        this.nodes = new ArrayList<Vertex>(graph.getVertices());
        this.edges = new ArrayList<Edge>(graph.getEdges());

    }

    public void initialize(Vertex src) {
        visited = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distFromStart = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();

        // initial set to nodes distance to start node -> infinity
        nodes.forEach(v -> distFromStart.put(v, Integer.MAX_VALUE));
        distFromStart.put(src, 0);
        unSettledNodes.add(src);

    }

    public Vertex getPredecessor(Vertex var) {
        return predecessors.get(var);
    }

    public Vertex getMin(Set<Vertex> vertices) {

        Vertex vertMinVal = null;
        for (Vertex vert : vertices) {
            if (vertMinVal == null)
                vertMinVal = vert;
            if (distFromStart.get(vertMinVal) > distFromStart.get(vert))
                vertMinVal = vert;
        }
        return vertMinVal;
    }

    public void execute(Vertex src) {

        initialize(src);

        while (unSettledNodes.size() > 0) {
            Vertex current = getMin(unSettledNodes);
            visited.add(current);    // open list
            unSettledNodes.remove(current);// closed list

            List<Vertex> adjacentNodes = getNeighbors(current);
            for (Vertex v : adjacentNodes)
                relaxDistance(current, v);

        }
    }

    public void relaxDistance(Vertex src, Vertex dest) {
        int tmp = distFromStart.get(src) + getEdgeWeight(src, dest);

        //update the min distance, from start node to so far
        if (distFromStart.get(dest) > tmp) {
            distFromStart.put(dest, tmp);
            predecessors.put(dest, src);
            unSettledNodes.add(dest);// like add v to openList to be examined
        }

    }

    public int getEdgeWeight(Vertex u, Vertex v) {
        for (Edge edge : edges)
            if (edge.getSrc().equals(u) && edge.getDst().equals(v))
                return edge.getWeight();

        throw new RuntimeException("Shouldn't happen!");
    }

    public List<Vertex> getNeighbors(Vertex current) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges)
            // current's adjacent should not be included in open list/ visited before
            if (edge.getSrc().equals(current) &&
                    !visited.contains(edge.getDst()))
                neighbors.add(edge.getDst());

        return neighbors;
    }

    public String getPath(Vertex dst) {
        StringBuilder path = new StringBuilder();

        path.append(dst).append("->");
        Vertex pre = dst;
        while (getPredecessor(pre) != null) {
            pre = predecessors.get(pre);
            path.append(pre).append(">");
        }
        return path.deleteCharAt(path.length() - 1).toString();
    }

}
