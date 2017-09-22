package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/6/8.

 construct a sentence where each word is a valid dictionary word. You may assume the dictionary
 does not contain duplicate words.

 Return all such possible sentences.

 For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].

 A solution is ["cats and dog", "cat sand dog"].

 */
public class WordBreak_2 {

    /* DFS + memoized to replace naive brute force */
    private Map<String, List<String>> maps = new HashMap<>();
    public List<String> wordBreak_dfs(String str, List<String> wordDict) {
        return wordBreak_dfs_helper(str, wordDict);
    }
    public List<String> wordBreak_dfs_helper(String str_suffix, List<String> wordDict) {
        // condition check 1 - memoized
        if (maps.containsKey(str_suffix))
            return maps.get(str_suffix);
        List<String> ret = new ArrayList<>();

        // condition check2 - if it has gone through the original string
        if (str_suffix.equals("") || str_suffix.length() == 0) {
            ret.add("");
            return ret;
        }
        for (String word : wordDict) {
            if (str_suffix.startsWith(word)) {
                List<String> subList = wordBreak_dfs_helper(str_suffix.substring(word.length()), wordDict);

                // append the returned sub-sentence
                for (String str_sub : subList)
                    ret.add(word + (str_sub.isEmpty()?"":" ") + str_sub);
            }
        }

        // after going through the entire string we store the sentence
        maps.put(str_suffix, ret);

        return ret;
    }


}
