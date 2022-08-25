package a_OA.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 字符串的排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */
public class PermutationString {
    /**
     * 通过交换遍历第k位得到可能的所有字符
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation2(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) return result;

        helper2(str.toCharArray(), 0, result);

        Collections.sort(result);

        return result;
    }

    public void helper2(char[] chars, int left, ArrayList<String> res) {
        if (left == chars.length - 1) {
            String str = String.valueOf(chars);
            if (!res.contains(str))
                res.add(str);
        } else {
            for (int i = left; i < chars.length; i++) {
                swap(chars, i, left);

                helper2(chars, left + 1, res);

                swap(chars, i, left);

            }
        }
    }

    public void swap(char[] chars, int a, int b) {
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
    }

    /**
     * 常规排列去重
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> results = new ArrayList<>();

        char[] chars = str.toCharArray();

        Arrays.sort(chars);

        helper(chars, 0, results, new boolean[26], new char[str.length()]);

        Collections.sort(results);

        return results;
    }

    public void helper(char[] chars, int left, ArrayList<String> results, boolean[] visited, char[] newchars) {
        if (left == chars.length) {
            results.add(String.valueOf(newchars));
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) continue;

            if (i > 0 && chars[i - 1] == chars[i] && !visited[i - 1])
                continue;

            visited[i] = true;

            char tmp = newchars[left];

            newchars[left] = chars[i];

            helper(chars, left + 1, results, visited, newchars);

            newchars[left] = tmp;

            visited[i] = false;
        }

    }

}
