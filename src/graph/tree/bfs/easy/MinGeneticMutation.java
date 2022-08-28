package graph.tree.bfs.easy;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 433. Minimum Genetic Mutation
 * Example 1:
 * <p>
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * <p>
 * return: 1
 * Example 2:
 * <p>
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * <p>
 * return: 2
 * Example 3:
 * <p>
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * <p>
 * return: 3
 */
public class MinGeneticMutation {
    private char[] CHAR = new char[]{'A', 'T', 'C', 'G'};

    public int minMutation(String start, String end, String[] bank) {
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Set<String> bankSet = new HashSet<String>();

        for (String str : bank)
            bankSet.add(str);

        queue.add(start);
        visited.add(start);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
//            level++;
            while (size-- > 0) {
                String curr = queue.poll();
                // not curr == end
                if (curr.equals(end))
                    return level;
                char[] chars = curr.toCharArray();
                for (int i = 0; i < curr.length(); i++) {
                    for (int j = 0; j < CHAR.length; j++) {
                        char c = CHAR[j];
                        if (chars[i] == c)
                            continue;
                        chars[i] = c;// get replaced
                        String newstr = String.valueOf(chars);// new String with one char changed differently
                        if (bankSet.contains(newstr) && visited.add(newstr)) {
                            queue.add(newstr);
                        }
                    }
                }

            }
            level++;
        }
        // not found
        return -1;


    }
}
