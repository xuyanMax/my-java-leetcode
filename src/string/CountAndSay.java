package string;

/**
 * @author xu
 * n sequence
 * 1. 1
 * 2. 11
 * 3. 21
 * 4. 1211
 * 5. 111221
 * 6. 312211
 * 7. 13112221
 * 8. 1113213211
 * 9. 31131211131221
 * <p>
 * the (i+1) th sequence is the "count and say" of the i-th sequence
 * <p>
 * https://leetcode.com/problems/count-and-say/?tab=Description
 * https://discuss.leetcode.com/topic/2264/examples-of-nth-sequence/11
 */
public class CountAndSay {

    public static void main(String[] args) {
        System.out.println(solution(9));
    }

    static String solution(int n) {
        if (n < 1) return "-1";

        String result = "1";
        for (int i = 1; i < n; i++)
            result = build(result);

        return result;
    }

    static String build(String result) {
        StringBuilder builder = new StringBuilder();
        int p = 0;// pointer
        while (p < result.length()) {
            char c = result.charAt(p);
            int count = 0;// count number of duplicates
            while (p < result.length() && c == result.charAt(p)) {
                p++;
                count++;//
            }
            builder.append(count);
            builder.append(c);
        }
        return builder.toString();
    }
}
