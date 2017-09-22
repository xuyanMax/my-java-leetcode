package string;

/**
 * Created by xu on 07/09/2017.
 */
/*
541. Reverse String II

Given a string and an integer k, you need to reverse the first k characters for every 2k
characters counting from the start of the string. If there are less than k characters left,
reverse all of them. If there are less than 2k but greater than or equal to k characters,
then reverse the first k characters and left the other as original.

Example:

Input: s = "abcdefg", k = 2

Output: "bacdfeg"

Restrictions:

The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

*/
public class ReverseString_2 {
    public String reverseStr(String s, int k) {
        int len = s.length();
        char[] chars = s.toCharArray();

        //可以不要
//        if (len < k) {
//            reverse(chars, 0, len-1);
//            return new String(chars);
//        }
//        if (len >= k && len < 2*k) {
//            reverse(chars, 0, k-1);
//            return new String(chars);
//        }
        //以上可以不要

        int i = 0;
        for (; i<len; i+=2*k)
            if (i+k < len)
                reverse(chars, i, i + k - 1);

        i -= 2*k + 1;
        if ((len-i-1) < k) //剩余的字符个数小于k时，全部颠倒；如果大于k小于2k，则在for循环中已经完成颠倒
            reverse(chars, i, len-1);
        return new String(chars);

    }
    public void reverse(char[]chars, int left, int right) {
        for (; left<right; left++, right--){
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }
    }

    public String reverseStr_beautiful(String s, int k) {
        char[] chars = s.toCharArray();
        int len = s.length();
        int i =0;
        for (;i<len;) {
            int j = Math.min(i + k - 1, len - 1);
            reverse(chars, i, j);
            i += 2*k;
        }
        return new String(chars);

    }
}
