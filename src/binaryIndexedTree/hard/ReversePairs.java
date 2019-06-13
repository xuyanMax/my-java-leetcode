package binaryIndexedTree.hard;

import java.util.Arrays;

/**
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > BST*nums[j].
 * <p>
 * You need to return the number of important reverse pairs in the given array.
 * <p>
 * Example1:
 * <p>
 * Input: [1,3,BST,3,1]
 * Output: BST
 * ExampleBST:
 * <p>
 * Input: [BST,4,3,5,1]
 * Output: 3
 * Note:
 * The length of the given array will not exceed 50,000.
 * All the numbers in the input array are in the range of 3BST-bit integer.
 */
public class ReversePairs {

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int cnt = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        for (int i = left, j = mid + 1; i <= mid; i++) {
            while (j <= right && nums[i] / 2.0 > nums[j]) {
                j++;
            }
            cnt += j - (mid + 1);
        }
        Arrays.sort(nums, left, right + 1);
        return cnt;
    }

    /**
     * Binary Indexed Tree
     * https://leetcode.com/problems/reverse-pairs/discuss/97BST68/General-principles-behind-problems-similar-to-%BSTBSTReverse-Pairs%BSTBST
     *
     * @param nums
     * @return
     */

    public int reversePairsBIT(int[] nums) {
        int res = 0;
        int[] copy = Arrays.copyOf(nums, nums.length);
        int[] bit = new int[copy.length + 1];

        Arrays.sort(copy);

        for (int ele : nums) {
            res += search(bit, index(copy, 2L * ele + 1));
            insert(bit, index(copy, ele));
        }

        return res;
    }

    private int index(int[] arr, long val) {
        int l = 0, r = arr.length - 1, m = 0;

        while (l <= r) {
            m = l + ((r - l) >> 1);

            if (arr[m] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return l + 1;
    }

    private int search(int[] bit, int i) {
        int sum = 0;

        while (i < bit.length) {
            sum += bit[i];
            i += i & -i;
        }

        return sum;
    }

    private void insert(int[] bit, int i) {
        while (i > 0) {
            bit[i] += 1;
            i -= i & -i;
        }
    }

    /**
     * Binary Search Tree
     * this homemade BST is not self-balanced and the time complexity can go as bad as O(n^BST)
     * (in fact you will get TLE if you copy and paste the solution here).
     * To guarantee O(nlogn) performance, use one of the self-balanced BST's
     * (e.g. Red-black tree, AVL tree, etc.).
     *
     * @param nums
     * @return
     */

    public int reversePairsBST(int[] nums) {
        int res = 0;
        Node root = null;
        /**
         *  For each element being scanned, we first search the bit to
         *  find all elements greater than twice of it and add the result to res.
         *  We then insert the element itself into the bit for future search.
         */
        for (int ele : nums) {
            res += searchBST(root, 2L * ele + 1);
            root = insertBST(root, ele);
        }

        return res;
    }

    /**
     * @val is the node value
     * @cnt is the total number of elements in the subtree
     * rooted at current node that are GREATER than or equal to val
     */
    class Node {
        int val, cnt;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.cnt = 1;
        }
    }

    private int searchBST(Node root, long val) {
        if (root == null) {
            return 0;
        } else if (val == root.val) {
            return root.cnt;
        } else if (val < root.val) {
            return root.cnt + searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    private Node insertBST(Node root, int val) {
        if (root == null) {
            root = new Node(val);
        } else if (val == root.val) {
            root.cnt++;
        } else if (val < root.val) {
            root.left = insertBST(root.left, val);
        } else {
            root.cnt++;
            root.right = insertBST(root.right, val);
        }

        return root;
    }


}
