package design.binarysearchtree.RedBlackTreeDesign;

/**
 * Created by xu on 2017/7/1.
 */
public class RedBlackNode<T extends Comparable<T>> {

    public RedBlackNode<T> left;
    public RedBlackNode<T> right;
    public RedBlackNode<T> parent;
    public T key;

    /*possible colors for the node*/
    public static final int BLACK = 0;
    public static final int RED = 1;

    public int color;
    /*the number of nodes to the right of each node*/
    public int numRight;
    /*the number of nodes to the left of each node*/
    public int numLeft;

    public RedBlackNode() {
        this.color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;

    }


    public RedBlackNode(T key) {
        this();
        this.key = key;
    }
}
