package nowcoder; /**
 * 题目描述
 * 牛牛的作业薄上有一个长度为 n 的排列 A，这个排列包含了从1到n的n个数，但是因为一些原因，
 * 其中有一些位置（不超过 10 个）看不清了，但是牛牛记得这个数列顺序对的数量是 k，顺序对是指满足 i < j 且 A[i] < A[j] 的对数，
 * 请帮助牛牛计算出，符合这个要求的合法排列的数目。
 * <p>
 * <p>
 * 输入描述:
 * 每个输入包含一个测试用例。每个测试用例的第一行包含两个整数 n 和 k（1 <= n <= 100, 0 <= k <= 1000000000），
 * 接下来的 1 行，包含 n 个数字表示排列 A，其中等于0的项表示看不清的位置（不超过 10 个）。
 *
 * @Author: xyx
 * @Date: 2018-12-01 21:51
 * @Version 1.0
 * 链接：https://www.nowcoder.com/questionTerminal/b698e67a2f5b450a824527e82ed7495d
 * 来源：牛客网
 * <p>
 * 思路：首先将模糊的数字统计出来，并求出这些数字的全排列，然后对每个排列求顺序对
 * 关键去求全排列，需要递归的求出所有排列。。。
 * <p>
 * ps：第一次做的时候没看清楚题，以为可以有重复数字，直接深搜计算了，结果。。。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ShuLieHuanYuan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int RES = 0;
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] A = new int[n];
            boolean[] flag = new boolean[n + 1];
            //flag标记哪些数字已经存在
            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
                if (A[i] != 0) {
                    flag[A[i]] = true;
                }
            }

            //统计排列中不存在的数字
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 1; i <= n; i++) {
                if (flag[i] == false)
                    list.add(i);
            }

            //perm用来存模糊数字的全排列
            List<ArrayList<Integer>> perm = new ArrayList<ArrayList<Integer>>();

            //计算perm
            calperm(perm, list, 0);

            //统计已有的排列的顺序对
            int cv = 0;
            for (int i = 0; i < n; i++) {
                if (A[i] != 0) {
                    for (int j = i + 1; j < n; j++) {
                        if (A[j] != 0 && A[i] < A[j])
                            cv++;
                    }
                }
            }

            //计算每个模糊数字排列的顺序对，如果与k相等，则结果RES加一
            for (ArrayList<Integer> tmp : perm) {
                int val = cv;
                int[] tmpA = Arrays.copyOf(A, n);
                val += calvalue(tmp, tmpA);
                if (val == k)
                    RES++;
            }

            System.out.println(RES);
        }
    }

    //计算排列的顺序对
    public static int calvalue(List<Integer> list, int[] A) {
        int val = 0;
        int j = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                A[i] = list.get(j++);
                for (int k = 0; k < i; k++) {
                    if (A[k] != 0 && A[k] < A[i])
                        val++;
                }
                for (int k = i + 1; k < A.length; k++) {
                    if (A[k] != 0 && A[k] > A[i])
                        val++;
                }
            }
        }
        return val;
    }

    //计算全排列
    public static void calperm(List<ArrayList<Integer>> perm, ArrayList<Integer> list, int n) {
        if (n == list.size()) {
            perm.add(new ArrayList<Integer>(list));
        } else {
            for (int i = n; i < list.size(); i++) {
                Collections.swap(list, i, n);
                calperm(perm, list, n + 1);
                Collections.swap(list, i, n);
            }
        }
    }
}
