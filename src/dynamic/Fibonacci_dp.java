package dynamic;

public class Fibonacci_dp {

	public static void main(String[] args) {
		
		int n = 10;
		int[] dp = new int[n+1];
		dp[0]=-1;dp[1]=1;dp[2]=1;
		System.out.println(fibonacci(10, dp));
		System.out.println(fibonacci(10));

	}
	
	
	
	/**
	 * 
	 * naive solution recursion
	 * time cost; exponential: bad!!!
	 * 
	 */
	
	static int fibonacci(int n) {
		if(n<=2) return 1;
		return fibonacci(n-1) + fibonacci(n-2);
	}
	/**
	* dynamic programming -- fibonacci
	* memoize version
	*  dynamic replaces recursion
	*  O(n) time
	*/
	static int fibonacci(int n, int[]dp) {
		
		if (dp[n] != 0)
			return dp[n];
		
		int f = 0;
		
		if (n <= 2) return 1;
		
		else 
			f = fibonacci(n-1, dp)+fibonacci(n-2, dp);
		
		dp[n] = f;// dynamic[3:] can be updated, so we need base dynamic[0:2]
		
		return f;
	}
	/**
	 *  Fibonacci, bottom-up dynamic-algorithm
	 *  practically faster: no recursion
	 *  O(n) time
	 *  constant space, from bottom-up dynamic-algo, you see what you really need to store
	 *  running time obvious 
	 */
	static int fibonacci2(int n) {
		if(n<=2) return 1;
		
		int first=1, second=1, sum=0;// constant space cost
		for(int i=3;i<=n;i++) {
			sum = first + second;
			first = second;
			second = sum;
		}
		 return sum;
		
	} 
	static int fibonacci3(int n) {
		int dp[] = new int[n+1];
		
		if(n<=2) return 1;
		
		dp[1]=1;dp[2]=1;		
		
		for(int i=3;i<=n;i++) 
			dp[i]=dp[i-1]+dp[i-2]; // lookup into the table takes O(1)time
		
		return dp[n];		
	}
	

}
