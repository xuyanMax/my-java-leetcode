package array.easy;
/**
 * 
 * @author xu
 * 27. Remove Element

 * Given an arr and a value, remove all instances of that value "in place" and return the new length.
 * 
 * Do not allocate extra space for another arr, you must do this "in place" with constant memory.
 * 
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length
 * 
 */
public class RemoveElement {

	public static void main(String[] args) {

            RemoveElement inst = new RemoveElement();
            int result = inst.solution(new int []{3,2,2,3}, 3);
            System.out.println(result);
    }
	/* TWO pointers */
	public int solution (int[] arr, int val) {
		
		int right = arr.length;
		int left = 0;
		while (left < right) {
			if (arr[left] == val) {
				arr[left] = arr[right];
				right--;
			}
			else 
				left++;
		}

//		for (i = 0;i<=right; i++)
//            System.out.println(arr[i]);
        return right;
    }

	/*Use an index pointer */
	public int solution_2(int[] arr, int val) {
		int begin = 0;
		for (int i=0; i<arr.length; i++) {
			if (arr[i] != val)
				arr[begin++] = arr[i];
		}
		return begin;
	}

	
}
