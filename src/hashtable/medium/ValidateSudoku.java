package hashtable.medium;

import java.util.HashSet;

/**
 * @Author: xyx
 * @Date: 2018-12-11 22:27
 * @Version 1.0
 * https://leetcode.com/problems/valid-sudoku/
 */
public class ValidateSudoku {
    public boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> columns = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.' && !rows.add(board[i][j]))
                    return false;
                if (board[j][i] != '.' && !columns.add(board[j][i]))
                    return false;

                int rowIndex, columnIndex;
                rowIndex = 3 * (i / 3);
                // columnIndex =3*(j%3);
                columnIndex = 3 * (i % 3);
                if (board[rowIndex + j / 3][columnIndex + j % 3] != '.'
                    && !cube.add(board[rowIndex + j / 3][columnIndex + j % 3]))
//                    if(board[columnIndex+j%3][rowIndex+j/3]!='.' && !cube.add(board[columnIndex+j%3][rowIndex+j/3]))
                    return false;
            }
        }
        return true;

    }
}
