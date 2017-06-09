package Sorting;
/*
 * quick sort 不适宜小数组排序，小数组排序可以使用直接插入法
 * 
 * time complexity:
 *  T(n) = T(k) + T(n-k-1) + O(n)
 *  The first two terms are for two recursive calls, 
 *  the last term is for the partition process. k is the number of elements which are smaller than pivot.
 *  The time taken by QuickSort depends upon the input array and partition strategy.
 *  
 *  worst case: O(n^2)
 *  The worst case occurs when the partition process always picks greatest or smallest element as pivot
 *  T(n) = T(0) + T(n-1) + O(n)
 *  	 = T(n-1) + O(n)
 *  
 *  Best case: O(nlgn)
 *  The best case occurs when the partition process always picks the middle element as pivot. 
 *  Following is recurrence for best case.
 *  T(n) = 2*T(n/2) + O(n)
 *  
 *  Average case: O(nlgn) http://www.geeksforgeeks.org/analysis-of-algorithms-set-2-asymptotic-analysis/
 *  
 *  *QuickSort Tail Call Optimization (Reducing worst case space to Log n )*
 *  
 *  In QuickSort, partition function is in-place, but we need extra space for recursive function calls. 
 *  A simple implementation of QuickSort makes two calls to itself and in worst case requires O(n) space on function call stack.
 *  
 *  
 *  Unstable 
 *  
 *  references:
 *  http://quiz.geeksforgeeks.org/quick-sort/
 *  http://www.geeksforgeeks.org/quicksort-tail-call-optimization-reducing-worst-case-space-log-n/
 */
public class QuickSort {


		private int[] array;
		
		public static void main(String[] args) {
			

			int[] arr = new int[]{10,80,30,90,40,50,70};
			QuickSort qSort = new QuickSort(arr);
			qSort.quickSorting(arr, 0, arr.length-1);
			qSort.dispalyInOrder();

		}

		public QuickSort(int[]array){
			
			this.array = array;
			
		}
		
		public void QuickSort(){			
			quickSorting(array, 0, array.length-1);
		}
		
		public int partition(int[]arr, int low, int high){
			
			int pivotKey;
			pivotKey = array[high];
			while (low < high) {
				
				while (low <high && array[low]<=pivotKey) 
					low++;
				swap(arr, low, high);
				while (low <high && array[high]>=pivotKey)
					high--;
				swap(arr, low, high);
				//array[high] = array[low];
				
				
//				array[low]=array[high];
				
			}
//			array[low] = pivotKey;
			return low;
		}
		/*
		 * median-of-three
		 */
		public int partition2(int[]arr, int low, int high){
			
			int pivotKey;
			int m = (low+high)/2;
		
			if(array[low]<array[high]) // place the larger at low
				swap(arr, low, high);	// place the larger of the rest at m
			if (array[m]<array[high])	// place the smaller of low and m at low
				swap(arr, m, high);
			if(array[low]<array[m])
				swap(arr, m, low);
			
			pivotKey = arr[0];
			int tmp = array[low];
			while (low < high){
				
				while(low<high && array[high]>=pivotKey)
					high--;
				array[low]=array[high];
				while (low<high && array[low]<=pivotKey)
					low++;
				array[high]=array[low];
				
			}
			array[low]=tmp;
			return low;
		}
		
		/**
		 * CLRS 
		 * 
		 */
		public int partition3 (int[]arr, int low, int high){
			
			// pivot element to be placed at right position
			int pivotKey = arr[high];
			int i=0; // index of smaller element
			
			for (int j=0; j<high;j++) {
				if (arr[j]<pivotKey) {// if current element is smaller than or equal to the pivotKey
					swap(arr, i, j);;
					i++;// increment index of smaller element
				}
			}
			swap(arr, high, i+1);
			return i+1;
		}
		public void quickSorting(int[]arr, int low, int high){
			
			int pivot;
		
			if (low < high) {	
				
				pivot = partition(array, low, high);
				
				quickSorting(array, low, pivot-1);
				
				quickSorting(array, pivot+1, high);
				
			}
		}
		
		/**
		 * 尾递归 减少栈空间的使用
		 * Although we have reduced number of recursive calls, the above code can still use O(n) 
		 * auxiliary space in worst case. In worst case, it is possible that array is divided in a way that the first part always 
		 * has n-1 elements. For example, this may happen when last element is chooses as pivot and array is sorted in decreasing order.
		 */
		
		public void quickSorting2(int[]arr, int low, int high){
			
			int pivot;
		
			while (low < high) {	
				
				pivot = partition2(array, low, high);
				
				quickSorting2(array, low, pivot-1);
			//尾递归 减少栈空间的使用
				low = pivot + 1;
				
			}
		}
		
		/**
		 * 
		 * We can optimize the above code to make a recursive call ONLY for the SMALLER part after partition. 
		 * Below is implementation of this idea.
		 * This QuickSort requires O(Log n) auxiliary space in worst case.
		 */
		
		public void quickSorting3(int[]arr, int low, int high){
			int pivot;
			while (low<high) {
				 /* pi is partitioning index, arr[p] is now
		           at right place */
				pivot = partition(arr, low, high);
				
			    // If left part is smaller, then recur for left
		        // part and handle right part iteratively
				if (pivot-low < high-pivot) {
					quickSorting3(arr, low, pivot-1);
					low = pivot +1;
				}else {
			        // Else recur for right part
					quickSorting3(arr, pivot+1, high);
					high = pivot -1;
				}
			}
		}	
		
		public void dispalyInOrder(){
			
			for(int i=0; i<array.length;i++) 
				
				System.out.println(array[i]);
			
			
		}
		public void shuffleArray(int[]arr) {
			for(int i=arr.length-1;i>=0;i--) {
				int rand = (int) (Math.random()*(i+1));
				swap(arr, i,rand);
				
			}
			
		}
		public void swap(int[]array, int a, int b) {
			
			int tmp = array[a];
			array[a] = array[b];
			array[b] = tmp;
			
		}
		
	}
