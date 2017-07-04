package bst.bstDesign;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by xu on 2017/6/25.
 *
 * reference - https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/code/BST.java
 */
public class BSTree <T extends Comparable<T>> implements Iterable<T>{
    private Node<T> root;
    private Comparator<T> comparator;


    public BSTree() {
        this.root = null;
        comparator = null;
    }

    public BSTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }
    public int compare(T x, T y) {
        if (comparator == null)
            return x.compareTo(y);
        else
            return comparator.compare(x, y);
    }
/*****************************************************
 *
 *            SEARCH
 *
 ******************************************************/
    public boolean search(T toSearch) {
        return search(root, toSearch);
    }
    public boolean search(Node<T>root, T toSearch) {
        if (root == null)
            return false;
        else {
            if (compare(root.data, toSearch) == 0)
                return true;
            else if (compare(root.data, toSearch) > 0)
                return search(root.left, toSearch);
            else
                return search(root.right, toSearch);
        }
    }
    public Node<T> find (T toBeFound) {
        return find(this.root, toBeFound);
    }
    public Node<T> find(Node<T> root, T toBeFound) {
        Node<T> p = root;
        if (p == null)
            return null;

        while (p != null) {
            if (compare(p.data, toBeFound) == 0) {
                return p;

            } else if (compare(p.data, toBeFound) > 0) {
                p = p.left;
            } else
                p = p.right;
        }
        return null;
    }
/*****************************************************
 *
 *            INSERT
 *
 ******************************************************/
    public void insert (T data){
        root = insert(root, data);
    }
    public Node<T> insert(Node<T> root, T toBeInsert){
        if (root == null) {
            return new Node(toBeInsert);
        }
        Node<T> p = null;
        Node<T> x = root;
        while (x != null) {
            p = x;
            if (compare(toBeInsert, p.data) < 0)
                x = x.left;
            else
                x = x.right;
        }
        Node<T> child = new Node<T>(toBeInsert);
        child.parent = p;
        if (compare(p.data, toBeInsert) < 0)
            p.right = child;
        else
            p.left = child;

        return root;
    };
/*****************************************************
 *
 *            SUCCESSOR
 *
 ******************************************************/
    public Node<T> successor(T toBeFound) {
        Node<T> x = find(this.root, toBeFound);

        // here we have the Node<T>(toBeFound) as p
        if (x.right != null) {
            return TreeMin(x.right);
        }else {
            Node<T> y = x.parent;
            while (y != null && x == y.right) {
                x = y;
                y = y.parent;
            }
            return y;
        }

    }
/*****************************************************
 *
 *            DELETE
 *
 ******************************************************/
    public void delete(T toDelete) {
        Node<T> z = find(this.root, toDelete);
        if (z.left == null)
            transplant(z, z.right);
        else if (z.right == null)
            transplant(z, z.left);
        else { // to children
            Node<T> y = TreeMin(z.right);
            if (y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z.data, y.data);

            y.left = z.left;
            y.left.parent = y;
        }
    }
    /*油温题*/
    public void transplant(T u, T v) {
        Node<T> U = find(u);
        Node<T> V = find(v);
        transplant(U,V);

    }
/*****************************************************
 *
 *            TRANSPLANT
 *
 ******************************************************/

    public void transplant(Node<T> U, Node<T>V) {
        if (U.parent == null)
            this.root = V;
        else if (U == U.parent.left)
            U.parent.left = V;
        else U.parent.right = V;

        if (V != null)
            V.parent = U.parent;
    }

    public Node<T> TreeMax(Node<T> x) {
        Node<T> p = find(this.root, x.data);

        while (p.right != null)
            p = p.right;

        return p;
    }
    public Node<T> TreeMin(Node<T> x) {
        Node<T> p = find(this.root, x.data);
        while (p.left != null)
            p = p.left;

        return p;
    }
    @Override
    public Iterator<T> iterator() {
        return null;
    }
/*************************************************
 *
 *            TRAVERSAL
 *
 **************************************************/
    public void preOrder(){
        preHelper(this.root);
    }
    public void preHelper(Node<T> root) {
        if (root == null)
            return;
        System.out.println(root.data+" ");
        preHelper(root.left);
        preHelper(root.right);
    }
/*************************************************
 *
 *            MISC
 *
 **************************************************/

    public int height() {
        return height(this.root);
    }
    public int height(Node<T> root) {
        if (root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    public int width() {
        int max = 0;
        for (int i=0; i<=height(); i++) {
            int tmp = width(root, i);
            max = Math.max(max, tmp);
        }
        return max;
    }
    public int width(Node<T> p, int depth) {
        if (p == null)
            return 0;
        else if (depth == 0)
            return 1;
        else return width(p.left, depth - 1) + width(p.right, depth-1);
    }
/*************************************************
 *
 *            toString
 *
 **************************************************/

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        return sb.toString();
    }
}

/*****************************************************
 *
 *            the Node class
 *
 ******************************************************/
class Node <T>{
    public Node<T> left, right;
    public Node<T> parent;
    public T data;

    public Node (T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Node(Node<T> left, Node<T> right, T data) {
        this.left = left;
        this.right = right;
        this.data = data;
        this.right.data = null;
        this.left.data = null;
    }

    @Override
    public String toString()
    {
        return data.toString();
    }

}
