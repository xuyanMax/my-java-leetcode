package a_OA.nowcoder;
public class ContinuousMaxSum {
    public int maxSum(int[] arr) {
        int maxSoFar = Integer.MIN_VALUE;
        int maxEndHere = Integer.MIN_VALUE;
        if (arr == null || arr.length == 0)
            return 0;
        for (int i=0; i<arr.length; i++) {
            if (maxEndHere < 0)
                maxEndHere = arr[i];//记录当前最大值
            else
                maxEndHere += arr[i];//如果为正，累加最大值
            maxSoFar = Math.max(maxSoFar, maxEndHere);
        }
        return maxSoFar;
    }
}
