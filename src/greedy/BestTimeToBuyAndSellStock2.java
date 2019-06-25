package greedy;

//https://discuss.leetcode.com/topic/17081/three-lines-in-c-with-explanation/12
public class BestTimeToBuyAndSellStock2 {

    public static void main(String[] args) {


    }
    // monotone sequence multiple transactions
    // 7 1 5 3 6 4
    // ans 0 + 4 + 0 + 3 + 0 = 7
    // 7 1 5 3 4 6
    // ans 0 + 4+ 0 + 1 + 2 = 7

    public static int solution(int[] prices) {
        int maxSumSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxSumSoFar = Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxSumSoFar;
    }

}
