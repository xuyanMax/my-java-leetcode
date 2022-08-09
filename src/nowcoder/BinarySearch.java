package nowcoder;
public class BinarySearch {

    /**
     * @param arr:   sorted unique number array
     * @param target
     * @return index
     */
    public int bs(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left++;
            } else right--;
        }
        return -1;
    }

    /**
     * @param arr:   sorted array with duplicate numbers
     * @param target
     * @return index of the first larger than or equal to target's element
     */
    public int bs2(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
