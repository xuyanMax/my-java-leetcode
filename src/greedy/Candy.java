package greedy;

import java.util.Arrays;

/**
 * LeetCode 135：分发糖果
 * <p>
 * 题意
 * <p>
 * n 个孩子站成一排，给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 需要按照以下要求给孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * <p>
 * 请你给每个孩子分发糖果，计算并返回需要准备的最少糖果数目。
 * <p>
 * 示例
 * <p>
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * <p>
 * 其实每个孩子的评分有 2 次比较，左比较和右比较，对于碰到类似这种左右比较或者相邻比较，我们都可以用两次遍历来解决
 */
public class Candy {
    public static void main(String[] args) {
        int[] rating = new int[]{1, 0, 2, 2, 4, 1};
        System.out.println(solution(rating));
    }

    public static int solution(int[] ratings) {
        int[] val = new int[ratings.length];
        Arrays.fill(val, 1);
        //左比较
        for (int i = 1; i < ratings.length; i++)
            if (ratings[i] > ratings[i - 1])
                val[i] = val[i - 1] + 1;

        //右比较
        for (int i = ratings.length - 1; i > 0; i--)
            if (ratings[i - 1] > ratings[i])
                val[i-1] = Math.max(val[i-1], val[i] + 1);

        return Arrays.stream(val).sum();
    }
}
