package arr.HARD;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

//715. Range Module
//https://leetcode.com/problems/range-module/description//
public class RangeModule {
    TreeMap<Integer, Integer> treeMap;//<start, end>
    public RangeModule() {
        treeMap = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if(left >= right) return;
        Integer r = treeMap.floorKey(left);
        Integer end = treeMap.floorKey(right);
        if(r == null && end == null){//不存在
            treeMap.put(left, right);
        }else if (r != null && left <= treeMap.get(r)){//与left一侧重叠，更新Key值为三者中的最大值
            treeMap.put(r, Math.max(Math.max(treeMap.get(r), right), treeMap.get(end)));
        }else {// right 右侧重叠 | 不重叠
            treeMap.put(left, Math.max(right, treeMap.get(end)));
        }
        //清楚intermediate intervals
        //  　更新的treeMap Key 可能是l或left
        // 更新的treeMap val 可能是 right或treeMap.get(r)å
        // 要删除的 Key值是在(left, right]之间，这个区间是产生overlapping的原因
        Map<Integer, Integer> map = treeMap.subMap(left, false, right, true);

        Set<Integer> set = new HashSet<>(map.keySet());
        treeMap.keySet().removeAll(set);
    }

    public boolean queryRange(int left, int right) {
        Integer start = treeMap.floorKey(left);
        if (start == null) return false;
        return treeMap.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        if (left >= right) return;
        Integer start = treeMap.floorKey(left);
        Integer end = treeMap.floorKey(right);

        //必须在前...
        if (end != null && treeMap.get(end) > right){
            treeMap.put(right, treeMap.get(end));// right is obtained
        }

        if (start != null && treeMap.get(start) > left){
            treeMap.put(start, left); // left is deleted
        }

        // clean intermediate intervals
        Map<Integer, Integer> map = treeMap.subMap(left, true, right, false);
        Set<Integer> set = new HashSet<>(treeMap.keySet());
        treeMap.keySet().removeAll(set);


    }
}
