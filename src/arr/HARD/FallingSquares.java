package arr.HARD;
//699. Falling Squares
/*
On an infinite number line (x-axis), we drop given squares in the order they are given.

The i-th square dropped (positions[i] = (left, side_length)) is a square with
the left-most point being positions[i][0] and sidelength positions[i][1].

The square is dropped with the bottom edge parallel to the number line,
and from a higher height than all currently landed squares.
We wait for each square to stick before dropping the next.

The squares are infinitely sticky on their bottom edge, and will remain fixed
to any positive length surface they touch (either the number line or another square).
Squares dropped adjacent to each other will not stick together prematurely.


Return a list ans of heights. Each height ans[i] represents the current highest height
of any square we have dropped, after dropping squares represented
by positions[0], positions[1], ..., positions[i].

*/

import java.util.ArrayList;
import java.util.List;

public class FallingSquares {
    /*
    * 扫描每一个方形之前所有falling 的squares，判断是否重叠，如果重叠，找到previousMaxHeight
    * 当前curr.height = curr.height + previousMaxHeight
    * 复杂度O(n^2)
    * */
    public List<Integer> fallingSquares(int[][] positions) {
        List<Interval> intervals = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int maxHeight=0;
        for (int[]pos : positions) {
            int start = pos[0], end =  pos[0]+pos[1]-1;
            Interval curr = new Interval(start, end, pos[1]);
            maxHeight = Math.max(maxHeight, getHeight(intervals, curr));
            res.add(maxHeight);
        }
        return res;
    }

    public int getHeight(List<Interval> intervals, Interval curr){
        int previousMaxHeight=0;
        for (Interval interval:intervals){
            // 无重叠的情况
            if (interval.start > curr.end) continue;
            if (interval.end < curr.start) continue;
            previousMaxHeight = Math.max(previousMaxHeight, interval.height);
        }
        curr.height += previousMaxHeight;
        intervals.add(curr);
        return  curr.height;
    }
    class Interval{
        int start, end, height;
        public Interval(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }
}
