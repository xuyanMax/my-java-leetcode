package array.easy;

/**
 * Created by xu on 15/09/2017.
 */
/*
A zero-indexed array A consisting of N different integers is given.
The array contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:

S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.

Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an array A consisting of N integers,
return the size of the largest set S[K] for this array.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation:
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of array A is an integer within the range [0, N-1].


*/
public class NestedArray {

    // 时间复杂度O(n^2)
    // 空间O(1)
    // 容易造成重复计算，耗时
    public int arrayNesting_brute_force(int[] nums) {
        int res = 0;
        int count = 0;

        for (int i=0; i<nums.length; i++) {
            int start = nums[i];

            do {
                start = nums[start];
                count++;
            }
            while (start !=  nums[i]);
            res = Math.max(count, res);
        }
        return res;
    }
    //nums元素都在1-N-1之间，因此不存在越界的问题
    // 再一个，由于元素的唯一性，元素形成的circle是唯一的，即，没有两个元素能够同时跳到K，
    // 因此不存在nums[a] == nums[b] == k 当 a != b时
    // 如果按照上一种方法，尝试每一个元素作为起始点，
    // 则会造成多余的运算，因为同一个circle里的任意一个元素作为起点，都会形成同样一个circle
    // 因此使用一个visited数组来记录哪些元素已经走过
    public int arrayNesting_visited(int[] nums) {
        int res = 0;
        boolean[] visited = new boolean[nums.length];
        for (int i=0; i<nums.length; i++) {
            if (!visited[i]) {
                int start = nums[i];
                int count = 0;
                do {
                    visited[start] = true;
                    start = nums[start];
                    count++;
                }while (start!=nums[i]);
                res = Math.max(res, count);
            }
        }
        return res;
    }
    //额外空间复杂度O(1)
    //利用原有的array[]将访问过的元素设置为一个大于20000的数值即可
    public int arrayNesting_no_visited(int[] nums) {
        int ret = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i];
                int count = 0;
                while (start!=Integer.MAX_VALUE) {
                    start = nums[start];
                    count++;
                    nums[start] = Integer.MAX_VALUE;
                }
                ret = Math.max(ret, count);
            }
        }
        return ret;

    }
}
