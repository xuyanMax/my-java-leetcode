package string;

import java.util.LinkedList;

/**
 * Created by xu on 05/09/2017.
 */
/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
*/

/*
可能遇到的情况如下
1、digit '123'
    继续累加
2、'+'
    加号前的num结束，加号前的result += sign * num (sign 是+号前的符号未知)
    归零num
3、'-'
   同上
4、'('
    将(前的result加入栈中，再将(前的sign值加入栈中，并重置result = 0, num = 0, sign = 1;
5、')'
    首先计算()内的result值 result += sign * num;
    再从栈中依次取出sign 和 previous result
    最后更新result = result * sign
           result += previous result + result;
最后一个元素需要单独处理
1、')'不需要处理，num归零
2、数字：需要单独处理，result += sign * num;



*/
public class Calculator1 {
    public int calculate(String s) {
        if (s == null || s.length() == 0)
            return 0;

        LinkedList<Integer> stack = new LinkedList<>();
        int result = 0; //(括号内部的值)
        int sign = 1; // sign '+'->1, '-'->-1
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c))
                num = num * 10 + c - '0';
            if (c == '+') {
                result += sign * num;
                num = 0;
                sign = 1;
            }

            else if (c == '-') {
                result += sign * num;
                num = 0;
                sign = -1;
            }

            else if (c == '(') {
                // we push the result first and then sign
                stack.push(result);
                stack.push(sign);
                // reset the result, num, sign for a new parenthesis
                result = 0;
                num = 0;
                sign = 1;
            }
            else if (c == ')') {
                result += sign * num;
                num = 0;

                result *= stack.pop(); //和sign相乘
                result += stack.pop(); //和()前的result相运算

            }

        }
        if (num != 0)
            result += sign * num;
        return result;

    }
}
