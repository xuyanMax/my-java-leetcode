package array.presum;

/**
 * Created by xu on 2022/9/20 09:26.
 * 你们班上有若干同学，每个同学有一个期末考试的成绩（满分 100 分），那么请你实现一个 API，
 * 输入任意一个分数段，返回有多少同学的成绩在这个分数段内。
 */
public class ScoreRange {

    int[] scores; // 存储着所有同学的分数
    // 试卷满分 100 分
    int[] count = new int[100 + 1];

    public ScoreRange(int[] scores) {
        this.scores = scores;
        for (int score : scores)
            count[score]++;
        // 构造前缀和
        for (int i = 1; i < count.length; i++)
            count[i] = count[i] + count[i - 1];
    }

    // 记录每个分数有几个同学
    public int scoreRange(int var1, int var2) {
        return Math.abs(count[var2] - count[var1]);
    }

// 利用 count 这个前缀和数组进行分数段查询
}
