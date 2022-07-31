package a_OA;

public class Longest_Palindrome {

    public int sol(String var) {
        if (var.length() < 2) return var.length();
        int re = 0;
        for (int i = 0; i < var.length(); i++)
            re = Math.max(getLongestPalinFromCenters(var, i, i), getLongestPalinFromCenters(var, i, i + 1));
        return re;

    }

    public int getLongestPalinFromCenters(String var, int center1, int center2) {
        while (center1 >= 0 && center2 < var.length()
                && var.charAt(center1) ==var.charAt(center2))
            center1--;center2++;

        return center2 - center1 + 2;
    }
}
