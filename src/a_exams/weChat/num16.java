package a_exams.weChat;

import java.math.BigInteger;


/**
 * 
 * @author xu
 *
 * 字符集合
 * 题目：两个大数相乘
 * 
 * 使用代码计算：1234567891011121314151617181920 * 2019181716151413121110987654321
 * 
 * http://www.geeksforgeeks.org/divide-and-conquer-set-2-karatsuba-algorithm-for-fast-multiplication/
 * http://www.geeksforgeeks.org/add-two-bit-strings/
 */
public class num16 {

	public static void main(String[] args) {
		
		String str1 = "1234567891011121314151617181920";
		String str2 = "2019181716151413121110987654321";
		
		/* declare big integer*/
		BigInteger num1 = new BigInteger(str1);
		BigInteger num2 = new BigInteger(str2);
		
		/* convert BigInteger to Binary String*/
		str1 = num1.toString(2);
		str2 = num2.toString(2);

		System.out.println(multiply(str1, str2));
	
	}
	
	/**
	 * Helper functions
	 * Java passed by value NOT reference. 
	 * 
	 * refer run->basics->java_pass_by_vale
	 * 
	 * given two unequal sized bit strings, converts them to same length by adding leading 0s in the smaller string.
	 */

	static String makeEqualLength(String str, int n) {
		int len1 = str.length();
		if (len1<=n) {
			for (int i=0;i<n-len1;i++)
				str = '0'+str;
			return str;
		}
		return null;
	} 
	/**
	 * 
	 * The main function that adds two bit sequences and returns the addition
	 * 
	 * -we one by one add bits from rightmost bit to leftmost bit. In every iteration, 
	 *  we need to sum 3 bits: 2 bits of 2 given strings and carry. 
	 *  
	 * -The sum bit will be 1 if, either all of the 3 bits are set or one of them is set. 
	 *  So we can do XOR of all bits to find the sum bit. 
	 * 
	 * -How to find carry – carry will be 1 if any of the two bits is set. 
	 *  So we can find carry by taking OR of all pairs
	 * 
	 * @param X0 first half of string floor(n/2)
	 * @param X1 second half of string ceil(n/2)
	 * @return add two bits sequences and return the addition 
	 */
	static String addBitStrings(String X0, String X1){
		StringBuilder result = new StringBuilder();// to store the sum bits
		
		// make the lengths same, before addition
		if (X0.length()<=X1.length())
			X0 = makeEqualLength(X0, X1.length());
		else
			X1 = makeEqualLength(X1, X0.length());
		
		int carry = 0;
		for (int i=X0.length()-1;i>=0;i--) {
			int X0Bit = X0.charAt(i)-'0';// to convert '1'->1, '0'->0
			int X1Bit = X1.charAt(i)-'0';
			
			int sum = (X0Bit ^ X1Bit ^ carry) + '0';// to convert 1->'1'
			result.append((char)sum);

			carry = (X0Bit&X1Bit) | (X0Bit&carry) | (X1Bit&carry);
						
		}
		// if overflow, then add a leading 1
		if (carry!=0)
			result.append('1');
		
		return result.reverse().toString();
	}
	/* the main function multiplying two bits strings X and Y and returns the result as long integer*/
	static BigInteger multiply(String X, String Y) {
		
		// make the shorter string the same length as the longer one.
		if (X.length()<=Y.length())
			X = makeEqualLength(X, Y.length());
		else 
			Y = makeEqualLength(Y, X.length());
		
		int n=X.length();
//		if (n==0) return new BigInteger("0");
		if (n==1) return multiplySingleBit(X,Y);
		
		
		int firstHalf = n/2; // first half of string, floor(n/2)
		int secHalf = n-n/2;// second half of string, ceil(n/2)
		
		String X0 = X.substring(0, firstHalf);
		String X1 = X.substring(firstHalf);
		String Y0 = Y.substring(0, firstHalf);
		String Y1 = Y.substring(firstHalf);

		// recursively calculate the three products of the inputs size n/2
		// refer notes
		BigInteger p1 = multiply(X0, Y0);
		BigInteger p2 = multiply(X1, Y1);
		BigInteger p3 = multiply(addBitStrings(X0, X1),addBitStrings(Y0, Y1));
		
		
		// add the three parts to get the final result
		BigInteger tmp1 = p3.subtract(p2).subtract(p1).shiftLeft(secHalf); 
		BigInteger tmp2 = p1.shiftLeft(2*secHalf);
		
		return 	tmp1.add(tmp2).add(p2);
	
	}
	static BigInteger multiplySingleBit(String X, String Y) {
		int tmp= (X.charAt(0)-'0')*(Y.charAt(0)-'0');
		
		return new BigInteger(Integer.toString(tmp));
	} 
	
	
	
}
