package bit;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xu on 2017/7/2.
 */
public class FindMaximumXOR {

    public int findMaximumXOR(int[] nums) {
        int mask = 0;
        int max = 0;

        for (int i=30; i>=0; i--) { //31/30都可以，因此第 2^31 -1 = 2147483647

            Set<Integer> set = new HashSet<>();
            mask |= 1 << i; // 1000-1100-1110-1111....

            // 每一次迭代都会不断缩小选择范围, 每一轮的选择，都是从上一轮中进行的筛选，
            // 因为如果x不被包含在第i-th此迭代中，x将永远不会出现在i之后的任何迭代中， 即x会是的
            // set.contains(candidate ^ leftPartNum) false
            // 如下程序试图将每一位依次设为1，如果在set中的整数中该位存在0和1。
            // 当将所有
            for (int num:nums) {
                int leftPartNum = mask & num;
                set.add(leftPartNum);
            }
            // 如果leftPartNumA ^ leftPartNumb = candidate - a & b都在set的范围内
            int candidate = max | (1 << i);

            for (Integer leftPartNum : set) {
                if (set.contains(candidate ^ leftPartNum)) {
                    max = candidate; //更新max
                    break;
                }
            }//否则不对max更新
        }
        return max;
    }
}
