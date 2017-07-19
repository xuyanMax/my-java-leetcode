package math;

/**
 * Created by xu on 08/07/2017.
 */
public class PerfectSquare {
    public boolean isPerfectSquare(int num) {
        // 1 = 1;
        // 4 = 1 + 3;
        // 9 = 1 + 3 + 5;
        //16 = 1 + 3 + 5 + 7;
        //...
        // 1 + 3 + 5 + ... + 2n-1 = 2n*n/2 = n*n
        int i=1;
        while (num>0) {
            num -=i;
            i+=2;
        }
        return num == 0;
    }

}
