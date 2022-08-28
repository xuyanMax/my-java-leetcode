package graph.tree.binaryIndexedTree;

//https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/

public class SegmentTree2 {
    int st[]; // The array that stores range sums of segment graph.tree nodes

    /**
     * Constructor to construct segment graph.tree from given array. This
     * constructor  allocates memory for segment graph.tree and calls
     * constructSTUtil() to  fill the allocated memory
     */
    SegmentTree2(int arr[], int n) {
        // Allocate memory for segment graph.tree
        //Height of segment graph.tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment graph.tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        st = new int[max_size]; // Memory allocation

        constructSTUtil(arr, 0, n - 1, 0);
    }

    // A utility function to get the middle index from corner indexes.
    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    /**
     * A recursive function to get the sum of values in given range
     * of the array.  The following are parameters for this function.
     * <p>
     * st    --> Pointer to segment graph.tree
     * si    --> Index of current node in the segment graph.tree. Initially
     * 0 is passed as root is always at index 0
     * ss & se  --> Starting and ending indexes of the segment represented
     * by current node, i.e., st[si]
     * qs & qe  --> Starting and ending indexes of queryHighestHeightBtwLR range
     */
    int getSumUtil(int ss, int se, int qs, int qe, int si) {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && se <= qe)
            return st[si];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    /**
     * A recursive function to updateHighestHeightBtwLR the nodes which have the given
     * index in their range. The following are parameters
     * st, si, ss and se are same as getSumUtil()
     * i    --> index of the element to be updated. This index is in
     * input array.
     * diff --> Value to be added to all nodes which have i in range
     */
    void updateValueUtil(int ss, int se, int i, int diff, int si) {
        // Base Case: If the input index lies outside the range of
        // this segment
        if (i < ss || i > se)
            return;

        // If the input index is in range of this node, then updateHighestHeightBtwLR the
        // value of the node and its children
        // 当前范围内包含要改变数值的元素索引i，则当前元素 += diff
        // 不同于 segmentTree 第一种 实现方式
        st[si] = st[si] + diff;
        if (se != ss) {
            int mid = getMid(ss, se);
            updateValueUtil(ss, mid, i, diff, 2 * si + 1);
            updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
        }
    }

    // The function to updateHighestHeightBtwLR a value in input array and segment graph.tree.
    // It uses updateValueUtil() to updateHighestHeightBtwLR the value in segment graph.tree
    void updateValue(int arr[], int n, int i, int new_val) {
        // Check for erroneous input index
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input");
            return;
        }

        // Get the difference between new value and old value
        int diff = new_val - arr[i];

        // Update the value in array
        arr[i] = new_val;

        // Update the values of nodes in segment graph.tree
        updateValueUtil(0, n - 1, i, diff, 0);
    }

    // Return sum of elements in range from index qs (quey start) to
    // qe (queryHighestHeightBtwLR end).  It mainly uses getSumUtil()
    int getSum(int n, int qs, int qe) {
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        }
        return getSumUtil(0, n - 1, qs, qe, 0);
    }

    // A recursive function that constructs Segment Tree for array[ss..se].
    // si is index of current node in segment graph.tree st
    int constructSTUtil(int arr[], int ss, int se, int si) {
        // If there is one element in array, store it in current node of
        // segment graph.tree and return
        // leaf node
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the sum of values in this node
        int mid = getMid(ss, se);
        // parent node.sum = left child.sum + right child.sum
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1)
                + constructSTUtil(arr, mid + 1, se, si * 2 + 2);
        // 返回当前节点
        return st[si];
    }

    // Driver program to test above functions
    public static void main(String args[]) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTree2 tree = new SegmentTree2(arr, n);

        // Build segment graph.tree from given array

        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " +
                tree.getSum(n, 1, 3));

        // Update: set arr[1] = 10 and updateHighestHeightBtwLR corresponding segment
        // graph.tree nodes
        tree.updateValue(arr, n, 1, 10);

        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " +
                tree.getSum(n, 1, 3));
    }
}
