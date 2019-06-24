package bit;

/**
 * Created by xu on 2017/5/28.
 * <p>
 * 0 ≤ x, y < 231.
 * Input: x = 1, y = 4
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * <p>
 * The above arrows point to positions where the corresponding bits are different.
 * We can use XOR and then count the number of 1's in the result of x XOR y
 * For example, 1101 and 0110  1101 XOR 0110 = 1011
 * Then things left is to count how many 1's in the above result.
 * Integer.bitCount(1011) = 3 can help
 */
public class HammingDistance {

    public int sol(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /* create Integer.bitCount() manually */
    public int sol2(int x, int y) {
        int result = x ^ y;
        int count = 0;

        // count # 1 bits
        for (int i = 0; i < 32; i++) {
            count += result & 1;
            // count += (result >>i) & 1;
            result >>>= 1;
        }
        return count;
    }

}
