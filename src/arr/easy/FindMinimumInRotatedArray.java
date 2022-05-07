package arr.easy;

public class FindMinimumInRotatedArray {

    // return the minimum value of the a 'sorted' array
    public int findMinValue(int[] array) {
        int left = 0, right = array.length, mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else if (array[mid] < array[right]) {
                right = mid;
            } else {
                //
                // When num[mid] == num[hi],
                // we couldn't sure the position of minimum in mid's left or right,
                // so just let upper bound reduce one
                right--;
            }
        }
        return array[left];
    }

    // return the index of the minimum value of a distorted 'sorted' array
//    Consider this case: 1 1 1 1 1 1 1 1 2 1 1
//    the min index returned is 0, while actually it should be 9.
//    For this case: 2 2 2 2 2 2 2 2 1 2 2
//    it will return the correct index, which is 8.
//
//    The reason is, the pivot index will be passed by at hi--. To avoid this, we can add the following judgement:
//

    public int findMinIndex(int[] array) {
        int left = 0, right = array.length, mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else if (array[mid] < array[right]) {
                right = mid;
            } else {
                if (array[right - 1] > array[right]) {
                    left = right;
                    break;
                }
                right--;
            }
        }
        return array[left];
    }
}