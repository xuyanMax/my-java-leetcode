package algorithm;

public class Average {
    //对两个无符号整数预先进行除法，同时通过按位与修正低位数字，保证在两个整数都为奇数时，结果仍然正确。
    public static int avg(int var1, int var2) {
        return var1 / 2 + var2 / 2 + var1 & var2 & 1;
    }

    public static int avg_2(int var1, int var2) {
        return var1 & var2 + (var1 ^ var2) / 2;
    }
}
