package Sorting;


/**
 * 
 * @author xu
 *	Like QuickSort, Merge Sort is a Divide and Conquer algorithm. It divides input array in two halves, 
 *	calls itself for the two halves and then merges the two sorted halves.
 *
 * time complexity: O(nlgn) in all 3 cases (best, worst, average) as merge sort always divides the array in two halves and take 
 * linear time to merge two halves.
 * 
 * T(n) = 2T(n/2) + O(n)
 * 2T(n/2) = 4(T/4) + O(2*n/2)
 * ....
 * T(n) = lgn * O(n) = O(nlgn)
 * 
 * Space complexity: O(n)
 *
 */
public class MergeSort {
	
	private int[] array;

	
	public MergeSort(int[] arr){
		this.array = arr;
	}
	
	public void mergeSorting(int[] array){
		
		mSort(array, array, 0, array.length-1);
		
	}
	// min = 0;
	// max = length -1;
	// SR is the original data, TR1 will return the upper half and lower half sorted data.
	// merge(TR2, TR1,) is to put and sort the lower level TR2 into the above level TR1
	
	public void mSort(int[] SR, int[] TR1, int min, int max){
		
		int m;
		int[] TR2 = new int[10000]; 
		
		if (min == max)
			
			TR1[min] = SR[min];
			 
		else{
			//后序遍历
			//
			m = (int)(min + max)/2;
			mSort(SR,TR2, min, m);//递归将sr[min...m]归并为有序的TR2[min...m]
			mSort(SR, TR2, m+1, max);//递归将sr[m+1...max]归并为有序的TR2[m+1...max]
			merge(TR2, TR1, min, m, max);
			
		}
		
	}
	
	public void merge(int[]SR, int[]TR, int min, int m, int max){
		
		int i, j, k;
		for(j = m+1, k=min; min<=m && j<=max; k++){
			
			if (SR[min]<SR[j]) 
				TR[k] = SR[min++];
			else 
				TR[k] = SR[j++];
		}
		
		if (j <=max){
			for (i=0;i<=max-j;i++)
				TR[k+i]=SR[j+i];
		}
		
		if (min<=m) {
			
			for(i=0; i<=m-min;i++)
				TR[k+i]=SR[min+i];
	
		}
			
		}
	/**
	 *  Merges two subarrays of arr[].
     *	First subarray is arr[l..m]
     *	Second subarray is arr[m+1..r]
	 */
	void merge2(int[] a, int l, int m, int r) {
		int n1 = m-l+1;
		int n2 = r-m;
		
		/*create temp arrays*/
		int[]L = new int[n1];
		int[]R = new int[n2];
		
		/*copy data to temp data*/
		for (int i=0;i<n1;i++) 
			L[i] = a[l+i];
		
		for (int j=0;j<n2;j++) 
			R[j]=a[m+1+j];
			

        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        
        while(i<n1 && j<n2) {
        	if (L[i]<R[j]) {
        		a[k] = L[i];
        		i++;
        		
        	} else {
        		a[k] = R[j];
        		j++;
        	}
        	k++;
        }
        /*Copy remaining elements of L[i] if any*/
        while (i<n1) {
        	a[k] = L[i];
        	i++;
        	k++;
        }
        /*Copy remaining elements of R[i] if any*/
        while (j<n2) {
        	a[k] = R[j];
        	j++;
        	k++;
        }
		
	}
	
	/**
	 * 
	 * Main function that sorts arr[l..r] using
	 * merge()
	 */
	
	void sort(int[] a, int l, int r) {
		if (l<r) {
			int m = (l+r)/2;
			sort(a, l, m);
			sort(a, m+1, r);
			merge2(a, l, m, r);
		}
	}
	/**
	 * 
	 * merge sort non-recursive 
	 * 
	 */

	public void mergeItr(int[] array){
		
		int k=0; 
		int[] TR = new int[100];
		while (k < array.length){
			mergePass(array, TR, k, array.length-1);
			
			if (k==0) k = (k+1)*2;
			else k *=2;
			
			mergePass(TR, array, k, array.length-1);
			k *=2;
		}	
		
	}
	public void mergePass(int[]SR, int[]TR, int min, int max){
		int i=0;
		int j;
		while(i <= max-2*min+1) {
			merge(SR, TR, i, i+min-1, i+2*min-1);
			i=i+2*min;
			
		}
		if(i<max-min+1)
			merge(SR, TR, i, i+min-1, max);
		else 
			for (j=i; j<=max; j++)
				TR[j]=SR[j];
	}
	
	public void displayInOrder(){
		for (int i=0; i<array.length;i++)
			System.out.println(array[i]);
		
	}
	 
	public static void main(String[] args) {
		
		int[] arr = new int[]{9,8,7,6,5,4,3,2,1, 112,23241,234};
		MergeSort mSort = new MergeSort(arr);
		mSort.sort(arr, 0, arr.length-1);
		mSort.displayInOrder();
		
		
	

	}

}
