package graph.tree.trie.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xyx
 * @Date: 2018-12-18 06:48
 * @Version 1.0
 *
 * 677. Map Sum Pairs
 * Medium
 *
 * Favorite
 *
 * Share
 * Implement a MapSum class with insert, and sum methods.
 *
 * For the method insert, you'll be given a pair of (string, integer).
 * The string represents the key and the integer represents the value.
 * If the key already existed, then the original key-value pair will be overridden to the new one.
 *
 * For the method sum, you'll be given a string representing the prefix,
 * and you need to return the sum of all the pairs' value whose key starts with the prefix.
 *
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 */
public class MapSum {
    /**
     * Approach #1: Brute Force [Accepted]
     */

    public void insert0(String key, int val) {
        map.put(key, val);
    }

    public int sum0(String prefix) {
        int ans = 0;
        for (String key : map.keySet()) {
            if (key.startsWith(prefix)) {
                ans += map.get(key);
            }
        }
        return ans;
    }

    Map<String, Integer> map;
    Map<String, Integer> score;

    /**
     * Approach #2: Prefix Hashmap [Accepted]
     */

    public MapSum() {
        map = new HashMap();
        score = new HashMap();
    }

    public void insert1(String key, int val) {
        // in-case refresh
        int delta = val - map.getOrDefault(key, 0);
        //update key itself
        map.put(key, val);
        //prefix by character
        String prefix = "";
        for (char c : key.toCharArray()) {
            prefix += c;
            //refresh existing prefix if exist or insert new prefix
            score.put(prefix, score.getOrDefault(prefix, 0) + delta);
        }
    }

    public int sum1(String prefix) {
        return score.getOrDefault(prefix, 0);
    }

    /**
     * Approach #3: Trie [Accepted]
     */
    TrieNode root;

    /**
     * @param key
     * @param val
     * @map: 记录之前key对应的value值，用于更新key对应的新value
     */
    public void insert(String key, int val) {
        //update each nodes
        int delta = val - map.getOrDefault(key, 0);

        map.put(key, val);

        TrieNode cur = root;

        //update root
        cur.score += delta;

        for (char c : key.toCharArray()) {
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
            cur.score += delta;
        }
    }

    public int sum(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) return 0;
        }
        return cur.score;
    }

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap();
//        TrieNode[] child = new TrieNode[26];
        int score;
    }
}

