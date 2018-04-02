package bfs;

import java.util.*;

/**
 * Created by xu on 25/08/2017.
 */
public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
          if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
          queue.add(s);
          visited.add(s);

        boolean found = false;

          while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            if (found) continue;

            // sol all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if(s.charAt(i)  != '(' && s.charAt(i) != ')')
                    continue;
                String t = s.substring(0,i) + s.substring(i+1, s.length());

                if (!visited.contains(t)) { // for each state, if it is not visited, add it to the queue
                    visited.add(t);
                    queue.add(t);
                }

            }
        }

          return res;
}

    // helper function checks if string s contains valid parantheses
    //判断括号是否有效
    // 1、可以利用counter，计算左括号与右括号的个数，如果相当则表示有效，否则无效
    // 2、可以利用stack

    boolean isValid(String s) {
        int count = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(')
                count++;
            if (s.charAt(i) == ')' && count-- == 0)
                return false;
        }
        return count == 0;
    }
}
