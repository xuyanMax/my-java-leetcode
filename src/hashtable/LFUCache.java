package hashtable;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @Author: xyx
 * @Date: 2018-12-12 15:32
 * @Version 1.0
 */
public class LFUCache {

    //key->val 一对一
    HashMap<Integer, Integer> keyToVal;
    //key->freq 一对一
    HashMap<Integer, Integer> keyToFreq;
    //freq->keys 是一对多的关系
    //LinkedHashSet: 链表和哈希集合的结合体，具备O(1)的的访问和删除元素的能力，同时具备链表记录插入数据的时序性特点
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    int capacity;
    int minFreq = -1;

    /**
     * 测试 put(1) put(2) put(3), get(1), get(2), get(3)
     *
     * @param capacity
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        freqToKeys.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key))
            return -1;

        int count = keyToFreq.get(key);
        keyToFreq.put(key, count + 1);

        //update linkedhashset
        freqToKeys.get(count).remove(key);
        if (count == minFreq && freqToKeys.get(count).size() == 0)
            minFreq++;

        if (!freqToKeys.containsKey(count + 1))
            freqToKeys.put(count + 1, new LinkedHashSet<>());
        freqToKeys.get(count + 1).add(key);

        return keyToVal.get(key);
    }

    public void increaseFreq(int key) {
        int keyFreq = keyToFreq.get(key);
        //在三个哈希表中 - 移除旧key对应的frequency
        freqToKeys.get(keyFreq).remove(key);
        freqToKeys.putIfAbsent(keyFreq + 1, new LinkedHashSet<>());
        freqToKeys.get(keyFreq + 1).add(key);

        keyToFreq.put(key, keyFreq + 1);

        if (freqToKeys.get(keyFreq).isEmpty()) {
            freqToKeys.remove(keyFreq);

            if (keyFreq == this.minFreq) {
                this.minFreq++;
            }
        }
    }

    public void put(int key, int value) {
        if (capacity <= 0)
            return;
        // not first time being inserted
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            get(key);
            return;
        }
        // capacity overflow
        if (keyToVal.size() >= capacity) {
            int evit = freqToKeys.get(minFreq).iterator().next();
            freqToKeys.get(minFreq).remove(evit);
            keyToVal.remove(evit);
        }
        // first time being inserted
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        minFreq = 1;
        freqToKeys.get(1).add(key);
    }
    public void removeMinFreqKey() {
        //更新三个哈希表，一个哈希链表
        LinkedHashSet<Integer> minFreqList = freqToKeys.get(minFreq);
        Integer deletedKey = minFreqList.iterator().next();

        minFreqList.remove(deletedKey);
        if (minFreqList.isEmpty()) {
            freqToKeys.remove(minFreq);
        }

        keyToVal.remove(deletedKey);
        keyToFreq.remove(deletedKey);
//        minFreq++;
    }
}
