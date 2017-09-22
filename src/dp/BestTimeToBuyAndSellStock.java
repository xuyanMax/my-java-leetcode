package dp;


public class BestTimeToBuyAndSellStock {

	public static void main(String[] args) {
		
		System.out.println(solution());
		System.out.println(solution2(new int[] {7,1,5,3,6,4}));
	}
	public static int solution() {
		int [] arr = new int[] {7,1,5,3,6,4};
		
		
		int[] dp = new int[arr.length];
		dp[0] = 0;
		
		int buy = arr[0];
		int sell = Integer.MIN_VALUE;
		
		for (int i=1; i<arr.length; i++) {
			if (buy > arr[i]) {
				buy = arr[i];
				sell = Integer.MIN_VALUE;
				dp[i] = dp[i-1];
			}
			
			else {
				if (sell < arr[i]) {
					sell = arr[i];
					dp[i] = Math.max(sell - buy, dp[i-1]);
					
				} else 
					dp[i] = dp[i-1];
				
			}
		}
		return dp[arr.length-1];
	}
	// kadaneAlgorithm: max subarray problem
	// but if the interviewer twists the question slightly by giving the 
	// difference array of prices, ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, 
	// you might end up being confused.
	// we will use the diff array and find a contiguous sub-array giving maximum profit
	
	public static int solution2 (int[] prices) {
		if (prices.length == 0)
			return 0;
		
		int[] diff = new int[prices.length - 1];
		for (int i=1;i<prices.length;i++) 
			diff[i - 1] = prices[i] - prices[i - 1];
		
		int maxSoFar = 0;
		int maxEndHere = 0;
		
		for (int i=0; i<diff.length; i++) {
			maxSoFar = Math.max(maxEndHere + diff[i], maxSoFar);
			maxEndHere = Math.max(0, maxEndHere + diff[i]);
		}
		return maxSoFar;
	
	}

}
