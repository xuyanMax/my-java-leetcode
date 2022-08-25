package a_OA.nowcoder;
import java.util.ArrayList;
/**
 * 链接：https://www.nowcoder.com/questionTerminal/c451a3fd84b64cb19485dad758a55ebe?orderByHotValue=1&questionTypes=000100&page=4&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]和为S的连续正数序列
 * 热度指数：162368时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class ContinuousTargetSum {
    /**
     * 当总和小于sum，大指针继续+
     * 否则小指针+
     *
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (sum == 0) return results;
        int i = 0, j = 1;

        while (i < j) {
            int currSum = (i + j) * (j - i + 1) / 2;

            if (currSum < sum) j++;

            else if (currSum == sum) {
                ArrayList<Integer> result = new ArrayList<>();
                for (int k = i; k <= j; k++)
                    result.add(k);
                results.add(new ArrayList<>(result));
            } else i++;
        }
        return results;
    }
}
