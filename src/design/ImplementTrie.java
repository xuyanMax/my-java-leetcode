package design;

/**
 * Created by xu on 2017/5/28.
 *
 * 每一个TrieNode对应一个Character，并拥有26个子节点（对应26个小写a-z）
 */
public class ImplementTrie {

    class TrieNode{
         public boolean isWord;
         public TrieNode[] children = new TrieNode[26];// 链表的形式存储sub-tree
         public TrieNode(){};

    }
    private TrieNode root; // the root of Trie is an empty Character

    public ImplementTrie(){
        this.root = new TrieNode();
    }

    public void insert(String str){
        TrieNode head = this.root;
        // loop through each character in the input string and check if the character the child of
        // the current TrieNode; if not, add it as a new child node of the current TrieNode
        for (char c : str.toCharArray()) {
            if (head.children[c-'a'] == null)
                head.children[c-'a'] = new TrieNode();

            head = head.children[c-'a'];
        }
        // after we loop through the input string, the Node we left off on is marked as a 'word' that allows
        // our Trie to to know which words exist in our dictionary
        head.isWord = true;
    }
    public boolean startsWith(String prefix){

        TrieNode head = this.root;
        for (char c:prefix.toCharArray()) {
            if (head.children[c-'a'] == null)
                return false;
            head = head.children[c-'a'];
        }

        return true;
    }
    public boolean search(String word){

        TrieNode head = this.root;
        for (char c:word.toCharArray()) {
            if (head.children[c-'a'] == null)
                return false;
            head = head.children[c-'a'];
        }
        // different from startsWith()
        return head.isWord;
    }
}
