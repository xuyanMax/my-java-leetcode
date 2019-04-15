package binarysearchtree.RedBlackTreeDesign;

import java.util.Comparator;

/**
 * Created by xu on 2017/7/1.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /*Root initialized to nil*/
    private RedBlackNode<T> nil = new RedBlackNode<T>();
    private RedBlackNode<T> root = nil;
    private Comparator<T> comparator;

    public RedBlackTree() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    /*ROTATE-LEFT
     *
     * performs left-rotate around x
     * */

    public void leftRotate(RedBlackNode<T> x) {

        leftRotateFix(x);
        RedBlackNode<T> y = x.right;
        x.right = y.left;

        if (y.left != nil)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == nil)
            this.root = y;
        else if (x.parent.left == x)
            x.parent.left = y;
        else if (x.parent.right == x)
            x.parent.right = y;

        y.left = x;
        x.parent = y;

    }
    /*ROTATE-RIGHT
     *
     * performs right-rotate around x
     * */

    public void rightRotate(RedBlackNode<T> y) {
        RedBlackNode<T> x = y.left;
        y.left = x.right;

        if (x.right != nil)
            x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == nil)
            this.root = x;
        else if (y.parent.left == x)
            y.parent.left = x;
        else if (y.parent.right == x) ;
        y.parent.right = x;
        x.right = y;
        y.parent = x;
    }

    /*updateHighestHeightBtwLR the numLeft & numRight affected by leftRotate
     * FOUR cases:
     * 1. x.left == nil && x.right.left == nil
     * 2. x.left != nil && x.right.left == nil
     * 3. x.left == nil && x.right.left != nil
     * 4. x.left != nil && x.right.left != nil
     * */
    public void leftRotateFix(RedBlackNode<T> x) {
        if (x.left == nil && x.right.left == nil) {
            x.numRight = 0;
            x.numLeft = 0;
            x.right.numLeft = 1; // after left rotation, y have 1 left child, which is x.

        } else if (x.left != nil && x.right.left == nil) {
            x.numRight = 0;
            x.right.numLeft = 1 + 1 + x.numRight + x.numLeft; // y: 1 for x and another 1 for x's left child
        } else if (x.left == nil && x.right.left != nil) {
            x.numLeft = 0;
            x.numRight = 1 + x.right.left.numRight + x.right.left.numLeft;
            x.right.numLeft = 1 + 1 + x.right.left.numRight + x.right.left.numLeft; // y: 1 for x and another 1 for x.right
        } else {
            x.numRight = 1 + x.right.left.numRight + x.right.left.numLeft;
            x.right.numLeft = 1 + 1 + x.left.numRight + x.left.numLeft // y: 1 for x; 1 for x.left; 1 for x.right.left
                    + 1 + x.right.left.numLeft + x.right.left.numRight; //1 + x.numLeft + x.numRight;
        }
    }

    /*Update the numLeft & numRight affected by rightRotate
     * FOUR cases:
     *
     * 1. y.right == nil && y.left.right == nil
     * 2. y.right != nil && y.left.right == nil
     * 3. y.right == nil && y.left.right != nil
     * 4. y.right != nil && y.left.right != nil
     * */
    public void rightRotateFix(RedBlackNode<T> y) {
        if (y.right == nil && y.left.right == nil) {
            y.numRight = 0;
            y.numLeft = 0;
            y.left.numRight = 1;
        } else if (y.right != nil && y.left.right == nil) {
            y.numLeft = 0;
            y.left.numRight = 1 + 1 + y.right.numRight + y.right.numLeft;
        } else if (y.right == nil && y.left.right != nil) {
            y.numRight = 0;
            y.numLeft = 1 + y.left.right.numLeft + y.left.right.numRight;
            y.left.numRight = 1 + 1 + y.left.left.numLeft + y.left.right.numRight;

        } else if (y.right != nil && y.left.right != nil) {
            y.numLeft = 1 + 1 + y.left.left.numLeft + y.left.right.numLeft +
                    1 + y.left.right.numLeft + y.left.right.numRight;
        }

    }

    /*****************************************************
     *
     *            INSERT
     *
     ******************************************************/
    public void insert(T z) {
        insert(new RedBlackNode<>(z));
    }

    public void insert(RedBlackNode<T> z) {

        RedBlackNode<T> y = nil;
        RedBlackNode<T> x = this.root;

        while (x != nil) {
            y = x;
            if (compare(x.key, z.key) < 0) {
                x.numLeft++; // updateHighestHeightBtwLR x's numLeft as z is less than z
                x = x.left;
            } else { // compare(z,x) >=0
                x.numRight++;
                x = x.right;
            }
        }

        z.parent = y;
        if (y == nil) {
            this.root = z;
        } else if (compare(z.key, y.key) < 0) { // z < y
            y.left = z;
        } else {                        // z >= y
            y.right = z;
        }

        z.left = nil;
        z.right = nil;
        z.color = RedBlackNode.RED;

        /* ---- FIX ---- */
        insertFixUp(z);

    }

    /*****************************************************
     *
     *            INSERT-FIXUP
     *
     ******************************************************/
    /*The node insertion may cause violation to the RedBlack Tree
     * We consider 3 cases:
     * 1.
     * 2.
     * 3.
     * */
    public void insertFixUp(RedBlackNode<T> z) {
        RedBlackNode<T> y = nil;

        /*while there is a violation*/
        while (z.parent.color == RedBlackNode.RED) {

            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right; // y is the uncle of x

                if (y.color == RedBlackNode.RED) {          // case 1
                    z.parent.color = RedBlackNode.BLACK;
                    y.color = RedBlackNode.BLACK;
                    z.parent.parent.color = RedBlackNode.RED;
                    z = z.parent.parent;
                } else if (z == z.parent.right) {             // case 2
                    z = z.parent;
                    leftRotate(z);
                }

                /*--------------case 3--------------------------------*/
                z.parent.color = RedBlackNode.BLACK;
                z.parent.parent.color = RedBlackNode.RED;
                rightRotate(z.parent.parent);

            } else if (z.parent == z.parent.parent.right) {
                y = z.parent.parent.left;
                if (y.color == RedBlackNode.RED) {          // case 1
                    z.parent.color = RedBlackNode.BLACK;
                    y.color = RedBlackNode.BLACK;
                    z.parent.parent.color = RedBlackNode.RED;
                    z = z.parent.parent;
                } else if (z == z.parent.left) {             // case 2
                    z = z.parent;
                    rightRotate(z);
                }

                /*--------------case 3--------------------------------*/
                z.parent.color = RedBlackNode.BLACK;
                z.parent.parent.color = RedBlackNode.RED;
                leftRotate(z.parent.parent);
            }
            this.root.color = RedBlackNode.BLACK;
        }
    }

    /*****************************************************
     *
     *            TRANSPLANT
     *
     ******************************************************/
    public void transplant(T u, T v) {
        RedBlackNode<T> U = search(u);
        RedBlackNode<T> V = search(v);

        if (U.parent == nil) {
            this.root = V;
        } else if (U == U.parent.left) {
            U.parent.left = V;
        } else if (U == U.parent.right) {
            U.parent.right = V;
        }
        V.parent = U.parent;
    }

    /*****************************************************
     *
     *            SEARCH
     *
     ******************************************************/
    public RedBlackNode<T> search(T key) {
        RedBlackNode<T> curr = this.root;

        while (curr != nil) {
            if (compare(curr.key, key) == 0)
                return curr;
            else if (compare(curr.key, key) < 0)
                curr = curr.right;
            else if (compare(curr.key, key) > 0)
                curr = curr.left;
        }
        // if not found, return null;
        return null;
    }


    /*****************************************************
     *
     *            DELETE
     *
     ******************************************************/
    public void delete(T z) {
        RedBlackNode<T> Z = search(z);
        RedBlackNode<T> y = Z;
        int y_original_color = y.color;
        RedBlackNode<T> x = nil;
        if (Z.left == nil) {
            x = Z.right;
            transplant(z, Z.right.key);
        } else if (Z.right == nil) {
            x = Z.left;
            transplant(z, Z.left.key);
        } else {
            y = TreeMinimum(Z.right);
            y_original_color = y.color;
            x = y.right;

            if (y.parent == Z) {
                x.parent = y;
            } else {
                transplant(y.key, y.right.key);
                y.right = Z.right;
                y.right.parent = y;
            }

            transplant(z, y.key);
            y.left = Z.left;
            y.left.parent = y;
            y.color = Z.color;
        }
        /*fix numLeft and numRight*/
        fixNodeData(x, y);

        if (y_original_color == RedBlackNode.BLACK)
            deleteFixUp(x);

    }


    /*****************************************************
     *
     *            DELETE-FIX-UP
     *
     ******************************************************/
    public void deleteFixUp(RedBlackNode<T> x) {
        while (x != nil && x.color == RedBlackNode.BLACK) {// x always points to a nonroot doubly black node
            RedBlackNode<T> w;
            if (x == x.parent.left) {
                w = x.parent.right;
                if (w.color == RedBlackNode.RED) {     //case 1
                    w.color = RedBlackNode.BLACK;      //case 1
                    x.parent.color = RedBlackNode.RED; //case 1
                    leftRotate(x.parent);
                    w = x.parent.right;                //case 1
                }
                if (w.left.color == RedBlackNode.BLACK && w.right.color == RedBlackNode.BLACK) {
                    w.color = RedBlackNode.RED;        //case 2
                    x = x.parent;                      //case 2 x can be either red-black or doubly black
                } else if (w.right.color == RedBlackNode.BLACK) {
                    w.left.color = RedBlackNode.BLACK;  //case3
                    w.color = RedBlackNode.RED;         //case3
                    rightRotate(w);                     //case3
                    w = x.parent.right;                 //case3
                }
                w.color = x.parent.color;                  //case4
                x.parent.color = RedBlackNode.BLACK;       //case4
                w.right.color = RedBlackNode.BLACK;        //case4
                leftRotate(x.parent);                      //case4
                x = nil; // exit the loop
            } else {
                w = x.parent.left;
                if (w.color == RedBlackNode.RED) {     //case 1
                    w.color = RedBlackNode.BLACK;      //case 1
                    x.parent.color = RedBlackNode.RED; //case 1
                    rightRotate(x.parent);
                    w = x.parent.left;                //case 1
                }
                if (w.right.color == RedBlackNode.BLACK && w.left.color == RedBlackNode.BLACK) {
                    w.color = RedBlackNode.RED;        //case 2
                    x = x.parent;                      //case 2 x can be either red-black or doubly black
                } else if (w.left.color == RedBlackNode.BLACK) {
                    w.right.color = RedBlackNode.BLACK;  //case3
                    w.color = RedBlackNode.RED;         //case3
                    leftRotate(w);                     //case3
                    w = x.parent.left;                 //case3
                }
                w.color = x.parent.color;                  //case4
                x.parent.color = RedBlackNode.BLACK;       //case4
                w.left.color = RedBlackNode.BLACK;        //case4
                rightRotate(x.parent);                      //case4
                x = nil; // exit the loop

            }
        }// end while
        x.color = RedBlackNode.BLACK;
    }


    /*****************************************************
     *
     *            FIX-NODE-DATA
     *  reference Figure 12.4, pp.294
     ******************************************************/
    public void fixNodeData(RedBlackNode<T> x, RedBlackNode<T> y) {
        /* y was the at the node deleted*/
        /* if x is nil, we'll start updating from y.parent
         *  else, from x.parent
         * */
        RedBlackNode<T> curr = nil;
        RedBlackNode<T> currChild = nil;

        if (x == nil) {
            curr = y.parent;
            currChild = y;
        } else {
            curr = x.parent;
            currChild = x;
        }
        while (curr != nil) { // NOT ROOT

            /*IF y != x.parent */
            if (compare(y.key, curr.key) != 0) {

                /*if y (before transplant) was smaller than x'parent*/
                if (compare(y.key, curr.key) < 0)
                    curr.numLeft--;
                else if (compare(y.key, curr.key) > 0)
                    curr.numRight--;
            }

            /*ELSEIF y == x.parent */
            else {
                if (curr.left == nil)
                    curr.numLeft--;
                else if (curr.right == nil)
                    curr.numRight--;
                else {
                    if (curr.right == currChild)
                        curr.numRight--;
                    else if (curr.left == currChild)
                        curr.numLeft--;
                }
            }
            /*updateHighestHeightBtwLR curr and currChild*/
            currChild = curr;
            curr = curr.parent;
        }

    }

    /*****************************************************
     *
     *            TREE-MINIMUM
     *
     ******************************************************/
    public RedBlackNode<T> TreeMinimum(RedBlackNode<T> x) {
        RedBlackNode<T> p = search(x.key);
        while (p.left != nil) {
            p = p.left;
        }
        return p;
    }


    /*****************************************************
     *
     *            TREE-MAXIIMUM
     *
     ******************************************************/
    public RedBlackNode<T> TreeMax(RedBlackNode<T> x) {
        RedBlackNode<T> p = search(x.key);
        while (p.right != nil) {
            p = p.right;
        }
        return p;
    }

    /*****************************************************
     *
     *            SUCCESSOR
     *
     ******************************************************/

    public RedBlackNode<T> successor(RedBlackNode<T> x) {
        RedBlackNode<T> X = search(x.key);
        RedBlackNode<T> y = nil;
        if (X.right != null) {
            X = TreeMinimum(x.right);
        } else {
            y = X.parent;
            while (y != nil && X == y.right) {
                X = y;
                y = y.parent;
            }
        }
        return y;
    }


    /*****************************************************
     *
     *            COMPARE
     *
     ******************************************************/

    public int compare(T a, T b) {
        if (comparator == null)
            return a.compareTo(b);
        else
            return comparator.compare(a, b);
    }
}
