package string;

/**
 * Created by xu on 08/09/2017.
 */
/**/
public class OptimalDivision {

    // it turned out to be a math trick problem.
    // X1/X2/X3/../Xn will always be equal to (X1/X2) * Y, no matter how you place parentheses.
    // i.e no matter how you place parentheses, X1 always goes to the numerator and X2 always goes
    // to the denominator. Hence you just need to maximize Y.
    // And Y is maximized when it is equal to X3 *..*Xn.
    // So the answer is always X1/(X2/X3/../Xn) = (X1 *X3 *..*Xn)/X2
    public String optimalDivision(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        if (nums.length == 1)
            return String.valueOf(nums[0]);
        if (nums.length == 2)
            return String.valueOf(nums[0]+"/"+nums[1]);
        String ret = nums[0] + "/(" + nums[1];

        for (int i=2;i<nums.length; i++)
            ret = ret + "/" + nums[i];
        ret = ret + ")";
        return ret;

    }
}
