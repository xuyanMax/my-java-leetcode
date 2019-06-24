package binarysearch;

/**
 * Created by xu on 14/07/2017.
 */
public class HINDEX_2 {


    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{1, 2, 3, 4, 5}));
    }

    //[100]
    //[1,2,100]
    //[1,100]
    static public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0)
            return 0;
        int left = 0, right = citations.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] < citations.length - mid)
                left = mid + 1;
            else if (citations[mid] > citations.length - mid) {
                right = mid - 1;
            } else // if (citations[mid] == citations.length-mid)
                return citations[mid];
        }
        return citations.length - right - 1;
    }
}
