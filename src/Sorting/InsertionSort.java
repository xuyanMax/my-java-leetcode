package Sorting;
/**
 * 
 * @author xu
 *
 * time comlexity - O(n^2) worst case when array is sorted in reverse order ; O(n) is array is sorted as best case. 
 * 
 * stable
 * 
 * sorting in place
 * 
 * Auxiliary space - O(1)
 * Uses: Insertion sort is used when number of elements is small. 
 * It can also be useful when input array is almost sorted, only few elements are misplaced in complete big array
 * 
 * Binary Insertion Sort (BIS)
 * We can use BIS to reduce the number of comparisons in normal insertion sort. BIS use binary search to find the proper location to insert
 * the selected item at each iteration by O(lgn). While the algorithm as a whole takes O(n^2) because of the series of swaps required for each insertion.
 * 
 * Implement Insertion Sort in Linked List
 *  
 * http://quiz.geeksforgeeks.org/insertion-sort/
 */
public class InsertionSort {
	
	private int[] array;
	
	public static void main(String[] args) {
		
		int[] array = new int[]{9,1,5,8,3,7,4,6,2};
		
		InsertionSort st = new InsertionSort(array);
		
//		st.straightInsertSorting();
//		st.binaryInsert();
//		Arrays.sort(array);
		st.binaryInsert();
//		System.out.println(st.binarySearch(array, 0, array.length-1, 7));
		st.displayInOrder();
		

	}
	public InsertionSort(int[] arr) {
		
		this.array = arr;
	}
	
	public void InsertionSorting(){
		
		int i,j;
		
		for (i=1; i<array.length; i++) {
		
			if(array[i] < array[i-1]) {
				
				int flag = array[i];
				
				// array[j] >flag && j>=0 does not work...
				for (j = i-1; j >= 0 && array[j]>flag;j--) {
					
					array[j+1] = array[j];
				}
		
				array[j+1] = flag;			
			
			}
		}
	}
	public void binaryInsert() {
		int i,j,index;
		int flag;
		for(i=1;i<array.length;i++) {
			
			flag = array[i];
			index = binarySearch(array, 0, i-1, array[i]);
			
			for(j=i;j>index;j--) {
				array[j] = array[j-1];
			}
			array[index] = flag;
			
		}
		
	}
	public int binarySearch(int[] arr, int left, int right, int target) {
		
		while(left<=right) {
			int mid = (left+right)/2;
			if(arr[mid]<target) left = mid+1;
			else if (arr[mid]>target) right = mid-1;
			else return mid;
		}
		return left;
	}	

	public void displayInOrder(){
		
		for (int i=0; i<array.length;i++) {
			
			System.out.print(array[i]+"\t");
		}
	}
	
	public void swap(int[]arr, int a, int b){
		
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b]=tmp;
		
	}

}
