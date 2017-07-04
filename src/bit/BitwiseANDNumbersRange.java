package bit;

/**
 * Created by xu on 2017/6/29.
 */
public class BitwiseANDNumbersRange {


/*
It is like asking to find the common prefix of m and n' binary code

-- IF n!=m, there at least an odd and an even number, so the last bit position is 0
-- SHIFT m and n right 1 bit

KEEP doing above until m == n (either 0 or 1: 0 case: no common prefix; 1 case: MSB is the same)

examples:
1.
1000
 111
-----
   0 := 0<<=4
2.
1000
1010
----
1000 := 1<<=3

3.
11000
11100
-----
11000 := 11<<=3

*/
    public int rangeBitwiseAnd(int m, int n) {
        //   if (m<=0)
        //     return 0;
        int factor = 1;
        while (m!=n) {
            m>>=1;
            n>>=1;
            factor<<=1;
        }
        return m * factor;
    }
}
