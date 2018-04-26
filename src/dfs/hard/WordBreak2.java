package dfs.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/6/8.
 140. Word Break II
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 add spaces in s to construct a sentence where each word is a valid dictionary word.
 You may assume the dictionary does not contain duplicate words.

 Return all such possible sentences.

 For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].

 A solution is ["cats and dog", "cat sand dog"].

 */
public class WordBreak2 {

    /* DFS + memoized to replace naive brute force */
    private Map<String, List<String>> maps = new HashMap<>();
    public List<String> wordBreak_dfs(String str, List<String> wordDict) {
        return wordBreak_dfs_helper(str, wordDict);
    }
    // top-down dynamic
    public List<String> wordBreak_dfs_helper(String suffix, List<String> wordDict) {
        // condition check 1 - memoized
        if (maps.containsKey(suffix))
            return maps.get(suffix);
        //当前层|结果集
        List<String> ret = new ArrayList<>();

        // condition check2 - if it has gone through the original string
        if (suffix.equals("") || suffix.length() == 0) {
            ret.add("");//对应下方的append ""
            return ret;
        }
        //遍历dictionary
        for (String word : wordDict) {
            if (suffix.startsWith(word)) {
                // 进入下一层|断开suffix
                List<String> subList = wordBreak_dfs_helper(suffix.substring(word.length()), wordDict);
                // append the returned sub-sentence
                for (String str_sub : subList)
                    ret.add(word + (str_sub.isEmpty()?"":" ") + str_sub);
            }
        }

        // after going through the entire string we store the sentence
        maps.put(suffix, ret);

        return ret;
    }


}
