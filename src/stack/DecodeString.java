package stack;

import java.util.LinkedList;

/*
 * https://leetcode.com/problems/decode-string/#/description
 * 
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * 
 */
public class DecodeString {

	public static void main(String[] args) {
	LinkedList<Integer> list = new LinkedList<>();
	System.out.println(list.peek());
			
	}
	/*
	 * We use two stacks to implement decoding 
	 * Integer Stack to store times of duplicates and StringBuilder Stack to store the so-far decoded String
	 * 
	 * For each char of the str, we check if it is one of the following four cases:
	 * 1. [
	 * 2. ]
	 * 3. Integer
	 * 4. Others (character)
	 * and do corresponding implementations.
	 * 
	 */
	public String Decode(String str){
		LinkedList<Integer> IntStack = new LinkedList<>();
		LinkedList<StringBuilder> StrBuilderStack = new LinkedList<>();
		int k = 0;
		StringBuilder curr = new StringBuilder();
		for (char c:str.toCharArray()) {
			if (Character.isDigit(c)) {
				k = k * 10 + c -'0';// considering 100[a] 
			}
			else if (c == '['){
				
				IntStack.push(k);
				k = 0; //reset the k;
				StrBuilderStack.push(curr); // first time add an empty string in the stack 
				curr = new StringBuilder(); // renew the current for new substring inside [] e.x., 3[ac] curr is used for "acacac"
				
			}
			else if(c == ']') { // end of substring duplicates e.x, 3[ac]4[df] 
				StringBuilder substr = curr; // store the current duplicates "dfdfdfdf"
				curr = StrBuilderStack.pop();// get the previous string "acacac"
				int count = IntStack.pop();
				while (count-->0) {
					curr.append(substr);
				}
				
			} 
			else
				curr.append(c);	
		}
		String a = "123";
		
		return curr.toString();
	}

}
