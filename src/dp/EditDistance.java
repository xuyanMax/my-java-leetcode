package dp;

/**
 * Date 07/07/2014
 *
 * @author Tushar Roy
 * <p>
 * Given two strings how many minimum edits(updateHighestHeightBtwLR, delete or add) is needed to convert one string to another
 * <p>
 * Time complexity is O(m*n)
 * Space complexity is O(m*n)
 * <p>
 * <p>
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 * https://en.wikipedia.org/wiki/Edit_distance
 */

public class EditDistance {

    public static void main(String[] args) {

        String str1 = "azced";
        String str2 = "abcdef";

        EditDistance ed = new EditDistance();
        int resultNum = ed.bottomUpDP(str1.toCharArray(), str2.toCharArray());
        System.out.print(resultNum);
    }

    public int bottomUpDP(char[] str1, char[] str2) {
        int dp[][] = new int[str1.length + 1][str2.length + 1];

        for (int i = 0; i < dp.length; i++)
            dp[i][0] = i;
        for (int j = 0; j < dp[0].length; j++)
            dp[0][j] = j;

        for (int i = 1; i < dp.length; i++)
            for (int j = 1; j < dp[0].length; j++) {

                //a***c vs b***c => a*** vs b***
                if (str1[i - 1] == str2[j - 1]) // if two chars are equal.
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + minOfTree(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);

            }
        printActualEdits(str1, str2, dp);
        return dp[str1.length][str2.length];
    }

    public int minOfTree(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    /**
     * Prints the actual edits which needs to be done.
     */
    public void printActualEdits(char[] str1, char[] str2, int[][] dp) {
        int i = str1.length;
        int j = str2.length;
        while (true) {

            if (i == 0 || j == 0)
                break;
            if (str1[i - 1] == str2[j - 1]) {
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j - 1] + 1) {
                System.out.println("Edit: Turn " + str1[i - 1] + " in str1 to " + str2[j - 1] + " ");
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j] + 1) {
                System.out.println("Edit: Delete " + str1[i - 1] + " in str1.");
                i--;
            } else if (dp[i][j] == dp[i][j - 1] + 1) {
                System.out.println("Edit: Delete " + str2[j - 1] + " in str2.");
                j--;
            } else throw new IllegalArgumentException("Some wrong with given key.");

        }

    }

}
