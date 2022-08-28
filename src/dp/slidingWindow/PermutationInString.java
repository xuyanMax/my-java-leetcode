package dp.slidingWindow;

import java.util.HashSet;
import java.util.Set;

//LeetCode 567 题，Permutation in String，难度 Medium：
public class PermutationInString {
    public static void main(String[] args) {

    }

    public static boolean checkInclusion(String t, String s) {
        int[] need = new int[256], window = new int[256];
        int left = 0, right = 0;
        Set<Character> set = new HashSet<>();
        for (char c : t.toCharArray()) {
            need[c]++;
            set.add(c);
        }
        int count = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (need[ch] != 0) {
                window[ch]++;
                if (window[ch] == need[ch])
                    count++;
            }
            while (right - left >= t.length()) {
                if (count == set.size())
                    return true;
                char del = t.charAt(left);

                if (need[del] != 0) {
                    if (need[del] == window[del])
                        count--;
                    window[del]--;
                }
            }
        }
        return false;

    }
}
