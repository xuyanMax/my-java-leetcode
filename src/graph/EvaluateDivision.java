package graph;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.*;

/**
 * Created by xu on 21/08/2017.
 * <p>
 * 399.
 * <p>
 * Equations are given in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating point number). Given some queries, return the answers.
 * If the answer does not exist, return -1.0.
 * <p>
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * <p>
 * According to the example above:
 * <p>
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * <p>
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero
 * and there is no contradiction.
 */
public class EvaluateDivision {
    // 把字母当作节点， a/b -> a到b 距离2，a/c -> a->c == a->b->c 距离 2*3=6
    // 创建一个look-up map <Node, Adjacent Nodes list>
    // 再创一个look-up map <Node, Adjacent weights list>
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, ArrayList<String>> pairs = new HashMap<>();
        Map<String, ArrayList<Double>> valuePairs = new HashMap<>();
        double[] ret = new double[queries.length];

        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];

            //检查map中是否已经存在key值及对应的List，若无，需要创建新List
            if (!pairs.containsKey(equation[0])) {
                pairs.put(equation[0], new ArrayList<>());
                valuePairs.put(equation[0], new ArrayList<>());
            }

            if (!pairs.containsKey(equation[1])) {
                pairs.put(equation[1], new ArrayList<>());
                valuePairs.put(equation[1], new ArrayList<>());
            }

            //记录邻边
            pairs.get(equation[0]).add(equation[1]);
            pairs.get(equation[1]).add(equation[0]);

            //记录邻边距离
            valuePairs.get(equation[0]).add(values[i]);
            valuePairs.get(equation[1]).add(1.0 / values[i]);

        }
        // 处理queries

        for (int j = 0; j < queries.length; j++) {
            String[] query = queries[j];
            String start = query[0];
            String goal = query[1];

            // set 用来防止重复走过某节点(形成环)
            double val = dfs(start, goal, pairs, valuePairs, 1.0, new HashSet<String>());

            ret[j] = val == 0.0 ? -1.0 : val;

        }

        return ret;

    }

    public double dfs(String start, String goal, Map pairs, Map valuePairs, Double weight, Set<String> set) {
        //判断是否"走过"
        if (set.contains(start))
            return 0.0;
        // 不存在这样的"边"
        if (!pairs.containsKey(start))
            return 0.0;
        //如果 start 与 goal相同，表明相连通，返回距离值
        if (start.equals(goal))
            return weight;

        //否则，加入set，对邻边进行dfs
        set.add(start);

        ArrayList<String> start_adj = (ArrayList<String>) pairs.get(start);
        ArrayList<Double> start_ajd_val = (ArrayList<Double>) valuePairs.get(start);

        double tmp = 0;
        for (int i = 0; i < start_adj.size(); i++) {

            String adj = start_adj.get(i);
            double dist = start_ajd_val.get(i);

            tmp = dfs(adj, goal, pairs, valuePairs, weight * dist, set);

            if (tmp != 0.0) {//如果不等0.0，表明存在这样的路径
                break;
            }
        }
        //可以不用remove
//        set.remove(start);
        return tmp; //返回距离值
    }

    // hashmap in hashmap
    // <Start Node -> <End Node, weight>>
    public double[] calcEquation_2(String[][] equations, double[] values, String[][] queries) {
        Map<String, HashMap<String, Double>> pairs = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            String start = equation[0];
            String goal = equation[1];

            if (!pairs.containsKey(start))
                pairs.put(start, new HashMap<>());

            if (!pairs.containsKey(goal))
                pairs.put(goal, new HashMap<>());

            pairs.get(start).put(goal, values[i]);
            pairs.get(goal).put(start, 1 / values[i]);

        }
        double val = 0;
        double[] ret = new double[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            String start = query[0];
            String goal = query[1];

            val = dfs(start, goal, pairs, new HashSet<String>(), 1.0);
            ret[i] = val == 0.0 ? -1.0 : val;
        }

        return ret;
    }

    public double dfs(String start, String goal, Map pairs, Set<String> visited, Double weight) {
        if (visited.contains(start))
            return 0.0;
        if (!pairs.containsKey(start))
            return 0.0;
        if (start.equals(goal))
            return weight;

        visited.add(start);

        HashMap<String, Double> adj = (HashMap<String, Double>) pairs.get(start);
        double tmp = 0;
        for (Map.Entry entry : adj.entrySet()) {
            double w = (double) entry.getValue();
            String key = (String) entry.getKey();
            tmp = dfs(key, goal, pairs, visited, weight * w);
            if (tmp != 0.0)
                break;
        }
        return tmp;
    }

}
