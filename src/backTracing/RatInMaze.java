package backTracing;

import java.util.ArrayList;
import java.util.List;

public class RatInMaze {

	public static void main(String[] args) {
		
		RatInMaze inst = new RatInMaze();
		int[][] maze = new int[][]{
			{1, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 1, 1, 0},
            {1, 1, 1, 1}};
        inst.print(maze);
//		inst.FindAWayOutMaze(maze);
        inst.FindWaysOutMaze(maze);
        
	}
	public List<int[][]> FindWaysOutMaze(int[][] maze) {
		List<int[][]> solutions = new ArrayList<>();
		int[][] sol = new int[maze.length][maze[0].length];
		for (int i = 0; i<maze.length; i++)
			for(int j=0; j<maze[0].length;j++)
				sol[i][j] = 0;
		
		FindWaysOutMazeUtil(maze, 0, 0, sol, solutions);
		printList(solutions);
		return solutions;
	}
	
	public void FindWaysOutMazeUtil(int[][] maze, int x, int y, int[][] sol, List<int[][]> solutions) {
		if (x == maze.length-1 && y == maze[0].length-1 && maze[x][y] == 1) {
			sol[x][y] = 1;
			int[][] newa = new int[sol.length][sol[0].length];
			
			for (int i = 0; i<sol.length; i++) 
				for (int j=0; j<sol[0].length; j++)
					newa[i][j] = sol[i][j];
		
			solutions.add(newa);
			return;
		}
		if (isSafe(maze, x, y)) {
			sol[x][y] = 1;
			
			FindWaysOutMazeUtil(maze, x + 1, y, sol, solutions);
			FindWaysOutMazeUtil(maze, x, y + 1, sol, solutions);
		
			sol[x][y] = 0;
		}
	}
	public int[][] FindAWayOutMaze(int[][] maze) {
		
		int[][] solution = new int[maze.length][maze[0].length];
		for (int i = 0; i<maze.length; i++)
			for(int j=0; j<maze[0].length;j++)
				solution[i][j] = 0;
		
		mazeUtil(maze, 0, 0, solution);
		print(solution);
		return solution;
	}
	public boolean mazeUtil(int[][]maze, int x, int y, int[][] solution) {
	
		if (x == maze.length-1 && y == maze[0].length-1) {
			solution[x][y] = 1;
			return true;
		}
		if (isSafe(maze, x, y)) {
			solution[x][y] = 1;
			if (mazeUtil(maze, x + 1, y, solution) )
				return true;
			if (mazeUtil(maze, x, y+1, solution))
				return true;
			
			solution[x][y] = 0;// un-make it 
			return false;
			
		}
		return false;
	}
	// check if [x,y] is a valid index for path in the maze
	public boolean isSafe(int[][]maze, int x, int y) {
		return (x >= 0 && x < maze.length && y>=0 && y<maze[0].length && maze[x][y] == 1 );
	}
	public void print(int[][] sol) {
		for (int[] row : sol) {
			for (int n : row){
				System.out.print(n);
			}
			System.out.println("");
		}
		System.out.println("");
			
	}
	public void printList(List<int[][]> solutions) {
		for (int[][] mat : solutions) 
			print(mat);
		
	}
}
