package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*787. Cheapest Flights Within K Stops

    There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

    Now given all the cities and fights, together with starting city src and the destination dst,
    your task is to find the cheapest price from src to dst with up to k stops.
    If there is no such route, output -1.

    Example 1:
    Input:
    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    src = 0, dst = 2, k = 1
    Output: 200
    Explanation:
    The graph looks like this:


    The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
    Example 2:
    Input:
    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
    src = 0, dst = 2, k = 0
    Output: 500
    Explanation:
    The graph looks like this:


    The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
    Note:

    The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
    The size of flights will be in range [0, n * (n - 1) / 2].
    The format of each flight will be (src, dst, price).
    The price of each flight will be in the range [1, 10000].
    k is in the range of [0, n - 1].
    There will not be any duplicated flights or self cycles.
    */
public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // <src, <dst, price>>
        Map<Integer, Map<Integer,Integer>> prices = new HashMap<>();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b)->(a[0]-b[0]));

        priorityQueue.add(new int[]{0, src, K});

        for (int[] flight:flights) {
            if (!prices.containsKey(flight[0]))
                prices.put(flight[0], new HashMap<>());
            prices.get(flight[0]).put(flight[1], flight[2]);
        }
        while (!priorityQueue.isEmpty()) {
            int[] curr = priorityQueue.poll();
            int price = curr[0];
            int city = curr[1];
            int stop = curr[2];
            if (city == dst) return price;
            if (stop>=0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (Map.Entry<Integer, Integer> entry:adj.entrySet())
                    priorityQueue.add(new int[]{price + entry.getValue(), entry.getKey(), stop - 1 });
            }

        }
        return -1;

    }
    // bellman-ford
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;
        for (int i=0; i<=K; i++){
            int[] copy = Arrays.copyOf(prices, prices.length);
            for (int[] flight:flights) {
                int curr = flight[0], next = flight[1], price = flight[2];
                if(prices[curr] == Integer.MAX_VALUE)
                    continue;
                copy[next] = Math.min(prices[curr] + price, copy[next]);
            }
            prices = copy;
        }
        return prices[dst]==Integer.MAX_VALUE?-1:prices[dst];
    }
}
