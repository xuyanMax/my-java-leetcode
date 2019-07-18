package string;

/**
 * Created by xu on 05/09/2017.
 * <p>
 * 537.Complex Number Multiplication
 * <p>
 * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
 * <p>
 * Example 1:
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2:
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * Note:
 * <p>
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi, where the integer a and b will both belong to the
 * range of [-100, 100]. And the output should be also in this form.
 */
public class ComplexNumberMultiplication {
    public static void main(String[] args) {
        String a = "a+-b";
        String[] aa = a.split("\\+");
        System.out.println(aa[0] + "-> " + aa[1]);
        System.out.println(aa[1].replace("-", ""));
    }

    public String complexNumberMultiply(String a, String b) {

        String[] first = a.split("\\+");
        String[] second = b.split("\\+");

        first[1] = first[1].replace("i", "");
        second[1] = second[1].replace("i", "");

        int f = Integer.parseInt(first[0]) * Integer.parseInt(second[0])
                + Integer.parseInt(second[1]) * Integer.parseInt(first[1]) * -1;
        int s = Integer.parseInt(second[1]) * Integer.parseInt(first[0])
                + Integer.parseInt(second[0]) * Integer.parseInt(first[1]);

        return new String(f + "+" + s + "i");
    }

    public String complexNumberMultiply_2(String a, String b) {

        String[] first = a.split("\\+");
        String[] second = b.split("\\+");

        int a1 = Integer.parseInt(first[0]);
        int a2 = Integer.parseInt(first[1].replace("i", ""));

        int b1 = Integer.parseInt(second[0]);
        int b2 = Integer.parseInt(second[1].replace("i", ""));

        String A = a1 * b1 - a2 * b2 + "";
        String B = a1 * b2 + a2 * b1 + "i";
        return A + "+" + B;

    }
}
