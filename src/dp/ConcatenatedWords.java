package dp;

import java.util.*;

/**
 * Created by xu on 2017/6/19.
 * <p>
 * 472.
 * Given a list of words (without duplicates), please write a program that returns all
 * concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words
 * in the given arr.
 * <p>
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * <p>
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * <p>
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * <p>
 * Note:
 * <p>
 * The number of elements of the given arr will not exceed 10,000
 * The length sum of elements in the given arr will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 */
public class ConcatenatedWords {
    /**
     * solution is based on question 'Word Break'
     * We iterate through each word and see if it can be formed by other words
     * <p>
     * tips: a word can be only formed by those shorter than itself. Therefore we sort the words by length and
     * apply word break technique to words before it (which are stored in @preDictionary)
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        List<String> result = new ArrayList<>();
        Set<String> dic = new HashSet<>(); //used as a dictionary

        Arrays.sort(words, Comparator.comparingInt(String::length));

        for (String str : words) {
            if (wordBreak(dic, str)) {//能分解，则不作为segment，添加到result list中
                result.add(str);
            }
            // 能或者不能分解，都添加到preDict中，作为一个segment
            dic.add(str);
        }
        return result;
    }

    public boolean wordBreak(Set<String> pre, String word) {
        if (pre.isEmpty())
            return false;

        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true; // empty string
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && pre.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }


}
