package backTracing.hard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author xu
 * <p>
 * printing all possible solutions
 * <p>
 * http://www.geeksforgeeks.org/printing-solutions-n-queen-problem/
 */
public class NQueens_PrintAll {

    public static class Array2D {
        private int[][] arr;

        Array2D(int[][] array) {
            arr = new int[array.length][array[0].length];
            for (int i = 0; i < arr.length; i++)
                for (int j = 0; j < array[0].length; j++)
                    arr[i][j] = array[i][j];
        }
    }

    private static boolean done = false;

    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        List<Array2D> resultList = NQueens_PrintAll.solveNQ(board);
    }

    public static List<Array2D> solveNQ(int[][] board) {
        List<Array2D> resultList = new ArrayList<>();
        solveQNUtil(board, 0, board.length, resultList);
        System.out.println(done);
        resultList.forEach(e -> printSolution(e));

        return resultList;
    }

    /*A utility function to solve N queens problem*/
    public static void solveQNUtil(int[][] board, int row, int N, List<Array2D> resultList) {

        if (row >= N) {
            resultList.add(new Array2D(board));
            done = true;
        }

        /* Consider this row and try placing this queen in all columns one by one*/
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                /*place this queen on board[row][col]*/
                board[row][col] = 1;

                /*recur to place the rest of queens on next..next.. row*/
                solveQNUtil(board, row + 1, N, resultList);

                // below commented statement is replaced
                // by above one, the below one will only return one solution..
	            /* if ( solveNQUtil(board, row + 1) )
	                 return true;*/

                /* unmake the queen from that place, under two scenarios
                 * 1. placing queen on board[row][col] doesn't lead to a solution, then remove the queen
                 * 2. placing queen on board[row][col] has obtained a solution, and then continue the trials on next columns
                 * */
                board[row][col] = 0;
            }

        }
        /* if queen cannot be placed on any columns is this row, return false*/
    }

    public static boolean isSafe(int[][] board, int row, int col) {
        /* check this column on upper side*/
        for (int r = 0; r < row; r++)
            if (board[r][col] == 1)
                return false;


        /* check left upper diagonal "\" */
        for (int c = col, r = row; c >= 0 && r >= 0; c--, r--)
            if (board[r][c] == 1)
                return false;


        /* check right upper diagonal "/" */
        for (int c = col, r = row; r >= 0 && c < board.length; c++, r--)
            if (board[r][c] == 1)
                return false;

        return true;
    }

    public static void printSolution(Array2D board) {
        int[][] arr2d = board.arr;
        for (int[] ints : arr2d) {
            for (int i : ints)
                System.out.print(i + " ");
            System.out.println("");
        }
        System.out.println();
    }

    // number of ways to place Queens board.
    int count = 0;

    public void NQueen_board_char_DFS_BY_ROW(int n) {

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<List<String>>();
        dfs_by_row(0, board, res);
//        res.forEach(e -> System.out.println(e+"\n\n"));
        System.out.println(count);
    }

    public void dfs_by_row(int row, char[][] board, List<List<String>> res) {
        if (row == board.length) {
            res.add(construct(board));
            count++;
            return;
        }

        // for this row only
        for (int col = 0; col < board[0].length; col++) {
            if (validate_2d_array(board, row, col)) {
                // place a Queen on a valid position
                board[row][col] = 'Q';
                dfs_by_row(row + 1, board, res);
                board[row][col] = '.';
            }
        }
    }

    // validate a potential Queen position [ROW, COL]
    public boolean validate_2d_array(char[][] board, int ROW, int COL) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // if current [i,j] is a Queen, checking its same cols, diagonals
                if (board[i][j] == 'Q' && (COL == j || i + j == ROW + COL || i - j == ROW - COL))
                    return false;
            }
        }
        return true;
    }

    public List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] chars : board) {
            res.add(String.valueOf(chars));
            System.out.println("rows - " + String.valueOf(chars));
        }

        return res;
    }

    @Test
    public void test_NQueen_board_char_DFS_BY_ROW() {
        NQueen_board_char_DFS_BY_ROW(4);
    }

    /**
     * return the total number of NQueen
     * don't need to actually place the queen,
     * instead, for each row, try to place without violation on
     * col/ diagonal1/ diagonal2.
     * trick: to detect whether 2 positions sit on the same diagnol:
     * if delta(col, row) equals, same diagonal1;
     * if sum(col, row) equals, same diagonal2.
     */

    private int set_count = 0;//total counts
    private final Set<Integer> occupiedCols = new HashSet<>();
    private final Set<Integer> occupied_diagonals1 = new HashSet<>();
    private final Set<Integer> occupied_diagonals2 = new HashSet<>();

    public void totalQueens_Set(int n) {
        dfs_Queens_set(0, n);
        //total counts
        System.out.println(set_count);
    }

    public void dfs_Queens_set(int row, int N) {
        if (row == N) {
            set_count++;
            return;
        }

        for (int c = 0; c < N; c++) {
            int diagonal1 = row + c, diagonal2 = row - c;
            if (occupied_diagonals1.contains(diagonal1)) continue;
            if (occupied_diagonals2.contains(diagonal2)) continue;

            occupied_diagonals1.add(diagonal1);
            occupied_diagonals2.add(diagonal2);
            dfs_Queens_set(row + 1, N);

            occupied_diagonals1.remove(diagonal1);
            occupied_diagonals2.remove(diagonal2);
        }

    }

    private int count_array = 0;

    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[n * 2];
        boolean[] diag2 = new boolean[n * 2];

        helper(n, 0, cols, diag1, diag2);
        return count;
    }

    public void helper(int n, int row, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) count_array++;
        for (int i = 0; i < n; i++) {
            int d1 = row - i + n;
            int d2 = row + i;
            if (cols[i] || diag1[d1] || diag2[d2]) continue;
            cols[i] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            helper(n, row + 1, cols, diag1, diag2);
            cols[i] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

}
