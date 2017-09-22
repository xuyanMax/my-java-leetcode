package sortAlgorithms;
/**
 * 
 * @author xu
 * Bubble Sort is the simplest sorting algorithm that works by repeatedly 
 * swapping the adjacent elements if they are in wrong order
 * 
 * sorting in place
 * stable 
 * auxiliary space O(1)
 */

public class BubbleSort {
	
	private int[] array;
	
	public void swap(int[]array, int a, int b) {
		
		int tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
		
	}
	public BubbleSort(int[]array){
		
		this.array = array;
		
	}
	
	public void bubbleSorting(){
		
		for (int i=0;i<array.length;i++) {
			
			for (int j=array.length-2; j>=i; j--){
				
				if (array[j] > array[j+1])
					swap(array, j, j+1);
			}
		}
	
		
	}

	public void dispalyInOrder(){
		
		for(int i=0; i<array.length;i++) {
			
			System.out.println(array[i]);
		}
		
	}
//冒泡排序优化
	public void optimizeBubbleSorting(){
		
		boolean flag = true;
		for (int i=0; i<array.length && flag; i++){
			
			flag = false;
			
			for (int j=array.length-2; j>=i; j++){
				
				if (array[j] > array[j+1]){
					
					swap(array, j, j+1);
					flag = true;//如果有数据交换, 
					
				}
			}
		}
		
	}
	public static void main(String[] args) {
		
		int[]array = new int[]{9,1,5,8,3,7,4,6,2};
		
		BubbleSort bubbleSort = new BubbleSort(array);
		bubbleSort.bubbleSorting();
		bubbleSort.dispalyInOrder();

	}

}
