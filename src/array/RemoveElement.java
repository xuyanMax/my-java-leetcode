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
public class RemoveElement {

	public static void main(String[] args) {

            RemoveElement inst = new RemoveElement();
            int result = inst.solution(new int []{3,2,2,3}, 3);
            System.out.println(result);
    }
	/* TWO pointers */
	public int solution (int[] arr, int val) {
		
		int len = arr.length;
		int i = 0;
		while (i < len) {
			if (arr[i] == val) {
				arr[i] = arr[len];
				len--;
			}
			else 
				i++;
		}

//		for (i = 0;i<=len; i++)
//            System.out.println(arr[i]);
        return len;
    }
    public int solution_ (int[] arr, int val) {

        int len = arr.length-1;
        int i = 0;
        while (i <= len) {
            if (arr[i] == val) {
                arr[i] = arr[len];
                len--;
            }
            else
                i++;
        }

        for (i = 0;i<=len; i++)
            System.out.println(arr[i]);
        return len+1;
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
