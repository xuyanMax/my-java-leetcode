package graph.tree.dfs.islands;
//827. Making A Large Island

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 *
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 */

public class MakingALargeIsland {
    public int N = 0;

    public int largestIsland(int[][] grid) {
        N = grid.length;
        //DFS every island and give it an index of island
        int index = 3, res = 0;
        // store <index, area>
        HashMap<Integer, Integer> area = new HashMap<>();
        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {
                if (grid[x][y] == 1) {
                    area.put(index, dfs(grid, x, y, index));
                    res = Math.max(res, area.get(index++));
                }
            }
        }

        //traverse every 0 cell and count biggest island it can connect
        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {
                // 0 grid
                if (grid[x][y] == 0) {
                    HashSet<Integer> seen = new HashSet<>();
                    int cur = 1;
                    for (Pair<Integer, Integer> p : move(x, y)) {
                        index = grid[p.getKey()][p.getValue()];
                        // index > 1 meaning visited before
                        if (index > 1 && !seen.contains(index)) {
                            seen.add(index);
                            cur += area.get(index);
                        }
                    }
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    public List<Pair<Integer, Integer>> move(int x, int y) {
        ArrayList<Pair<Integer, Integer>> res = new ArrayList<>();
        if (valid(x, y + 1))
            res.add(new Pair<Integer, Integer>(x, y + 1));
        if (valid(x, y - 1))
            res.add(new Pair<Integer, Integer>(x, y - 1));
        if (valid(x + 1, y))
            res.add(new Pair<Integer, Integer>(x + 1, y));
        if (valid(x - 1, y))
            res.add(new Pair<Integer, Integer>(x - 1, y));
        return res;
    }

    public boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public int dfs(int[][] grid, int x, int y, int index) {
        int area = 0;
        grid[x][y] = index;
        for (Pair<Integer, Integer> p : move(x, y))
            if (grid[p.getKey()][p.getValue()] == 1)
                area += dfs(grid, p.getKey(), p.getValue(), index);
        return area + 1;
    }
}
