package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date 04/06/2013
 *
 * @author Tushar Roy
 * <p>
 * Data structure to support following operations
 * extracMin - O(logn)
 * addToHeap - O(logn)
 * containsKey - O(1)
 * decreaseKey - O(logn)
 * getKeyWeight - O(1)
 * <p>
 * It is a combination of binary arr.heap and hash map
 */
public class BinaryMinHeap<T> {

    private List<Node> allNodes = new ArrayList<>();
    private Map<T, Integer> nodePosition = new HashMap<>();

    class Node {
        int weight;
        T key;

        Node(int w, T data) {
            weight = w;
            key = data;
        }
    }

    public static void main(String args[]) {
        BinaryMinHeap<String> heap = new BinaryMinHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.add(2, "AFR");
        heap.decrease("Pramila", 1);
        heap.printHeap();
        heap.printPositionMap();
    }

    /**
     * Checks whether the key exists in arr.heap or not
     */
    public boolean containsData(T key) {
        return nodePosition.containsKey(key);
    }

    /**
     * Add key and its weight to the arr.heap
     */
    public void add(int weight, T key) {
        Node node = new Node(weight, key);

        allNodes.add(node);
        int size = allNodes.size();
        int index = size - 1;
        int parentIndex = (index - 1) / 2;
        nodePosition.put(node.key, index);

        while (parentIndex >= 0) {
            Node parentNode = allNodes.get(parentIndex);
            Node currentNode = allNodes.get(index);

            if (parentNode.weight > currentNode.weight) {
                swap(parentNode, currentNode);// updateHighestHeightBtwLR arr.heap index
                updatePositionMap(parentNode.key, currentNode.key, parentIndex, index);
                index = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Get the arr.heap min without extracting the key
     */
    public T min() {
        return allNodes.get(0).key;
    }

    /**
     * Checks with arr.heap is empty or not
     */
    public boolean empty() {
        return allNodes.size() == 0;
    }

    /**
     * Decreases the weight of given key to newWeight
     */
    public void decrease(T key, int newWeight) {
        Integer position = nodePosition.get(key);

        allNodes.get(position).weight = newWeight;

        int parent = (position - 1) / 2;

        while (parent >= 0) {
            if (allNodes.get(parent).weight > allNodes.get(position).weight) {
                swap(allNodes.get(parent), allNodes.get(position));
                updatePositionMap(allNodes.get(parent).key, allNodes.get(position).key, parent, position);
                position = parent;
                parent = (parent - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Get the weight of given key
     */
    public Integer getWeight(T key) {
        Integer position = nodePosition.get(key);
        if (position == null) {
            return null;
        } else {
            return allNodes.get(position).weight;
        }
    }

    /**
     * Returns the min val of the arr.heap
     */
    public Node extractMinNode() {
        int size = allNodes.size() - 1;
        // returned min mode
        Node minNode = new Node(allNodes.get(0).weight, allNodes.get(0).key);

        int lastNodeWeight = allNodes.get(size).weight;

        //更新allNodes[0]
        allNodes.get(0).weight = lastNodeWeight;
        allNodes.get(0).key = allNodes.get(size).key;

        nodePosition.remove(minNode.key);// remove first
        nodePosition.remove(allNodes.get(0));// remove last (used to be last node but now first node)
        nodePosition.put(allNodes.get(0).key, 0);// updateHighestHeightBtwLR index

        allNodes.remove(size);//remove last index

        int currentIndex = 0;
        size--;

        // adjust the binary min arr.heap
        while (true) {
            int left = 2 * currentIndex + 1;
            int right = 2 * currentIndex + 2;
            if (left > size) {
                break;
            }
            if (right > size) {
                right = left;
            }
            int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
            // ADJUST TO MIN HEAP
            if (allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight) {
                swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
                updatePositionMap(allNodes.get(currentIndex).key, allNodes.get(smallerIndex).key, currentIndex, smallerIndex);
                currentIndex = smallerIndex;
            } else {
                break;
            }
        }
        return minNode;
    }

    /**
     * Extract min value key from the arr.heap
     */
    public T extractMin() {
        Node node = extractMinNode();
        return node.key;
    }

    private void printPositionMap() {
        System.out.println(nodePosition);
    }

    private void swap(Node node1, Node node2) {
        int weight = node1.weight;
        T data = node1.key;

        node1.key = node2.key;
        node1.weight = node2.weight;

        node2.key = data;
        node2.weight = weight;
    }

    private void updatePositionMap(T data1, T data2, int pos1, int pos2) {
        nodePosition.remove(data1);
        nodePosition.remove(data2);
        nodePosition.put(data1, pos1);
        nodePosition.put(data2, pos2);
    }

    public void printHeap() {
        for (Node n : allNodes) {
            System.out.println(n.weight + " " + n.key);
        }
    }


}