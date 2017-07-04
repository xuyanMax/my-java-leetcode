package math;

/**
 * Created by xu on 2017/6/19.
 */
public class UglyNumbers {
    /*Ugly numbers are positive numbers whose prime factors only include 2, 3, 5
    * 除2，3，5后余数为1
    * */
    public boolean isUgly(int num) {
        // just divide 2,3,5 as many as possible and see if we get to 1

        if(num == 0) return false;
        if(num == 1) return true;
        while(num%2==0) num /= 2;
        while(num%3==0) num /= 3;
        while(num%5==0) num /= 5;

        return num==1;
    }
}
