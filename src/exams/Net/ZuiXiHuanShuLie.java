package exams.Net;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xu on 28/09/2017.
 */
/*
小易非常喜欢拥有以下性质的数列:
1、数列的长度为n
2、数列中的每个数都在1到k之间(包括1和k)
3、对于位置相邻的两个数A和B(A在B前),都满足(A <= B)或(A mod B != 0)(满足其一即可)
例如,当n = 4, k = 7
那么{1,7,7,2},它的长度是4,所有数字也在1到7范围内,并且满足第三条性质,所以小易是喜欢这个数列的
但是小易不喜欢{4,4,4,2}这个数列。小易给出n和k,希望你能帮他求出有多少个是他会喜欢的数列。

*/
public class ZuiXiHuanShuLie {
    private static final int mod = 1000000007;
     public static void main(String[] args){

     }
     public static void sol_TLE(){
         Scanner in = new Scanner(System.in);

         while(in.hasNextInt()) {
             int n = in.nextInt();
             int k = in.nextInt();

             int[][] dp = new int[n + 1][k + 1]; // dp[i][j]:表示长度为i的数列，以结尾的
             // dp[i][j] = sum(dp[i-1][m]) where m <= j || k%j!=0, m from 1:k
             //初始化设置，长度为1，结尾为1-k的个数为1个
             for (int i=1; i<=k ;i++)
                 dp[1][i] = 1;
             for (int i=2; i<=n; i++) {
                 for (int j=1; j<=k ;j++) {
                     for (int m=1; m<=k; m++) {
                         dp[i][j] += m<=j || m % j != 0 ? dp[i-1][m] % mod : 0;// 递推关系
                     }
                 }
             }
             int sum = 0;
             for (int i=1; i<=k; i++)
                 sum += dp[n][i] % mod;
             System.out.println(sum);
         }
     }
     public static void sol_ac(){
         Scanner in = new Scanner(System.in);

         while(in.hasNextInt()) {
             int n = in.nextInt();
             int k = in.nextInt();

             int[][] dp = new int[n + 1][k + 1]; // dp[i][j]:表示长度为i的数列，以结尾的
             // dp[i][j] = sum(dp[i-1][m]) where m <= j || k%j!=0, m from 1:k
             //初始化设置，长度为1，结尾为1-k的个数为1个
             for (int i=1; i<=k ;i++)
                 dp[1][i] = 1;

             for (int i=2; i<=n; i++) {
                 int sum = 0;
                 for (int j=1; j<=k ;j++) {
                     sum = (sum + dp[i-1][j]) % mod;
                 }
                 // 计算invalid的和
                 for (int j=1; j<=k ;j++) {
                     int invalid = 0;
                     int p = 2;
                     while (p * j <= k) {
                        invalid = (invalid + dp[i-1][j*p]) % mod;
                        p++;
                     }
                     dp[i][j] = (sum - invalid + mod) % mod;// sum 经过%运算后可能小于invalid，因此需要加一个mod，保证非负
                 }
             }

             int sum = 0;
             for (int i=1; i<=k; i++)
                 sum = (sum + dp[n][i]) % mod;
             System.out.println(sum);
         }
     }
}
