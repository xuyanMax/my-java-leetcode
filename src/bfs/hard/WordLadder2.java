package bfs.hard;

import java.util.*;

/**
 * Created by xu on 11/01/2018.
 */
public class WordLadder2 {

    // bfs + backtrace
    // Map<String, Adjacent parent nodes>
    // Map<String, steps> from start string to curr String takes N steps
    // Queue<String> bfs
    // min steps, the first time reaching 'end' is the min steps.
    Map<String, List<String>> map;
    List<List<String>> result;
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        result = new ArrayList<>();
        map = new HashMap<>();
        Map<String, Integer> ladder = new HashMap<>();
        //  初始化距离参数为无穷大，like dijkstra
        for (String str:dict)
            ladder.put(str, Integer.MAX_VALUE);
        int min = Integer.MIN_VALUE;
        Queue<String> queue =  new LinkedList<String>();
        queue.add(start);
        ladder.put(start, 0);
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int step = ladder.get(curr) + 1;// steps take to get curr String
            // character iteration to find adjacent words
            for (int i=0; i<curr.length(); i++) {
                char[] chars = curr.toCharArray();
                for (char c='a'; c<='z'; c++){
                    if(chars[i] == c) continue;
                    chars[i] = c;
                    String nstr = new String(chars);
                    //判断nstr是否在dic
                    if (ladder.containsKey(nstr)) {
                        // 距离小，才更新；距离大，意味着从bfs的外层向里层的探索，禁止此类bfs
                        if (ladder.get(nstr) > step) { //如果满足该条件，说明是首次reach nstr
                            ladder.put(nstr, step);
                            //首次遇到，加入queue
                            queue.add(nstr);
                        }else
                            continue;
                    }
                    if (map.containsKey(nstr)) {
                        //添加curr作为nstr的临近节点
                        map.get(nstr).add(curr);
                    }else {
                        map.put(nstr, new ArrayList<>());
                        map.get(nstr).add(curr);
                    }

                    if (nstr.equals(end)) {
                        min = step;
                        break;// 达到终点，break
                    }
                }
            }

        }
        backtrace(end, start, new ArrayList<>());
        return result;
    }
    // start最后添加进list，不断变更adj的指向
    private void backtrace(String adj, String start, List<String> list){
        if (start.equals(adj)){
            list.add(0, start);
            result.add(new ArrayList<>(list));
            list.remove(0);
            return;
        }
        List<String> l = map.get(start);
        list.add(0, adj);
        if (l != null) {
            for (String str:l)
                backtrace(str, start, list);
        }
        list.remove(0);
    }
}
