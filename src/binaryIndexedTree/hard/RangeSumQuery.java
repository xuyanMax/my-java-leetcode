package binaryIndexedTree.hard;

/**
 * 307. Range Sum Query - Mutable
 * <p>
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * <p>
 * The updateHighestHeightBtwLR(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 * Given nums = [1, 3, 5]
 * <p>
 * sumRange(0, 2) -> 9
 * updateHighestHeightBtwLR(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * The array is only modifiable by the updateHighestHeightBtwLR function.
 * You may assume the number of calls to updateHighestHeightBtwLR and sumRange function is distributed evenly.
 */
public class RangeSumQuery {

    class SegmentTreeNode {
        int start, end;
        SegmentTreeNode left, right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }

    SegmentTreeNode root = null;

    public RangeSumQuery(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else {
            SegmentTreeNode ret = new SegmentTreeNode(start, end);
            if (start == end) {
                ret.sum = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                ret.left = buildTree(nums, start, mid);
                ret.right = buildTree(nums, mid + 1, end);
                ret.sum = ret.left.sum + ret.right.sum;
            }
            return ret;
        }
    }

    void update(int i, int val) {
        update(root, i, val);
    }
    //后续遍历
    void update(SegmentTreeNode root, int pos, int val) {
        if (root.start == root.end) {
            root.sum = val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (pos <= mid) {//更新的索引在mid左侧
                update(root.left, pos, val);
            } else {//右侧
                update(root.right, pos, val);
            }
            // 更新父节点的sum
            root.sum = root.left.sum + root.right.sum;
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    public int sumRange(SegmentTreeNode root, int start, int end) {
        if (root.end == end && root.start == start) {
            return root.sum;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid) {
                return sumRange(root.left, start, end);
            } else if (start >= mid + 1) {
                return sumRange(root.right, start, end);
            } else {
                return sumRange(root.right, mid + 1, end) + sumRange(root.left, start, mid);
            }
        }
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.updateHighestHeightBtwLR(i,val);
 * int param_2 = obj.sumRange(i,j);
 */