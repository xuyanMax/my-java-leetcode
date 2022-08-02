package sort;

public class TopKStrings {
    /**
     * 给定一个字符串数组，再给定整数 k ，请返回出现次数前k名的字符串和对应的次数。
     * 返回的答案应该按字符串出现频率由高到低排序。如果不同的字符串有相同出现频率，按字典序排序。
     * 对于两个字符串，大小关系取决于两个字符串从左到右第一个不同字符的 ASCII 值的大小关系。
     * 比如"ah1x"小于"ahb"，"231"<”32“
     * 字符仅包含数字和字母
     *
     * @param strings
     * @param k
     * @return
     */

    /**
     * 输入：
     * ["123","123","231","32"],2
     * <p>
     * 返回值：
     * [["123","2"],["231","1"]]
     * <p>
     * 说明：
     * "123"出现了2次，记["123","2"]，"231"与"32"各出现1次，但是"231"字典序在"32"前面，记["231","1"]，最后返回[["123","2"],["231","1"]]
     *
     * @param strings
     * @param k
     * @return
     */
    public String[][] topKstrings(String[] strings, int k) {
        // write code here
        return null;
    }
}
