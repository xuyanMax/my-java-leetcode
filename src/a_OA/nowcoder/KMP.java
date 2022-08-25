package a_OA.nowcoder;

import java.util.ArrayList;
import java.util.List;

public class KMP {

    public boolean kmp(String s, String p) {

        int[] lps = new int[p.length()];

        initNext(p, lps);

        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                if (lps[j - 1] != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                    j = 0;
                }
            }
        }
        if (j == p.length())
            return true;

        return false;

    }

    /**
     * 返回所有符合要求的首字符索引
     *
     * @param s
     * @param p
     * @return
     */

    public List<Integer> kmp2(String s, String p) {

        List<Integer> res = new ArrayList<>();
        int[] lps = new int[p.length()];

        initNext(p, lps);

        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            }
            if (j == p.length())
                res.add(i - j + 1);
            else if (i < s.length() && s.charAt(i) != p.charAt(j)) {
                if (lps[j - 1] != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                    j = 0;
                }
            }

        }
        return res;

    }

    public void initNext(String p, int[] lps) {
        //len: max proper prefix which is also suffix
        int len = 0, i = 1;
        lps[0] = 0;//lps[0] always 0
        while (i < p.length()) {
            if (p.charAt(len) == p.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    len = lps[i - 1];
                }

            }
        }
    }
}
