package sort;

import java.util.Arrays;

/**
 * Created by xu on 28/08/2017.
 */
/*

179. Largest Number
Given a list of non negative integers, arrange them such that they form the largest number.
For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
Note: The result may be very large, so you need to return a string instead of an integer.

*/
public class LargestNumber {
    public static void main(String[] args) {

        String a = "1";
        String b = "312";
        String[] strs = new String[]{"1", "32", "43", "9"};
        System.out.println((a + b).compareTo(b + a));
        Arrays.sort(strs, (aa, bb) -> -(aa + bb).compareTo(bb + aa));
        for (String s : strs)
            System.out.println(s);
    }

    //创建一个String Comparator用来决定字符串数字a，b哪个在前
    //比如 a="123"，b="4" a+b = "1234" < b+a = "4123"
    //因此 会将排在前面
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strings[i] = String.valueOf(nums[i]);

        Arrays.sort(strings, (a, b) -> ((b + a).compareTo(a + b)));//大的在前

        StringBuilder builder = new StringBuilder();
        //特例："0 0 0 0 0 0 0 0"
        //第一个字符串的第一个字符如果是'0'，返回"0"
        if (strings[0].charAt(0) == '0')
            return "0";
        for (String s : strings)
            builder.append(s);
        return builder.toString();
    }
}
