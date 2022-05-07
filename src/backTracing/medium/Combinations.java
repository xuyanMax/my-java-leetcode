package backTracing.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 7. Combinations
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * <p>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> rs = new ArrayList<>();
        dfs(rs, new ArrayList<>(), n, k, 1);
        return rs;
    }

    public void dfs(List<List<Integer>> res, List<Integer> aList, int n, int k, int pos) {
        if (k < 0) return;
        if (k == 0) {
            res.add(new ArrayList<>(aList));
            return;
        }
        for (int i = pos; i <= n; i++) {
            aList.add(i);
            dfs(res, aList, n, k - 1, i + 1);// no duplicate numbers
            aList.remove(aList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
//        String ret = combinations.largestTimeFromDigits2(new int[]{1,2,3,4});
        String ret = combinations.largestTimeFromDigits2(new int[]{5,5,5,5});
        System.out.println(ret);
    }
    static String ans = "";
    public String largestTimeFromDigits(int[] A) {
        if (A == null || A.length == 0) return null;

        dfs(0, "", A);
        return ans;
    }
    public void dfs(int pos, String pref, int[]A) {
        if (pos == 2 && pref.compareTo("24") > 0) return;
        if (pos == 4) {
            if(pref.substring(2).compareTo("59") > 0)
                return;
            if (pref.compareTo(ans) > 0) {
                ans = pref;
                System.out.println(ans+"," +pref);
            }
            return;
        }
        String tmp = pref;
        for (int i=pos; i<4; i++) {
            // if (sb.length()>0 && sb.charAt(index) == A[i]) continue;
            pref += A[i];
            System.out.println("pref " + pref);
            dfs(pos + 1, pref, A);
            pref = tmp;
            System.out.println("pref after" + pref);
        }
    }

    public String largestTimeFromDigits2(int[] A) {
        if (A == null || A.length == 0) return null;
        ArrayList<String> permuts = permutations(A);
        String ret = "";
        for (String p:permuts) {
            String H = p.substring(0,2), M = p.substring(2);
            if (H.compareTo("24") < 0 && M.compareTo("59") < 0 && ret.compareTo(p) < 0) {
                ret = p;
            }
        }
        return !ret.isEmpty() ? ret.substring(0,2) + ":" + ret.substring(2) : ret;
    }
    public ArrayList<String> permutations(int[] A){
      String s = "";
      for (int a:A) s += a;
        ArrayList<String> res = new ArrayList<>();
        dfs(res, s, "");
        return res;
    }
    public void dfs(List<String> res, String s, String sofar) {
        if (s.isEmpty()) {
            res.add(sofar);
            return;
        }

        for (int i=0; i<s.length(); i++) {
            dfs(res, s.substring(0, i) + s.substring(i+1), sofar + s.charAt(i));
        }
    }
}
