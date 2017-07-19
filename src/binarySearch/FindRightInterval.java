package binarySearch;

import java.util.*;

/**
 * Created by xu on 08/07/2017.
 */
public class FindRightInterval {

/*
1. Sort starts
2. for each Interval's end, use binary search to find the start of next right Interval
3、to get the original index, we need a map
*/
    public int[] findRightInterval(Interval[] intervals) {
        int[] res = new int[intervals.length];

        Map<Integer, Integer> maps = new HashMap<>();
        List<Integer> starts = new ArrayList<>();
        for (int i=0; i<intervals.length; i++) {
            maps.put(intervals[i].start, i);
            starts.add(intervals[i].start);
        }

        Collections.sort(starts);

        for (int i=0; i<intervals.length; i++) {
            int end = intervals[i].end;
            int start = bs(starts, end);
            if (start < end) {  // 不能用start == -1，如果测试数据中start等与-1，则错误
                res[i] = -1;
            } else res[i] = maps.get(start);

        }

        return res;
    }

    public int bs(List<Integer> list, int target) {
        if (target > list.get(list.size()-1))
//            return -1;
            return list.get(0);
        int low = 0, high = list.size()-1;
        while (low < high) {
            int mid = low + (high-low)/2;
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
        for (int i=0; i<intervals.length; i++) {
            maps.put(intervals[i].start, i);
        }
        int[] ret = new int[intervals.length];
        for (int i=0; i<intervals.length; i++) {
            Integer key = maps.ceilingKey(intervals[i].end);
            ret[i] = key==null?-1:maps.get(key);
        }
        return ret;
    }


    class Interval{
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
