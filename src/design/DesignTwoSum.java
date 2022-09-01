package design;

import java.util.*;

// sum中就储存了所有加入数字可能组成的和，每次find只要花费 O(1) 的时间在集合中判断一下是否存在就行了，显然非常适合频繁使用find的场景
public class DesignTwoSum {

    // two sum cache
    Set<Integer> pre = new HashSet<>();
    // two sum array
    Deque<Integer> nums = new ArrayDeque<>();
    /* 向数据结构中添加一个数 number*/
    public void add(int num) {
        // store every possible sum value
        nums.forEach(e -> pre.add(num + e));
        nums.add(num);
    }

    // search if current data structure has TwoSum=value
    // 寻找当前数据结构中是否存在两个数的和为 value
    // O(1) time complexity
    public boolean find(int value) {
        return pre.contains(value);
    }
}
