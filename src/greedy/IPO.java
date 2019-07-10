package greedy;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author xu
 * https://leetcode.com/problems/ipo/#/description
 * <p>
 * Given a profit arr of projects' profits[], a capital arr of projects' capital[]
 * A K #distinct projects and W initial capital.
 * Pick a list of at most K projects to maximize your profits
 * <p>
 * Example
 * Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
 * Output: 4
 * <p>
 * Explanation: Since you have initial capital 0, you can only start the project indexed 0. You got W=W+profit[0] = 1;
 * With capital 1 you can start either project 1 or 2. Since you can choose at most 2 projects. You will choose project2
 * W = W + profit[2] = 4;
 */

public class IPO {

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        /** Store the projects' Capital and Profit by increasing order w.r.t Capital
         *  So that we get the lowest capital at the head of pq.
         */
        PriorityQueue<int[]> queue_cap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for (int i = 0; i < Capital.length; i++)
            queue_cap.add(new int[]{Capital[i], Profits[i]});

        /**
         * Store the projects' Capital and Profit by decreasing order w.r.t Profit
         * So that at head of the pq, stands the highest profit.s
         */
        PriorityQueue<int[]> queue_profit = new PriorityQueue<>((a, b) -> (b[1] - a[1]));

        // if k > total #projects. we small the k
        k = Math.min(k, Capital.length);

        while (k-- > 0) {
            while (!queue_cap.isEmpty() && W >= queue_cap.peek()[0]) {
                queue_profit.add(queue_cap.poll());
            }

            if (queue_profit.isEmpty())// no available projects so far
                break;

            W += queue_profit.poll()[1];// add up the profit
        }

        return W;
    }
}

