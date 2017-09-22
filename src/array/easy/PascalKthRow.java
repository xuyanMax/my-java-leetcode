package array.easy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xu on 16/09/2017.
 */
public class PascalKthRow {

    // wrong 从左到有会覆盖下一步计算将会用到的值
    //比如计算i如的值，需要row[i]+row[i-1]，更新i处值后，在计算row[i+1]时，还需要用到旧的row[i]，
    // 然而，旧值已经被替换
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        if (rowIndex == 0) {
            row.add(1);
            return row;
        }else if (rowIndex == 1) {
            row.add(1);
            row.add(1);
            return row;
        } else {
            row.add(1);
            row.add(1);

            for (int i=2; i<rowIndex; i++) {
                for (int j=1; j<i; j++)
                    row.set(j, row.get(j-1) + row.get(j));
                row.add(1);
            }

        }
        return row;
    }

    //建立一个rowIndex+1大小数组，从右向左进行更新，不影响当前元素更新需要利用到的值
    //比如，循环开始，计算最右侧i的值，需要[i]+[i-1]，注意每一次开始[i]值为0，因此最右侧始终保持值为1
    public List<Integer> getRow_2(int rowIndex) {
        // sanity check
        if (rowIndex < 0)
            return new ArrayList<>();

        int[] row = new int[rowIndex + 1];
        row[0] = 1;
        for (int i=1; i<=rowIndex; i++) {
            for(int j=i; j>=1; j--) {// row[0]处的值不用更新，始终保持1
                row[j] += row[j-1];
            }
        }
        List<Integer> ret = new ArrayList();

        for (int n:row )
            ret.add(n);
        return ret;

    }
    //更新第一种算法
    public List<Integer> getRow_3(int rowIndex) {
        List<Integer> ret = new ArrayList<>();
        if (rowIndex < 0)
            return ret;
        for(int i=0; i<=rowIndex; i++) {
            ret.add(0, 1);
            for (int j=1; j<ret.size() - 1; j++) {
                ret.set(j, ret.get(j) + ret.get(j+1));
            }
        }
        return ret;
    }
}
