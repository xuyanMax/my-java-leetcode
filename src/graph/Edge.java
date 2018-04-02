package graph;

/**
 *
 * Edge
 *
 *
 */
public class Edge<T> {
    private boolean isDirected = false;
    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private int weight;

    public Edge(Vertex<T>v1, Vertex<T>v2) {
        this.vertex1 = v1;
        this.vertex2 = v2;
    }

    public Edge (Vertex<T>v1, Vertex<T>v2, boolean isDirected, int weight) {
        this.isDirected =isDirected;
        this.vertex1 = v1;
        this.vertex2 = v2;
        this.weight = weight;

    }
    public Vertex<T> getVertex1(){
        return vertex1;
    }

    public Vertex<T> getVertex2(){
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
