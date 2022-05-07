package dag;

import java.util.*;

public class AllPathsFromSrc2Dest {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfsSearch(graph, 0, res, path);

        return res;
    }

    private void dfsSearch(int[][] graph, int node, List<List<Integer>> res, List<Integer> path) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int nextNode : graph[node]) {
            path.add(nextNode);
            dfsSearch(graph, nextNode, res, path);
            path.remove(path.size() - 1);
        }
    }

    //Another dfs solution is to use memorization. Each node will be only visited once since the sub result from this node has already been recorded. Memorization increses space cost as well as time cost to record existing paths.
    public List<List<Integer>> allPathsSourceTargetWithMemo(int[][] graph) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();

        return dfsSearch(graph, 0, map);
    }

    private List<List<Integer>> dfsSearch(int[][] graph, int node, Map<Integer, List<List<Integer>>> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        List<List<Integer>> res = new ArrayList<>();
        if (node == graph.length - 1) {
            List<Integer> path = new LinkedList<>();
            path.add(node);
            res.add(path);
        } else {
            for (int nextNode : graph[node]) {
                List<List<Integer>> subPaths = dfsSearch(graph, nextNode, map);
                for (List<Integer> path : subPaths) {
                    LinkedList<Integer> newPath = new LinkedList<>(path);
                    newPath.addFirst(node);
                    res.add(newPath);
                }
            }
        }

        map.put(node, res);
        return res;
    }

}
