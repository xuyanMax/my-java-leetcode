package hashtable;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @Author: xyx
 * @Date: 2018-12-12 15:32
 * @Version 1.0
 */
public class LFUCache {
    HashMap<Integer, Integer> vals;
    HashMap<Integer, Integer> counts;
    HashMap<Integer, LinkedHashSet<Integer>> lists;
    int capacity;
    int min = -1;

    /**
     * 测试 put(1) put(2) put(3), get(1), get(2), get(3)
     *
     * @param capacity
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!vals.containsKey(key))
            return -1;

        int count = counts.get(key);
        counts.put(key, count + 1);

        //update
        lists.get(count).remove(key);
        if (count == min && lists.get(count).size() == 0)
            min++;

        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());

        //reinsert
        lists.get(count + 1).add(key);
        return vals.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0)
            return;
        // not first time being inserted
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        }
        // capacity overflow
        if (vals.size() >= capacity) {
            int evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            vals.remove(evit);
        }
        // first time being inserted
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}
