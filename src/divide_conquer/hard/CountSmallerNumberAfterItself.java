package divide_conquer.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
*315. Count of Smaller Numbers After Self

You are given an integer array nums and you have to return a new counts array.
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
* */
public class CountSmallerNumberAfterItself {
    class Node {
        Node left, right;
        // sum: #nodes on the left subtree
        // dup: #nodes with same val
        int val, sum, dup = 1;

        public Node(int val, int sum) {
            this.val = val;
            this.sum = sum;
        }
    }

    //构建平衡二叉树，并记录两个数据 duplicate and sum
    //分别表示节点重复数目，当前节点左子树节点个数
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        Node root = null;
        Integer[] res = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i--)
            root = insert(root, nums[i], res, i, 0);

        return Arrays.asList(res);
    }

    /*smaller: 表示当前节点的父节点的左子树节点个数，这些节点都比当前节点小!*/
    public Node insert(Node node, int insert, Integer[] res, int index, int smaller) {
        if (node == null) {
            node = new Node(insert, 0);
            res[index] = smaller;
        } else if (node.val == insert) {
            node.dup++;//重复节点个数++
            res[index] = node.sum + smaller;//
        } else if (node.val < insert) {
            node.right = insert(node.right, insert, res, index,
                    smaller + node.dup + node.sum);// 插入右子节点，更新smaller=smaller+dup+sum
        } else {
            //插入左子节点，更新sum
            node.sum++;
            node.left = insert(node.left, insert, res, index, smaller);
        }
        return node;
    }

    // merge-sort
    class Index {
        public int number;
        public int index;

        public Index(int number, int index) {
            this.number = number;
            this.index = index;
        }

        public Index(Index input) {
            this.index = input.index;
            this.number = input.number;
        }
    }

    public List<Integer> countSmallerSol2(int[] nums) {

        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Index[] INDEX = new Index[nums.length];
        //记录原始数组nums的对应元素&索引信息
        for (int i = 0; i < nums.length; i++)
            INDEX[i] = new Index(nums[i], i);
        int[] smaller = new int[nums.length];

        //排序后Index[0:n]升序排列
        INDEX = sort(INDEX, smaller);
        // stream
        res = Arrays.stream(smaller).mapToObj(Integer::new).collect(Collectors.toList());
        return res;
    }

    // INDEX[] 数组个数最小划分为1
    public Index[] sort(Index[] nums, int[] smaller) {
        int half = nums.length / 2;
        if (half > 0) {
            Index[] left = new Index[half];
            Index[] right = new Index[nums.length - half];
            for (int i = 0; i < half; i++) left[i] = new Index(nums[i]);
            for (int j = 0; j < right.length; j++) right[j] = new Index(nums[half + j]);

            Index[] retLeft = sort(left, smaller), retRight = sort(right, smaller);

            int n = retLeft.length, m = retRight.length, i = 0, j = 0;
            while (i < n || j < m) {
                if (j == m || (i < n && left[i].number <= right[j].number)) {
                    nums[i + j] = left[i];
                    smaller[left[i].index] += j;//每次累加数值来源于 右侧同级数组的比当前值小的元素个数，合并一次，累加一次
                    i++;
                } else {
                    nums[i + j] = right[j];
                    j++;
                }
            }

        }
        return nums;
    }

    // mergesort 2
    public void sort(Index[] INDEX, int[] smaller, int[] nums) {
        // 排序
    }

}
