package string.patternMatching;

/**
 * @author xu
 * <p>
 * references:
 * https://www.youtube.com/watch?v=GTJr8OvyEVQ&index=4&list=PLrmLmBdmIlpvxhscYQdvfFNWU_pdkG5de
 * http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
 */
public class KMP {
    private String pattern;
    private String text;
    private int[] next;

    public KMP(String S, String T) {
        this.pattern = T;
        this.text = S;
        this.next = new int[pattern.length()];
    }

    public void initiateNext() {
        int i = 0, j = -1;
        this.next[0] = -1;
        while (i < pattern.length() - 1) {
            if (j == -1) {
                next[++i] = ++j;
            } else if (j >= 0 && pattern.charAt(i) == pattern.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
    }

    public void displayNext() {
        for (int num : this.next) {
            System.out.println(num);
        }
    }

    public int index_KMP(int pos) {
        int i = pos;
        int j = 0;
        while (i < text.length() && j < pattern.length()) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = this.next[j];
            }
        }
        if (j >= pattern.length()) return i - pattern.length();
        else return 0;

    }

    public static void main(String[] args) {
        String S = "googagoogle";
        String T = "google";
        KMP kmp = new KMP(S, T);
        kmp.initiateNext();
        kmp.displayNext();

        System.out.println("index: " + kmp.index_KMP(0));

    }

}
