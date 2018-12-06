package bosonnlp_written;

import java.util.Arrays;

/**
 * Created by xu on 2017/6/10.
 */
public class Q1 {

    public static void main(String[] args) {
//         Test test = new Test();
//         String a ="aaablaskdssxa";
//         byte[] b1 = a.getBytes();
//         Arrays.sort(b1);
//         System.out.println(new String(b1));
//
//         System.out.println(test.sol3("avicaa", "iavc"));
    }

    /*判断a、b两个字符串的字母以及各个字母的个数是一样的，只是排列顺序不同而已
     * 时间复杂度 - O(n^2)
     * 空间复杂度 - O(n)
     * */
    public boolean sol(String a, String b) {

        if (a.length() != b.length())
            return false;

        for (int i = 0; i < b.length(); i++) {
            if (a.charAt(0) == b.charAt(i))
                return sol(sol_helper(a, 0), sol_helper(b, i));
        }
        return b.length() == 0;
    }

    /*删除字符串中索引为index的字符*/
    public String sol_helper(String str, int index) {

        if (index < str.length())
            return new String(str.substring(0, index) + str.substring(index + 1));
        else
            return "";
    }

    //================================================================================================
    /*算法优化1
     * 排序法，将两个字符串中的字符排序，比较两个排序后的字符串是否相等。
     * 时间复杂度 - O(nlgn)
     * 空间复杂度 - O(n)
     * */
    public boolean sol2(String a, String b) {
        if (a.length() != b.length())
            return false;
        byte[] aBytes = a.getBytes();
        byte[] bBytes = b.getBytes();

        //算法速度取决于排序算法
        Arrays.sort(aBytes);
        Arrays.sort(bBytes);

        String atmp = new String(aBytes);
        String btmp = new String(bBytes);

        if (atmp.equals(btmp))
            return true;
        else
            return false;

    }

    //================================================================================================
    /*算法优化2
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * 利用哈希表判断字符及其出现的个数，如完全一致则返回true
     * */
    public boolean sol3(String a, String b) {
        if (a.length() != b.length())
            return false;

        int[] maps = new int[256];

        for (char c : a.toCharArray())
            maps[c - 'a']++;

        for (char c : b.toCharArray()) {
            maps[c - 'a']--;
        }
        for (int i = 0; i < 256; i++)
            if (maps[i] != 0)
                return false;

        return true;

    }
}
