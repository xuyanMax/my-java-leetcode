package graph.tree.dfs.hard;

import java.util.*;

/**
 * Created by xu on 2017/6/8.
 * 140. Word Break II
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Return all such possible sentences.
 * <p>
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * <p>
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class WordBreak2 {

    /* DFS + memoized to replace naive brute force */
    private Map<String, List<String>> maps = new HashMap<>();

    public List<String> wordBreak_dfs(String str, List<String> wordDict) {
        return wordBreak_dfs_helper(str, wordDict);
    }

    // top-down dp
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
                    ret.add(word + (str_sub.isEmpty() ? "" : " ") + str_sub);
            }
        }

        // after going through the entire string we store the sentence
        maps.put(suffix, ret);

        return ret;
    }

    /*********************************************************************************/
    /**** similar solution to WordBreak, without memo its runtime will be bad.**/
    int[] memo;
    static Set<String> dict;
    static LinkedList<String> ret;
    static LinkedList<String> concatenation;

    public static List<String> wordBreak_(String s, List<String> wordDict) {
        //int[] memo;
        dict = new HashSet<>(wordDict);
        ret = new LinkedList<>();
        concatenation = new LinkedList<>();
        backtrace_(s, 0);
        return ret;
    }

    //排列组合，返回值void
    //回溯算法框架
    public static void backtrace_(String str, int pos) {
        if (pos == str.length()) {
            ret.add(String.join(" ", concatenation));
            return;
        }

        for (int j = pos + 1; j <= str.length(); j++) {
            String substr = str.substring(pos, j);
            if (dict.contains(substr)) {
                concatenation.addLast(substr);
                backtrace_(str, j);
                concatenation.removeLast();
            }
        }
    }

    public static List<String> wordBreak_DP(String s, List<String> wordDict) {
        //int[] memo;
        dict = new HashSet<>(wordDict);
        cachMap = new HashMap<>();
        return dp__(s, 0);
    }

    static Map<String, List<String>> cachMap;
    static List<String>[] cachLists;
    //返回用wordDict构成s[i:]的所有可能性
    // results[i:] = results[i:j]*排列组合*results[j:]
    public static List<String> dp__(String str, int pos) {

        List<String> ret = new LinkedList<>();

        if (pos == str.length()) {
            ret.add("");
            return ret;
        }

        if (cachMap.containsKey(str.substring(pos))) {
            return cachMap.get(str.substring(pos));
        }

        for (int j = pos + 1; j <= str.length(); j++) {
            String substr = str.substring(pos, j);
            if (dict.contains(substr)) {
                // 找到一个单词匹配 s[j:)
                List<String> subList = dp__(str, j);
                // 构成 s[i+len..] 的所有组合加上 prefix
                // 就是构成构成 s[i] 的所有组合
                for (String s:subList) {
                    if (s.isEmpty())
                        ret.add(substr);
                    else
                        ret.add(substr + " " + s) ;
                }
            }
        }
        cachMap.put(str.substring(pos), ret);
        return ret;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new LinkedList<>();
        wordDict.add("cats");
        wordDict.add("sand");
        wordDict.add("dog");
        wordDict.add("and");
        wordDict.add("cat");
        wordDict.add("og");

        System.out.println(wordBreak_(s, wordDict));
        System.out.println(wordBreak_DP(s, wordDict));
    }

}