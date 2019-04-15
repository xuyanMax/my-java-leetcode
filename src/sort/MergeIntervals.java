package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xu on 28/08/2017.
 */

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        // 参照start升序排列

        Collections.sort(intervals, (a, b) -> (a.start - b.start));

        int end = intervals.get(0).end;
        int start = intervals.get(0).start;

        List<Interval> ret = new ArrayList<>();

        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (end >= curr.start) { //[[1,4],[2,3],[1,5]]
                end = Math.max(end, curr.end);
            } else {//不重叠，更新interval，并添加上一个重叠区
                ret.add(new Interval(start, end));
                start = curr.start;
                end = curr.end;
            }
        }
        //添加最后一组
        ret.add(new Interval(start, end));
        return ret;

    }

    public List<Interval> merge_2(List<Interval> intervals) {
        // 参照end升序排列
        Collections.sort(intervals, (a, b) -> (a.end - b.end));

        int end = intervals.get(0).end;
        int start = intervals.get(0).start;

        List<Interval> ret = new ArrayList<>();

        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (end >= curr.start) { //[[1,4],[2,3],[1,5]]
                //区别
                start = Math.min(start, curr.start);
            } else {
                ret.add(new Interval(start, end));
                start = curr.start;
                end = curr.end;
            }
        }
        //添加最后一组
        ret.add(new Interval(start, end));
        return ret;

    }

    class Interval {
        int start;
        int end;

        public Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
