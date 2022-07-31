package a_OA;

public class SortAlgo {

    public void bubbleSort(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length && flag; i++) {
            flag = false;
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    flag = true;
                }
            }
        }
    }

    public void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            mergeSortHelper(arr, left, right, mid);
        }
    }

    public void mergeSortHelper(int[] arr, int left, int right, int mid) {
        int[] arr_left = new int[left - mid + 1];
        int[] arr_right = new int[right - mid];

        System.arraycopy(arr, left, arr_left, 0, arr_left.length);
        System.arraycopy(arr, mid + 1, arr_right, 0, arr_right.length);

        int i = 0, j = 0, index = left;
        while (i < arr_left.length && j < arr_right.length) {
            if (arr_left[i] <= arr_right[j])
                arr[index++] = arr_left[i++];
            else
                arr[index++] = arr_right[j++];
        }

        if (i < arr_left.length) {
            System.arraycopy(arr_left, i, arr, index, arr_left.length - i);
        }
        if (i < arr_right.length) {
            System.arraycopy(arr_right, j, arr, index, arr_right.length - j);
        }

    }

    public void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            left = pivot + 1;
        }
    }

    public int partition(int[] arr, int left, int right) {
        int pivotKey = arr[right];
        int ind = left;
        int low_than_pivot = left;
        for (; ind < right; ind++) {
            if (arr[ind] <= pivotKey) {
                swap(arr, ind, low_than_pivot);
                low_than_pivot++;
            }
        }
        swap(arr, low_than_pivot, right);
        return low_than_pivot;
    }

    public void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public void radixSort(int[] arr) {

    }
}
