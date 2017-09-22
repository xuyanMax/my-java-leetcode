package stack;

import java.util.LinkedList;

/**
 * Created by xu on 05/09/2017.
 */
/*
150. Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();

        String operator = "+";
        for (String s : tokens){
            if (s.matches("[-]?\\d+")){
                stack.push(Integer.parseInt(s));
            } else {
                int a = stack.pop();
                int b = stack.pop();

                if (s.equals("+")) {
                    stack.push(a+b);
                } else if (s.equals("-")) {
                    stack.push(a-b);
                } else if (s.equals("*")) {
                    stack.push(a*b);
                } else if (s.equals("/")) {
                    stack.push(a/b);
                }
            }

        }
        return stack.pop();

    }
}
