package string;

/**
 * @author xu
 * 正则：
 * http://www.cnblogs.com/lzq198754/p/5780340.html
 */
public class DetectCapitalUse {

    public static void main(String[] args) {
        System.out.println(detect("USA"));

    }

    static boolean detect(String word) {

        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");

    }

    /**
     * | 或
     * ？ 0或1
     * + 1或多
     * * 任意次 包括0
     */


    static boolean detect2(String word) {

        return word.matches("[A-Z]*|[A-Z]？[a-z]*");

    }

}
