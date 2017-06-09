package BackTracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author xu
 * 
 * Given N*N board, place N queens on this board so that they don't attack each other.
 * One solution is to find one placement of queens which don't attack each other.
 * The other solution finds all placements of queens on board.
 * 
 * 
 * Time complexity  - O(N!) T(N) = N*(T(N-1) + O(1))
 * Space complexity - O(N*N)
 * 
 * 
 * references:
 * http://www.geeksforgeeks.org/backtracking-set-3-N-queen-problem/
 * https://www.youtube.com/watch?v=xouin83ebxE
 * 
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/NQueenProblem.java
 *
 */
public class NQueens {
	class Position{
		int row, col;
		Position(int r, int c) {
			row = r;
			col = c;
		}
	}
	public static void main(String[] args) {
		
		NQueens nQueens = new NQueens();
		Position[] positions = nQueens.solveNQueenOneSolution(4);
		Arrays.stream(positions).forEach(position->System.out.println(position.row+" "+position.col));
		
		List<List<String>> results = nQueens.solveNQueens(8);
		System.out.println(results);
	}
	
	public Position[] solveNQueenOneSolution(int N){
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
		for (int col=0;col<N;col++) {
			boolean isSafe = true;
			
			/* check if this row and col would under attack from any one of previously placed queens */
			
			for (int queen=0;queen<row;queen++) { 
				
				/*same column || "/" diagonal (sum of col+row equals); "\" (difference of row-col equals)*/
				if (positions[queen].col==col || positions[queen].row - positions[queen].col ==row-col 
						||positions[queen].col+positions[queen].row == row+col) {
					isSafe = false;
					break;// break row column;
				}				
				
			}
			if (isSafe) {
				positions[row] = new Position(row, col);
				
				/* recur (occur repeatedly) to the rest of queens */
				if (solveNQueenOneSolutionUtil(N, row+1, positions)) 
					return true;
			}
		}
		/* if queen cannot be placed in any col in this row, then return false*/
		return false;
	}
	
	 
	 public List<List<String>> solveNQueens(int N) {
		 List<List<String>> results = new ArrayList<>();
		 Position[] positions = new Position[N];
		 solve(0, positions, results, N);
		 
		 return results;
	 }
	 public void solve(int row, Position[] positions, List<List<String>> results, int N) {
		 if (N==row) {
			 StringBuilder buff = new StringBuilder();
			 List<String> oneResult = new ArrayList<>();
			 
			 for (Position p : positions) {
				 for (int col=0; col<N; col++) {
					 if (p.col==col)
						 buff.append("Q");
					 else 
						 buff.append(".");
				 }
				 oneResult.add(buff.toString());
				 buff = new StringBuilder();
			 }
			 results.add(oneResult);
			 return;
			
		 }
		 
		 for (int col=0; col<N; col++){
			 boolean isSafe = true;
			 for (int queen=0; queen<row; queen++) {
				 if (positions[queen].col == col || positions[queen].row + positions[queen].col == col + row ||positions[queen].row - positions[queen].col == row - col) {
					 isSafe = false;
					 break;
				 }
					 
			 }
			 if (isSafe) {
				 positions[row] = new Position(row, col); // positions[row] will be overwritten.
				 solve(row+1, positions, results, N);
			 }//else un        make it
		 }
	 } 	

}
