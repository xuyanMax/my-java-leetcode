package array.easy;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 16/09/2017.
 */
public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        if (numRows < 1)
            return ret;
        for (int i=0; i<numRows; i++) {

            //CLONE the row
            ret.add(new ArrayList<>());
            ret.get(i).add(1);//
            for (int j=1; j<i; j++) {
                int a= ret.get(i-1).get(j-1);
                int b = ret.get(i-1).get(j);
                ret.get(i).add(a+b);
            }
            if (i >= 1) //否则 第一行数据为 【1，1】
                ret.get(i).add(1);
        }
        return ret;
    }

    public List<List<Integer>> generate_2(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        if (numRows < 1)
            return ret;
        for (int i=0; i<numRows; i++) {

            ArrayList<Integer> row = new ArrayList<>();

            row.add(1);
            for (int j=1; j<i; j++) {
                int a= ret.get(i-1).get(j-1);
                int b = ret.get(i-1).get(j);
                row.add(a+b);
            }
            if (i >= 1) //否则 第一行数据为 【1，1】
                row.add(1);
            ret.add(row);
        }
        return ret;
    }
}
