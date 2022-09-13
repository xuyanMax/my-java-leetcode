package design;

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
    // 记录 LFU 缓存的最大容量
    int capacity;
    // 记录最小的频次
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
        // 增加 key 对应的 freq
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
        int freq = keyToFreq.get(key);
        //在三个哈希表中 - 移除旧key对应的frequency
        //更新FK
        freqToKeys.get(freq).remove(key);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        //更新KF
        keyToFreq.put(key, freq + 1);

        // 如果 freq 对应的列表空了，移除这个 freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            //更新minFreq
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }

    public void put(int key, int value) {
        if (capacity <= 0)
            return;
        ///* 若 key 已存在，修改对应的 val 即可 */
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
//            get(key);
            increaseFreq(key);
            return;
        }
        /* key 不存在，需要插入 */
        /* 容量已满的话需要淘汰一个 freq 最小的 key */
        if (keyToVal.size() >= capacity) {
//            int evit = freqToKeys.get(minFreq).iterator().next();
//            freqToKeys.get(minFreq).remove(evit);
//            keyToVal.remove(evit);
            removeMinFreqKey();
        }
        // first time being inserted
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        this.minFreq = 1;
    }

    public void removeMinFreqKey() {
        //更新三个哈希表，一个哈希链表
        // freq 最小的 key 列表
        LinkedHashSet<Integer> minFreqList = freqToKeys.get(minFreq);
        // 其中最先被插入的那个 key 就是该被淘汰的 key
        Integer deletedKey = minFreqList.iterator().next();

        minFreqList.remove(deletedKey);
        /**
         * 但是有个细节问题，如果keyList中只有一个元素，那么删除之后minFreq对应的key列表就为空了，也就是minFreq变量需要被更新。
         * 如何计算当前的minFreq是多少呢？实际上没办法快速计算minFreq，只能线性遍历FK表或者KF表来计算，这样肯定不能保证 O(1) 的时间复杂度。
         * 但是，其实这里没必要更新minFreq变量，因为你想想removeMinFreqKey这个函数是在什么时候调用？在put方法中插入新key时可能调用。
         * 而你回头看put的代码，插入新key时一定会把minFreq更新成 1，所以说即便这里minFreq变了，我们也不需要管它。
         */
        if (minFreqList.isEmpty()) {
            freqToKeys.remove(minFreq);
        }


        keyToVal.remove(deletedKey);
        keyToFreq.remove(deletedKey);
    }
}
