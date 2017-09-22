package dp;

import java.util.*;

/**
 * Created by xu on 2017/6/19.
 */
/*
472.
Given a list of words (without duplicates), please write a program that returns all
concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words
in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Note:

The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.

*/
public class ConcatenatedWords {
    /*solution is based on question 'Word Break'
    * We iterate through each word and see if it can be formed by other words
    *
    * tips: a word can be only formed by those shorter than itself. Therefore we sort the words by length and
    * apply word break technique to words before it (which are stored in @preDictionary)
    * */
    public List<String> findAllConcatenatedWordsInADict (String[] words) {

        List<String> result = new ArrayList<>();
        Set<String> preDictionary = new HashSet<>(); //used as a dictionary

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });

        for (String str:words) {
            if (wordBreak(preDictionary, str));//能分解，则不作为segment，添加到result list中
                result.add(str);
            // 不能分解，则添加到preDict中，作为一个segment
            preDictionary.add(str);
        }

        return result;
    }

    public boolean wordBreak(Set<String> pre, String word) {
        if (pre.isEmpty())
            return false;

        boolean[] dp = new boolean[word.length() + 1];
        for (int i=0; i<word.length(); i++) {
            for (int j=0; j<=i; j++) {
                if (dp[j] && pre.contains(word.substring(j, i + 1))) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }


}
