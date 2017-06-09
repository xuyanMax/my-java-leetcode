package bit;

/**
 * Created by xu on 2017/5/28.
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 *
 *  Input:
    26
   Output:
   "1a"

   Input:
   -1

   Output:
   "ffffffff"

 */

public class ConvertNumToHex {

    // solution: each time we TAKE a look at the last four digits of binary version of input,
    // and maps that to a hex char, shift the input by 4 bits to the right do it again until the input becomes 0
    public static void main(String[] args){
        System.out.println(0xf);

    }

    char[] maps = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    public String toHex(int num) {
        if (num == 0) return "0";
        String str = "";
        while (num != 0) {
            str = maps[num & 0xf] + str;
            num >>>= 4; // num = num>>>4;
        }
        return str;
    }
    // stringbuilder
    public String toHex2(int num) {
        if (num == 0)return "0";
        StringBuilder builder = new StringBuilder();
        while (num != 0) {
            builder.append(maps[num & 0xf]);
            num >>>= 4;
        }
        return builder.reverse().toString();
    }
}
