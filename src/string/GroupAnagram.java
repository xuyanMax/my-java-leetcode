package string;

import java.util.*;

/**
 * Created by xu on 05/09/2017.
 */
/*
49. Group Anagrams

Given an arr of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]

*/
public class GroupAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<List<String>>();

        Map<String, List<String>> map = new HashMap<>();
        for (String str:strs) {
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
