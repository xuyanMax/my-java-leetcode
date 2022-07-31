package a_OA;

public class Linked {

    class ListNode {
        public ListNode next;
        public int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    //判断链表是否有环
    public ListNode hasCycle(ListNode head) {
        //先判断链表为空的情况
        if (head == null)
            return null;
        //快慢双指针
        ListNode fast = head;
        ListNode slow = head;
        //如果没环快指针会先到链表尾
        while (fast != null && fast.next != null) {
            //快指针移动两步
            fast = fast.next.next;
            //慢指针移动一步
            slow = slow.next;
            //相遇则有环
            if (fast == slow)
                return slow;
        }
        //到末尾则没有环
        return null;
    }

    //链表中环的入口结点
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode slow = hasCycle(pHead);
        //没有环
        if (slow == null)
            return null;
        //快指针回到表头
        ListNode fast = pHead;
        //再次相遇即是环入口
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 删除有序链表中重复的元素-I
     * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
     * 例如：
     * 给出的链表为1→2→3→3→4→4→5, 返回1→2→5.
     * 给出的链表为1→1→1→2→3, 2→3.
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr.next != null && curr.next.next != null)
            //遍历链表，每次比较相邻两个节点，如果遇到了两个相邻节点相同，则新开内循环将这一段所有的相同都遍历过去。
            if (curr.next.val == curr.next.next.val) {
                int tmp = curr.next.val;
                while (curr.next != null && curr.next.val == tmp)
                    curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        return dummy.next;
    }

    //给出的链表为1→2→3→3→4→4→5, 返回1→2→3→4→5.
    public ListNode deleteDuplicates2(ListNode head) {
        // write code here

        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr != null && curr.next != null)
            //遍历链表，每次比较相邻两个节点，如果遇到了两个相邻节点相同，则新开内循环将这一段所有的相同都遍历过去。
            if (curr.val == curr.next.val) {
                int tmp = curr.val;
                while (curr.next != null && curr.next.val == tmp)
                    curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        ListNode slow = dummy;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //slow.next 正是倒数第n个节点
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode ReverseList(ListNode head) {
        if (head == null) return head;
        /**
         * 当前节点是head，pre为当前节点的前一节点，next为当前节点的下一节点
         * 需要pre和next的目的是让当前节点从pre->head->next1->next2变成pre<-head next1->next2
         * 即pre让节点可以反转所指方向，但反转之后如果不用next节点保存next1节点的话，此单链表就此断开了
         */
        ListNode pre = null;//head is current node
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        //如果head为null的时候，pre就为最后一个节点了，但是链表已经反转完毕，pre就是反转后链表的第一个节点
        //直接输出pre就是我们想要得到的反转后的链表
        return pre;
    }

    /**
     * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)。
     * 例如：
     * 给出的链表为 1→2→3→4→5→NULL, m=2,n=4m=2,n=4,
     * 返回 1→4→3→2→5→NULL
     *
     * @param head
     * @param m
     * @param n
     * @return
     */

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

//        ListNode pre = null;
        ListNode pre = dummy;
        ListNode curr = head;

//        while (m-- > 1) {
//            pre = curr;
//            curr = curr.next;
//        }
        for (int i = 1; i < m; i++) {
            pre = curr;
            curr = curr.next;
        }
        for (int i = m; i < n; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
//            next.next = curr;
            next.next = pre.next;
            pre.next = next;
        }

        return dummy.next;
    }
    public void test() {

    }
}
