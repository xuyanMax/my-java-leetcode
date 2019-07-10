package dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The Weighted Interval Scheduling problem is strictly more general version,
 * in which each interval has a certain weight, and we want to accept a set of maximum weight.
 * The input is a set of time-intervals, where each interval has a weight.
 * The output is a subset of non-overlapping intervals.
 * Our objective is to maximize the sum of the weights in the subset.
 */
public class WeightedJobScheduling {

    public static void main(String[] args) {
        WeightedJobScheduling mp = new WeightedJobScheduling();

        Job jobs[] = new Job[6];
        jobs[0] = mp.new Job(1, 3, 5);
        jobs[1] = mp.new Job(2, 5, 6);
        jobs[2] = mp.new Job(4, 6, 5);
        jobs[3] = mp.new Job(6, 7, 4);
        jobs[4] = mp.new Job(5, 8, 11);
        jobs[5] = mp.new Job(7, 9, 2);

        System.out.println(mp.MaxProfit(jobs));

    }

    class Job {
        int start;
        int end;
        int profit;

        Job(int s, int e, int p) {
            start = s;
            this.end = e;
            this.profit = p;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getProfit() {
            return profit;
        }
    }

    /**
     *
     * top-down dp
     *
     * Time complexity: O(n^2)
     * <p>
     * if (jobs don't overlap)
     * <p>
     * dp[i] = max(dp[i], dp[j]+jobs[i].profit) where j in [0, i-1];
     * <p>
     * <p>
     * 1. First, sort jobs by early finish time
     * <p>
     * 2. Initially dp[i] = jobs[i].profit: meaning the max profit until the i-th job
     * <p>
     * 3. Update the max profit for dp[i] by scanning the non-overlapping jobs before i-th job
     * by the formula dp[i] = max(dp[i], jobs[i].profit + dp[j]) where j in [0,i-1]
     *
     * @param jobs
     * @return
     */
    public int MaxProfit(Job[] jobs) {

        int[] dp = new int[jobs.length];
        Arrays.sort(jobs, Comparator.comparingInt(Job::getEnd));

        dp[0] = jobs[0].profit;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = jobs[i].profit;//Math.max(dp[i-1], jobs[i].profit);
            for (int j = i - 1; j >= 0; j--) {
                if (jobs[j].end <= jobs[i].start) {
                    dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

}
