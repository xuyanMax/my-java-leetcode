package graph.tree.bfs;

import java.util.LinkedList;

/**
 * Created by xu on 2022/9/17 18:29.
 * <p>
 * 所谓「名人」的定义：
 * <p>
 * 1、所有其他人都认识名人。
 * <p>
 * 2、名人不认识任何其他人
 * <p>
 * 它保证了人群中最多有一个名人。
 */
public class FindCelebrity {
    // 可以直接调用，能够返回 i 是否认识 j in O(1)
    boolean knows(int i, int j) {
        return false;
    }

    //第 277 题「搜寻名人」, 并不是直接把邻接矩阵传给你，而是只告诉你总人数n，同时提供一个 API knows来查询人和人之间的社交关系：
    //暴力破解，O(n^2)，从n个人种拿出一个candidate人跟其余人进行对比，如果都是单方向认识candidate，说明candidate是名人
    //优化方法，采用排除法，排除不是名人的人，最终剩下的一个人，就是名人(最终还需要一个for循环验证该人是否为celebrity)
    //如何优化？两个人之间存在四种组合关系，
    // - AB相互认识 (AB都不是名人，排除)
    // - AB彼此不认识 (AB都不是名人，排除)
    // - A->B, B-xA (A不是名人，因为名人不认识其他人，排除)
    // - B->A A-xB (B不是名人，排除)
    // 综上，只要观察任意两个之间的关系，就至少能确定一个人不是名人
    // 请你实现：返回「名人」的编号, t c: O(N), s c: O(N)
    int findCelebrity(int n) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            queue.add(Integer.valueOf(i));
        while (queue.size() >= 2) {
            int A = queue.removeFirst();
            int B = queue.removeFirst();
            //A not celebrity
            if (knows(A, B) || !knows(B, A)) {
                queue.add(B);
            } else { // B not celebrity
                queue.add(A);
            }
        }
        int last_candidate = queue.removeFirst();
        for (int other = 1; other <= n; other++) {
            if (last_candidate == other) continue;
            if (!knows(other, last_candidate) || knows(last_candidate, other))
                return -1;
        }
        return last_candidate;


    }

    // tc: O(N)
    // sc: O(1)
    int findCelebrity_best(int n) {
        // 先假设 cand 是名人
        int cand = 0;
        for (int other = 1; other < n; other++) {
            if (!knows(other, cand) || knows(cand, other)) {
                // cand 不可能是名人，排除
                // 假设 other 是名人
                cand = other;
            } else {
                // other 不可能是名人，排除
                // 什么都不用做，继续假设 cand 是名人
            }
        }

        // 现在的 cand 是排除的最后结果，但不能保证一定是名人
        for (int other = 0; other < n; other++) {
            if (cand == other) continue;
            // 需要保证其他人都认识 cand，且 cand 不认识任何其他人
            if (!knows(other, cand) || knows(cand, other)) {
                return -1;
            }
        }

        return cand;
    }
}
