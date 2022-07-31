package a_OA;

public class test4 {

    public static int sol(int N) {

        while (N > 10) {
            N = help(N);
        }
        return N;
    }

    public static int help(int N) {
        int sum = 0;
        while (N > 10) {
            sum += N % 10;
            N /= 10;
        }
        sum += N;
        return sum;
    }

    public static void main(String[] args) {
        int N = 9999;
        System.out.println(help(N));
        System.out.println(sol(N));
    }
}
