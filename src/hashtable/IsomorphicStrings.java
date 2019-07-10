package hashtable;
//205. Isomorphic Strings

import java.util.HashMap;
import java.util.Map;

/**
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 */
public class IsomorphicStrings {
    // may assume s and t is of same length
    // compare index instead of chars
    public boolean isIsomorphic(String s, String t) {

        int[] ss = new int[256];
        int[] tt = new int[256];
        for (int i = 0; i < s.length(); i++) {
            ss[i] = -1;
            tt[i] = -1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (ss[s.charAt(i)] != tt[t.charAt(i)])
                return false;
            ss[s.charAt(i)] = i;
            tt[t.charAt(i)] = i;
        }
        return true;

    }

    public boolean sol2(String s, String t) {
        // <s.char(i), t.chat(i)>
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                Character val = map.get(s.charAt(i));
                if (val != t.charAt(i)) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i)))
                    return false;
                else
                    map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }
}
