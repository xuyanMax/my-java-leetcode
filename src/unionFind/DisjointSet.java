package unionFind;

import java.util.HashMap;
import java.util.Map;

// https://www.youtube.com/watch?v=ID00PMy0-vE
public class DisjointSet { //并查集

    private Map<Long, Node> maps = new HashMap<>(); // store <val, new Node(val)> pairs

    public static void main(String[] args) {

        DisjointSet ds = new DisjointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));

    }

    class Node {
        long val;
        Node parent;
        int rank;

        Node(long input) {
            this.val = input;
            this.parent = this;
            this.rank = 0;
        }
    }

    // initialization
    // create a set with only single element
    public void makeSet(long data) {
        Node node = new Node(data);
        maps.put(data, node);
    }

    // combine two sets to 1 by rank
    // return true if data1 and data2 are in different set before union else false
    //路径压缩的加权
    public boolean union(long data1, long data2) {
        Node nodeA = maps.get(data1);
        Node nodeB = maps.get(data2);

        Node parentA = findSet(nodeA);
        Node parentB = findSet(nodeB);

        // if they are in the same set, do nothing
        if (parentA.val == parentB.val)
            return false;
        // else the parent val with higher rank becomes the parent of another parent
        if (parentA.rank >= parentB.rank) {
            parentB.parent = parentA;
            // increment rank only if both sets have the same rank
            parentA.rank = (parentA.rank == parentB.rank) ? parentA.rank + 1 : parentA.rank;

        } else
            parentA.parent = parentB;

        return true;

    }

    // find the representative of this set
    public long findSet(long data) {
        return findSet(maps.get(data)).val;
    }

    // find the representative of its set recursively and
    // do path compression as well (make the parent of the nodes along the path to representative as the representative)
    private Node findSet(Node node) {
        Node parent = node.parent;

        if (parent == node)  // check if itself is the representative.
            return parent;

        node.parent = findSet(parent); // do path compression here
        return node.parent;
    }
}
