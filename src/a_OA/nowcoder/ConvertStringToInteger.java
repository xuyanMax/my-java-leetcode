package a_OA.nowcoder;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/1277c681251b4372bdef344468e4f26e?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 */
public class ConvertStringToInteger {

    public int StrToInt(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int n = str.length(), s = 1;
        int res = 0;
        if (str.charAt(0) == '-')
            s = -1;
        for (int i = (str.charAt(0) == '-' || str.charAt(0) == '+') ? 1 : 0; i < n; i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') {
                return 0;
            }
            res = (res << 1) + (res << 3) + (str.charAt(i) & 0xf);
        }
        return res * s;

    }
}
