package backTracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xu
 * 
 * Gray code, also called reflected binary code. 
 * 
 * Gray code with n bits could be constructed from the Gray code with n-1 bits
 * 3 steps: 
 * (1) prefix all the gray code with n-1 bits with 0;
 * (2) reverse the order of the n -1 bits and prefix all of the reversed  with 1 (reflected)
 * (3) concatenate (1) and (2) and obtain the Gray code of n bits
 * 
 * 1. solution
 * using formula method 
 * 下面我们把二进制数和Gray码都写在下面，可以看到左边的数异或自身右移的结果就等于右边的数
 * n = 3 bits
 * 二进制数   Gray码
   000       000
   001       001
   010       011
   011       010
   -------------
   100       110
   101       111
   110       101
   111       100
   从二进制数的角度看，“镜像”位置上的数即是对原数进行not运算后的结果。比如，第3个数010和倒数第3个数101的每一位都正好相反。
   假设这两个数分别为x和y，那么x xor (x shr 1)和y xor (y shr 1)的结果只有一点不同：后者的首位是1，前者的首位是0。
   而这正好是Gray码的生成方法。这就说明了，Gray码的第n个数确实是n xor (n shr 1)。
 	2. solution2
 	iterative method
 	依此在list中，添加原来所有由n-1个bits组成的codes与在MSB补1的结果到list中
 	
 	3. solution3
 	利用string 特性，在string前补0，补1，最后在转Integer.parseInt(s,2);//to 2 radix
 *   
 *   
 * 参考
 * https://leetcode.com/problems/gray-code/?tab=Description
 * http://www.matrix67.com/blog/archives/266
 * https://leetcode.com/problems/gray-code/
 * https://github.com/shawnfan/LintCode/blob/master/Java/Gray%20Code.java
 */
public class GrayCode {

	public static void main(String[] args) {
	
		
		solution3(3);
		
	}
	//Note: << has less priority than additive +
	static void basicsOfShift() {

		for (int i=1;i<5;i++)
			System.out.println(Integer.toBinaryString((1<<i) + 1));// 1*2, 1*2^2, 1*2^3, 1*2^4; (1<<i)+1: 11, 101, 1001, 10001 
		System.out.println("");
		for (int i=1;i<5;i++)
			System.out.println((i<<1)+1); // i*2 each iteration, 2, 4, 6, 8;(i<<1)+1: 3,5,7,9,
		
		char[] chars = new char[]{'a','b','c'};
		System.out.println(new String(chars));
	}
	
	static List<Integer> solution(int n) {
		List<Integer> results = new ArrayList<>();
		// n=3, 111 < 2^3=8 <=> 1 left shift 3 bits 
		for (int i=0;i<1<<n;i++)
			results.add(i^i>>1);
		display(results);
	
		return results;
	}
	
	/**
	 * (1)补0 补不不零，得到的integer值相等，因此可省略
	 * (2)补1 因此，直接在List后对所有elem倒序补1，elem  = elem + 1<<i (i为bits个数)
	 * https://leetcode.com/problems/gray-code/
	 */
	static List<Integer> solution2(int n) {
		List<Integer> results = new ArrayList<>();
		results.add(0);
		for (int i=0;i<n;i++) {

			//计算补一元素 1,10,100,1000,1000,1(n-1)个0
			int toBeAddedAtFrontBit = 1<<i;
			for (int j=results.size()-1;j>=0;j--) 
				//直接添加首位字节补一元素，倒序
				results.add(toBeAddedAtFrontBit+results.get(j));
			
		}
//		display(results);
		return results;
	}
	static void display(List<Integer> results) {
		for (Integer n:results)
			System.out.println(Integer.toBinaryString(n));
	}
	
	/**
	 * iterative method
	 * @return 利用string 可连接属性进行补零，补1
	 */
	static List<String> solution3(int n) {
		
		if (n<=0) return null;
		List<String> results = new ArrayList<>();

		// start with 1-bit pattern
		results.add("0");
		results.add("1");
		
		// Every iteration of this loop generates 2*i codes from previously
	    // generated i codes.
		int i, j;
		for (i=2;i<(1<<n);i=(i<<1)) {
			
			// add the previously generated codes again in LIST in reverse
	        // order. Now LIST has double number (2*i) of codes.
			for (j=i-1;j>=0;j--)
				results.add(results.get(j));
			
			//(1) append 0 to the first half
			for (j=0;j<i;j++)
				results.set(j, "0"+results.get(j));

			//(2) append 1 to the second half
			for (j=i;j<2*i;j++) 
				results.set(j, "1"+results.get(j));
		}
		List<Integer> IntResults= new ArrayList<>();
		
		for (String s: results)
			
			IntResults.add(Integer.parseInt(s, 2));
		System.out.println(IntResults);
		return results;
	}
}
