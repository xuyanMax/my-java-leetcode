package math;

/**
 * Created by xu on 26/07/2017.
 */
/*
Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer
given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

a = 2147483647
b = [1,0]

Result: 1024
*/
public class SuperPow {
    // a^1234567 % 1337 = (a^(123456) % 1337 )^10 % 1337 * (a^7 % 1337)
    // ab % k = a%k * b%k
    // say f(a,b) = a^b % k;
    // f(a, 1234567) = f(a, 1234560) * f(a, 7) = f(f(a, 123456), 10) * f(a,7)
    //                                         = f(f(f(a, 12345), 10), 10) * f(a,7)
    //                                         = ( ( ( (a^12345 % k) ^ 10) % k)^10 ) % k * (a^7 %k)
    public int superPow(int a, int[] b) {

        int base = 1337;
        return superPow(a, b, base, b.length);

    }
    public int superPow(int a, int[] b, int k, int length){
        if (length == 1)
            return powMod(a, b[0], k);

        return powMod(superPow(a, b, k, length-1),10, k) * powMod(a, b[length-1], k);
    }
    // x^y mod k
    public int powMod(int x, int y, int k){

        x %= k;// to avoid overflow if a = 2147483647;
        int pow = 1;
        for (int i=0; i<y; i++) {
            pow = (pow * x) % k;
        }
        return pow;
    }

}
