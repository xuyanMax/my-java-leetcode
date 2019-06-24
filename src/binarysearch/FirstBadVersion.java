package binarysearch;

/**
 * Created by xu on 08/07/2017.
 */
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid))
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }

    /*The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version)*/
    public boolean isBadVersion(int num) {
        return false;
    }
}
