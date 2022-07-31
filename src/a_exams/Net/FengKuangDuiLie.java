package a_exams.Net;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xu on 28/09/2017.
 * <p>
 * 小易老师是非常严厉的,它会要求所有学生在进入教室前都排成一列,并且他要求学生按照身高不递减的顺序排列。
 * 有一次,n个学生在列队的时候,小易老师正好去卫生间了。学生们终于有机会反击了,于是学生们决定来一次疯狂的队列,
 * 他们定义一个队列的疯狂值为每对相邻排列学生身高差的绝对值总和。由于按照身高顺序排列的队列的疯狂值是最小的,
 * 他们当然决定按照疯狂值最大的顺序来进行列队。现在给出n个学生的身高,请计算出这些学生列队的最大可能的疯狂值。
 * 小易老师回来一定会气得半死。
 * 输入描述:
 * 输入包括两行,第一行一个整数n(1 ≤ n ≤ 50),表示学生的人数
 * 第二行为n个整数h[i](1 ≤ h[i] ≤ 1000),表示每个学生的身高
 * <p>
 * <p>
 * 输出描述:
 * 输出一个整数,表示n个学生列队可以获得的最大的疯狂值。
 * <p>
 * 如样例所示:
 * 当队列排列顺序是: 25-10-40-5-25, 身高差绝对值的总和为15+30+35+20=100。
 * 这是最大的疯狂值了。
 * <p>
 * 输入例子1:
 * 5
 * 5 10 25 40 25
 * <p>
 * 输出例子1:
 * 100
 */
public class FengKuangDuiLie {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++)
                nums[i] = in.nextInt();
            Arrays.sort(nums);


            int max = nums[n - 1];// 上一次加入疯狂队列的那个最大值
            int min = nums[0]; // 上一次加入疯狂队列的那个最小值
            int diff_sum = max - min;
            int i, j;//未加入疯狂队列的最小值、最大值索引
            for (i = 1, j = n - 2; i < j; i++, j--) {
                diff_sum += max - nums[i]; //将未加入的最小值，放到上一次最大值的边上
                diff_sum += nums[j] - min; //将未加入的最大值，放到上一次最小值的边上
                min = nums[i];//更新
                max = nums[j];//更新
            }
            //如果最后还剩下一个
            if (n % 2 == 0) {
                System.out.println(diff_sum);
                return;
            } else { //只需要判断剩下的值放在上一次最大值还是最小值的边上即可
                diff_sum += Math.max(max - nums[i], nums[i] - min);
                System.out.println(diff_sum);
                return;
            }

        }
    }
}
