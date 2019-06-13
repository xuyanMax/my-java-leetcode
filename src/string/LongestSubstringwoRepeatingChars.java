package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xu
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", with the length 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 * <p>
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/?tab=Description
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/string/LongestSubstringWithoutRepetingCharacter.java
 */
public class LongestSubstringwoRepeatingChars {

    public static void main(String[] args) {

        System.out.println(getLength2("pwwkew".toCharArray()));

    }

    /**
     * two pointers
     * left pointer (j) and right pointer (i) makes the range of substring
     */
    static int getLength(char[] str) {

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        int maxLength = 0;
        int i = 0, j = 0;
        for (; i < str.length; i++) {
            if (map.containsKey(str[i])) {
                j = Math.max(j, map.get(str[i]) + 1);//If the character is already in the hashmap,
                //then move the left pointer to the right of the same character last found
            }
            map.put(str[i], i);// if map contains str[i], updateHighestHeightBtwLR to its latest index; if not, just store its place for the first time
            maxLength = Math.max(maxLength, i - j + 1);
        }

        return maxLength;
    }

    static int getLength2(char[] str) {

        Set<Character> set = new HashSet<Character>();
        int max = 0;
        int j = 0;
        for (int i = 0; i < str.length; i++) {
            if (set.add(str[i])) {
                if (set.size() > max)
                    max = set.size();
            } else {
                while (str[j] != str[i]) {
                    set.remove(str[j]);
                    j++;
                } //
                j++;// move to the right of the same character last second time found.
            }

        }
        return max;
    }

    /**
     * use HashSet to store unique characters.
     * The idea is use a hash set to track the longest substring without repeating characters so far,
     * use a fast pointer i to see if character i is in the hash set or not, if not, great, add it to the hash set, move i
     * forward and updateHighestHeightBtwLR the max length, otherwise, delete from the head by using a slow pointer j until we can put character i to the hash set.
     */
    static int getLength3(char[] str) {
        Set<Character> set = new HashSet<>();
        int max = 0, i = 0, j = 0;
        while (i < str.length) {
            if (set.add(str[i])) {
                i++;
                max = Math.max(max, set.size());
            } else {

                set.remove(str[j]);
                j++;
            }
        }
        return max;
    }


}
