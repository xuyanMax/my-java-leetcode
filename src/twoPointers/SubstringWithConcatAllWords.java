package twoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author xu
 *
 *
 * You are given a string, s, and a list of words, words, that are all of the same length.
 *
 * Find all starting indices of substring(s) in s that is a concatenation of each word
 *
 * in words EXACTLY once and without any intervening characters.

	For example, given:
	s: "barfoothefoobarman"
	words: ["foo", "bar"]


	You should return the indices: [0,9].
	(order does not matter).

	"aaaaaaaa"
	["aa","aa","aa"]


 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/#/description
 * https://discuss.leetcode.com/topic/6617/an-o-n-solution-with-detailed-explanation
 *
 */
public class SubstringWithConcatAllWords {

    //1. use two pointers to maintain a window for the substring
    // since each word of words is of the same length, we need two for loops
    // first for: index i, we start from [0:wl-1], (wl - words[0]. length), in case we have some like "ambarfoo"
    // second for: we start from [i:s.length() - wl * K] to locate the substring

    //2. we need two HashMaps to maintain the number of occurrences of a string;
    // maps for words[], seen for what we have seen so far
    // seen[someString] should be <= maps[someString]; otherwise, we need to advance the left pointer
    // by one more word (wl steps)
    // until the above condition is satisfied

    //3. we need a counter to check if all words have been matched and add the left pointer to the List
    // remember to un-make the seen HashMap
    public List<Integer> findSubstring(String s, String[] words) {
        int N = s.length();
        int K = words.length;
        int wl = words[0].length();
        List<Integer> result = new ArrayList<>();
        if (N < K * wl)
            return result;

        HashMap<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        HashMap<String, Integer> seen = new HashMap<>();

        // "ambarfoo"符合要求的为bar foo但是是从索引=2的位置开始的，因此需要遍历 0:wl
        for (int i = 0; i < wl; i++) {
            int count = 0;
            for (int left = i, right = i; right < N - wl + 1; right += wl) {
                String str = s.substring(right, right + wl);

                if (map.containsKey(str)) {
                    seen.put(str, seen.getOrDefault(str, 0) + 1);

                    if (map.get(str) >= seen.get(str)) {
                        count++;
                    } else { // e.g. amfoofoofoobar
                        //当连续出现words中的某一个str时（超出了应该出现的次数），需要将左侧的指针每次右移wl
                        // 同时在seen 中减1 （左侧指针指向的wl长度的str在seen中出现的次数）
                        // move left pointer forward by one more word
                        // WHILE
                        while (map.get(str) < seen.get(str)) {
                            String left_str = s.substring(left, left + wl);

                            // 减少seen中的次数
                            seen.put(left_str, seen.get(left_str) - 1);

                            // seen减少一个left_str后，如果出现的次数比map中对应的少，说明left_str属于words
                            // 记录的count值因此需要减少1
                            if (map.get(left_str) > seen.get(left_str))
                                count--;
                            // 左侧指针 - 右移wl个单位
                            left += wl;
                        }
                    }
                    // 更新完 seen中的值后，验证左右指针指向的区间是否是符合要求的
                    // 符合要求 则count 值 == words.length
                    // come to A result and check count
                    if (count == K) {
                        result.add(left);
                        count--;// move to the next;
                        left += wl;
                    }

                } else {// the str does not exist in the maps reset all vars;
                    // left pointer goes to the position of right pointer
                    count = 0;
                    left = right + wl;
                    seen.clear();
                }
            }
            seen.clear();
        }


        return result;

    }

}
