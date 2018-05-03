package string.easy;
//383. Ransom Note
//You may assume that both strings contain only lowercase letters.
//
//canConstruct("a", "b") -> false
//canConstruct("aa", "ab") -> false
//canConstruct("aa", "aab") -> true
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chars = new int[26];
        for (int i=0; i<magazine.length(); i++) {
            chars[magazine.charAt(i) - 'a']++;
        }
        for (int i=0; i<ransomNote.length(); i++) {
            if (--chars[ransomNote.charAt(i) -'a'] < 0)
                return false;
        }
        return true;
    }
}
