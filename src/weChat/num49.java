package weChat;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author xu
 *
 * 输入描述：
 * 每组数据输入一个字符串，字符串最大长度100，且只包含字母，不可能为空串，区分大小写。
 * 输出描述：
 * 魅族数据一行，按字符串原有字符顺序，输出字符集合，即重复出现并靠后的字母不输出
 */
public class num49 {

	public static void main(String[] args) {
		String str = "abcdwerabc";
		solution(str);
		solution2(str);
		
	}
	static String solution(String s){
		char[] strs = new char[256];
		StringBuilder stringBuilder = new StringBuilder();
		for (char c:s.toCharArray())
			if (strs[c]==0) {
				strs[c]++;
				stringBuilder.append(c);
			} 
		 
		
		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}
	static String solution2(String s) {
		Set<Character> set = new HashSet<Character>();
		StringBuilder stringBuilder = new StringBuilder();
		for (char c:s.toCharArray()) {
			if (!set.contains(c)) {
				stringBuilder.append(c);
				set.add(c);
			}	
		}
		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}

}
