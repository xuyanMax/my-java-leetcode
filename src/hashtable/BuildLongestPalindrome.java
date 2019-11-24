package hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xu
 * <p>
 * https://leetcode.com/problems/longest-palindrome/#/description
 * <p>
 * Given a string which consists of lowercase or uppercase letters,
 * find the length of the longest palindromes that can be built with those letters.
 * <p>
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * <p>
 * Note:
 * Assume the length of given string will not exceed 1,010.
 * <p>
 * Input:
 * "abccccdd"
 * <p>
 * Output:
 * 7
 * <p>
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class BuildLongestPalindrome {

    static int solution(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        // count the number of char pairs
        // ab****ba
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                count++;
                set.remove(c);
            }
        }
        //如果最终所有字符没有全部成对，那么存在一个或多个单独出现的字符，随便加一个字符在中间即可
        // ab**X**ba
        if (!set.isEmpty()) return count * 2 + 1;
        else return count * 2;
    }

}
