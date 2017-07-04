package dp;

import java.util.*;

/**
 * Created by xu on 2017/6/19.
 */
public class ConcatenatedWords {
    /*solution is based on questin 'Word Break'
    * We iterate through each word and see if it can be formed by other words
    *
    * tips: a word can be only formed by those shorter than itself. Therefore we sort the words by length and
    * apply word break technique to words before it (which are stored in @preDictionary)
    * */
    public List<String> findAllConcatenatedWordsInADict (String[] words) {

        List<String> result = new ArrayList<>();
        Set<String> preDictionary = new HashSet<>(); //used as a dictionary

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });

        for (String str:words) {
            if (wordBreak(preDictionary, str));
                result.add(str);
            preDictionary.add(str);
        }

        return result;
    }

    public boolean wordBreak(Set<String> pre, String word) {
        if (pre.isEmpty())
            return false;

        boolean[] dp = new boolean[word.length() + 1];
        for (int i=0; i<word.length(); i++) {
            for (int j=0; j<=i; j++) {
                if (dp[j] && pre.contains(word.substring(j, i + 1))) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }


}
