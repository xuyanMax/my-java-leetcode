package bit;

/**
 * Created by xu on 2017/5/29.
 */
public class MajorityElement {
    //Moore Voting Algorithm
    public int majorityElement(int[] nums) {
        int majority = nums[0];
        int count = 1;
        for (int i=0; i<nums.length; i++) {
            if (count == 0) {
                count = 1;
                majority = nums[i];
            } else if (nums[i] == majority){
                count++;
            } else
                count--;
        }
        return majority;
    }

    // bit manipulation
    public int majorityElementBit(int[] nums) {
        /*
        *   int majorityElement(vector<int>& nums) {
            int len = sizeof(int)*8, size = nums.size();
            int count = 0, mask = 1, ret = 0;
            for(int i = 0; i < len; ++i) {
                count = 0;
                for(int j = 0; j < size; ++j)
                    if(mask & nums[j]) count++;
                if(count > size/2) ret |= mask;
                mask <<= 1;
            }
            return ret;
        }
*/
        return 1;

    }
}
