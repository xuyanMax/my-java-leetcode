package sort;

/**
 * Created by xu on 28/08/2017.
 */
public class IsSubsequence {
    // 判断string t是否为s的"subsequence":t是s删除个别的字符后的字符串
    public boolean algo(String s, String t) {
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();

        int i = 0, j = 0;// i, j分别指向t，s
        int k = s.length() - 1;
        while (i < t.length() && j < s.length()) {
            if (tchar[i] == schar[j])
                i++;
            j++;
        }
        return i == t.length();

    }

}
