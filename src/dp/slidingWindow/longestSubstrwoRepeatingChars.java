package dp.slidingWindow;

import org.junit.Assert;
import org.junit.Test;

public class longestSubstrwoRepeatingChars {
    public String sol(String S) {
        char[] chars = S.toCharArray();

        int right = 0, left = 0, start = 0;
        int max = Integer.MIN_VALUE;
        int[] window = new int[256];
        while (right < chars.length) {
            char c = chars[right];
            window[chars[right++]]++;

            while (window[c] > 1) {
//                window[c]--;
                // delete 1 frequency of far left element
                char del = chars[left++];
                window[del]--;
            }
            if (max < right - left) {
                max = Math.max(max, right - left);
                start = left;
            }
        }
        return S.substring(start, start + max);

    }

    @Test
    public void test_abc() {
        String S="abcabcbb";
        Assert.assertEquals("abc", sol(S));
    }
    @Test
    public void test_pwwkew() {
        String S="pwwkew";
        Assert.assertEquals("wke", sol(S));
    }
}


