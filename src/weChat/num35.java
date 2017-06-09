package weChat;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author xu
 * 对于一个字符，请设计一个高校的算法，找到第一次重复出现的字符
 * 给定一个字符串及它的长度
 * 测试样例："qywyqer23tdd", 11
 * 输出：y
 *`
 */
public class num35 {

	public static void main(String[] args) {
		String str = "aywyqer23tdd";
		int testASCII = str.charAt(0);
		System.out.println(testASCII);
		System.out.println(solutionOne(str.toCharArray()));
		System.out.println(solutionTwo(str.toCharArray()));
		
		
	}
	// time: O(n)
	// space: O(1)
	static char solutionOne(char[] arr) {
		
		// there are 2^8 = 256 characters in an Extended ASCII char set
		boolean[] charSet = new boolean [256];
		for (int i=0; i<arr.length; i++) {
			if(!charSet[arr[i]]) 
				charSet[arr[i]] = true;
			else 
				return arr[i];
			
		}
		return ' ';
	}
	// space O(n)
	// time O(n)
	static char solutionTwo(char[] arr) {
		Set<Character> hs = new HashSet<Character>();
		for (int i=0; i<arr.length; i++) {
			if(hs.contains(arr[i])) return arr[i];
			else 
				hs.add(arr[i]);
		}
		return ' ';
	}

}
