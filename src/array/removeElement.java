package array;
/**
 * 
 * @author xu
 * 
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * 
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length
 * 
 */
public class removeElement {

	public static void main(String[] args) {


	}
	/* TWO pointers */
	static int solution (int[] arr, int val) {
		
		int len = arr.length;
		int i = 0;
		while (i < len) {
			if (arr[i] == val) {
				arr[i] = arr[len - 1];
				len--;
			}
			else 
				i++;
		}
		return len;	
		
	}
	
}
