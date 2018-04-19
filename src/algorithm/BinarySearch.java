package algorithm;

public class BinarySearch {

    public static int binarySearchNextLarger (int lo, int hi, int key, int[] arr) {

        if (arr[hi] < key) return -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (arr[mid] >= key) {
                hi = mid - 1;
            }
            else
                lo = mid + 1;
        }
        return lo;
    }
    public static int binarySearchNextSmaller (int lo, int hi, int key, int[] arr) {

        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (arr[mid] >= key) {
                hi = mid - 1;
            }
            else
                lo = mid + 1;
        }

        return hi;
    }
}
