package bfs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xu on 19/08/2017.
 */
/*
Given a 2D board containing 'X' and 'O' (the LETTER!!!!!! O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

*/
public class SurroundedRegions {
    public final int[][] dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

     public static void main(String[] args){
         SurroundedRegions inst = new SurroundedRegions();
         char[][] board = new char[][]{{'X','X','X','X'},
                 {'X','O','O','X'},
                 {'X','X','O','X'},
                 {'X','O','X','X'}

         };
         inst.solve(board);
         for (char[] chars : board) {
             for (char ch : chars)
                 System.out.print(ch + " ");
             System.out.println("");
         }
     }
    public void solve(char[][] board) {

        if (board == null || board.length == 0)
            return;

        // 废弃！初始化 将'0'的cell加入queue
        // 初始化，将四条边界上的'0'以及与之相连通的'0'利用bfs标记为'B'，如下所示
        // 最后，将与边界隔绝的'0'->'X'; 将'B'->'0'即可

//        X X X X           X X X X             X X X X
//        X X O X  ->       X X O X    ->       X X X X
//        X O X X           X B X X             X O X X
//        X O X X           X B X X             X O X X

        // 对四条边界处理'0'->'B'
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == 'O')
                bfsBoundary(board, row, 0);//第一列
            if (board[row][board[0].length-1] == 'O')
                bfsBoundary(board, row, board[0].length-1);//最后一列
        }
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == 'O')
                bfsBoundary(board, 0, col);//第一行
            if (board[board.length-1][col] == 'O')
                bfsBoundary(board, board.length-1, col);//最后一行
        }
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'B')
                    board[i][j] = 'O';
            }
        }
    }
    public void bfsBoundary(char[][] board, int x, int y) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            board[curr[0]][curr[1]] = 'B';
            for (int[] dir:dirs) {
                int adj_x = dir[0] + curr[0];
                int adj_y = dir[1] + curr[1];
                if (adj_x >= 0 && adj_x < board.length && adj_y >= 0
                        && adj_y < board[0].length
                        && board[adj_x][adj_y] == 'O') {
                    board[adj_x][adj_y] = 'B';
                    queue.add(new int[]{adj_x, adj_y});
                }
            }


        }
    }
}
