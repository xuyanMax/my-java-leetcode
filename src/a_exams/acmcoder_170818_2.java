package a_exams;

import java.util.Scanner;

/**
 * Created by xu on 18/08/2017.
 * <p>
 * 搭积木
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 一天，小明买了许多积木回家，他想把这些积木拼接在一起。每块积木有两个接口，
 * 每个接口我们用一个数字标记，规定只有当两块积木有相同数字标记的接口时，这两块积木才可以通过该接口拼接在一起。
 * 举例，有两块积木，接口数字分别为1，2和3，4，那么这两块积木无法拼接；若两块积木接口数字分别为1，2和2，3，
 * 那么这两块积木可以通过由数字2标记的接口拼接在一起。
 * 现在小明知道所有积木的数量和每块积木接口的数字标记，你能告诉他他可以将所有积木拼接成一个整体么？
 * <p>
 * 输入
 * 第一行一个整数t，表示测试数组组数1≤t≤10；
 * 接下来在每组测试数据中：
 * 第一行一个整数n，表示积木的数量1≤n≤100000，
 * 下面n行每行2个整数x，y，表示其中一块积木的两个接口的数字标记；1≤x，y≤100000；
 * <p>
 * 输出
 * 对于每组测试数据，输出”YES”，表示该组数据中的所有积木可以拼接成一个整体，”NO”表示不行。（注意输出不包括引号）
 * <p>
 * 样例输入
 * 2
 * 3
 * 1 2
 * 2 3
 * 4 5
 * 6
 * 1 2
 * 2 3
 * 3 5
 * 4 5
 * 4 6
 * 5 1
 * 样例输出
 * NO
 * YES
 * <p>
 * Hint
 * 在第一组测试数据中，有3块积木，显然前两块是可以拼接在一起的，但是第3块无论如何也无法和前两块拼接，所以输出NO；
 * 第二组数据中我们可以这样拼接：5-1-1-2-2-3-3-5-5-4-4-6，因此输出YES。
 */
public class acmcoder_170818_2 {
    public static void main(String args[]) {
        //参考资源：http://m.blog.csdn.net/u012324136/article/details/77483072
        Scanner cin = new Scanner(System.in);
        int t, n;
        //遍历完链表后，链表中除了head头外只剩下两个节点，则表示成功，否则失败
        while (cin.hasNextInt()) {
            t = cin.nextInt();

            String[] res = new String[t];//存储t次结果
            int index = -1;
            while (t > 0) {

                index++;
                t--;
                n = cin.nextInt();
                int[][] data = new int[n][2];

                //输入数据
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < 2; j++)
                        data[i][j] = cin.nextInt();

                //创建链表头-头节点不存放数据
                Node head = new Node();

                //第一个插入节点
                //按升序排列
                Node first_node_x = new Node(data[0][0]);
                Node first_node_y = new Node(data[0][1]);

                if (first_node_x.val > first_node_y.val) {
                    head.next = first_node_y;
                    first_node_y.next = first_node_x;
                } else {
                    head.next = first_node_x;
                    first_node_x.next = first_node_y;
                }
                for (int i = 1; i < n; i++) {

                    for (int j = 0; j < 2; j++) {
                        Node curr = head;

                        while (head != null) {
                            if (data[i][j] == curr.next.val) {
                                curr.next = curr.next.next;//如果存在相同节点，则删除该节点
                                break;//结束
                            } else if (data[i][j] > curr.next.val) { //大于
                                if (curr.next.next != null)
                                    curr = curr.next;
                                else
                                    curr.next.next = new Node(data[i][j]);
                            } else { //插入新节点，并调整顺序
                                Node node = new Node(data[i][j]);
                                node.next = curr.next;
                                curr.next = node;
                                break;//结束
                            }
                        }
                    }
                }
                int length = getLength(head);
                if (length == 2)
                    res[index] = "Yes";
                else
                    res[index] = "No";

            }//while (t>0)
            print(res);
        }
    }

    public static void print(String[] res) {
        for (String str : res)
            System.out.println(str);
    }

    public static int getLength(Node node) {
        Node curr = node.next;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }

    static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
