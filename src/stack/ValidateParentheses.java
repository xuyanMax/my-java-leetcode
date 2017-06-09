package stack;

import java.util.LinkedList;

/*
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 */
public class ValidateParentheses {

	public boolean isValid(String str) {
		LinkedList<Character> stack = new LinkedList<>();
        for (char c:str.toCharArray()) {
        	if (c == '[' || c=='(' || c=='{' )
        		stack.push(c);
        	else {
        		
        		if ( c == '}') {
        		    if (!stack.isEmpty()&& stack.peek() == '{')
        		    stack.pop();
        		    else
        		        return false;
        		} 
       
    			if (c == ']'){
    			     if (!stack.isEmpty()&& stack.peek() == '[')
    			     stack.pop();
        		    else
        		        return false;
    			}
    			
				if ( c == ')' ) {
				    if (!stack.isEmpty()&& stack.peek() == '(')
				     stack.pop();
        		    else
        		        return false;
				}        	
        	} 
        		
        }
        return stack.isEmpty();
	}
}
