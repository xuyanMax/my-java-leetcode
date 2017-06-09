package basics;


public class GreatestCommonDivisor {

	public static void main(String[] args) {

		System.out.println(GCD(10, 5));
	}
	// division method
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
	 * example GCD2(4,13) = GCD2(4, 13-4) = GCD2(4, 9-4) =GCD2(4, 5-4) = GCD2(4-1,1)=..GCD2(1,1) return 1; 
	 */
	public static long GCD2(long a, long b) {
		while (a != b) {
			if (a > b) 
				a = a-b;
			else
				b = b - a;
	
		}
		return a;
	}
	
	public static int GCD3(int m, int n) {
		int A, B, diff;
	
		if (m % 2 == 0) 
			 while (m % 2 != 0)
				 m /= 2;
	
		if (n % 2 == 0) 
			while (n % 2 != 0) {
				n /= 2;
			}
		A = Math.max(m, n);
		B = Math.min(m, n);
		diff = A - B;
		while (diff != B) {
			diff = A - B;
			A = Math.max(diff, B);
			B = Math.max(diff, B);
		}
		return diff;
		
		
	}

}
