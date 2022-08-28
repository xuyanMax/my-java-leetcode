package array.twosum;

import java.util.*;

public class Design_TwoSum {

    // two sum cache
    Set<Integer> pre = new HashSet<>();
    // two sum array
    Deque<Integer> nums = new ArrayDeque<>();

    public void add(int num) {
        nums.forEach(e -> pre.add(num + e));
        nums.add(num);
    }

    // search if current data structure has TwoSum=num
    public boolean find(int num) {
        return pre.contains(num);
    }
}
