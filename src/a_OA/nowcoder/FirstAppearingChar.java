package a_OA.nowcoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/1c82e8cf713b4bbeb2a5b31cf5b0417c?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]第一个只出现一次的字符
 * 热度指数：199238时间限制：1秒空间限制：32768K
 * <p>
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class FirstAppearingChar {

    //或者使用LinkedHashMap

    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0)
            return 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++)
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        //map自动排序
        for (int i = 0; i < str.length(); i++)
            if (map.get(str.charAt(i)) == 1)
                return i;
        return -1;
    }
}
