package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xu on 2017/6/7.
 * time complexity: O(n*k^2)
 */
public class PalindromePairs {
    /* 每一个TrieNode对应一个Character，并拥有26个子节点（对应26个小写a-z）
   * */
    class TrieNode{
        public boolean isWord;
        public TrieNode[] children;// 链表的形式存储sub-tree
        int index;
        public List<Integer> list;

        public TrieNode(){
            index = -1;
            children = new TrieNode[26];// 链表的形式存储sub-tree
            list = new ArrayList<>();
        };


    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        TrieNode root = new TrieNode();

        // construct the Trie Structure
        for (int i=0; i<words.length; i++)
            addWord(words[i], root, i);

        // search
        for (int i=0; i<words.length; i++)
            search(words[i], i, root, ret);

        return ret;
    }

    // construct the Trie Structure from the word's last character to the first (deal with common suffix)
    public void addWord(String word, TrieNode root, int index) {
        for (int pos=word.length(); pos>=0; pos--) {
            if (root.children[word.charAt(pos) -'a'] == null)
                root.children[word.charAt(pos) -'a'] = new TrieNode();

            // add the INDEX of the word to list if the prefix of the WORD is palindrome
            // example. lls + (ss)sll since (ss) is palindrome and lls matches sll, so lls+sssll is a palindrome
            if (isPalindrome(word, 0, pos))
                root.list.add(index);

            root = root.children[word.charAt(pos) -'a'];
        }
        root.isWord = true;
        // only one character (a TrieNode) is a palindrome
        root.list.add(index);
        // set its index
        root.index = index;
    }
    /*Start the search(match) from the beginning of the word to the end */
    public void search(String word, int i, TrieNode root, List<List<Integer>> ret) {

        // search from the root (empty TrieNode)
        for (int j=0; j<word.length(); j++) {
            // match the WORD prefix with the suffix of some word and if match && the WORD the suffix is palindrome as well.
            // then these two words' concatenation is a palindrome
            if (root.index >=0  && root.index !=i && isPalindrome(word, j, word.length()-1))
                ret.add(Arrays.asList(i, root.index));

            root = root.children[word.charAt(j) - 'a'];

            if (root == null)
                return;

        }

        // last TrieNode of the WORD
        for (int l : root.list) {
            if (l == i) continue; // UNIQUE
            ret.add(Arrays.asList(i, l));
        }
    }
    /*
    @left = 0
    @right = right boundary
    Check if str.substring(0,  right) is palindrome or not
    *
    * */
    public boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left)!=str.charAt(right))
                return false;
            left++;
            right--;
        }
        // only one character return true
        return true;
    }

}
