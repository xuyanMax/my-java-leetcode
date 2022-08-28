package graph.dijkstra;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class dijkstra {
    // graph: List<int[]>[] ; a list of nodes which map to their adjacent nodes array:int[]
    // edge: [id, distance]
    public int[] dijkstra(int start, List<int[]>[] graph) {
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;

        Queue<State> pq = new PriorityQueue<>();
        pq.add(new State(start, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            if (curr.distFromStart > distTo[curr.id])
                continue;

            for (int[] edge : graph[curr.id]) {
                //RELAX/update adjacent nodes' distances from start
                //add it to priority queue
                if (distTo[edge[0]] > curr.distFromStart + edge[1]) {
                    distTo[edge[0]] = curr.distFromStart + edge[1];
                    pq.add(new State(edge[0], distTo[edge[0]]));
                }
            }
        }
        return distTo;
    }

    class State implements Comparable<State> {
        //node id
        int id;
        //dist from start
        int distFromStart;

        public State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }

        @Override
        public int compareTo(State o) {
            return this.distFromStart - o.distFromStart;
        }
    }
}
