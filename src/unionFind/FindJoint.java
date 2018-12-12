package unionFind;

import java.io.IOException;
import java.util.Scanner;

/*
https://blog.csdn.net/niushuai666/article/details/6662911

某省调查城镇交通状况，得到现有城镇道路统计表，表中列出了每条道路直接连通的城镇。
省政府“畅通工程”的目标是使全省任何两个城镇间都可以实现交通（但不一定有直接的道路相连，只要互相间接通过道路可达即可）。
问最少还需要建设多少条道路？


Input
测试输入包含若干测试用例。每个测试用例的第1行给出两个正整数，分别是城镇数目N ( < 1000 )和道路数目M；
随后的M行对应M条道路，每行给出一对正整数，分别是该条道路直接连通的两个城镇的编号。为简单起见，城镇从1到N编号。
注意:两个城市之间可以有多条道路相通,也就是说
3 3
1 2
1 2
2 1

这种输入也是合法的
当N为0时，输入结束，该用例不被处理。


Output
对每个测试用例，在1行里输出最少还需要建设的道路数目。
*/
public class FindJoint {
    //通过并查集，求一共有多少个独立联通分支，如果是1个联通分支，那么不需要修路，如果是2个，那么需要修一条路，如果是三个，需要修两条路
    private static int[] pre = new int[1005];
    private static int[] block = new int[1005];

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int num_city = in.nextInt(); // city
            int m = in.nextInt();         //  m pairs of roads
            int[][] edge = new int[m][m];

            //初始化：每个点相互独立，自成集合，N个点的父节点都是自己本身
            for (int i = 1; i <= num_city; i++)
                pre[i] = i;

            int p1 = -1, p2 = -1, f1 = -1, f2 = -1;
            //记录路线
            for (int i = 0; i < m; i++) {
                p1 = in.nextInt();
                p2 = in.nextInt();
                f1 = find(p1);
                f2 = find(p2);

                if (f1 != f2)
                    pre[f2] = f1;
            }
            //标记parent节点
            for (int i = 1; i <= num_city; i++)
                block[find(i)] = 1;//同一个联通域的所有节点具有同一个父节点，因此所有联通与，只有父节点被标记未1，

            int ans = 0;

            //统计一共有多少独立联通区域，即统计存在多少个被标记的父节点
            for (int i = 1; i <= num_city; i++) {
                if (block[i] == 1)//
                    ans++;
            }

            // N个独立联通区域，表示需要修N-1条路
            System.out.println(ans - 1);
        }
    }

    public static int find(int x) {
        int r = x;
        while (r != pre[r]) {
            r = pre[r];
        }
        int i = x;
        int j;

        //路径压缩
        while (pre[i] != r) {
            j = pre[i];
            pre[i] = r;//把上级改为根节点
            i = j;
        }
        return r;
    }

}
