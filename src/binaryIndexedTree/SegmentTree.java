package binaryIndexedTree;
/*https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/*/
public class SegmentTree {
    class SegmentTreeNode {
        int start, end;
        SegmentTreeNode left, right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;// left index of the range
            this.end = end;// right index
            this.left = null; // left node
            this.right = null;// right node
            this.sum = 0;// sum of nums[start:end]
        }
    }

    SegmentTreeNode root = null;

    public SegmentTree(int[] nums) {
        root = buildTree(nums, 0, nums.length-1);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else {
            SegmentTreeNode ret = new SegmentTreeNode(start, end);
            if (start == end) {
                ret.sum = nums[start];
            } else {
                int mid = start  + (end - start) / 2;
                ret.left = buildTree(nums, start, mid);
                ret.right = buildTree(nums, mid + 1, end);
                // 自底向上计算 sum
                ret.sum = ret.left.sum + ret.right.sum;
            }
            return ret;
        }
    }

    void update(int i, int val) {
        update(root, i, val);
    }

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
            // 前序遍历，逻辑在后
            // update 更新父节点的sum
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
            } else if (start >= mid+1) {
                return sumRange(root.right, start, end);
            }  else {
                return sumRange(root.right, mid+1, end) + sumRange(root.left, start, mid);
            }
        }
    }
}
