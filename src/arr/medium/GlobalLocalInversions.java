package arr.medium;
//775. Global and Local Inversions
/*We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.*/
public class GlobalLocalInversions {
    public boolean isIdealPermutation(int[] A) {
        if (A==null || A.length==0) return true;
        int global = 0, local = 0;
        for(int i=0; i<A.length-1; i++){
            for (int j=i+1; j<A.length; j++){
                if (j==i+1 && A[i]>A[j])
                    local++;
                if (A[i]>A[j])
                    global++;
            }
        }
        return global==local;
    }
    // 0 1 2 3 4 5
    // 1 0 2 3 4 5 ok
    // 0 2 1 3 4 5 ok
    // 0 3 2 1 4 5 nok
    // => A[i] - (i+1) > 1 nok
    public boolean _1d(int[]A){
        if (A==null || A.length==0) return true;
        for (int i = 0; i < A.length; ++i) {
            if (Math.abs(A[i] - i) > 1)
                return false;
        }
        return true;
    }
}
