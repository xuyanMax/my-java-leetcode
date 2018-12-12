package hashtable;

/**
 * @Author: xyx
 * @Date: 2018-12-12 09:37
 * @Version 1.0
 */

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：圣小童
 * 来源：CSDN
 * 原文：https://blog.csdn.net/elricboa/article/details/78847305
 * 版权声明：本文为博主原创文章，转载请附上博文链接！}
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache_KV<K, V> {
    private static final float hashLoadFactory = 0.75f;
    private LinkedHashMap<K, V> map;
    private int cacheSize;

    public LRUCache_KV(int cacheSize) {
        this.cacheSize = cacheSize;
        int capacity = (int) Math.ceil(cacheSize / hashLoadFactory) + 1;
        map = new LinkedHashMap<K, V>(capacity, hashLoadFactory, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > LRUCache_KV.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int usedSize() {
        return map.size();
    }

    public void print() {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.print(entry.getValue() + "--");
        }
        System.out.println();
    }
}