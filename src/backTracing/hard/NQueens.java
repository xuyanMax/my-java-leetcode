package backTracing.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xu
 * <p>
 * Given N*N board, place N queens on this board so that they don't attack each other.
 * One solution is to find one placement of queens which don't attack each other.
 * The other solution finds all placements of queens on board.
 * <p>
 * <p>
 * Time complexity  - O(N!)
 * T(N) = N*(T(N-1) + O(1))
 * Space complexity - O(N*N)
 * <p>
 * <p>
 * references:
 * http://www.geeksforgeeks.org/backtracking-set-3-N-queen-problem/
 * https://www.youtube.com/watch?v=xouin83ebxE
 * <p>
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/NQueenProblem.java
 */
public class NQueens {
    class Position {
        int row, col;

        Position(int r, int c) {
            row = r;
            col = c;
        }
    }

    public static void main(String[] args) {

        NQueens nQueens = new NQueens();
        // initialize N queens as there are only N in any N*N Matrix
        Position[] positions = nQueens.solveNQueenOneSolution(4);
        // iterate over queens positions
        Arrays.stream(positions).forEach(position -> System.out.println(position.row + " " + position.col));

        List<List<String>> results = nQueens.solveNQueens(4);
        results.forEach(e -> System.out.println(e));
    }

    public Position[] solveNQueenOneSolution(int N) {
        Position[] positions = new Position[N];
        boolean hasSolution = solveNQueenOneSolutionUtil(N, 0, positions);

        if (hasSolution)
            return positions;
        else
            return new Position[0];

    }

    /* A recursive utility function to solve N
    Queen problem */
    private boolean solveNQueenOneSolutionUtil(int N, int row, Position[] positions) {
		
		/* base case: If all queens are placed
        then return true */
        if (row >= N)
            return true;

        /* Consider this row and try placing a queen in one of these columns one by one*/
        for (int col = 0; col < N; col++) {
            boolean isSafe = true;

            /* check if this row and col would under attack from any one of previously placed queens */

            for (int queen = 0; queen < row; queen++) {

                /*same column || "/" diagonal (sum of col+row equals); "\" (difference of row-col equals)*/
                if (positions[queen].col == col || positions[queen].row - positions[queen].col == row - col
                        || positions[queen].col + positions[queen].row == row + col) {
                    isSafe = false;
                    break;// break row column;
                }

            }
            if (isSafe) {
                positions[row] = new Position(row, col);

                /* recur (occur repeatedly) to the rest of queens */
                if (solveNQueenOneSolutionUtil(N, row + 1, positions)) return true;
            }
        }
        /* if queen cannot be placed in any col in this row, then return false*/
        return false;
    }


    public List<List<String>> solveNQueens(int N) {
        List<List<String>> results = new ArrayList<>();
        Position[] positions = new Position[N];
        // start from first row = 0
        solveNQueens_solve(0, positions, results, N);

        return results;
    }

    public void solveNQueens_solve(int ROW, Position[] positions, List<List<String>> results, int N) {

        if (N == ROW) {
            StringBuilder builder = new StringBuilder();
            List<String> oneResult = new ArrayList<>();

            for (Position p : positions) {
                for (int col = 0; col < N; col++) {
                    if (p.col == col)
                        builder.append("Q");
                    else
                        builder.append(".");

                    oneResult.add(builder.toString());
                    builder = new StringBuilder();
                }
                System.out.println(oneResult);
            }
            results.add(oneResult);
            return;

        }
        // for a particular ROW and compare column and diagonals
        // if delta(col, ROW) equals, same diagonal1;
        // if sum(col, ROW) equals, same diagonal2.
        for (int col = 0; col < N; col++) {
            boolean isSafe = true;
            // checking rows 0:ROW
            for (int queen = 0; queen < ROW; queen++) {
                if (positions[queen].col == col ||
                        positions[queen].row + positions[queen].col == col + ROW ||
                        positions[queen].row - positions[queen].col == ROW - col) {
                    isSafe = false;
                    break;
                }

            }
            if (isSafe) {
                positions[ROW] = new Position(ROW, col); // positions[ROW] will be overwritten.
                solveNQueens_solve(ROW + 1, positions, results, N);
            }//else unmake it
        }
    }


}
