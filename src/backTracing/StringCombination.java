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
        System.out.println("zabc");
        combination("zabcc");
    }

    static List<String> combination(String str) {
        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        for (char c : str.toCharArray()) {

            countMap.put(c, 1 + countMap.getOrDefault(c, 0));
        }
        char[] chars = new char[countMap.size()];
        int[] count = new int[countMap.size()];

        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            chars[index] = entry.getKey();
            count[index] = entry.getValue();
            System.out.println(entry.getKey() + "," + entry.getValue());
            index++;
        }

        List<String> results = new ArrayList<String>();
        char[] result = new char[countMap.size()];

        int level = 0;    // index of the result arr
        int pos = 0;    // pos index, indicates available characters are from the pos index to the n-1.
        combinationUtil(results, chars, count, new ArrayList<>(), level, pos);
        System.out.println(results);
        return results;
    }

    static void combinationUtil(List<String> results, char[] chars, int[] count, ArrayList<Character> aList, int index, int pos) {
        // level indicates the depth of recursion， add the upper level's recursion result[]
        if (index < chars.length)
            addToList(results, aList, index);
        else return;

        // difference to string permutation, which starts at i=0
        for (int p = pos; p < chars.length; p++) {
            if (count[p] == 0)
                continue;

            aList.add(chars[p]);
            count[p]--;
            combinationUtil(results, chars, count, aList, index + 1, p); //level++ 会影响本层for循环的level值
            count[p]++;
            aList.remove(aList.size() - 1);
        }
    }

    static void addToList(List<String> results, ArrayList<Character> aList, int ind) {

        StringBuilder sb = new StringBuilder();
        for (Character c : aList)
            sb.append(c);
        results.add(sb.toString());
    }

}
