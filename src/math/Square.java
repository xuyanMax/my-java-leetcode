package math;

/**
 * Created by xu on 08/07/2017.
 */
public class Square {

    // Newton's Method
    public int mySqrt(int x) {
        long r = x;

        while (r*r > x)
            r = (r+x/r)/2;
        return (int) r;
    }

    // binary search
    public int mySqrt_2(int x) {
        int low = 1, high = x;
        while (low <= high){
            int mid = low +(high-low)/2;
//            if (mid*mid == x)
            if (mid == x/mid)
                return mid;
            else if (mid < x/mid) {
                if ((mid+1) > x/(mid+1))
                    return mid;
                low = mid + 1;
            }
            else high = mid -1;
        }
        return low;
    }

    // concise version
    // Near the very end, low = high = mid
    // in while if mid < sqrt(x) left = mid + 1;
    // in while if mid > sqrt(x) right = mid -1;
    // return right will be the answer under above two cases.
    public int mySqrt_3(int x) {
        int low = 1, high = x;
        while (low <= high){
            int mid = low +(high-low)/2;
            if (mid == x/mid)
                return mid;
            else if (mid < x/mid) {
                low = mid + 1;
            }
            else high = mid -1;
        }
        return high;
    }
}
