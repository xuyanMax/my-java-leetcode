package a_exams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by xu on 21/09/2017.
 */
public class Oracle_170921_3 {
    public static void main(String[] args) throws IOException {
//         System.out.println('4'-'0');
//         StringBuilder test = new StringBuilder();
//         System.out.println(test.append("a").append("+").append("b").insert(0,"(").append(")"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        LinkedList<StringBuilder> builder_stack = new LinkedList<>();
        StringBuilder curr = new StringBuilder();
        StringBuilder curr1 = null;
        StringBuilder curr2 = null;
        for (char c : line.toCharArray()) {
            if (isOperator(c)) {
                curr.append(c);
                builder_stack.push(curr);
                curr = new StringBuilder();
            } else {
                curr1 = builder_stack.poll();
                curr2 = builder_stack.poll();

                switch (c) {
                    case '+':
                        plus(curr2, curr1, builder_stack);
                        break;
                    case '-':
                        minus(curr2, curr1, builder_stack);
                        break;
                    case '*':
                        multiply(curr2, curr1, builder_stack);
                        break;
                    case '/':
                        divide(curr2, curr1, builder_stack);
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(builder_stack.poll().toString());
        br.close();
    }

    public static boolean isOperator(char c) { // operator a-z|A-Z

        return c != '+' && c != '-' && c != '*' && c != '/';
    }

    public static void plus(StringBuilder pre, StringBuilder post,
                            LinkedList<StringBuilder> stack) {
        pre.append("+").append(post).append(")").insert(0, "(");

        stack.push(pre);

    }

    public static void minus(StringBuilder pre, StringBuilder post,
                             LinkedList<StringBuilder> stack) {
        pre.append("-").append(post).append(")").insert(0, "(");

        stack.push(pre);

    }

    public static void multiply(StringBuilder pre, StringBuilder post,
                                LinkedList<StringBuilder> stack) {
        pre.append("*").append(post).append(")").insert(0, "(");

        stack.push(pre);

    }

    public static void divide(StringBuilder pre, StringBuilder post,
                              LinkedList<StringBuilder> stack) {
        pre.append("/").append(post).append(")").insert(0, "(");

        stack.push(pre);

    }
}
