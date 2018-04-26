package dynamic.hard;
/*
* 174. Dungeon Game

The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
The dungeon consists of M x N rooms laid out in a 2D grid.
Our valiant knight (K) was initially positioned in the top-left room
and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer.
If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers)
upon entering these rooms; other rooms are either empty (0's) or contain magic orbs
that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible,
the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight
must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
* */
public class DungeonGame {
/*
*
Now the question become to how to create a health array using dungeon.

dungeon

-2,-3,3
-5,-10,1
10,30,-5
From the Dungeon grid, we can simply compute health for the [last row][last column].

Now we get

?,?,?
?,?,?
?,?,6
Now because the knight can only move rightward or downward in each step,

we CAN compute all the health value for last row from right to left
using its rightward neighbor. we can also compute all the health value
for last column from bottom to up using its downward neighbor.

?,?,2
?,?,5
1,1,6
Now, we can compute all the health value using its downward neighbor
and rightward neighbor(we use the min value of these 2 health value).

7,5,2
6,11,5
1,1,6
Now we get the answer [0][0], which is 7.
* */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon==null || dungeon.length==0) return 0;
        int m = dungeon.length, n =dungeon[0].length;
        int[][] dp = new int[m][n];
        // 如果当前dungeon[i][j]为负数，要消耗health值，因此至少此处为1+abs(dungeon[i][j])
        //如果为正，增加health值，最小health point 为1即可
        dp[m-1][n-1] = Math.max(1, 1 - dungeon[m-1][n-1]);// 1 代表存活过dp[m-1][n-1]后的最小health point

        // 最右侧纵向 初始化
        for(int i=m-2; i>=0; i--)
            dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1]);

        // 底部横向 初始化
        for (int j=n-1; j>=0; j--)
            dp[m-1][j] = Math.max(1, dp[m-1][j+1] - dungeon[m-1][j]);
        for (int i=m-2; i>=0; i--){
            for (int j=n-2; j>=0; j--){
                dp[i][j] = Math.min(
                        Math.max(1, dp[i+1][j] - dungeon[i][j]),
                        Math.max(1, dp[i][j+1] - dungeon[i][j])
                );
            }
        }
        return dp[0][0];
    }
}
