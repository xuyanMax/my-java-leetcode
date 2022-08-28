package algorithm;
/*
504. Base 7

    Given an integer, return its base 7 string representation.

    Example 1:
    Input: 100
    Output: "202"
    Example 2:
    Input: -7
    Output: "-10"
    Note: The input will be in range of [-1e7, 1e7].

    Seen this question in a real interview before?
    Difficulty:Easy
    Total Accepted:26.8K
    Total Submissions:60.9K


*/

import org.junit.Assert;
import org.junit.Test;

public class Base {
    public String convertToBase7(int num) {
        if (num < 0)
            return '-' + convertToBase7(-num);
        if (num < 7)
            return num + "";
        return convertToBase7(num / 7) + num % 7;
    }

    private static final String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    // num is base 10: 100->1c
    public String toBase62(long num) {
        int BASE_SIZE = BASE.length();
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            int n = (int) (num % BASE_SIZE);
            builder.append(BASE.charAt(n));
            num = num / BASE_SIZE;
        }
        return builder.reverse().toString();
    }

    // 1c -> 100
    public int toBase10(String base62, String BASE) {
        int num = 0;
        int BASE_SIZE = BASE.length();
        // 1c -> index(1)=1*62 + index(c)38 *1 = 62 + 38
        for (int i = base62.length() - 1, j = 0; i >= 0; i--, j++) {
            char c = base62.charAt(i);
            num += BASE.indexOf(c) * Math.pow(BASE_SIZE, j);
        }
        return num;
    }

    @Test
    public void test_toBase62() {
        long num = 100;
        Assert.assertEquals("1c", toBase62(num));
    }

    @Test
    public void test_toBase10() {
        String base62 = "1c";
        Assert.assertEquals(100, toBase10("1c", BASE));
    }
}
