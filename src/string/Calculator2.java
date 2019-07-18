package string;

import java.util.LinkedList;

/**
 * Created by xu on 05/09/2017.
 * <p>
 * 227. Basic Calculator II
 * <p>
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 * <p>
 * You may assume that the given expression is always valid.
 * <p>
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * Note: Do not use the eval built-in library function
 */
public class Calculator2 {
    public int calculate(String s) {

        if (s == null || s.length() == 0)
            return 0;

        LinkedList<Integer> stack = new LinkedList<>();

        int num = 0;
        char operator = '+';//初始值为+，因为第一个操作数是加入栈的
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)))
                num = num * 10 + s.charAt(i) - '0';

            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i))
                    || i == s.length() - 1) {//处理最后一个字符 num的值
                if (operator == '+')
                    stack.push(num);
                if (operator == '-')
                    stack.push(-num);
                if (operator == '/')
                    stack.push(stack.pop() / num);
                if (operator == '*')
                    stack.push(stack.pop() * num);
                operator = s.charAt(i);
                num = 0;//重置为0
            }
        }


        num = 0;
        for (int n : stack)
            num += n;
        return num;
    }
}
