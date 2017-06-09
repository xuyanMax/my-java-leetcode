package stack;

import java.util.LinkedList;

/*
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * 
 * Some examples:
   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
   
 */
public class ValidReversePolishNotation {

	public static void main(String[] args) {
		

	}
	public static int evalRPN(String[] tokens) {
		
		LinkedList<String> stack = new LinkedList<>();
		String regex = "[-]?\\d+";// minus sign at most once
		for (int i=0; i<tokens.length; i++) {
			if (tokens[i].matches(regex)) 
				stack.push(tokens[i]);
			else {
				int b = Integer.parseInt(stack.pop());
				int a = Integer.parseInt(stack.pop());
				
				if (tokens[i].equals("*")){
					int c = a * b;
					stack.push(Integer.toString(c));
				}else if (tokens[i].equals("/")){
					stack.push(Integer.toString((int)a/b));
				}else if (tokens[i].equals("+")){
					stack.push(Integer.toString(a + b));
				}else if (tokens[i].equals("-")){
					stack.push(Integer.toString(a - b));
				}
				
			}
		}
		
		return Integer.valueOf(stack.pop());
	}
public static int evalRPN2(String[] tokens) {
		
		LinkedList<Integer> stack = new LinkedList<>();
		String regex = "[-]?\\d+";// minus sign at most once
		for (int i=0; i<tokens.length; i++) {
			if (tokens[i].matches(regex)) 
				stack.push(Integer.parseInt(tokens[i]));
			else {
				int b = stack.pop();
				int a = stack.pop();
				
				if (tokens[i].equals("*")){
					int c = a * b;
					stack.push(c);
				}else if (tokens[i].equals("/")){
					stack.push((int)a/b);
				}else if (tokens[i].equals("+")){
					stack.push(a + b);
				}else if (tokens[i].equals("-")){
					stack.push(a - b);
				}
				
			}
		}
		
		return stack.pop();
	}

}
