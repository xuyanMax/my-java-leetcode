package dfs.medium;

/*
* 79. Word Search
DescriptionHintsSubmissionsDiscussSolution
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/
public class WordSearch1 {
//    Here accepted solution based on recursion.
// To save memory I decided to apply bit mask for every visited cell.
// Please check board[y][x] ^= 255;

    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++)
            for (int x=0; x<board[y].length; x++)
                if (exist(board, y, x, w, 0))
                    return true;

        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        // mask it
        board[y][x] ^= 255;
        boolean exist = exist(board, y, x+1, word, i+1)
                || exist(board, y, x-1, word, i+1)
                || exist(board, y+1, x, word, i+1)
                || exist(board, y-1, x, word, i+1);
        // unmake it 255 = 1111 1111
        board[y][x] ^= 255;
        return exist;
    }
}
