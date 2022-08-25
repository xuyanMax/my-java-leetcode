package slidingWindow;

import java.util.HashSet;
import java.util.Set;

//LeetCode 76 题，Minimum Window Substring，难度 Hard
//就是说要在S(source) 中找到包含T(target) 中全部字母的一个子串，且这个子串一定是所有可能子串中最短的。
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String S = "ADOBECODEBANC", T = "ABC";
        System.out.println(sol(S, T));
    }

    public static String sol(String S, String T) {
        int[] chars = new int[256];
        Set<Character> set = new HashSet<>();
        for (char ch : T.toCharArray()) {
            chars[ch]++;
            set.add(ch);
        }
        System.out.println(set.size());
        int left = 0, right = 0;
        int[] window = new int[256];
        int count = 0, start = 0;
        int min_len = Integer.MAX_VALUE;
        while (right < S.length()) {
            char ch = S.charAt(right++);
            if (chars[ch] != 0) {
                window[ch]++;
                if (chars[ch] == window[ch])
                    count++;
            }

            while (count == set.size()) {
                if (right - left < min_len) {
                    min_len = right - left;
                    start = left;
                }
                char del = S.charAt(left++);
                if (chars[del] != 0) {
                    if (window[del] == chars[del])
                        count--;
                    window[del]--;
                }
            }
        }
        System.out.printf("%d, %d\n", min_len, start);
        return min_len == Integer.MAX_VALUE ? "" : S.substring(start, start + min_len);

    }
}
