package array.HARD;

import java.util.Arrays;

public class MaxChunks2 {
    int maxChunksToSorted(int[] arr) {
        int sum1 = 0, sum2 = 0, ans = 0;
        int[] tmp = new int[arr.length];
        System.arraycopy(arr, 0, tmp,0, arr.length);
        Arrays.sort(tmp);
        for(int i = 0; i < arr.length; i++) {
            sum1 += tmp[i];
            sum2 += arr[i];
            if(sum1 == sum2) ans++;
        }
        return ans;
    }
    /*
    *  Iterate through the array, each time all elements to the left are
    *  smaller (or equal) to all elements to the right, there is a new chunck.
    *  Use two arrays to store the left max and right min to achieve O(n) time complexity.
    *  Space complexity is O(n) too.
    *  This algorithm can be used to solve ver1 too.
    *
    *  */
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int[] maxOfLeft = new int[n];//可以不要
        int[] minOfRight = new int[n];

        maxOfLeft[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i-1], arr[i]);
        }

        minOfRight[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (maxOfLeft[i] <= minOfRight[i + 1]) res++;
        }

        return res + 1;
    }
    // updated sol2
    public int maxChunksToSorted3(int[] arr) {
        int n = arr.length;
        int[] minOfRight = new int[n];
        minOfRight[n - 1] = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i+1]);
        }
        int res = 0, max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            if(max<=minOfRight[i]) res++;
        }
        return res;
    }
}
