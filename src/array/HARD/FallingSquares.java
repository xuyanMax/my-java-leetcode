package array.HARD;
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

import java.util.*;

public class FallingSquares {
    /*
    * 扫描每一个方形之前所有falling 的squares，判断是否重叠，如果重叠，找到previousMaxHeight
    * 当前curr.height = curr.height + previousMaxHeight
    * 复杂度O(n^2)
    * */
    public List<Integer> fallingSquares(int[][] positions) {
        List<Interval> previousIntervals = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int maxHeightSoFar=0;
        for (int[]pos : positions) {
            int start = pos[0], end =  pos[0]+pos[1]-1;
            Interval curr = new Interval(start, end, pos[1]);
            maxHeightSoFar = Math.max(maxHeightSoFar, getMaxHeight(previousIntervals, curr));
            res.add(maxHeightSoFar);
        }
        return res;
    }

    public int getMaxHeight(List<Interval> intervals, Interval curr){
        int previousMaxHeight=0;
        for (Interval preInterval:intervals){
            // 无重叠的情况
            if (preInterval.start > curr.end) continue;
            if (preInterval.end < curr.start) continue;
            previousMaxHeight = Math.max(previousMaxHeight, preInterval.height);
        }
        curr.height += previousMaxHeight;
        intervals.add(curr);
        return curr.height;
    }
    class Interval{
        int start, end, height;
        public Interval(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }

// solution 2
/*
Let qans[i] be the maximum height of the INTERVAL specified by positions[i].
At the end, we'll return a running max of qans.

For each square positions[i], the maximum height will get higher
by the size of the square we already drop. Then, for any future squares that intersect
the interval [left, right) (where left = positions[i][0],
right = positions[i][0] + positions[i][1]),
we'll updateHighestHeightBtwLR, the maximum height of that interval.
*/
    public List<Integer> fallingSquares2(int[][] positions) {
        int[] intervalMax = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int left = positions[i][0];
            int size = positions[i][1];
            int right = left + size;
            intervalMax[i] += size;//如果和[0:i-1]有重叠，那么intervalMax[i]的值已经累加过重叠squares，若无则为0

            for (int j = i+1; j < positions.length; j++) {
                int left2 = positions[j][0];
                int size2 = positions[j][1];
                int right2 = left2 + size2;
                /*if (left2 < right && right2 > right
                        || left2 < left && right2 > left
                        || left2 > left && right2 < right)
                */
                if (left2 < right && left < right2) { //if two squares intersect
                    intervalMax[j] = Math.max(intervalMax[j], intervalMax[i]);
                }
            }
        }

        List<Integer> ans = new ArrayList();
        int cur = -1;
        for (int x: intervalMax) {
            cur = Math.max(cur, x);
            ans.add(cur);
        }
        return ans;

    }
    // solution 3
    // Brute Force with Coordinate Compression

    int[] heights;
    public List<Integer> fallingSquares3(int[][] positions) {
        //Coordinate Compression
        Set<Integer> coords = new HashSet();
        for (int[] pos: positions) {
            coords.add(pos[0]);
            coords.add(pos[0] + pos[1] - 1);
        }
        List<Integer> sortedCoords = new ArrayList(coords);
        Collections.sort(sortedCoords);

        Map<Integer, Integer> index = new HashMap();
        int t = 0;
        for (int coord: sortedCoords) index.put(coord, t++);
        // compression over

        // t: how many compressed intervals
        heights = new int[t];
        int maxSoFar = 0;
        List<Integer> ans = new ArrayList();

        for (int[] pos: positions) {
            int L = index.get(pos[0]);
            int R = index.get(pos[0] + pos[1] - 1);
            int h = queryHighestHeightBtwLR(L, R) + pos[1];
            updateHighestHeightBtwLR(L, R, h);
            maxSoFar = Math.max(maxSoFar, h);
            ans.add(maxSoFar);
        }
        return ans;
    }
    public int queryHighestHeightBtwLR(int L, int R) {
        int ans = 0;
        for (int i = L; i <= R; i++) {
            ans = Math.max(ans, heights[i]);
        }
        return ans;
    }
    public void updateHighestHeightBtwLR(int L, int R, int h) {
        for (int i = L; i <= R; i++) {
            heights[i] = Math.max(heights[i], h);
        }
    }
}
