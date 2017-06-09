package greedy;

/**
 * 
 * @author xu
 * https://leetcode.com/problems/remove-k-digits/#/description
 * 
 * Given a non-negative integer num represented as a string, 
 * remove k digits from the number so that the new number is the smallest possible.
 * 
 * Input: num = "1432219", k =3 
 * Output: "1219"
 * 
 * Input: num = "10200", k =1 
 * Output: "200"
 * 
 * Input: num = "10", k =2 
 * Output: "0"
 * "
 */
public class RemoveKDigits_Stack {

	public static void main(String[] args) {
//		String string = "001234";
//		int a = Integer.parseInt(string);
//		System.out.println(a);
//		
//		System.out.println(sol_not_solved("3432789", 3));
		System.out.println(sol("3432789", 1));
	}
	// 相邻两个字符对比大小，记录较小的一个。最后无法得到正确结果
	public static String sol_not_solved(String s, int n){
		
		int pos = 0;
		if (n >= s.length())
			return "0";
		StringBuilder builder = new StringBuilder();
		while (pos < s.length()-1 && n > 0) {
			if ((int) s.charAt(pos) >= (int) s.charAt(pos+1)) {
				builder.append(s.charAt(pos + 1));
				if (pos <= s.length()-3) {
					pos += 2;
				}else {
					pos++;
					break;
				}
				n--;
			} else{
				builder.append(s.charAt(pos));
				pos++;
				n--;
			}
		}
		if (pos != s.length()-1) {
			for (;pos<s.length();pos++){
				builder.append(s.charAt(pos));
			}
		}
		
		return builder.toString();
		
	}
	/**
	 * 
	 */
	public static String sol(String str, int k) {
		int avail_digits = str.length() - k;
		char[] stack = new char[str.length()];
		
		int top=0; // top index of the stack
		
		for (int pos = 0; pos<str.length(); pos++) {
			char c = str.charAt(pos);
			
			while (top > 0 && stack[top-1] > c && k > 0) {
				k--;
				top--; // overwrite the top element in stack with a smaller one 
			}
				
			stack[top++] = c;
		}
		// find out the first non "0" element "10200", 1
		int offset = 0;
		while (offset < avail_digits && stack[offset] == '0')
			offset++;
		return offset==avail_digits?"0":new String(stack, offset, avail_digits - offset);
				
	}

}
