package exams.aiqiyi;


import java.util.Scanner;

/**
 * Created by xu on 14/10/2017.
 */
public class sol2 {
    public static void main(String[] args) {

        //牛牛羊羊吃草
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int t = in.nextInt();
            for (int i = 0; i < t; i++) {
                int n = in.nextInt();
                n %= 5;
                if (n == 2 || n == 0)
                    System.out.println("yang");
                else
                    System.out.println("niu");
            }
        }

    }
}
