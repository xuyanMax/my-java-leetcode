package dp;

import java.util.Arrays;
import java.util.Comparator;


public class WeightedJobScheduling {

	public static void main(String[] args) {
		WeightedJobScheduling mp = new WeightedJobScheduling();
		
		Job jobs[] = new Job[6];
        jobs[0] = mp.new Job(1,3,5);
        jobs[1] = mp.new Job(2,5,6);
        jobs[2] = mp.new Job(4,6,5);
        jobs[3] = mp.new Job(6,7,4);
        jobs[4] = mp.new Job(5,8,11);
        jobs[5] = mp.new Job(7,9,2);
        
        System.out.println(mp.MaxProfit(jobs));

	}
	
	 class  Job{
		int start;
		int end;
		int profit;
		Job(int s, int e, int p) {
			start = s;
			this.end = e;
			this.profit = p;
		}
	}
	class FinishTimeComparator implements Comparator<Job> {
		@Override
		public int compare(Job j1, Job j2){
			if (j1.end <= j2.end)
				return -1;
			else
				return 1;
		
		}
	}
	/**
	 * Time complexity: O(n^2)
	 * 
	 * if (jobs don't overlap) 
	 * 	
	 * 		dp[i] = max(dp[i], dp[j]+jobs[i].profit) where j in [0, i-1];
	 * 
	 * 
	 * 1. First, sort jobs by early finish time
	 * 
	 * 2. Initially dp[i] = jobs[i].profit: meaning the max profit until the i-th job
	 * 
	 * 3. Update the max profit for dp[i] by scanning the non-overlapping jobs before i-th job
	 * 	  by the formula dp[i] = max(dp[i], jobs[i].profit + dp[j]) where j in [0,i-1]
	 * 
	 * 
	 * @param jobs
	 * @return
	 */
	public int MaxProfit(Job[] jobs){
		FinishTimeComparator comparator = new FinishTimeComparator();
		int[] dp = new int[jobs.length];
		Arrays.sort(jobs, comparator);
		
		dp[0] = jobs[0].profit;
		for (int i=1; i<dp.length; i++) {
			dp[i] = jobs[i].profit;//Math.max(dp[i-1], jobs[i].profit);
			for (int j=i-1;j>=0;j--){
				if (jobs[j].end <= jobs[i].start){
					dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int n: dp){
			System.out.println(n);
			if (n > max)
				max = n;
		}
		return max;
	}

}
