package sort;

import java.util.Collections;
import java.util.List;

/**
 * Created by xu on 28/08/2017.
 */
/*

Add to List
524. Longest Word in Dictionary through Deleting
DescriptionHintsSubmissionsDiscussSolution
Discuss Pick One
Given a string and a string dictionary, find the longest string in the dictionary that can be
formed by deleting some characters of the given string. If there are more than one possible results,
return the longest word with the smallest lexicographical order.
If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"

Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
*/
public class LongestWordInDictionary {

    // no need to sort
    // use isSubsequence() 方法
    public String findLongestWord(String s, List<String> d) {
        String res = "";
        for (String str:d) {
            if (isSubsequence(s, str)) {
                if (str.length() > res.length())
                    res = str;
                if (str.length() == res.length() && str.compareTo(res) < 0)
                    res = str;
            }
        }
        return res;
    }
    // 判断string t是否为s的"subsequence":t是s删除个别的字符后的字符串
    public boolean isSubsequence(String s, String t) {
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();

        int i=0, j=0;// i, j分别指向t，s
        while (i < t.length() && j < s.length()) {
            if (tchar[i] == schar[j])
                i++;
            j++;
        }
        return i == t.length();

    }
    //sort by length & by lexicography
    public String findLongestWord_2(String s, List<String> d) {
        // sort
        Collections.sort(d, (a,b)->(a.length()!=b.length())?b.length()-a.length():a.compareTo(b));
        String res = "";
        for (String str : d) {
            if (isSubsequence(s, str)) {
                res = str;//最先遇到的一定是最长的
                break;
            }

        }
        return res;
    }
}
