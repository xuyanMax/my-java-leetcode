package stack;

import java.util.LinkedList;

/**
 * Created by xu on 21/09/2017.
 */
public class InfixToPostfix {//逆波兰表达式又称为"后缀表达式"
     public static void main(String[] args){
         System.out.println(RPN("a - b * c + (d * e + f)*g"));
     }

     //将中缀表达式 转换为 后缀表达式

    public static String RPN (String input){
        LinkedList<Character> operator = new LinkedList<>();
        String output = new String();

        for (char c:input.toCharArray()) {
            if (Character.isDigit(c) || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                output += c;
            else if (c == ')') {
                while (!operator.isEmpty() && operator.peek() != '(')
                    output += operator.poll();
                //弹出左括号( 且不输入输出到字符川
                operator.poll();

            } else if (c == '+' | c == '-' | c == '*' | c == '/' | c == '('){
                if (operator.isEmpty()) {
                    operator.push(c);
                } else {
                    while (!operator.isEmpty() && operator.peek()  != '(' && (priority(c) <= priority(operator.peek())))
                        output += operator.poll();
                    operator.push(c);
                    //不弹出左括号
                }
            }
        }
        //当全部遍历完表达式后，将操作符栈中剩余的一次输出
        while(!operator.isEmpty())
            output += operator.poll();
        return output;

    }
    public static int priority(char c){
        if (c == '+' | c == '-')
            return 1;
        if (c == '*' | c == '/')
            return 2;
        if (c == '(')
            return 3;
        return -1;
    }

}
