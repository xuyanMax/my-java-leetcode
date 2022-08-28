package graph.tree.dfs.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 139. Word Break
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 */
public class WordBreak {

    boolean found = false;
    LinkedList<String> res = new LinkedList<>();

    public boolean wordBreak(String str, List<String> wordDict) {
        backtrace(str, wordDict, 0);
        return found;
    }

    //solution 1, time complexity O(n^2*N*M) - N size of s, M size of wordDict
    //递归函数的时间复杂度的粗略估算方法就是用递归函数调用次数（递归树的节点数） x 递归函数本身的复杂度。
    // 对于这道题来说，递归树的每个节点其实就是对s进行的一次切割，那么最坏情况下s能有多少种切割呢？
    //长度为N的字符串s中共有N - 1个「缝隙」可供切割，每个缝隙可以选择「切」或者「不切」，所以s最多有O(2^N)种切割方式，即递归树上最多有O(2^N)个节点
    //当然，实际情况可定会好一些，毕竟存在剪枝逻辑，但从最坏复杂度的角度来看，递归树的节点个数确实是指数级别的。
    //it will result in TIMEOUT running large str and wordDict
    public void backtrace(String str, List<String> wordDic, int pos) {
        if (found) // 如果已经找到答案，就不要再递归搜索了
            return;
        // 只有走到这一步代表完全匹配.
        if (pos == str.length()) {
            found = true;
            return;
        }

        for (int j = pos + 1; j < str.length() + 1; j++) {
            String substr = str.substring(pos, j);
            if (wordDic.contains(substr)) {
                res.addLast(substr);
                backtrace(str, wordDic, j);
                res.removeLast();
            }
        }

    }

    int[] memo;
    // 转化为哈希集合，快速判断元素是否存在
    Set<String> map;

    public boolean wordBreakWithMemo(String str, List<String> wordDict) {
        this.memo = new int[str.length()];
        Arrays.fill(memo, -1);
        return dp(str, 0);
    }

    //solution 2, graph.tree.dfs with memo => dp
    public boolean dp(String str, int pos) {
        if (pos == str.length()) return true;

        if (memo[pos] != -1) {
            return memo[pos] == 0 ? false : true;
        }
        for (int j = pos + 1; j <= str.length(); j++) {
            String substr = str.substring(pos, j);
            if (map.contains(substr)) {
                boolean subProb = dp(str, j);
                if (subProb) {
                    //update memo
                    memo[pos] = 1;
                    return true;
                }
            }
        }
        memo[pos] = 0;
        return false;
    }

    //solution 3 pure DP, 拆分法
    public boolean workBreakDP(String str, List<String> wordDict) {
        boolean[] dp = new boolean[str.length() + 1];

        //dp[i] means str[0:i] can be made up of from wordDict
        dp[0] = true;// empty string;  dp[str.length] means str[0:] can be made up of strings from wordDict

        for (int i = 0; i < str.length() + 1; i++)
            for (String word : wordDict)
                if (i >= word.length())
                    if (dp[i - word.length()] && word.equals(str.substring(i - word.length(), i)))
                        // dp[i] is true only iff dp[i-word.length] && str[i-len:i].equals(word)
                        dp[i] = true;

        return dp[str.length()];
    }
}
