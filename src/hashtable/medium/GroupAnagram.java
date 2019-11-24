package hashtable.medium;

import java.util.*;

/**
 * @Author: xyx
 * @Date: 2018-12-11 22:37
 * @Version 1.0
 * https://leetcode.com/problems/group-anagrams/
 */
public class GroupAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<List<String>>();

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String keyStr = new String(chars);

            if (!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<String>());

            map.get(keyStr).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
