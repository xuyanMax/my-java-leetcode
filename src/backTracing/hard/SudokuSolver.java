package backTracing.hard;

/**
 * @author xu
 * <p>
 * references:
 * <p>
 * http://www.geeksforgeeks.org/backtracking-set-7-suduku/
 * https://www.youtube.com/watch?v=QI0diwmx3OY
 * https://leetcode.com/problems/sudoku-solver/#/description
 */
public class SudokuSolver {

    public static void main(String[] args) {
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
        start(stringArr);

    }

    static void start(String[] stringArr) {
        char[][] board = new char[9][9];

        for (int num = 0; num < stringArr.length; num++)
            board[num] = stringArr[num].toCharArray();

        solveSudoku(board);
        printCharArr(board);

    }

    static void printCharArr(char[][] board) {
        for (char[] char1d : board) {
            for (char c : char1d)
                System.out.print(c);
            System.out.println("");
        }
    }

    // algorithm
    static void solveSudoku(char[][] board) {

        if (board == null || board.length == 0)
            return;
        solve(board);
    }

    /* This function finds an entry in grid that is still unassigned*/
    static boolean findUnassignedLocation(int[][] board) {

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == 0)
                    return true;
        return false;
    }

    static boolean solve(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board))
                                return true;
                            else
                                board[i][j] = '.'; // unmake
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isValid(char[][] board, int row, int col, char c) {

        int blockRow = row - row % 3; // 或者(row/3) *3
        int blockCol = col - col % 3;
        for (int i = 0; i < 9; i++)
            if (board[i][col] == c || board[row][i] == c || board[blockRow + i / 3][blockCol + i % 3] == c)
                return false;

        return true;
    }

}
