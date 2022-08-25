package a_OA.nowcoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 桶排序
 * uniformly distributed
 */
public class BucketSort {
    public void sort(int[] nums) {
        if (nums == null) return;

        // Math.sqrt(nums.length)
        ArrayList<Integer>[] buckets = new ArrayList[hash(nums)[1]];

        for (int i = 0; i < buckets.length; i++) buckets[i] = new ArrayList<>();
        //hash到bucket中
        for (int num : nums) buckets[hash(nums, num)].add(num);
        //对bucket排序
        for (ArrayList<Integer> list : buckets) Collections.sort(list);

        int ind = 0;
        for (ArrayList<Integer> list : buckets)
            for (Integer num : list)
                nums[ind++] = num;

    }

    public int hash(int[] hash, int num) {
        return num / hash[0] * (hash[1] - 1);
    }

    public int[] hash(int[] nums) {
        int max = Arrays.stream(nums).reduce(Integer::max).getAsInt();
        return new int[]{max, (int) Math.sqrt(nums.length)};
    }
}
