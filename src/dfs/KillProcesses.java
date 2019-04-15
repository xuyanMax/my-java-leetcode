package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * https://leetcode.com/problems/kill-process/#/description
 * 582.
 *  Input:
	pid =  [1, 3, 10, 5]
	ppid = [3, 0, 5, 3] // parent pid
	kill = 5 // kill 5 process and its children processes will also be killed.
	Output: [5,10]
	Explanation: 
	           3
	         /   \
	        1     5
	             /
	            10
	Kill 5 will also kill 10.
 */
public class KillProcesses {

    public static void main(String[] args) {

        KillProcesses kp = new KillProcesses();
        List<Integer> pid = new ArrayList<>();
        List<Integer> ppid = new ArrayList<>();
        pid.add(6);
        pid.add(1);
        pid.add(3);
        pid.add(9);
        pid.add(5);
        pid.add(8);
        pid.add(7);
        pid.add(4);
        pid.add(10);

        ppid.add(5);
        ppid.add(8);
        ppid.add(4);
        ppid.add(5);
        ppid.add(10);
        ppid.add(5);
        ppid.add(3);
        ppid.add(8);
        ppid.add(0);
        List<Integer> result = kp.killProcess(pid, ppid, 10);
        for (Integer n : result)
            System.out.println(n);

    }

    /* Use hash map to iteratively complete the mapping verification
     *  Time complexity: O(n^2)
     * Space complexity: O(n)
     * */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> ret = new ArrayList<>();
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < pid.size(); i++)
            maps.put(pid.get(i), ppid.get(i));

        ret.add(kill);
        for (int i = 0; i < pid.size(); i++) {
            int index = i;
            int val = pid.get(index);
            while (maps.get(val) != 0) {
                if (maps.get(val) == kill) { // 找到，不论映射几次，只添加第一次映射的pid
                    ret.add(pid.get(index));
                    break; // 跳到下一个pid
                }

                val = maps.get(val);
            }
        }
        return ret;
    }
    /* DFS
     * Store parent pid 's process tree as an adjacent list
     * Kill process will act as the root process and we continually add its children and children's children to a queue
     * (TO FIND children, the parent process will be popped out )
     * until the queue is empty.
     * */

    public List<Integer> killProcess2(List<Integer> pid, List<Integer> ppid, int kill) {

        Map<Integer, List<Integer>> maps = new HashMap<>(); //<ppid, list_of_direct_children>
        for (int i = 0; i < ppid.size(); i++) {
            maps.putIfAbsent(ppid.get(i), new ArrayList<Integer>()); // initialize the adjacent list if it's empty;
            maps.get(ppid.get(i)).add(pid.get(i));
        }
        List<Integer> ret = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(kill);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            ret.add(curr);
            if (maps.get(curr) != null)
                queue.addAll(maps.get(curr));
            else continue;
        }
        return ret;
    }
}
