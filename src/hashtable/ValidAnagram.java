package hashtable;

public class ValidAnagram {
    boolean solution(String s, String p) {
        if (s.length() != p.length()) return false;
        int[] hash = new int[26];
        for (char c : s.toCharArray())
            hash[c - 'a']++;
        for (char c : p.toCharArray()) {
            hash[c - 'a']--;
            if (hash[c - 'a'] < 0) return false;
        }
        for (int n : hash)
            if (n != 0) return false;

        return true;
    }
}
