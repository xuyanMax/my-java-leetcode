package a_OA;

import java.util.Arrays;

public class test2 {
    public static int solution(int[] A) {
        if (A.length == 1) return 1;
        if (A.length == 3 && A[0] == A[2]) return 3;

        int[] dp= new int[A.length];
        Arrays.fill(dp, 1);

        for (int i=2; i<A.length; i++) {
            if (A[i] ==A[i-2]) {
                dp[i] = dp[i-2] +1;
            }
        }
        for (int j=3; j<A.length; j++) {
            if (A[j] ==A[j-2]) {
                dp[j] = dp[j-2] +1;
            }
        }

        int max_odd = Integer.MIN_VALUE;
        int max_even = Integer.MIN_VALUE;
        for (int i=0; i<A.length-1; i++) {
            max_odd = Math.max(max_odd, dp[i+1]);
            max_even = Math.max(max_even, dp[i]);
        }
        return max_odd + max_even;
    }
    public static void main(String[] args) {
//        int[] arr = new int[]{7,-5,-5,-5,7,-1,7};
        int[] arr = new int[]{7,-5,7,-5,7,-1,7};
//        System.out.println(solution(arr));
        System.out.println(sol(arr));
    }

    public static int sol(int[] A) {
        if (A.length <= 2) return A.length;
        if (A.length == 3) return A[0] == A[2] ? 3 : 1;
        int even = A[0], odd = A[1];
        int left = 0, max_len = 0;
        for (int i = 2; i < A.length; i++) {
            if ((i % 2 == 0 && A[i] != even) || (i % 2 == 1 && A[i] != odd)) {
                max_len = Math.max(max_len, i - left);
                System.out.println(max_len);
                //update left pointer
                left = i - 1;
                if (i % 2 == 0) {
                    even = A[i]; odd = A[i - 1];
                } else {
                    even = A[i - 1]; odd = A[i];
                }
            }
        }
        int ret = Math.max(max_len, A.length - left);
        return ret;
    }
}
