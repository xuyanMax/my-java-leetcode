package arr.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 16/09/2017.
 * 118. Pascal's Triangle

 Given numRows, sol the first numRows of Pascal's triangle.
 For example, given numRows = 5,
 Return

 [
 [1],
 [1,1],
 [1,2,1],
 [1,3,3,1],
 [1,4,6,4,1]
 ]
 */
public class PascalTriangle {

    public List<List<Integer>> sol(int numRows) {
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
            //补每一行的最后一个1
            if (i >= 1) //否则 第一行数据为 【1，1】
                ret.get(i).add(1);
        }
        return ret;
    }

    public List<List<Integer>> sol2(int numRows) {
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
