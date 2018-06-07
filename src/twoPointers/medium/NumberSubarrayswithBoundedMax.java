package twoPointers.medium;
//795. Number of Subarrays with Bounded Maximum
/*
We are given an array A of positive integers, and two positive integers L and R (L <= R).

Return the number of (contiguous, non-empty) subarrays such that the value of
the maximum array element in that subarray is at least L and at most R.
*/
public class NumberSubarrayswithBoundedMax {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        // time: O(n)
        // 遍历时，以每一个ele作为subarrays的最后元素
        // 如果当前元素在范围[L,R]，那么
        int cost = 0, res = 0, j = 0;
        for (int i=0; i<A.length; i++){
            //如果当前元素valid
            //那么有(i-j+1)subarrays
            //cost考试，当前合法的A[i]对应的subarray数量
            if(A[i]>=L && A[i]<=R){
                res += i-j+1;
                cost = i-j+1;
            }else if (A[i]<=L){//最大值要在[L,R]之间，最小值可以小于L
                res += cost;//加等上一个valid subarray的结尾A[i]对应的subarray数量，因为A[j:i:curr] 中
                            // A[i:curr]必须一起出现，因此相当于A[j:i]的valid subarray数量
            }else{//大于R，那么将j后移一位
                j =i+1;
            }
        }
        return res;
    }
}
