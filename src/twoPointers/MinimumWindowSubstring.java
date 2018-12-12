package twoPointers;

// refer to package HashTable -> minWindowSubstring.java

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumWindowSubstring {

    /**
     * Since you have to find the minimum window in S which has all the characters from T,
     * you need to expand and contract the window using the two pointers
     * and keep checking the window for all the characters.
     * This approach is also called Sliding Window Approach.
     *
     * L ------------------------ R , Suppose this is the window that contains all characters of T
     *
     *         L----------------- R , this is the contracted window.
     *         We found a smaller window that still contains all the characters in T
     *
     * When the window is no longer valid, start expanding again
     * @param s
     * @param T
     * @return
     */
    public String minWindow(String s, String T) {

        int left = 0, right = 0, start = 0;
        int size = Integer.MAX_VALUE;
        int[] cnt = new int[256];
        for (int i = 0; i < T.length(); i++)
            cnt[T.charAt(i)]++;

        int count = T.length();

        //先右指针向右侧移动，直到count == 0，然后移动左指针
        while (right < s.length()) {

            if (cnt[s.charAt(right)] > 0) { //满足条件为t的元素
                count--;//count --
            }

            cnt[s.charAt(right)]--;
            right++;

            while (count == 0) { //找到合适的窗口
                if (right - left < size) {
                    size = right - left;//更新窗口大小
                    start = left;
                }
                cnt[s.charAt(left)]++;
                if (cnt[s.charAt(left)] > 0) //测试最左侧元素是否为T中元素
                    count++;
                left++;
            }
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);

    }

    //https://leetcode.com/problems/minimum-window-substring/solution/
    public String minWindowOptimized(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> dictT = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            dictT.put(t.charAt(i), dictT.getOrDefault(t.charAt(i), 0) + 1);
        }

        int required = dictT.size();

        // Filter all the characters from s into a new list along with their index.
        // The filtering criteria is that the character should be present in t.
        List<Pair<Integer, Character>> filteredS = new ArrayList<Pair<Integer, Character>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new Pair<Integer, Character>(i, c));
            }
        }

        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();
        int[] ans = {-1, 0, 0};

        // Look for the characters only in the filtered list instead of entire s.
        // This helps to reduce our search.
        // Hence, we follow the sliding window approach on as small list.
        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = filteredS.get(l).getValue();

                // Save the smallest window until now.
                int end = filteredS.get(r).getKey();
                int start = filteredS.get(l).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
