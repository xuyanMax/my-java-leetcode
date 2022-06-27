package OA;

import java.math.BigInteger;

public class test1 {
    public static void main(String[] args) {
        //111111111111111111111111111111111111111111111111111111111111

    }

    public static int sol(String S) {
        if (S == null || S.isEmpty()) return 0;
        // StringBuilder sb = new StringBuilder(S.replaceFirst("^0*", ""));
        //long str2int = Long.parse(S, 2);
        int count = 0;
        S = S.replaceFirst("^0*", "");
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1') count++;
        }

        return count*2 + (S.length() - count - 1);
    }

    // failed with input "1111111111111111111111111111111111111111"
    public static int solInteger(String S) {

        S.replaceFirst("^0*", "");
        long str2int = Long.parseLong(S);
        System.out.println(str2int);
        int count=1;

        while (str2int != 0 ) {
            if (str2int % 2 == 0) {
                str2int = str2int >>1;
            } else {
                str2int--;
            }
            count++;
        }

        return count;
    }

    public static int solStringBuilder(String S) {
//        String S = "011100";
        StringBuilder sb = new StringBuilder(S.replaceFirst("^0*", ""));
        System.out.println(S.replaceFirst("^0*", ""));
        int count = 1;

        while (sb.length() != 1 && sb.toString() != "0") {
            if (sb.charAt(sb.length() - 1) == '0') {
                sb = new StringBuilder(sb.substring(0, sb.length() - 1));
                System.out.println(sb.toString());
            } else {
                sb.setCharAt(sb.length() - 1, '0');
                System.out.println("odd" + sb.toString());
                System.out.println(sb.toString().equals("0"));
            }
            count++;

        }
        System.out.println("out" + count);

        return count;
    }
}
