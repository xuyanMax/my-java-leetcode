package binarySearch;

/**
 * Created by xu on 2017/6/10.
 */
public class BinarySearch {
    /*
    reference:https://discuss.leetcode.com/topic/52948/share-my-thoughts-and-clean-java-code/75
    The key point for any binary search is to figure out the "Search Space".
    For me, I think there are two kind of "Search Space" -- index and range(the range from the smallest number
    to the biggest number). Most usually, when the array is sorted in one direction,
    we can use index as "search space", when the array is unsorted and we are going to find
    a specific number, we can use "range".

*/
    /*测试*/
     public static void main(String[] args){
         BinarySearch bs = new BinarySearch();
         System.out.println(bs.binarySearchIter(new int[]{1,2,3,4,6,9}, 7));
         System.out.println(bs.binarySearchRec(new int[]{1,2,3,4,6,9}, 6));
         System.out.println(bs.binarySearchNextSmaller(new int[]{1,2,3,4,6,9}, 5));
         System.out.println(bs.binarySearchNextLarger(new int[]{1,2,3,4,6,9}, 5));


     }

    public int binarySearchIter(int[] arr, int key) {
        int left = 0;
        int right = arr.length;

        //边界
        if (arr[0] > key || arr[right-1]< key)
            return -1;

        while (left <= right) {
            int mid = left + (right - left)/2;
            if (arr[mid] < key)
                left = mid+1;
            else if (arr[mid] > key)
                right = mid-1;
            else
                return mid;

        }
        return -1;
    }
// =========================================================================================================
    /*返回有序数组arr中等于 key 元素的索引 - 必须满足数组中key存在，无限循环*/
    public int binarySearchRec(int[] arr, int key) {

        return binarySearchRec_helper(arr, 0, arr.length, key);
    }
    public int binarySearchRec_helper(int[] arr, int left, int right, int key) {
        if (left <= right) {
            int mid = left + (right - left)/2;
            if (arr[mid] < key)
                return binarySearchRec_helper(arr, mid + 1, right, key);
            else if (arr[mid] > key)
                return binarySearchRec_helper(arr, left, mid - 1, key);
                else return mid;

        }

        return -1;

    }
// =========================================================================================================
// iterative 数组中返回仅小于key的值
    public int binarySearchNextSmaller(int[] arr, int key) {
        int left = 0, right = arr.length;

        if (arr[0] > key || arr[right-1] < key)
            return -1;

        while (left <= right) {
            int mid = left + (right-left)/2;
            if (arr[mid] < key) {
                if (arr[mid + 1] > key)
                    return arr[mid];
                left = mid + 1;
            }
            if (arr[mid] > key) {
                right = mid -1;
            }
        }
        return arr[left];
    }

// =========================================================================================================
// iterative 数组中返回刚刚大于key的值
    public int binarySearchNextLarger(int[] arr, int key) {
        int left = 0, right = arr.length;

        if (arr[0] > key || arr[right-1] < key)
            return -1;

        while (left <= right) {
            int mid = left + (right-left)/2;
            if (arr[mid] < key) {
                left = mid + 1;
            } else if (arr[mid] > key) {
                if (arr[mid - 1] < key)
                    return arr[mid];
                right = mid -1;

            }
        }
        return arr[left];

    }
}
