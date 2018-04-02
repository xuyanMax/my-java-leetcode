package sort;

/**
 * Created by xu on 31/12/2017.
 */
//无序数组中返回最小/大的第K个元素
public class LargestKInArray {

    public int maxK(int[] arr, int K) {
        int index = quickSort(arr, 0, arr.length-1, K);
        return arr[index];
    }
    // 返回index
    public int quickSort(int[] arr, int left , int right, int K) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            if (pivot - left + 1 == K)
                return pivot;
            else if (pivot - left + 1 < K)
                return quickSort(arr, pivot+1, right, K-(pivot-left+1));
            else
                return quickSort(arr, left, pivot-1, K);
        }
        return -1;
    }
    public int partition(int[] arr, int left, int right) {
        return 1;
    }

}
