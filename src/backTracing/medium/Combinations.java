package backTracing.medium;

import java.util.ArrayList;
import java.util.List;

/*
* 7. Combinations
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]*/
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> rs= new ArrayList<>();
        dfs(rs, new ArrayList<>(), n, k, 1);
        return rs;
    }
    public void dfs(List<List<Integer>> res, List<Integer> aList, int n, int k, int pos){
        if (k<0) return;
        if (k == 0) {
            res.add(new ArrayList<>(aList));
            return;
        }
        for (int i=pos; i<=n; i++) {
            aList.add(i);
            dfs(res, aList, n, k-1, i+1);// no duplicate numbers
            aList.remove(aList.size()-1);
        }
    }
}
