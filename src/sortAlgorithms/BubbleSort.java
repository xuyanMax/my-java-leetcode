package sortAlgorithms;


/**
 * @author xu
 * Bubble Sort is the simplest sorting algorithm that works by repeatedly
 * swapping the adjacent elements if they are in wrong order
 * <p>
 * sorting in place
 * stable
 * auxiliary space O(1)
 */

public class BubbleSort {

    public void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;

    }

    public void bubbleSorting(int[] arr) {

        for (int i = 0; i < arr.length; i++)
            for (int j = arr.length - 2; j >= i; j--)
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
    }

    //冒泡排序优化
    public void optimizedBubbleSorting(int[] arr) {

        boolean flag = true;
        for (int i = 0; i < arr.length && flag; i++) {
            flag = false;
            for (int j = arr.length - 2; j >= i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;//如果有数据交换,
                }
            }
        }
    }

}
