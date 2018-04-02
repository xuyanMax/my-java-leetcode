package backTracing.hard;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author xu
 * 
 * printing all possible solutions 
 * 
 * http://www.geeksforgeeks.org/printing-solutions-n-queen-problem/
 * 
 */
public class NQueens2 {

	static class Array2D{
		int[][] arr;
		Array2D (int[][] array){
			arr = array;
		}
	}
	public static void main(String[] args) {
		int[][] board = new int[][] {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
		
		List<Array2D> resultList = NQueens2.solveNQ(board);
		
		for (Array2D arr:resultList) {

			printSolution(arr);
		}
 		
	}
	public static List<Array2D> solveNQ(int[][] board){
		List<Array2D> resultList = new ArrayList<>(); 
		solveQNUtil(board, 0, board.length, resultList);
		
		return resultList;
	}
	/*A utility function to solve N queens problem*/
	public static boolean solveQNUtil(int[][] board, int row, int N, List<Array2D> resultList){
		
		if (row>=N) {
			
			resultList.add(new Array2D(board));		
			printSolution(new Array2D(board));
			return true; // just "return", "true" useless 
		}
		
		/* Consider this row and try placing this queen in all columns one by one*/
		for (int col=0; col<N; col++ ){
			if (isSafe(board, row, col)){
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
		return false;
	}
	public static boolean isSafe(int[][] board, int row, int col) {
		/* check this column on upper side*/
		for (int r=0; r<row; r++) {
			if (board[r][col] == 1)
				return false;
		}
		
		/* check left upper diagonal "\" */
		for (int c=col, r= row; c>=0 && r>=0; c--,r--) {
			if (board[r][c]==1)
				return false;
		}
		
		/* check right upper diagonal "/" */
		for (int c=col, r=row; r>=0 && c<board.length;c++, r--) {
			if (board[r][c]==1)
				return false;
		}
		return true;
	}
	public static void printSolution(Array2D board){
		for (int i=0;i<board.arr.length;i++){
			for (int j=0;j<board.arr[0].length;j++)
				System.out.print(board.arr[i][j]+" ");
			System.out.println("");
		}
		System.out.println("");
	}
	
}
