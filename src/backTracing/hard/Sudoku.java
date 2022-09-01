package backTracing.hard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
    //O(9^M)

    /**
     * 算法的核心思路非就是对每一个空着的格子穷举 1 到 9，如果遇到不合法的数字（在同一行或同一列或同一个 3×3 的区域中存在相同的数字）则跳过，如果找到一个合法的数字，则继续穷举下一个空格子
     * 如果给定的数字越少，相当于给出的约束条件越少，对于计算机这种穷举策略来说，是更容易进行下去，而不容易走回头路进行回溯的，所以说如果仅仅找出一个可行解，这种情况下穷举的速度反而比较快。
     */

    public List<List<String>> sudoku(char[][] board) {
        if (board == null || board.length == 0) return null;

        List<List<String>> res = new ArrayList<>();
        sudoku_dfs(board, 0, 0, res);
        return res;
    }

    public void sudoku_dfs(char[][] board, int row, int col, List<List<String>> res) {

        // reached to the LAST column, then move to the beginning of NEXT row
        if (col == board[0].length) {
            sudoku_dfs(board, row + 1, 0, res);
            return;
        }
        // reached the entire board
        if (row == board.length) {
            // add board to final result
            res.add(construct(board));
            return;
        }
        // current place has default value, we just jump over to the next column.
        if (board[row][col] != '.') {
            sudoku_dfs(board, row, col + 1, res);
            return;
        }

        for (char c = '1'; c <= '9'; c++) {
            if (!isValid(board, row, col, c))
                continue;
            board[row][col] = c;
            sudoku_dfs(board, row, col + 1, res);
            //put back.
            board[row][col] = '.';

        }

    }

    // 在同一行或同一列或同一个 3×3 的区域中存在相同的数字
    // 判断 board[r][c] 是否可以填入 ch
    public boolean isValid(char[][] board, int row, int col, char ch) {
        //row
        //col
        //3*3 box
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == ch) return false;
            if (board[row][i] == ch) return false;
            if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == ch) return false;
        }
        return true;
    }

    public List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] chars : board)
            res.add(String.valueOf(chars));


        return res;
    }

    @Test
    public void test_dfs() {
        String[] stringArr = new String[]{
                "..9748...",
                "7........",
                ".2.1.9...",
                "..7...24.",
                ".64.1.59.",
                ".98...3..",
                "...8.3.2.",
                "........6",
                "...2759.."
        };


        char[][] board = new char[9][9];
        int i = 0;
        for (String row : stringArr)
            board[i++] = row.toCharArray();

        List<List<String>> res = sudoku(board);
        res.forEach(e -> System.out.println(e));

//        for (char[] ch : board)
//            Arrays.fill(ch, '.');
//        res = sudoku(board);
//        res.forEach(e -> System.out.println(e));

    }
}
