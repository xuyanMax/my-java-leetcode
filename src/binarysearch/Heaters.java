package binarysearch;

import java.util.Arrays;

/**
 * Created by xu on 08/07/2017.
 * Now, you are given positions of houses and heaters on a horizontal line,
 * find out minimum radius of heaters so that all houses could be covered by those heaters.
 * <p>
 * So, your input will be the positions of houses and heaters separately,
 * and your expected output will be the minimum radius standard of heaters.
 * <p>
 * Note:
 * <p>
 * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * As long as a house is in the heaters' warm radius range, it can be warmed.
 * All the heaters follow your radius standard and the warm radius will the same.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard,
 * then all the houses can be warmed.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: [1,2,3,4],[1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard,
 * then all the houses can be warmed.
 */
public class Heaters {

    public static void main(String[] args) {
        System.out.println(7);
        System.out.println(~7);
        System.out.println(Integer.toBinaryString(~7));// 11111111111111111111111111111000
        System.out.println(~(-7));
    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int result = 0;//[1],[1,2,3,4]

        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            // 不用考虑正值index，返回正值表明有heater在该house位置，radius为0
            if (index < 0) {
                index = ~index; //取反 ～8 = -9; -8 = ~8 + 1
                // 距离左heater距离dist1(index-1>=0), 距离右heater距离dist2 (index < heater.length)
                // 距离左右heaters距离，选择最小
                // 获取所有这一步距离中的最大值，即为返回值
                int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
                int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
                result = Math.max(result, Math.min(dist1, dist2));
            }
        }
        return result;
    }
}
