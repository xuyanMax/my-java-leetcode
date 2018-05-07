package bfs.medium;

import java.util.*;
import java.util.stream.Collectors;

// 752. Open the Lock
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {

        Set<String> set = Arrays.stream(deadends).collect(Collectors.toCollection(HashSet::new));
        Deque<String> queue = new ArrayDeque<>();

        queue.add("0000");
        Set<String> visited = new HashSet<>();
        int level = 0;
        while (!queue.isEmpty()){

            int size = queue.size();
            while (size>0){
                String curr = queue.poll();
                if (curr.equals(target))
                    return level;
                if(set.contains(curr)){
                    size--;
                    continue;
                }
                StringBuilder builder = new StringBuilder(curr);
                for (int i=0; i<curr.length(); i++){
                    char c = curr.charAt(i);
                    String str1 = builder.substring(0, i) + (c=='9'?0:c-'0'+1) + builder.substring(i+1);
                    String str2 = builder.substring(0, i) + (c=='0'?9:c-'0'-1) + builder.substring(i+1);
                    if (!visited.contains(str1) && !set.contains(str1)){
                        visited.add(str1);
                        queue.add(str1);
                    }
                    if (!visited.contains(str2) && !set.contains(str2)){
                        visited.add(str2);
                        queue.add(str2);
                    }

                }
                size--;
            }
            level++;
        }
        return -1;
    }
}
