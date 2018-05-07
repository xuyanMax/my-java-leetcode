package string.medium;
/*
767. Reorganize String

    Given a string S, check if the letters can be rearranged so that two characters
    that are adjacent to each other are not the same.

    If possible, output any possible result.  If not possible, return the empty string.

    Example 1:

    Input: S = "aab"
    Output: "aba"

    Example 2:

    Input: S = "aaab"
    Output: ""
    Note:

    S will consist of lowercase letters and have length in range [1, 500].
*/

public class ReorganizeString {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0)
            return "";
        int[] counts = new int[26];
        int max = 0;
        int maxI = 0;
        for (char c:S.toCharArray()){
            counts[c - 'a']++;
            if (counts[c - 'a'] > max){
                max = counts[c - 'a'];
                maxI = c - 'a';
            }
        }
        // 判断是否 返回""方法
        if (max < (S.length()+1)/2)
            return "";

        int i = 0;//交错填入
        char[] chars = S.toCharArray();
        //先将most frequent char 填入，交错 += 2
        while(counts[maxI]-- > 0){
            chars[i] = (char) ('a' + maxI);
            i += 2;
        }
        for (int j=0; j<26; j++){
            while (counts[j]-->0){
                if (i>=S.length()){
                    i = 1;
                }
                chars[i] = (char) (j + 'a');
                i += 2;
            }
        }
        return String.valueOf(chars);


    }
}
