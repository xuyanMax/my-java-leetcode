package backTracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu
 * <p>
 * String combination of all subsets of string
 * For example, ABC.
 * Combination includes:字符满足1到n
 * A
 * AB
 * ABC
 * B
 * BC
 * C
 * The combination results are not considering the positions. Say, combinations takes ABC, BAC, CAB the same.
 * <p>
 * Permutation includes:字符只是n
 * ABC, ACB, BAC, BCA, CAB, CBA
 * <p>
 * references
 * <p>
 * https://www.youtube.com/watch?v=xTNFs5KRV_g&list=PLrmLmBdmIlpslxZUHHWmfOzNn6cA7jvyh&index=3
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/Combination.java
 */
public class StringCombination {

    public static void main(String[] args) {

        combination("zabc");
    }

    static List<String> combination(String str) {
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        for (char c : str.toCharArray()) {

            countMap.compute(c, (key, val) -> {
                if (val == null)
                    return 1;
                else return val + 1;
            });

            countMap.put(c, 1 + countMap.getOrDefault(c, 0));
        }
        char[] chars = new char[countMap.size()];
        int[] count = new int[countMap.size()];

        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            chars[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }

        List<String> results = new ArrayList<String>();
        char[] result = new char[countMap.size()];

        int level = 0;    // index of the result arr
        int pos = 0;    // pos index, indicates available characters are from the pos index to the n-1.
        combinationUtil(results, chars, count, result, level, pos);
        System.out.println(results);
        return results;
    }

    static void combinationUtil(List<String> results, char[] chars, int[] count, char[] result, int ind, int pos) {
        // level indicates the depth of recursion， add the upper level's recursion result[]
        addToList(results, result, ind);

        // difference to string permutation, which starts at i=0
        for (int i = pos; i < chars.length; i++) {
            if (count[i] == 0)
                continue;

            result[ind] = chars[i];
            count[i]--;
            combinationUtil(results, chars, count, result, ind + 1, i); //level++ 会影响本层for循环的level值
            count[i]++;
        }
    }

    static void addToList(List<String> results, char[] result, int ind) {

        StringBuilder tmp = new StringBuilder();

        // 如果不截止到level，会将字符全部输出。下一层递归得到的result[]也会输出，不合要求
        for (int i = 0; i < ind; i++) {
            tmp.append(result[i]);
        }
        if (tmp.toString().length() != 0)
            results.add(tmp.toString());

    }

}
