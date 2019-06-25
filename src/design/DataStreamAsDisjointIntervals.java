package design;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals.
 * <p>
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 * <p>
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * Follow up:
 * What if there are lots of merges and the number of disjoint intervals are small
 * compared to the data stream's size?
 */
public class DataStreamAsDisjointIntervals {
    /**
     * Initialize your data structure here.
     */
    private TreeMap<Integer, Interval> treeMap;// Key: start; Value <start, end>

    public DataStreamAsDisjointIntervals() {
        this.treeMap = new TreeMap<>();
    }

    public void addNum(int val) {
        //已包含
        if (treeMap.containsKey(val)) return;

        Integer l = treeMap.lowerKey(val);
        Integer h = treeMap.higherKey(val);
        // 1. 考虑是否需要合并 l & h，合并后移除多余项
        if (l != null && h != null && treeMap.get(l).end + 1 == val && h - 1 == val) {
            treeMap.get(l).end = treeMap.get(h).end;
            treeMap.remove(h);
        } else if (l != null && treeMap.get(l).end >= val - 1) {//无法合并 l 和 h，val靠近l，有空当 [3,3] 5 [7,7]
            treeMap.get(l).end = Math.max(treeMap.get(l).end, val);
        } else if (h != null && h == val + 1) { // val靠近h，因为val比h小，key要变更
            treeMap.put(val, new Interval(val, treeMap.get(h).end));
            treeMap.remove(h);// 需要移除!!!
        } else {//否则，是单独的一个interval，和l、h无交集
            treeMap.put(val, new Interval(val, val));
        }

    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(treeMap.values());
    }

    class Interval {
        int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
