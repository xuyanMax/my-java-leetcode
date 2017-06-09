package Sorting;

public class SimpleSelectionSort {
	
	private int[] array;
	
	public SimpleSelectionSort(int[] arr) {
		
		this.array = arr;
	}
	
	public void simpleSelectionSorting(){
		
		int min;
		for (int i =0; i<array.length-1; i++) {
			min=i;
			for (int j=i+1; j<array.length; j++){
				
				if(array[min] > array[j]) {//从小到大排序
					
					min = j;
				}
				
			}
			if(min != i) 
				swap(array, i, min);
		}
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

	public static void main(String[] args) {
		
		int[] array = new int[]{9,1,5,8,3,33,7,4,6,2,};
		
		SimpleSelectionSort simpleSelectionSort = new SimpleSelectionSort(array);
		
		simpleSelectionSort.simpleSelectionSorting();
		simpleSelectionSort.displayInOrder();

	}

}
