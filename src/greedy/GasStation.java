package greedy;

// https://leetcode.com/problems/gas-station/#/description
// https://discuss.leetcode.com/topic/1344/share-some-of-my-ideas/57
public class GasStation {

    public static void main(String[] args) {


    }

    /**
     * 1, if sum of gas is more than sum of cost, then there must be a solution.
     * And the question guaranteed that the solution is unique(The first one I found is the right one).
     * <p>
     * 2, The tank should never be negative, so restart whenever there is a negative number.
     */
    public static int solution(int[] gas, int[] cost) {

        int gasSum = 0;
        int costSum = 0;
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

//		if (tank < 0)
        if (gasSum < costSum)
            return -1;
        else
            return start;
    }
}
