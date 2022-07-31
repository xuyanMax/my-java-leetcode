package a_exams.weChat;

/**
 * 
 * @author xu
 * 小易是一个数论爱好者，并且对于一个数的奇数约数十分感兴趣。一天小易遇到这样一个问题： 定义函数f(x)为x最大的奇数约数，x为正整数。例如:f(44) = 11.
*  现在给出一个N，需要求出 f(1) + f(2) + f(3).......f(N)

*  例如： N = 7 
*  f(1) + f(2) + f(3) + f(4) + f(5) + f(6) + f(7) = 1 + 1 + 3 + 1 + 5 + 3+ 7 = 21
*  小易计算这个问题遇到了困难，需要你来设计一个算法帮助他。

*  输入描述:
*  输入一个整数N (1 ≤ N ≤ 1000000000)

*  输出描述 :
*  输出一个整数，即为f(1) + f(2) + f(3).......f(N)

*  输入例子:
*  7

*  输出例子:
*  21
 *
 */
public class num34 {

	public static void main(String[] args) {
		
		System.out.println(solutionTwo(700000000));
	}
	// 此类方法，可用，但是n最大十亿，遍历会超时。因此需要优化，见方法2
	static long solutionOne(int n){
		long sum = 0;
		int tmp = 0;
		for(int i=1; i<n;i++) {
			tmp = i;
			if(tmp%2 == 0) {
				while((tmp%2) != 0) // 除2 除到奇数
				tmp = tmp/2;
			
				sum += tmp;
			}else 
				sum += i;
		}
		return sum;
	}
	static long solutionTwo(int n) {
		int[] dp = new int[n+1];
		int tmp = 0;
		for (int i=1; i<=n; i++) {
			tmp = i;
			if((tmp%2)==0) {
				while (tmp%2 == 0) 
					tmp /= 2;
				
				dp[i] = dp[tmp];
				
			} else 
				dp[i] = i;
			
		}
		int sum=0;
		for (int d:dp) 
//			System.out.println(d);
			sum+=d;
		return sum;
	}
	
	// recursive method ??
}
