package trie;

import java.util.List;

/*
648. Replace Words

In English, we have a concept called root,
which can be followed by some other words to form another longer word -
let's call this word successor. For example, the root an,
followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000
*/
public class ReplaceWord {
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = buildTrie(dict);
        return replace(sentence.split(" "), root);
    }

    public String replace(String[] tokens, final TrieNode root) {
        StringBuilder builder = new StringBuilder();
        for (String str : tokens)
            builder.append(compareAndGet(str, root)).append(" ");
        //移除" "
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public String compareAndGet(String str, TrieNode root) {
        StringBuilder builder = new StringBuilder();
        TrieNode curr = root;
        for (char c : str.toCharArray()) {
            builder.append(c);
            if (curr.children[c - 'a'] != null) {
                if (curr.children[c - 'a'].isWord)
                    return builder.toString();
                curr = curr.children[c - 'a'];
            } else
                return str; //返回原来的str

        }
        return str;//Trie: str是root中某词的前缀，因此返回str
    }

    public TrieNode buildTrie(List<String> dict) {
        TrieNode root = new TrieNode(' ');
        for (String str : dict) {
            TrieNode curr = root;
            for (char c : str.toCharArray()) {
                if (curr.children[c - 'a'] == null)
                    curr.children[c - 'a'] = new TrieNode(c);
                curr = curr.children[c - 'a'];
            }
            curr.isWord = true;
        }
        return root;
    }

}

class TrieNode {
    char val;
    TrieNode[] children;
    boolean isWord;

    public TrieNode(char val) {
        this.val = val;
        this.children = new TrieNode[26];
        this.isWord = false;
    }
}
