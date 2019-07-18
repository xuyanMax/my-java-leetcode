package string;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author xu
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 *
 * 递归 & 回溯
 * 参考：
 * https://leetcode.com/problems/restore-ip-addresses/?tab=Description
 * http://www.wtoutiao.com/p/f7e3vI.html
 */
public class RestoreIPAddress {

    public static void main(String[] args) {
        List<String> results = restoreIP("2552551423");
        for (String result : results)
            System.out.println(result);

    }

    static List<String> restoreIP(String str) {
        List<String> results = new LinkedList<String>();

        int levelInit = 0;
        String IPSegment = str;
        String IPForepart = new String();

        doRestore(results, levelInit, IPSegment, IPForepart);

        return results;
    }

    static void doRestore(List<String> results, int level, String IPSegment, String IPForepart) {
        level++;// 层级加一

        if (level == 4) {// 到第四层，判断IPSegment(此ip片段为“IP-IPForepart”,即剩余的字符)是否是合法IP, 判断递归边界

            if (IsValidIPv4Token(IPSegment))
                results.add(IPForepart + "." + IPSegment);
            return;//无论是否合法，都返回，否则无法退出递归
        }

        for (int i = 1; i < 4; i++) { // ip 地址最多三位

            if (IPSegment.length() < i)//如果要取的长度超过了ip片段本身，如字符片段10，而i=3，要取三位不可
                continue;

            //根据i，逐一取可能的ip片段， 例如1122， 会尝试取1，11，112。
            String singleIPField = IPSegment.substring(0, i);

            //判断所取的片段是否合法，如00不合法，10合法，0合法
            if (IsValidIPv4Token(singleIPField)) {

                //定位：所取片段后的index，并取得所取片段后所有的字符，例如112233，single ip片段 为1122，接下来
                // 剩余的所有字符22将传入递归
                int nextStartIndex = IPSegment.indexOf(singleIPField) + singleIPField.length();
                String IPprefix;

                // 判断层级数，如果是第一层，不需要加 . 在IPForepart 后，因IPForepart 为空字符。例如
                // 例如 要处理的字符11111， 层级1，i=0，
                if (level == 1)
                    IPprefix = singleIPField;
                else
                    IPprefix = IPForepart + "." + singleIPField;

                //例如112233，ip片段为1122，剩下的33(IPSegment.substring(nextStartIndex))、层级level、
                // 以及已经找到的符合要求的IP前缀 IPprefix 都将一并传入新递归
                doRestore(results, level, IPSegment.substring(nextStartIndex), IPprefix);
            } else // 截取的ip片段不符ipv4，退出，无需递归下去并返回上一层level；并，i++，继续for循环
                return;
        }

    }

    static boolean IsValidIPv4Token(String token) {
        if (token.startsWith("0") && token.length() > 1) return false;
        try {
            int tokenInt = Integer.parseInt(token);
            if (tokenInt > 255 || tokenInt < 0) return false;
            if (token.startsWith("0") && token.charAt(0) != '0') return false;

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
