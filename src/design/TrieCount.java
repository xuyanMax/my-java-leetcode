package design;

import java.util.List;

public class TrieCount {
    TrieMap<Integer> map = new TrieMap<>();

    //可以重复加入相同的key，对应的val代表添加次数
    public void insert(String key) {
        if (map.containsKey(key))
            map.put(key, map.get(key));
        map.put(key, 1);
    }

    public int countWordsEqualTo(String word) {
        Integer count = map.get(word);
        return count == null ? 0 : count;
    }

    public int countWordsStartsWith(String word) {
        List<String> keys = map.keysWithPrefix(word);
        int sum = 0;
        for (String key : keys)
            sum += map.get(key);
        return sum;
    }

    public void eraseKey(String key) {
        if (!map.containsKey(key))
            return;
        int freq = map.get(key);
        if (freq == 1) map.remove(key);
        map.put(key, freq - 1);
    }

}
