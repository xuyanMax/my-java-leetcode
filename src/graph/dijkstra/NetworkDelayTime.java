package graph.dijkstra;

import java.util.*;

/**
 * There are N network nodes, labelled 1 to N.
 * <p>
 * Given times, a list of travel times as directed edges times[i] = (u, v, w),
 * where u is the source node, v is the target node, and w is the time it takes
 * for a signal to travel from source to target.
 * <p>
 * Now, we send a signal from a certain node K. How long will it take for
 * ALL nodes to receive the signal? If it is impossible, return -1.
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] travel_times, int N, int K) {
        if (travel_times == null || travel_times.length == 0) return 0;

        /**prepare work**/
        // Map<start, <end, distance>>
        Map<Integer, Map<Integer, Integer>> start2Edges = new HashMap<>();
        // [distance, node]
        Queue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for (int[] time : travel_times) {
            int curr = time[0], end = time[1], delay = time[2];
            if (!start2Edges.containsKey(curr))
                start2Edges.put(curr, new HashMap<>());
            start2Edges.get(curr).put(end, delay);
        }
        priorityQueue.add(new int[]{0, K});
        int[] distance = new int[N + 1];// <node, distance>
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K] = 0;

        Set<Integer> visited = new HashSet<>();
        /*prepare work done*/
        while (!priorityQueue.isEmpty()) {
            int[] curr = priorityQueue.poll();

            // check if visited or not
            if (visited.contains(curr[1]))
                continue;
            visited.add(curr[1]);

            Map<Integer, Integer> adj = start2Edges.getOrDefault(curr[1], new HashMap<>());
            for (Map.Entry<Integer, Integer> entry : adj.entrySet()) {
                if (!visited.contains(entry.getKey()) &&
                        distance[entry.getKey()] > curr[0] + entry.getValue())
                    distance[entry.getKey()] = curr[0] + entry.getValue();

                priorityQueue.add(new int[]{distance[entry.getKey()], entry.getKey()});
            }
        }
        OptionalInt max = Arrays.stream(distance).reduce(Integer::max);
        return max.getAsInt() == Integer.MAX_VALUE ? -1 : max.getAsInt();

    }

    public int networkDelayTime2(int[][] travel_times, int N, int SRC) {
        List<int[]>[] graph = new LinkedList[N + 1];
        for (int i = 1; i <= travel_times.length; i++)
            graph[i] = new LinkedList<>();
        for (int[] edge : travel_times) {
            int from = edge[0], to = edge[1], weight = edge[2];
            // create adjacent tables to store edges info
            graph[from].add(new int[]{to, weight});
        }
        int[] distToEnd = dijkstra(SRC, graph);


        int res = 0;
        for (int i = 1; i < N + 1; i++) {
            if (distToEnd[i] == Integer.MAX_VALUE)
                return -1;
            res = Math.max(res, distToEnd[i]);
        }
        return res;
    }

    // input a start, calculate the shortest distance among other nodes to start.
    public int[] dijkstra(int start, List<int[]>[] graph) {
        // distToEnd[i] = distance between start and node graph[i]
        int[] distToEnd = new int[graph.length];
        Arrays.fill(distToEnd, Integer.MAX_VALUE);
        distToEnd[start] = 0;
        Queue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distFromStart));
        pq.add(new State(start, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int currId = curr.id;
            int currDistFromStart = curr.distFromStart;

            if (currDistFromStart > distToEnd[currId])
                continue;

            // add adjacent nodes to pq
            for (int[] edge : graph[currId]) {
                int nextTo = edge[0];
                int nextDist = edge[1];
                if (distToEnd[nextTo] > nextDist + currDistFromStart) {
                    distToEnd[nextTo] = nextDist + currDistFromStart;
                    pq.add(new State(nextTo, distToEnd[nextTo]));
                }
//                pq.add(new State(nextTo, distToEnd[nextTo]));
            }
        }

        return distToEnd;
    }

    class State {
        int id;
        int distFromStart;

        public State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }

    }
}
