package dp.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xyx
 * @Date: 2018-12-11 20:59
 * @Version 1.0
 */
public class LongestSubStringWithoutRepeatingCharacters {
    // two pointers
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(left++));
            }
        }
        return max;
    }
}
