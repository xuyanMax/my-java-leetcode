package binarysearch;

import java.util.Arrays;

/**
 * Created by xu on 08/07/2017.
 */
public class Heaters {

    public static void main(String[] args){
        System.out.println(7);
        System.out.println(~7);
        System.out.println(Integer.toBinaryString(~7));// 11111111111111111111111111111000
        System.out.println(~(-7));
    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int result = 0;//[1],[1,2,3,4]

        for (int house:houses) {
            int index = Arrays.binarySearch(heaters, house);
            // 不用考虑正值index，返回正值表明有heater在该house位置，radius为0
            if (index < 0) {
                index = ~index; //取反 ～8 = -9; -8 = ~8 + 1
                // 距离左heater距离dist1(index-1>=0), 距离右heater距离dist2 (index < heater.length)
                // 距离左右heaters距离，选择最小
                // 获取所有这一步距离中的最大值，即为返回值
                int dist1 = index-1>=0? house-heaters[index-1]:Integer.MAX_VALUE;
                int dist2 = index<heaters.length?heaters[index]-house:Integer.MAX_VALUE;
                result = Math.max(result, Math.min(dist1, dist2));

            }
        }
        return result;
    }
}
