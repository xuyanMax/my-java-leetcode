package design;

import java.util.List;

public class TrieSet {
    //底层用一个TrieMap，键就是TrieSet
    private final TrieMap<Object> map = new TrieMap<>();

    public void add(String key) {
        map.put(key, new Object());
    }

    public void remove(String key) {
        map.remove(key);
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public String shortestPrefix(String query) {
        return map.shortestPrefix(query);
    }

    public String longestPrefix(String query) {
        return map.longestPrefix(query);
    }

    public List<String> keysWithPrefix(String prefix) {
        map.keysWithPrefix(prefix);
        map.hasKeyWithPattern(prefix);
        map.size();
        return map.keysWithPrefix(prefix);
    }

    public boolean hasKeyWithPattern(String pattern) {
        return map.hasKeyWithPattern(pattern);
    }

    public List<String> keysWithPattern(String pattern) {
        return map.keysWithPattern(pattern);
    }

    public int size() {
        return map.size();
    }


}
