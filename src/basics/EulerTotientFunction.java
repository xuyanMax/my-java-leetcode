package basics;

public class EulerTotientFunction {

	public static void main(String[] args) {


	}
	/*
	 * Time complexity: O(N) N is input size (a+b)
	 * 
	 * reference: http://stackoverflow.com/questions/3980416/time-complexity-of-euclids-algorithm
	 * 
	 */
	public static long GCD(long a, long b) {
		long min = Math.min(a, b);
		long max = Math.max(a, b);
		while (min != 0) {
			long tmp = max % min;
			max = min;
			min = tmp;
		}
		return max;
	}
	/*
	 * ϕ(n) is the number of numbers k, with 1≤k≤n, such that gcd(n,k)=1
	 * properties
	 * 1. ϕ(n) is even for n>3.
	 * 
	 * 2. For a prime number p, Φ(p) is p-1. 
	 * 	  For example Φ(5) is 4, Φ(7) is 6 and Φ(13) is 12.
	 * 
	 * 3. For two numbers a and b, if gcd(a, b) is 1, 
	 *    then Φ(ab) = Φ(a) * Φ(b).
	 * 
	 *    For example Φ(5) is 4 and Φ(6) is 2, so Φ(30) must be 8 as 5 and 6 are relatively prime.
	 * 
	 * 4. For any two prime numbers p and q, 
	 *    Φ(pq) = (p-1)*(q-1).
	 * 
	 * 5. If p is a prime number, 
	 *    then Φ(p^k) = p^k – p^(k-1).
	 * 
	 * 6. Sum of values of totient functions of all divisors of n is equal to n.
	 * 	  For example, n = 6, the divisors of n are 1, 2, 3 and 6. 
	 *     According to Gauss, sum of Φ(1) + Φ(2) + Φ(3) + Φ(6) should be 6. 
	 *     We can verify the same by putting values, we get (1 + 1 + 2 + 2) = 6.
	 * 
	 * 7. For n>3, ϕ(n) is even. 
	 * 	  
	 *    proof: https://math.stackexchange.com/questions/702550/why-is-eulers-totient-function-always-even     
	 *
	 * Time complexity: O(nlgn)
	 */
	public static long Phi(int N) {
		long count = 1; // 1 is the "1" common factor
		for (int i=2; i<N; i++) {
			if (GCD(i, N) == 1)
				count++;
		}
		return count;
	}
	
	public static long PhiOptimized(int N) {
	
		return 1;
	}

}
