package graph;

import java.util.*;

/*
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w),
where u is the source node, v is the target node, and w is the time it takes
for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for
all nodes to receive the signal? If it is impossible, return -1.

*/
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times == null || times.length == 0) return 0;
        // Map<start, <end, distance>>
        Map<Integer, Map<Integer, Integer>> distMap = new HashMap<>();
        // [dist, node]
        Queue<int[]> priorityQueue = new PriorityQueue<>((a,b)->(a[0]-b[0]));
        for (int[] time : times){
            int curr = time[0], end = time[1], delay = time[2];
            if (!distMap.containsKey(curr)){
                distMap.put(curr, new HashMap<>());
            }
            distMap.get(curr).put(end, delay);
        }
        priorityQueue.add(new int[]{0, K});
        int[] distance = new int[N+1];// <node, distance>
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K] = 0;

        Set<Integer> visited = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            int[] curr = priorityQueue.poll();

            // check if visited or not
            if (visited.contains(curr[1]))
                continue;

            visited.add(curr[1]);

            Map<Integer, Integer> adj = distMap.getOrDefault(curr[1], new HashMap<>());

            for (Map.Entry<Integer, Integer> entry : adj.entrySet()){

                if (!visited.contains(entry.getKey()) &&
                        distance[entry.getKey()] > curr[0] + entry.getValue()){
                    distance[entry.getKey()]= curr[0] + entry.getValue();
                }
                priorityQueue.add(new int[]{distance[entry.getKey()], entry.getKey()});
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i=1; i<distance.length; i++){
            max = Math.max(max, distance[i]);
        }
        return max == Integer.MAX_VALUE?-1:max;

    }
}
