package heap;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by xu on 11/09/2017.
 */
/*
You have k lists of sorted integers in ascending order. Find the smallest range that
includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:

Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]

Explanation:

List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>.
And after you reset the code template, you'll see this point.

references:
http://www.geeksforgeeks.org/find-smallest-range-containing-elements-from-k-lists/
*/
public class SmallestRange_Input_Array {

    // using pointers
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        int[] next = new int[k];

        int MIN_L = Integer.MAX_VALUE, MIN_R = Integer.MIN_VALUE;

        for (List<Integer> num:nums) {
            for (int i=0; i<num.size(); i++){

            }
        }
        return new int[]{1,2};
    }

    //O(n^2*k)
    public int[] smallestRange(int[][] nums) {
        int k = nums.length;
        int n = nums[0].length;
        //if any list exhaust we will not get any better answer ,so break the while loop

        boolean flag = false;
        int minInd, minVal, maxVal;
        int[] next = new int[k];
        //全局变量
        int minRange = Integer.MAX_VALUE, minRangeLeftVal = 0, minRangeRightVal = 0;
        while (true) {

            minInd = -1;
            minVal = Integer.MAX_VALUE;
            maxVal = Integer.MIN_VALUE;

            //先更新，后计算
            //计算完了判断是否越界
            for (int i=0; i<k; i++) {//遍历next[]指针

                if (next[i] == n) { //如果指针指向了list[i]的边界：遍历完成
                    flag = true;
                    break;
                }

                if (next[i] < n && nums[i][next[i]] < minVal) {
                    minInd = i; // update index
                    minVal = nums[i][next[i]];
                }
                if (next[i] < n && nums[i][next[i]] > maxVal) {//优化: 通过观察
                                            // 加入的元素与maxVal比较，更新maxVal即可
                    maxVal = nums[i][next[i]];
                }
            }// for 结束

            if (flag) //退出 while
                break;

            next[minInd]++;
            if (maxVal - minVal < minRange) {
                minRange = maxVal - minVal;
                minRangeLeftVal = minVal;
                minRangeRightVal = maxVal;
            }

        }
        return new int[]{minRangeLeftVal,minRangeRightVal};

    }
    // heap O(n*log(m)): Heapify m elements requires O(logm) time.
    public int[] smallestRange_heap(int[][] nums) {
        PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>((a,b)->(a.val - b.val));

        //全局变量双指针
        int start = -1; //最终窗口的左侧最小值
        int end = -1;   //最终窗口的右侧最大值

        int max = Integer.MIN_VALUE;// 小顶堆中除每次poll和add，始终保持着k-1个元素，max为k个元素的最大值

        int range = Integer.MAX_VALUE;

        boolean flag = false;


        for(int i=0; i<nums.length; i++) {
            minHeap.add(new MinHeapNode(nums[i][0], i, 0));
            max = Math.max(end, nums[i][0]);//更新 max 值
        }

        while (!minHeap.isEmpty()) { // while(!minHeap.size() == nums.length)
            MinHeapNode currNode = minHeap.poll();

            // 先计算（有初始值的情况下，或使用队列的情况下），后更新range
            if (max - currNode.val < range) {
                range = max - currNode.val;
                start = currNode.val;
                end = max;              //最终的结果
            }

            if (currNode.pointer == nums[0].length)
                break;

            // 单个list没有遍历到最后
            // 找到该list的下一个元素，并加入小顶堆
            else {
                // 继续利用currNode ？？
                currNode.pointer++;
                currNode.val = nums[currNode.k][currNode.pointer];

                minHeap.add(currNode);

                //更新 max
                if (max < currNode.val)
                    max = currNode.val;
            }

        }
        // while的 退出条件／添加新元素的条件
//        while (pq.size() == nums.length) {
//
//            Element curr = pq.poll();
//            if (max - curr.val < range) {
//                range = max - curr.val;
//                start = curr.val;
//                end = max;
//            }
//            if (curr.idx + 1 < nums[curr.row].length) {
//                curr.idx = curr.idx + 1;
//                curr.val = nums[curr.row][curr.idx];
//                pq.offer(curr);
//                if (curr.val > max) {
//                    max = curr.val;
//                }
//            }
//        }


        return new int[] {start, end};

    }


    class MinHeapNode{
        int val;
        int k; // index of lists
        int pointer; // index of the next element to be picked from list k

        public MinHeapNode(int val, int k, int pointer) {
            this.val = val;
            this.k = k;
            this.pointer = pointer;
        }
    }

}
