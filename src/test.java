import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String s = "闫旭";
        System.out.println(s.substring(0, 2));
        System.out.println(s.getBytes().length == s.length());
        String[] strings = new String[5];
        strings[0] = "a";
        strings[1] = "ab";
        strings[2] = "abc";
        strings[3] = "abcd";
        strings[4] = "abcde";
        System.out.println(Arrays.stream(strings).reduce((s1, s2) -> (s1 + "\n" + s2)).get());
        String brackets = "asdasd(asd)";
        String brackets2 = "asdasd（asd）";
        System.out.println(brackets.indexOf("(") + "， " + brackets.indexOf("（"));
        System.out.println(brackets2.indexOf("(") + "， " + brackets2.indexOf("（"));

    }
}
