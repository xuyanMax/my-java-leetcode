package nowcoder;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 题目描述
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 */
public class MinKNums {
    /**
     * 原生大堆排序
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if (input == null || input.length == 0 || input.length <= k) return new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        //从数组的前k个元素中，提取最大值
        for (int i = k / 2 - 1; k >= 0; k--)
            heapify(input, i, k - 1);

        //从第k个元素开始分别与最大堆的最大值做比较，如果比最大值小，则替换并调整堆。
        //最终堆里的就是最小的K个数。
        for (int i = k; i < input.length; i++) {
            if (input[i] < input[0]) {
                swap(input, i, 0);
                heapify(input, 0, k - 1);//调整索引0的位置为K中最大值
            }
        }
        for (int i = 0; i < k; i++)
            result.add(input[i]);
        return result;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public void heapify(int[] nums, int s, int n) {
        int tmp = nums[s];
        int j = 0;
        for (j = s * 2 + 1; j <= n; ) {
            if (j < n && nums[j] < nums[j + 1])
                j++;
            if (nums[j] <= tmp) break;

            nums[s] = nums[j];

            s = j;
            j = j * 2 + 1;
        }
        nums[j] = tmp;
    }

    /**
     * 优先队列
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        if (input == null || input.length == 0) return res;
        if (k > input.length) return res;
        for (int num : input) {

            pq.add(num);
            if (pq.size() >= k) {
                pq.poll();
            }
        }
        res.addAll(pq);
        return res;
    }


}
