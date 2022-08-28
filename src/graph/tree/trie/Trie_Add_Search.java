package graph.tree.trie;

/**
 * Created by xu on 2017/6/7.
 */
public class Trie_Add_Search {
    class TrieNode {
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];// 链表的形式存储sub-graph.tree

        public TrieNode() {
        }
    }

    private TrieNode root; // the root of Trie is an empty Character

    /**
     * Initialize your key structure here.
     */
    public Trie_Add_Search() {
        this.root = new TrieNode();
    }

    /**
     * Adds a word into the key structure.
     */
    public void addWord(String word) {
        TrieNode head = root;
        for (char c : word.toCharArray()) {
            if (head.children[c - 'a'] == null)
                head.children[c - 'a'] = new TrieNode();

            head = head.children[c - 'a'];

        }
        head.isWord = true;

    }

    /**
     * Returns if the word is in the key structure.
     * A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return helper(word.toCharArray(), 0, this.root);
    }

    public boolean helper(char[] chars, int pos, TrieNode root) {
        if (pos == chars.length)
            return root.isWord;
        if (chars[pos] != '.') {

            TrieNode leaf = root.children[chars[pos] - 'a'];
            // if leaf == null return false, meaning this TrieNode does not exist
            // recursively check the next position with a new root leaf
            return leaf == null ? false : helper(chars, pos + 1, leaf);

        } else {
            // go all possible ways
            for (int i = 0; i < 26; i++) {
                if (root.children[i] != null && helper(chars, pos + 1, root.children[i]))
                    return true;
            }
        }

        return false;
    }

}
