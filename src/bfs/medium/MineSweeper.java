package bfs.medium;

import java.util.LinkedList;
import java.util.Queue;
/*
* 529. Minesweeper
*
* */
public class MineSweeper {
    // BFS
    public char[][] updateBoard_bfs(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        // 进入queue中的cell，要么被标记为'B'，要么被标记为'数字'
        while (!queue.isEmpty()) {
            int[] curr_cell = queue.poll();
            int count = 0;
            for (int i=-1; i<2; i++) {
                for (int j=-1; j<2; j++) {
                    if (i==0 && j==0)
                        continue;
                    int r = curr_cell[0] + i, c = curr_cell[1] + j;
                    //边界
                    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
                        continue;
                    if (board[r][c] == 'M')
                        count++;//统计
                }
            }
            if (count > 0)//更新周围炸弹计数
                board[curr_cell[0]][curr_cell[1]] = (char) (count + '0');
            else { // 周围没有炸弹。re BFS to its adjacent cells
                //标记当前cell为'B'
                board[curr_cell[0]][curr_cell[1]] = 'B';
                for (int i=-1; i<2; i++) {
                    for (int j=-1; j<2; j++) {
                        if (i==0 && j==0)
                            continue;
                        int r = curr_cell[0] + i, c = curr_cell[1] + j;
                        //边界
                        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
                            continue;
                        if (board[r][c] == 'E') {//添加'E' 未走过的cell，走过的cell全部被标记过
                            // 避免被重复加入到queue中，当取出该cell时，
                            // 计算count，如果大于0，数字覆盖原来的'B'
                            // 否则，被再次标记为'B'
                            board[r][c] = 'B';
                            queue.add(new int[]{r,c});
                        }
                    } // for
                }// for
            }


        }

        return board;

    }

}
