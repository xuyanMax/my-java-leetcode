package dfs;

/**
 * Created by xu on 09/08/2017.
 */
/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use CONSTANT extra space.
*/
public class ConnectTreeNode2 {
    public static void main(String[] args) {
        ConnectTreeNode2 inst = new ConnectTreeNode2();
        inst.test();
    }

    public void test() {
        TreeLinkNode a = new TreeLinkNode(0);
        TreeLinkNode b = a;

        b.next = new TreeLinkNode(1);
        b = b.next;
        b.next = new TreeLinkNode(2);
        b = b.next;
        System.out.println(a.next.val);
    }

    public void solution(TreeLinkNode root) {

        TreeLinkNode tmp = new TreeLinkNode(918);

        while (root != null) {
            TreeLinkNode curr = tmp;

            while (root != null) {
                if (root.left != null) {
                    // tmp.next = root.left;
                    curr.next = root.left;
                    // curr = root.left;
                    curr = curr.next;
                }
                if (root.right != null) {
//                    root.left.next = root.right;
                    curr.next = root.right;
                    // curr = root.right;
                    curr = curr.next;
                }
                root = root.next;
            }
            // root指向下一层的最左侧节点
            root = tmp.next;
            // 原因-第一个while的第一行，curr指向tmp。之后while内的两个if语句 curr.next = root.left
            // 使得 curr指向的Java堆内对应的对象的next指向了新的对象，即root.left，为下一层的第一个节点
            // 然后 curr = curr.next 导致，curr指向的地址更新，不再指向tmp，之后tmp.next指向的对象始终锁定在下一层的第一个节点
            // 而curr则指向了 root.left/right 所对应的地址

            // 不加这一句，for example {1,2}
            // tmp.next将永远指向2，最后会无限循环，导致TLE
            tmp.next = null;

        }
    }

    public void solution_2(TreeLinkNode root) {
        TreeLinkNode head = root; // The left most node in the lower level
        TreeLinkNode pre = null; // the previous node in the lower level
        TreeLinkNode curr = null; // the current node in the higher level

        while (head != null) {
            curr = head;
            head = null;
            pre = null;

            while (curr != null) { // iterate on the current level
                if (curr.left != null) {
                    if (pre != null) {
                        pre.next = curr.left;
                    } else {
                        head = curr.left;
                    }
                    // 更新pre指向
                    pre = curr.left;
                }

                if (curr.right != null) {
                    if (pre != null) {
                        pre.next = curr.right;
//                        pre = pre.next;
                    } else {
                        head = curr.right;
//                        pre = curr.right;
                    }
                    //更新pre指向（无论pre是null与否，都需要更新）
                    // if pre == null, pre = curr.right;
                    // if pre != null, pre = pre.next;
                    pre = curr.right;
                }
                // 在同个level继续执行
                curr = curr.next;
            }
        }
    }

    class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int v) {
            val = v;
            left = null;
            right = null;
            next = null;
        }
    }
//     while (root != null) {
//        TreeLinkNode curr = root;
//
//        while (curr != null) {
//            if (curr.left != null && curr.right != null)
//                curr.left.next = curr.right;
//            curr = curr.next;
//        }
//        if (root.left != null )
//            root = root.left;
//        if (root.left == null && root.right != null)
//            root = root.right;
//        if (root.left == null && root.right == null)
//            break;
//    }

}
