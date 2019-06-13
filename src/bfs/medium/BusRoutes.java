package bfs.medium;

import java.util.*;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
 * For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels
 * in the sequence 1->5->7->1->5->7->1->... forever.
 * <p>
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T.
 * Travelling by buses only, what is the least number of buses we must take to reach our destination?
 * Return -1 if it is not possible.
 * <p>
 * Example:
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Note:
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 */
public class BusRoutes {
    //If we regard bus, i.e., route as a node,
    // the problem will be a shortest path problem which can be solved by BFS.
    public int numBusesToDestination(int[][] routes, int S, int T) {

        if (S == T) {
            return 0;
        }
        int N = routes.length;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Arrays.sort(routes[i]); // sorted for Binary Search afterwards
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isConnected(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Bus> q = new LinkedList<>();
        Set<Integer> targets = new HashSet<>();

        for (int i = 0; i < N; i++) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                visited.add(i);
                q.add(new Bus(i, 0));//mark possible staring station of routes, level=0
            }
            if (Arrays.binarySearch(routes[i], T) >= 0) {
                targets.add(i);
            }
        }

        while (!q.isEmpty()) {
            Bus tmpBus = q.poll();
            if (targets.contains(tmpBus.busNum)) {
                return tmpBus.level + 1;
            }
            for (int neighBusNum : graph.get(tmpBus.busNum)) {
                if (!visited.contains(neighBusNum)) {
                    visited.add(neighBusNum);
                    q.add(new Bus(neighBusNum, tmpBus.level + 1));
                }
            }
        }

        return -1;
    }

    /**
     * Utility Method to check if two routes(buses) are connected
     *
     * @param route1
     * @param route2
     * @return true if route1 and route2 share at least one element
     */
    private boolean isConnected(int[] route1, int[] route2) {
        int i1 = 0, i2 = 0;
        while (i1 < route1.length && i2 < route2.length) {
            if (route1[i1] == route2[i2]) {
                return true;
            } else if (route1[i1] < route2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }
        return false;
    }

    class Bus {

        int busNum;
        int level;

        public Bus(int busNum, int level) {
            this.busNum = busNum;
            this.level = level;
        }
    }
}
