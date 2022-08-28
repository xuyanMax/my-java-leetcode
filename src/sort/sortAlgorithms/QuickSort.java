package sort.sortAlgorithms;

/**
 * quick sort 不适宜小数组排序，小数组排序可以使用直接插入法
 * <p>
 * time complexity:
 * T(n) = T(k) + T(n-k-1) + O(n)
 * The first two terms are for two recursive calls,
 * the last term is for the partition process. k is the number of elements which are smaller than pivot.
 * The time taken by QuickSort depends upon the INPUT ARR and PARTITION STRATEGY.
 * <p>
 * The worst case occurs when the partition process always picks greatest or smallest element as pivot
 * T(n) = T(0) + T(n-1) + O(n)
 * = T(n-1) + O(n)
 * <p>
 * Best case: O(nlgn)
 * The BEST case occurs when the partition process always picks the MIDDLE element as pivot.
 * Following is recurrence for best case.
 * T(n) = 2*T(n/2) + O(n)
 * <p>
 * Average case: O(nlgn) http://www.geeksforgeeks.org/analysis-of-algorithms-set-2-asymptotic-analysis/
 * <p>
 * *QuickSort Tail Call Optimization (Reducing worst case space to Log n )*
 * <p>
 * In QuickSort, partition function is in-place, but we need extra space for recursive function calls.
 * A simple implementation of QuickSort makes two calls to itself and in worst case requires O(n) space
 * on function call stack.
 * <p>
 * Unstable
 * <p>
 * references:
 * http://quiz.geeksforgeeks.org/quick-sort/
 * http://www.geeksforgeeks.org/quicksort-tail-call-optimization-reducing-worst-case-space-log-n/
 */
public class QuickSort {

    
    public void quickSorting(int[] arr, int low, int high) {

        int pivot;

        if (low < high) {
            pivot = partition3(arr, low, high);
            quickSorting(arr, low, pivot - 1);
            quickSorting(arr, pivot + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {

        int pivotKey;
        pivotKey = arr[high];
        while (low < high) {

            while (low < high && arr[low] <= pivotKey)
                low++;
            swap(arr, low, high);
            while (low < high && arr[high] >= pivotKey)
                high--;
            swap(arr, low, high);
            // does not reach the end of loop
            // compare low < high
        }
        //			arr[low] = pivotKey;
        return low;
    }

    /*
     * median-of-three
     */
    public int median_of_three(int[] arr, int low, int high) {

        int pivotKey;
        int m = (low + high) / 2;

        if (arr[low] < arr[high])  // place the larger at low
            swap(arr, low, high);
        if (arr[m] < arr[high])    // place the larger one of the rest at m
            swap(arr, m, high);
        if (arr[low] > arr[m])     // place the smaller one between low and m at low
            swap(arr, m, low);

        return low;
    }

    /**
     * This function takes last element as pivot,
     * places the pivot element at its correct
     * position in sorted arr, and places all
     * smaller (smaller than pivot) to left of
     * pivot and all greater elements to right
     * of pivot
     */
    public int partition3(int[] arr, int low, int high) {

        // pivot element to be placed at right position
        int pivotKey = arr[high];
        int i = low; // indexing elements smaller than pivotKey

        // example: arr[] = {10, 80, 30, 90, 40, 50, 70}
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivotKey) {// if current element is smaller than or equal to the pivotKey
                // increment index of smaller element
                swap(arr, i++, j);
            }
        }
        swap(arr, high, i);//将pivot放入合适的位置
        return i;
    }


    /**
     * 尾递归 减少栈空间的使用
     * Although we have reduced number of recursive calls, the above code can still use O(n)
     * auxiliary space in worst case. In worst case, it is possible that arr is divided in a way that the first part always
     * has n-1 elements. For example, this may happen when last element is chooses as pivot and arr is sorted in decreasing order.
     */

    public void quickSorting2(int[] arr, int low, int high) {

        int pivot;

        while (low < high) {

            pivot = partition3(arr, low, high);

            quickSorting2(arr, low, pivot - 1);
            //尾递归 减少栈空间的使用
            low = pivot + 1;

        }
    }

    /**
     * We can optimize the above code to make a recursive call ONLY for the SMALLER part after partition.
     * Below is implementation of this idea.
     * This QuickSort requires O(Log n) auxiliary space in worst case.
     */

    public void quickSorting3(int[] arr, int low, int high) {
        int pivot;
        while (low < high) {
                     /* pi is partitioning index, arr[p] is now
                       at right place */
            pivot = partition(arr, low, high);

            // If left part is smaller, then recur for left
            // part and handle right part iteratively
            if (pivot - low < high - pivot) {
                quickSorting3(arr, low, pivot - 1);
                low = pivot + 1;
            } else {
                // Else recur for right part
                quickSorting3(arr, pivot + 1, high);
                high = pivot - 1;
            }
        }
    }

    public void shuffleArray(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            swap(arr, i, rand);
        }
    }

    public void swap(int[] array, int a, int b) {

        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;

    }

}
