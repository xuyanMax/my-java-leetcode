package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by xu on 2017/6/20.
 */
public class  KthSmallest {
      public static void main(String[] args){
          KthSmallest inst = new KthSmallest();
          int ret = inst.kthSmallest(new int[][]{{1,5,9},
                                    {10, 11, 13},
                                    {12, 13, 15}}, 3);
          System.out.println(ret);
      }
    public int kthSmallest(int[][] mat, int k) {
          /*Min Heap - 顺序*/
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        for(int i=0; i<mat.length; i++) {
            for (int j=0; j<mat[0].length; j++) {
                pq.add(mat[i][j]);
                if (pq.size() > k)
                    pq.poll();
            }
        }
        for (int n:pq)
            System.out.println(n);
        return pq.peek();
    }
    public int kth_binary_search_range(int[][] mat, int k ) {

        int low = mat[0][0], high = mat[mat.length-1][mat[0].length-1]; // declare smallest and largest numbers
        while (low < high) {
            int mid = low + (high - low)/2;

            int count = 0;
            int j = mat[0].length-1;
            for (int i=0; i<mat.length; i++) {
                while (j >= 0 && mat[i][j] > mid )
                    j--;
                count += (j+1); // count + each row #elments smaller or equal to mid
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
