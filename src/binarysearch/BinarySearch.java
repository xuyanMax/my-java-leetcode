package binarysearch;

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
         System.out.println(bs.binarySearchNextSmaller(new int[]{1,2,3,4,4,4,9}, 10));
         System.out.println(bs.binarySearchNextLarger(new int[]{1,2,3,4,6,9}, 7));


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
    //[2,3,3,4,5,6] -target = 3 返回第一个大于或者等于3的索引
    //find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].

/*
Now according to the relative value of A[mid] to target, there are 3 possibilities:

1. If A[mid] < target, then the range must begins on the right of mid (hence i = mid+1 for the next iteration)
2. If A[mid] > target, it means the range must begins on the left of mid (j = mid-1)
3. If A[mid] = target, then the range must begins on the left of or at mid (j= mid)

Since we would move the search range to the same side for case 2 and 3,
we might as well merge them as one single case so that less code is needed:
2*. If A[mid] >= target, j = mid;

*/
    public int bs_find_first_greater_equal (int[] nums, int target) {
        if (nums[0] > target)
            return -1;
        if (nums[nums.length-1] < target)
            return nums.length;
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = left + (right-left)/2;
            // left <= mid < right
            if (nums[mid] < target)
                left = mid + 1;
            else
                //should not be mid -1 when nums[mid] = target
                //could be mid when nums[mid]>target, because right > mid
                right = mid;
        }
        return left;
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

        if (arr[0] > key)
            return -1;
        if (arr[right-1] < key)
            return arr[right-1];

        while (left <= right) {
            int mid = left + (right-left)/2;
            if (arr[mid] < key) {
                if (arr[mid + 1] >= key)
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
// iterative 数组中返回刚刚大于key的值, 仅限于非duplicate数组
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
            } //else return arr[mid]; //处理相同元素
        }
        return arr[left];

    }
}
