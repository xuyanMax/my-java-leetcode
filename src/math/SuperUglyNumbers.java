package math;

import java.util.*;

/**
 * Created by xu on 2017/6/19.
 */
public class SuperUglyNumbers {
    /*Write a program to find the nth super ugly number
    * Super ugly numbers are positive numbers whose all prime factors are in the given prime list
    * primes of size k.
    * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the
    * first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
    */

     public static void main(String[] args){
         SuperUglyNumbers inst = new SuperUglyNumbers();
         inst.nthSuperUglyNumber(12, new int[]{1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32});
         Set<Integer> testSet = new HashSet<>();

//         List<Integer> list = new ArrayList<>();
//         list = Arrays.asList(new Integer[] {1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32});
//         testSet.addAll(list);


     }
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        if (n<=0) return -1;
        if (n==1) return 1;

        dp[0] = 1;

        int[] index = new int[primes.length];

        for (int i=0;i<n;i++) {
            int min = getMin(index, primes, dp);
            dp[i] = min;
            updateIndex(index, primes, dp, i);
        }
        for (int nn:dp)
            System.err.println(nn);
        return dp[n-1];

    }
    public int getMin(int[] index, int[] primes, int[] dp) {
        int min = Integer.MAX_VALUE;
        for (int i=1; i<index.length; i++) {
            min = Math.min(dp[ index[i] ] * primes[i], min);
        }
        return min;
    }
    // update index and to avoid duplicates
    // for example, 6 = 2 * 3 both pointers should be incremented
    public void updateIndex (int[] index, int[] primes, int[] dp, int i) {
        for (int j=0; j<index.length; j++) {
            if (dp[i] == dp[ index[j] ] * primes[j])
                index[j]++;
        }
    }
    /*==============================OPTIMIZED==========================================*/
    // reduce 2 inner for loops to one

    public int nthSuperUglyNumber_opt(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] ind = new int[primes.length];

        for (int i=1; i<primes.length; i++) {

            int min = Integer.MAX_VALUE;
            for(int j=0; j<primes.length; j++) {
                if (dp[i-1] == primes[j] * dp[ ind[j]])
                    ind[j]++;
                min = Math.min(min, primes[j] * dp[j]);
            }
        }

        return dp[n-1];
    }
    /*==============================OPTIMIZED==========================================*/
    // heap

    public int nthSuperUglyNumber_heap(int n, int[] primes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        return 1;
    }


}
