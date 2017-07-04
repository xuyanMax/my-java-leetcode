package bit;

/**
 * Created by xu on 2017/5/29.
 */
public class FindDifference {
    public static void main(String[] args) {
//        char c='a' ^ 'd' ^'a';
        int c='a' ^ 'd' ^'a';
        System.out.println(" a" + c);
        System.out.println(97^98^97);
        System.out.println(findTheDifference("a","a"));
        System.out.println((int)Math.pow(2,31));
        System.out.println(Integer.toBinaryString((int)Math.pow(2,31)));


    }


    public static char findTheDifference(String a, String b) {
        int[] chars = new int[26];
        for (char c : a.toCharArray())
            chars[c - 'a']++;
        for (char c : b.toCharArray()) {
            chars[c - 'a']--;
            if (chars[c - 'a'] < 0)
                return c;
        }
        // otherwise, a and b are the same
        // this line of code was a trap...
        return '0';
    }
    public char findTheDifference2(String a, String b) {
        char c = 0;
        for (char ch : a.toCharArray())
            c ^= ch;
        for (char ch:b.toCharArray())
            c ^= ch;
        return c;
    }
    // use XOR
    // XOR is commutative and associative
    // a^a^b^c^b = c
    public char findTheDifference2_1(String a, String b) {
        char c = 0;
        for (int i=0; i<a.length(); i++) {
            c ^= a.charAt(i);
            c ^= b.charAt(i);
        }
        c ^= b.charAt(b.length()-1);
        return c;
    }
    // count the separate sum of ASCII code
    public char ASCII(String s, String t) {
        int ascii_s = 0;
        int ascii_t = 0;
        for (int i=0; i<s.length(); i++)
            ascii_s += (int) (s.charAt(i));
        for (char ch:t.toCharArray())
            ascii_t += (int) ch;
        return (char) (ascii_t - ascii_s);
    }
    // optimized version of the ASCII code method
    // since string s and t differences by one charater, we can
    // manipulate the extra character first.
    public char ASCII_opt(String s, String t) {
        int ASCII = (int)t.charAt(t.length()-1);
        for (int i=0; i<s.length(); i++) {
            ASCII -= s.charAt(i);
            ASCII += t.charAt(i);
        }
        return (char) ASCII;
    }


}
