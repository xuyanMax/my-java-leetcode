package hashtable;

/**
 * Created by xu on 14/07/2017.
 */
public class HINDEX {
    /**
     * Bucket sort
     * h-index can be at most len
     * 1、Create an arr COUNT of size len+1, storing the number of papers having a citation equal to its index for i in [0:len-1];
     * for i = L, it stores the number of papers having a citation >= len
     * 2、We can locate its h-index by scanning the COUNT from right to left.
     *  By definition, index k is the h-index if the summation of
     * all elements from count[k] to count[len] is no less than k.
     */
    public int hIndex(int[] citations) {
        int length = citations.length;
        if (citations == null || length == 0)
            return 0;
        int[] count = new int[length + 1];
        for (int c : citations) {
            if (c >= length)
                count[length]++;
            else
                count[c]++;
        }
        int res = 0;
        for (int k = length; k >= 0; k--) {
            res += count[k];
            if (res >= k)
                return k;
        }
        return 0;
    }
}
