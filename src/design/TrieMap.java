package design;

import java.util.ArrayList;
import java.util.List;

public class TrieMap<V> {
    // size of ASCII
    private static final int S = 256;
    //size of total keys
    private int size = 0;

    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[S];
    }

    //root node
    private TrieNode<V> root;

    // 在 Map 中添加 key
    public void put(String key, V val) {
        if (!containsKey(key))
            size++;

        TrieNode<V> curr = root;
        for (char c : key.toCharArray()) {
            if (curr.children[c] == null)
                curr.children[c] = new TrieNode<>();
            curr = curr.children[c];
        }
        //赋值表明TrieMap中存在String key
        curr.val = val;
    }

    // 删除键 key 以及对应的值
    // 递归修改数据，如果一个节点需要被删除，向上一层直接返回空指针
    // 一个节点如何知道自己需要被删除？查看val字段和children数组是否都为空
    // 这里可以采用后序遍历，先递归处理子树，再后序位置检查自己的val和children是否为空，判断自身是否需要删除】】。，mnccdf[]

    public void remove(String key) {
        if (containsKey(key)) {
            root = remove(key, root, 0);
            size--;
        }

    }

    public TrieNode remove(String key, TrieNode node, int pos) {
        if (node == null)
            return null;

        // remove the mark of Key from TrieMap
        if (pos == key.length())
            node.val = null;

        node.children[key.charAt(pos)] = remove(key, node.children[key.charAt(pos)], pos++);

        // 后序位置，递归路径上的节点可能需要进一步清理
        // 只要有一个子节点不是空，即可返回当前节点，否则...
        if (node.val == null) {
            for (int i = 0; i < S; i++)
                if (node.children[i] != null)
                    return node;
        }
        //...否则说明当前节点val=null 且无子节点，直接返回null
        return null;
    }

    // 判断 key 是否存在在 Map 中
    // containsKey("tea") -> false
    // containsKey("team") -> true
    public boolean containsKey(String key) {
//        TrieNode<V> node = getNode(root, key);
//        return node.val == null ? false : true;
        return get(key) != null ? true : false;
    }

    // 在 Map 的所有键中搜索 query 的最短前缀
    // shortestPrefixOf("themxyz") -> "the"
    public String shortestPrefix(String query) {
        //重复工作
//        String sub;
//        for (int i = 0; i < query.length(); i++)
//            if (getVal((sub = query.substring(0, i + 1))) != null)
//                return sub;
        TrieNode<V> curr = root;
        for (int i = 0; i < query.length(); i++) {
            if (curr == null) return "";
            //到目前为止curr.val存在值，考察是query.charAt(i-1)，因此返回query[0:i-1]
            if (curr.val != null) return query.substring(0, i);
            curr = curr.children[query.charAt(i)];
        }
        // query本身就是最短prefix
        if (curr != null && curr.val != null) return query;
        return null;

    }

    // 在 Map 的所有键中搜索 query 的最长前缀
    // longestPrefixOf("themxyz") -> "them"
    public String longestPrefix(String query) {
        int MAX = 0;
        TrieNode<V> curr = root;
        for (int i = 0; i < query.length(); i++) {
            if (curr == null)
                return query.substring(0, MAX);
            if (curr.val != null)
                MAX = Math.max(MAX, i);
            curr = curr.children[query.charAt(i)];
        }
        // 测试query本身
        if (curr != null && curr.val != null)
            return query;
        return query.substring(0, MAX);
    }

    // 判断是和否存在前缀为 prefix 的键
    // hasKeyWithPrefix("tha") -> true
    // hasKeyWithPrefix("apple") -> false
    public boolean hasKeyWithPrefix(String query) {
        return get(query) != null ? true : false;
    }

    // 搜索所有前缀为 prefix 的键
    // keysWithPrefix("th") -> ["that", "the", "them"]
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        traverse(getNode(root, prefix), res, new StringBuilder(prefix));
        return res;

    }

    // 遍历以 node 节点为根的 Trie 树，找到所有键,遍历到最深处
    public void traverse(TrieNode<V> node, List<String> res, StringBuilder key) {
        if (node == null /*|| node.val == null*/)
            return;
        if (node.val != null)
            res.add(key.toString());

        for (char i = 0; i < S; i++) {
            key.append(i);
            traverse(node.children[i], res, key);
            key.deleteCharAt(key.length() - 1);
        }
    }

    // 通配符 . 匹配任意字符，搜索所有匹配的键
    // keysWithPattern("t.a.") -> ["team", "that"]
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new ArrayList<>();
        traverse(root, res, new StringBuilder(), pattern, 0);
        return res;
    }

    public void traverse(TrieNode<V> node, List<String> res, StringBuilder key, String pattern, int pos) {
        if (node == null) return;

        if (pos == pattern.length()) {
            if (node.val != null)
                res.add(key.toString());//key.size==pattern.sub(0, pos)
            return;
        }
        char ch = pattern.charAt(pos);
        // 不是通配符, ch需要去下一层children[ch]检验，是否存在，且是否有标记
        if (ch != '.') {
            key.append(ch);
            traverse(node.children[ch], res, key, pattern, pos + 1);
            key.deleteCharAt(key.length() - 1);
        } else {
            for (char i = 0; i < S; i++) {
                key.append(i);
                traverse(node.children[i], res, key, pattern, pos + 1);
                key.deleteCharAt(key.length() - 1);
            }
        }
    }

    // 判断是否存在前缀为 prefix 的键
    // hasKeyWithPattern(".tha") -> true
    // hasKeyWithPattern(".apple") -> false
    public boolean hasKeyWithPattern(String pattern) {
        // 效率低
        // return !keysWithPattern(pattern).isEmpty();
        return hasKeyWithPattern(pattern, root, 0);
    }

    public boolean hasKeyWithPattern(String pattern, TrieNode<V> node, int pos) {
        // pattern match fails
        if (node == null) return false;
        if (pos == pattern.length()) {
            /*
            if (node.val != null) return true;
            else return false;
            */
            return node.val != null;
        }
        char ch = pattern.charAt(pos);

        if (ch == '.') {
            for (char i = 0; i < S; i++)
                if (hasKeyWithPattern(pattern, node.children[i], pos + 1))
                    return true;
        } else { //need return
            return hasKeyWithPattern(pattern, node.children[ch], pos + 1);
        }

        return false;
    }

    // 返回 Map 中键值对的数量
    public int size() {
        return size;
    }

    // start to search from node (not root) for key
    public TrieNode<V> getNode(TrieNode node, String key) {
        TrieNode<V> p = node;
        for (char c : key.toCharArray()) {
            if (p == null)
                return null;
            p = p.children[c];
        }
        return p;
    }

    // 搜索 key 对应的值，不存在则返回 null
    // get("the") -> 4
    // get("tha") -> null
    public V get(String key) {
        TrieNode<V> node = getNode(root, key);
        if (node == null || node.val == null) return null;
        return node.val;

    }
}
