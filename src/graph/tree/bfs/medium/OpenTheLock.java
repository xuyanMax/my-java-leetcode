package graph.tree.bfs.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

// 752. Open the Lock

/**
 * 752. Open the Lock
 * <p>
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around:
 * for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 * <p>
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * <p>
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 * <p>
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 * <p>
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 * Example 4:
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 * Note:
 * The length of deadends will be in the range [1, 500].
 * target will not be in the list deadends.
 * Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.
 */
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {

        Set<String> deadSet = Arrays.stream(deadends).collect(Collectors.toCollection(HashSet::new));
        Deque<String> queue = new ArrayDeque<>();

        queue.add("0000");
        Set<String> visited = new HashSet<>();
        int level = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            while (size > 0) {
                String curr = queue.poll();
                if (curr.equals(target))
                    return level;
                if (deadSet.contains(curr)) {
                    size--;
                    continue;
                }
                StringBuilder builder = new StringBuilder(curr);
                for (int i = 0; i < curr.length(); i++) {
                    char c = curr.charAt(i);
                    String str1 = builder.substring(0, i)
                            + (c == '9' ? 0 : c - '0' + 1)
                            + builder.substring(i + 1);
                    String str2 = builder.substring(0, i)
                            + (c == '0' ? 9 : c - '0' - 1)
                            + builder.substring(i + 1);
                    if (!visited.contains(str1) && !deadSet.contains(str1)) {
                        visited.add(str1);
                        queue.add(str1);
                    }
                    if (!visited.contains(str2) && !deadSet.contains(str2)) {
                        visited.add(str2);
                        queue.add(str2);
                    }

                }
                size--;
            }
            level++;
        }
        return -1;
    }

    // 0000 ->  8种相邻的节点，可以想象成8叉数，求最短距离，BFS.
    int count = 0;

    public static int labuladong(String[] deadends, String target) {
        Set<String> deadset = Arrays.stream(deadends).collect(Collectors.toSet());
        Deque<String> queue = new ArrayDeque<>();
        queue.add("0000");

        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();

        int count = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
//            count++;
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String curr = queue.pollFirst();
                if (deadset.contains(curr))
                    continue;
                System.out.println(curr);
                /* 判断是否到达终点 */
                if (curr.equals(target))
                    return count;
                /* 将一个节点的相邻节点加入队列 */
                for (int j = 0; j < curr.length(); j++) {
                    String up = tweakUpOne(curr, j);
                    String down = tweakDownOne(curr, j);
                    System.out.println(up + "," + down);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }

                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public static int bidirect_bfd(String[] deadends, String target) {
        Set<String> deadset = Arrays.stream(deadends).collect(Collectors.toSet());
        Set<String> queue_front = new HashSet<>(), queue_back = new HashSet<>();
        queue_front.add("0000");
        queue_back.add(target);
        Set<String> visited = new HashSet<>();
        int count = 0;
        while (!queue_back.isEmpty() && !queue_front.isEmpty()) {
            //优化 - 遍历集合的元素个数尽可能少
            if (queue_front.size() > queue_back.size())
                exch_q(queue_front, queue_back);
            //存储下一层所有节点
            Set<String> front_tmp = new HashSet<>();
            /*相当于从front queue中提取出来，再判断是否到达终点*/
            for (String curr : queue_front) {
                if (curr.equals(target)) return count;
                if (deadset.contains(curr)) continue;
                /**当前节点向四周扩散**/
                for (int i = 0; i < curr.length(); i++) {
                    String up = tweakUpOne(curr, i);
                    String down = tweakDownOne(curr, i);
                    if (!visited.contains(up)) front_tmp.add(up);
                    if (!visited.contains(down)) front_tmp.add(down);
                }
            }
            count++;
            //交换q1和q1,相当于下一轮while就是扩散q2
            queue_front = queue_back;
            queue_back = front_tmp;
        }
        return -1;
    }

    public static void exch_q(Set<String> var1, Set<String> var2) {
        Set<String> tmp = var1;
        var1 = var2;
        var2 = tmp;
    }

    public static String tweakUpOne(String s, int pos) {
        char[] chars = s.toCharArray();
        if (s.charAt(pos) == '9')
            chars[pos] = '0';
        chars[pos] += 1;

        return String.valueOf(chars);
    }

    public static String tweakDownOne(String s, int pos) {
        char[] chars = s.toCharArray();
        if (s.charAt(pos) == '0')
            chars[pos] = '9';
        chars[pos] -= 1;

        return String.valueOf(chars);
    }

    @Test
    public void test_lbld() {
        String[] deadlist = new String[]{"0201", "0101", "0102", "1212", "2002"};
        int count = OpenTheLock.labuladong(deadlist, "0202");
        Assert.assertEquals(count, 6);
    }

    @Test
    public void test_tweakup() {
        String s = "0000";
        String res = OpenTheLock.tweakUpOne(s, 1);
        Assert.assertEquals(res, "0100");
    }
}
