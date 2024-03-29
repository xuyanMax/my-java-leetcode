package a_OA.nowcoder; /**
 * @Author: xyx
 * @Date: 2018-12-01 18:53
 * @Version 1.0
 * https://www.nowcoder.com/questionTerminal/0385945b7d834a99bc0010e67f892e38
 */

import java.util.*;

public class DiLaoTaoSheng {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();


        char[][] map = new char[n][m];//存这个地牢的信息

        for (int i = 0; i < n; i++) {
            map[i] = in.next().toCharArray();
        }

        int startX = in.nextInt();//起点
        int startY = in.nextInt();
        int k = in.nextInt();//步子数

        int[][] dir = new int[k][2];//神奇的步子

        for (int i = 0; i < k; i++) {
            dir[i][0] = in.nextInt();
            dir[i][1] = in.nextInt();
        }

        int[][] targ = new int[n][m];//到可行点的最小步数

        for (int i = 0; i < n; i++) {
            Arrays.fill(targ[i], Integer.MAX_VALUE);
        }
        targ[startX][startY] = 0;

        class Node {//局部内部类定义一个点
            int x;
            int y;

            Node(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public Node goK(int k) {//尝试走第K种步子到达的节点
                return new Node(x + dir[k][0], y + dir[k][1]);
            }

            public boolean isLegal() {//判断这个节点是不是在地牢内 && 是不是障碍
                return x >= 0
                        && y >= 0
                        && x < n
                        && y < m
                        && map[x][y] == '.';
            }
        }


        Node startNode = new Node(startX, startY);

        //用队列来记录是不是所有 "." 都被处理了
        Deque<Node> queue = new ArrayDeque<>();

        queue.add(startNode);//从起点开始

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0; i < k; i++) {
                Node next = now.goK(i);
                if (next.isLegal()) {
                    //这里避免了往回走，不加1也可以，就是大于等于minThisPosition[now.x][now.y]+1
                    //bfs层的思想决定了，下一步不可能向内层走
                    if (targ[next.x][next.y] > targ[now.x][now.y] + 1) {
                        //每种步子都尝试一下，找到最小值
                        targ[next.x][next.y] = targ[now.x][now.y] + 1;
                        queue.add(next);//加到队列里等待处理
                    }
                }
            }
        }


        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //找到最大值
                if (map[i][j] == '.') result = Math.max(result, targ[i][j]);
            }
        }
        //如果存在不能到达的 "." ,就会保留原来的初试值，返回-1
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);

    }
}
