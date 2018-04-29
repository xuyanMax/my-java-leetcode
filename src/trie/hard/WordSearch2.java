package trie.hard;

import java.util.ArrayList;
import java.util.List;

/*
* 212. Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix,
you could stop backtracking immediately.
What kind of data structure could answer such queryHighestHeightBtwLR efficiently?
Does a hash table work? Why or why not? How about a Trie?
If you would like to learn how to implement a basic trie,

please work on this problem: Implement Trie (Prefix Tree) first.
*/
public class WordSearch2 {
    class TrieNode{
        String item;
        TrieNode[] links;

        public TrieNode() {
            links = new TrieNode[26];
        }
        public void put (char ch) {
            links[ch - 'a'] = new TrieNode();
        }
        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }
        public String getItem() {
            return item;
        }
        public void setItem(String item) {
            this.item = item;
        }
        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i=0; i<board.length; i++)
            for (int j=0; j<board[0].length; j++)
                dfs(board, i, j, res, root);

        return res;
    }
    public void dfs(char[][] board, int x, int y, List<String> res, TrieNode curr) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return;
        char ch = board[x][y];

        if (ch == '#' || !curr.containsKey(ch))
            return;

        if (curr.get(ch).item != null) { // found one
            res.add(curr.get(ch).item);
            // 继续遍历，不退出
            curr.get(ch).item = null;//标记为"visited"
        }

        // mark it as visited
        board[x][y] = '#';

        dfs(board, x-1, y, res, curr.get(ch));
        dfs(board, x+1, y, res, curr.get(ch));
        dfs(board, x, y+1, res, curr.get(ch));
        dfs(board, x, y-1, res, curr.get(ch));
        // unmake it
        board[x][y] = ch;
    }
    public TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for (String word:words) {
            TrieNode curr = root;
            for (int i=0; i<word.length(); i++) {
                if (!curr.containsKey(word.charAt(i)))
                    curr.put(word.charAt(i));
                curr = curr.get(word.charAt(i));
            }
            curr.item = word;
        }
        return root;
    }
}
