package binarysearch;

import java.util.*;

/**
 * Created by xu on 08/07/2017.
 */

/**
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point
 * is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 *
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the
 * minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1
 * for the interval i. Finally, you need output the stored value of each interval as an arr.
 *
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 * Example 1:
 * Input: [ [1,2] ]
 *
 * Output: [-1]
 *
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * Example 2:
 * Input: [ [3,4], [2,3], [1,2] ]
 *
 * Output: [-1, 0, 1]
 *
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 * Example 3:
 * Input: [ [1,4], [2,3], [3,4] ]
 *
 * Output: [-1, 2, -1]
 *
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 */
public class FindRightInterval {

    /*
    1. Sort starts
    2. for each Interval's end, use binary search to find the start of next right Interval
    3、to get the original index, we need a map
    */
    public int[] findRightInterval(Interval[] intervals) {
        int[] res = new int[intervals.length];

        //存储区间索引映射关系：intervals[i].start : i
        Map<Integer, Integer> maps = new HashMap<>();
        List<Integer> starts = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            maps.put(intervals[i].start, i);
            starts.add(intervals[i].start);
        }
        //升序排序
        Collections.sort(starts);

        for (int i = 0; i < intervals.length; i++) {
            int end = intervals[i].end;
            //在starts 中寻找比当前区间.end大的start对应的索引
            int start = bs(starts, end);
            if (start < end) {  // 没有比end大的start值；tips：不能用start == -1，如果测试数据中start等于-1，则错误
                res[i] = -1;
            } else res[i] = maps.get(start);

        }

        return res;
    }

    public int bs(List<Integer> list, int target) {
        if (target > list.get(list.size() - 1))
//            return -1;
            return list.get(0);
        int low = 0, high = list.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) < target)
                low = mid + 1;
            else // 高级
                high = mid;
        }
        return list.get(low);
    }

/*
 TreeMap
*/

    public int[] findRightInterval_TREEMAP(Interval[] intervals) {
        TreeMap<Integer, Integer> maps = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            maps.put(intervals[i].start, i);
        }
        int[] ret = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Integer key = maps.ceilingKey(intervals[i].end);
            ret[i] = key == null ? -1 : maps.get(key);
        }
        return ret;
    }


    class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval() {

        }
    }
}
