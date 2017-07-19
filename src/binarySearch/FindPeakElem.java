package binarySearch;

/**
 * Created by xu on 13/07/2017.
 */
/*
Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that num[-1] = num[n] = -∞.

*/
public class FindPeakElem {
    public int findPeakElement(int[] num) {
        int low = 0;
        int high = num.length-1;

        while(low < high)
        {
            int mid1 = (low+high)/2;
            int mid2 = mid1+1;
            if(num[mid1] < num[mid2])
                low = mid2;
            else
                high = mid1;
        }
        return low;
    }

    public int findPeakElement_2(int[] num) {
        return  helper(num, 0, num.length-1);
    }
    public int helper(int[] num, int left, int right) {
        if (left == right)
            return left;
        else {
            int mid = left + (right-left)/2;
            if (num[mid] > num[mid+1])
                return helper(num, left, mid);
            else
                return helper(num, mid+1, right);
        }
    }
    // sequential search
    public int findPeakElement_3 (int[] num) {
        for (int i=1; i<num.length; i++)
            if (num[i] < num[i-1])
                return i-1;
        return num.length-1;
    }
}
