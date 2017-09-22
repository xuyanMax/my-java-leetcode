package exams;
import java.util.PriorityQueue;

import java.util.Scanner;

/**
 * Created by xu on 26/08/2017.
 */
public class DiDi_170826_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            String input = in.nextLine();

            //按空格拆分
            String[] nums_str = input.split("\\s+");
            //新建整形数组
            int[] nums = new int[nums_str.length];

            //字符数组转整形数组
            for (int i=0; i<nums.length; i++)
                nums[i] = Integer.valueOf(nums_str[i]);

            //输入K
            int K = in.nextInt();

            //利用堆
            PriorityQueue<Integer> pqueue = new PriorityQueue<>((a,b)->(a-b));

            for (int i=0; i<nums.length; i++) {
                pqueue.add(nums[i]);
                if (i >= K)
                    pqueue.poll();
            }
            System.out.println(pqueue.peek());

        }
    }

}
