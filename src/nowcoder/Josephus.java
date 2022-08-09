package nowcoder;

import java.util.LinkedList;

public class Josephus {
    /**
     * 建立一个有n个元素的链表，然后从链表头开始遍历并记数，数到第m个踢出，
     * 在遍历过程中要判断当前元素是否到达链表末尾，如果到达末尾，把迭代器移到链表的头部。
     * 在删除第m个时记得要记录下一个元素的地址，以便删除之后重新从1开始报数。
     */
    public int LastRemaining_Solution(int n, int m) {
        // write code here
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int posNext = 0;
        while (list.size() > 1) {
            int delPos = (posNext + m - 1) % list.size();
            list.remove(delPos);
            posNext = delPos % list.size();
        }

        return list.size() == 0 ? -1 : list.get(0);
    }
}
