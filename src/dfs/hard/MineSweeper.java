package dfs.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xu on 09/08/2017.
 529. Minesweeper
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine,
'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent
(above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are
adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
return the board after revealing this position according to the following rules:

    If a mine ('M') is revealed, then the game is over - change it to 'X'.

    If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all
of its adjacent UNREVEALED squares should be revealed recursively.

    If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8')
representing the number of adjacent mines.

Return the board when no more squares will be revealed.

 Example 1:
 Input:

 [['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

 Click : [3,0]

 Output:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Example 2:
 Input:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Click : [1,2]

 Output:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
*/
public class MineSweeper {

// it is a typical search problem use either visit or bfs
// search rules
// 1. if click on a mine('M'), mark it as 'X' and stop further search
// 2. click on an empty cell ('E'), depends on how having or not having adjacent mines
//       2.1 Yes. mark it with the number of adjacent mines
//       2.2 No. mark it 'B', and do further search (8 neighbors)

    public char[][] updateBoard(char[][] board, int[] click) {
        //
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click);
        return board;
    }

    public void dfs(char[][] board, int[] pos) {
        int row = pos[0];
        int col = pos[1];

        //计算该点周围有没有mine，有多少mine
        int count= 0;

        for (int i=-1; i<2; i++) {
            for (int j=-1; j<2; j++) {
                if (i == 0 && j == 0)
                    continue;
                int r = row + i, c = col + j;
                //边界
                if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
                    continue;
                //累加mine个数
                if (board[r][c] == 'M')
                    count++;

            }
        }
        // 递归终止条件2.1
        if (count > 0) {
            board[row][col] = (char) (count + '0');
            return;
        }
        else{ // continue DFS to adjacent cells
            board[row][col] = 'B'; // mark it as revealed blank

            //递归终止条件2.2 search超越二维数组边界
            for (int i=-1; i<2; i++) {
                for (int j=-1; j<2; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    int r = row + i, c = col + j;
                    //边界
                    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
                        continue;
                    // 在对已经定义为'B'的cell周边的cell进行探索时的条件如下
                    // 只会对标为'E'的cell进行探索：'E'代表 unrevealed empty mine
                    // 因为'B'意味着: revealed blank mine
                    // 'M' 意味: unrevealed mine
                    if (board[r][c] == 'E')
                        dfs(board, new int[]{r,c});
                }
            }
        }

    }

}
