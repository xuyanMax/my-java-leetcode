package dp.slidingWindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllAnagramsInString {
    public static void main(String[] args) {
        String S = "cbaebabacd", P = "abc";
        List<Integer> res = findAnagrams(S, P);
        for (Integer re : res)
            System.out.println(re);

    }

    public static List<Integer> findAnagrams(String S, String P) {
        int[] need = new int[256], window = new int[256];
        int left = 0, right = 0, count = 0;
        List<Integer> res = new ArrayList<>();
        Set<Character> set = new HashSet<>();

        for (char c : P.toCharArray()) {
            need[c]++;
            set.add(c);
        }
        while (right < S.length()) {
            char ch = S.charAt(right++);
            if (need[ch] != 0) {
                window[ch]++;
                if (need[ch] == window[ch])
                    count++;
            }
            while (right - left >= P.length()) {
                if (count == set.size())
                    res.add(left);
                char del = S.charAt(left++);
                if (need[del] != 0) {
                    if (need[del] == window[del])
                        count--;
                    window[del]--;
                }
            }
        }
        return res;
    }
}
