package sortAlgorithms;

public class ShellSort {

	public static void main(String[] args) {

		int[] arr = new int[]{9,1,5,8,3,7,4,6,2};
		int[] arrB  = new int[]{6,5,4,3,2,9,8,7,1};
//		sort(arr);
//		shellSort(arr);
		shellSortTwo(arr, arrB);
	}
	
	/*
	 * 大话数据结构 pp.392
	 * 希尔排序的策略是：跳跃分割式：将相距某个“增量”的记录组成一个子序列，这样能保证在子序列内分别进行直接插入排序后得到的序列是“基本有序”。
	 * 
	 * 希尔排序将关键字较小的记录，不是一步一步得向前移动，而是跳跃式向前移，因此一轮循环过后，
	 * 整个序列向着“基本有序”迈进。基本有序即，小的关键字在前，大的关键字在后，不大不小的居中。
	 * 
	 * 一般复杂度o(n^2) 
	 * 
	 * 不稳定排序
	 * 
	 */
	public static void sort(int[] arr) {
		
		int i, j;
		int increment = arr.length;
		
		do {
			increment = increment / 3 + 1;
			
			for (i = increment; i<arr.length; i++) {
				if (arr[i] < arr[i - increment]) {
					int tmp = arr[i];
					for (j=i-increment; j>=0 && tmp < arr[j]; j -= increment) {
						arr[j + increment] = arr[j];// 记录后移，查找插入位置
					}
					arr[j + increment] = tmp;//插入
				}
					
			}
			printArr(arr);	
		} while (increment > 1);
		
	}
	private static void printArr(int[] arr) {
		for (int n : arr)
			System.out.print(n);
		System.out.println("");
	}
	// http://quiz.geeksforgeeks.org/shellsort/
	public static void shellSort (int[] arr) {
		int n = arr.length;

        // Start with a big gap, then reduce the gap
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1)
            {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                int temp = arr[i];

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i-gap; j >= 0 && arr[j] > temp; j -= gap)
                    arr[j + gap] = arr[j];

                // put temp (the original a[i]) in its correct
                // location
                arr[j + gap] = temp;
            }
	
            printArr(arr);
        }
	}
	public static void shellSortTwo(int[] A, int[] B) {
		int len = A.length;
		int j, tmpA, tmpB;
		for (int gap = len/2; gap>=1; gap /= 2) {
			for (int i = gap; i<len; i++) {
				tmpA = A[i];
				tmpB = B[i];
				
				for (j = i-gap; j>=0 && A[j] > tmpA; j -= gap) {
					A[j + gap] = A[j];
					B[j + gap] = B[j];
				}
				A[j+gap] = tmpA;
				B[j+gap] = tmpB;
				
			}
			printArr(A);
			printArr(B);
		}
		
	}
}
