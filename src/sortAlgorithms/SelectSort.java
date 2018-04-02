package sortAlgorithms;

public class SelectSort {


    //不稳定排序--
	public void simpleSelectionSorting(int[] arr) {

		int min;
		for (int i = 0; i < arr.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[min] > arr[j]) //从小到大排序
					min = j;
            if (min != i)
                swap(arr, i, min);
		}
	}
	public void swap(int[]arr, int  a, int b){
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b]=tmp;
	}
}
