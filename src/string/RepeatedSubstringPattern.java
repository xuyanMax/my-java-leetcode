package string;

/**
 * @author xu
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple
 * copies of the substring together. You may assume the given string consists of lowercase English letters
 * only and its length will not exceed 10000.
 * <p>
 * https://leetcode.com/problems/repeated-substring-pattern/?tab=Description
 * https://discuss.leetcode.com/topic/67992/java-simple-solution-with-explanation
 */

public class RepeatedSubstringPattern {

    public static void main(String[] args) {
        System.out.println(help2("abcabcabc"));
        System.out.println("".split(" ").length);

    }

    /**
     * 1. the length of the repeating substring must be a divisor of the length of the string
     * 2. search for all possible divisor of length, starting from len/2;
     * 3. if i is a divisor of length, repeat the substring len/i number of times to see
     * 4. if the concatenated string is the input string return true
     */
    static boolean help(String str) {
        int len = str.length();
        for (int i = len / 2; i >= 0; i--) {
            if (len % i == 0) {
                int num = len / i;
                StringBuilder substringRepeated = new StringBuilder();
                for (int j = 0; j < num; j++)
                    substringRepeated.append(str.substring(0, i));
                if (substringRepeated.toString().equals(str))
                    return true;
            }
        }
        return false;

    }

    /**
     * "smiles".substring(1,5) = mile (index:1-4)
     * i = 5: 0-4, 5-9, 10-14
     */
    static boolean help2(String str) {
        int len = str.length();
        for (int i = len / 2; i >= 0; i--) {
            if (len % i == 0) {
                int num = len / i;
                int j = 1;
                for (; j < num; j++)// no need to create the whole string and match
                    if (!str.substring(0, i).equals(str.substring(j * i, (j + 1) * i)))
                        break;
                if (j == num) {
                    return true;
                }
            }
        }
        return false;
    }
}
