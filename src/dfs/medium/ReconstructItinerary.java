package dfs.medium;

import java.util.*;

/**
 * Created by xu on 24/08/2017.
 * 332. Reconstruct Itinerary
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 * <p>
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that
 * has the smallest lexical order
 * when read as a single string. For example, the itinerary ["JFK", "LGA"]
 * has a smaller lexical order than
 * ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * <p>
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * <p>
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * <p>
 * Another possible reconstruction is ["JFK","SFO","ATL","J77FK","ATL","SFO"].
 * But it is larger in lexical order.
 */
public class ReconstructItinerary {

    // 字符串大小比较String compareTo会自己比较并返回一个整数
    // 参考：http://blog.csdn.net/Evan123mg/article/details/46595319
    private Map<String, PriorityQueue<String>> flight;
    private LinkedList<String> route;

    public List<String> findItinerary(String[][] tickets) {
        flight = new HashMap<>();
        route = new LinkedList<>();

        for (String[] ticket : tickets) {
            flight.putIfAbsent(ticket[0], new PriorityQueue<>());
//            flight.put(ticket[0], flight.get(ticket[0]).add(ticket[1]));
            flight.get(ticket[0]).add(ticket[1]);
        }

        visit("JFK");
        return route;
    }

    public void visit(String departure) {
        PriorityQueue<String> arrivals = flight.get(departure);

        while (arrivals != null && !arrivals.isEmpty())
            visit(arrivals.poll());//清除flight数中尝试过的边
        route.addFirst(departure);//在栈顶添加元素，无路可走的节点

//        list.addFirst("A"); //栈
//        list.addFirst("B");
//        for (String a:list)
//            System.out.println(a); // "B" "A"


    }
}
