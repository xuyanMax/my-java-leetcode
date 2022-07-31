package a_exams.quner;

import java.util.*;

/**
 * BFS广度优先算法
 * 最后AC 40%，请大神帮助提点
 * 写得不好，请多担待
 */
public class T1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        Map<String, ArrayList<String>> map = new HashMap<>();

        String depart = in.next();
        String destination = in.next();

        for (int i = 0; i < m; i++) {
            String from = in.next();
            String to = in.next();
            if (!map.containsKey(from))
                map.put(from, new ArrayList<String>());

            if (!map.containsKey(to))
                map.put(to, new ArrayList<String>());

            map.get(from).add(to);
            map.get(to).add(from);

        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(depart);
        visited.add(depart);
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;

            while (size > 0) {
                size--;
                String curr = queue.poll();
//                 visited.add(curr);

                ArrayList<String> des = map.get(curr);
                if (des != null) {
                    for (String str : des) {
                        if (str.equals(destination) || str == destination) {
                            System.out.println(step);
                            return;
                        } else {
                            if (visited.add(str)) {
                                queue.add(str);
                            }
                        }
                    }
                } else
                    continue;
            }

        }
        System.out.println("DISCONNECTED");
    }
}
