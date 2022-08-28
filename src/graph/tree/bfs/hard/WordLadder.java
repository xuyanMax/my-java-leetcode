package graph.tree.bfs.hard;

import java.util.*;

/**
 * Created by xu on 10/01/2018.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // two ends BFS
        if (wordList == null || wordList.size() == 0)
            return 0;
        if (beginWord.equals(endWord)) return 0;

        Set<String> wordSet = new HashSet<String>();
        Set<String> visited = new HashSet<String>();
        // initialize a set or map so that contains() in O(1)
        for (String str : wordList)
            wordSet.add(str);
        Set<String> qstart = new HashSet<>(), qend = new HashSet<>();
        qstart.add(beginWord);
        visited.add(beginWord);
        if (wordSet.contains(endWord))
            qend.add(endWord);

        //初始值设置为2
        int level = 2;
        // 双向BFS需要，qstart 每次指向最近一层level的所有节点，同时qend指向也是最外层的所有节点
        //因此，每一轮BFS结束后，需要不断更新qstart和qend，以保证两个set指向的都是最外层节点
        while (!qstart.isEmpty()) {
            Set<String> newlevel = new HashSet<>();
//            level++;
            for (String str : qstart) {
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (str.charAt(i) == c) continue;
                        chars[i] = c;
                        String newstr = String.valueOf(chars);
                        if (qend.contains(newstr))
                            return level;
                        if (wordSet.contains(newstr) && visited.add(newstr))
                            newlevel.add(newstr);
                    }
                }
            }
            // qstart switch to smaller one btw(qend and newlevel(from start side))
            qstart = (qend.size() < newlevel.size()) ? qend : newlevel;
            //
            qend = (qstart == newlevel) ? qend : newlevel;
            //++
            level++;
        }
        return level;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // one end BFS
        if (wordList == null || wordList.size() == 0) return 0;
        Set<String> wordSet = new HashSet<String>();
        // initialize a set or map so that contains() in O(1)
        for (String str : wordList)
            wordSet.add(str);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(beginWord);
        visited.add(beginWord);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                String curr = queue.poll();
                if (curr.equals(endWord))
                    return level;
                for (int i = 0; i < curr.length(); i++) {
                    char[] chars = curr.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == chars[i])
                            continue;
                        chars[i] = c;//尝试26种变换curr字符串每一位26*n
                        //即，变换后的chars只有一位与curr字符串不同
                        //并在wordSet中查看是否存在，如果存在且unvisited，加入queue
                        String neigh = String.valueOf(chars);
                        if (wordSet.contains(neigh) && visited.add(neigh))
                            queue.add(neigh);
                    }
                }
            }
        }
        return 0;


    }

}
