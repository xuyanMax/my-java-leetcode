package exams;

import java.util.*;

/**
 * Created by xu on 17/09/2017.
 */
public class Sohu_170917_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {//注意while处理多个case
            String path = in.next();
            String res = simplifyPath(path);
            System.out.println(res);
        }
    }

    public static String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty())
                stack.pop();
            else if (!skip.contains(dir))
                stack.push(dir);
        }
        String res = "";
        for (String dir : stack)
            res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }
}
