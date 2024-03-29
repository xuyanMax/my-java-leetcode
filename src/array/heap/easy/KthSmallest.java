package array.heap.easy;

import java.util.PriorityQueue;

/**
 * Created by xu on 2017/6/20.
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * Example:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class KthSmallest {
    public static void main(String[] args) {
        KthSmallest inst = new KthSmallest();
        int ret = inst.kthSmallest(new int[][]{{1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}}, 3);
        System.out.println(ret);
    }

    public int kthSmallest(int[][] mat, int k) {
        /*Min Heap - 降序排列*/
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                pq.add(mat[i][j]);
                if (pq.size() > k)
                    pq.poll();
            }
        }
        return pq.peek();
    }

    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for (int j = 0; j < n; j++) {
            pq.offer(new Tuple(0, j, matrix[0][j]));
        }
        for (int i = 0; i < k - 1; i++) {
            Tuple t = pq.poll();
            if (t.x == n - 1) {
                continue;
            }
            pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return pq.poll().val;
    }


    public int kth_binary_search_range(int[][] mat, int k) {

        int low = mat[0][0], high = mat[mat.length - 1][mat[0].length - 1]; // declare smallest and largest numbers
        while (low < high) {
            int mid = low + (high - low) / 2;

            int count = 0;
            int j = mat[0].length - 1;
            for (int i = 0; i < mat.length; i++) {
                while (j >= 0 && mat[i][j] > mid)
                    j--;
                count += (j + 1); // count + each row #elments smaller or equal to mid
            }
            if (count < k) {
                low = mid + 1;// smallest number increments 1
            } else
                high = mid;
//                high = mid -1;
//            } else return mid;

        }
        return low;
    }

}

class Tuple implements Comparable<Tuple> {
    int x, y, val;

    public Tuple(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Tuple that) {
        return this.val - that.val;
    }
}
