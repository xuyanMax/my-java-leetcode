package exams.multi_thread;

import java.util.Scanner;

/**
 * Created by xu on 18/09/2017.
 */
public class WuYi_170918_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {//注意while处理多个case
           String[] input = new String[2];

           for (int i=0; i<2; i++)
               input[i] = in.next();

            Thread[] t = new Thread[3];

            for (int i = 0; i < 3; i++) {
                t[i] = new Input(input);
                t[i].start();
            }
            System.out.println(Input.totalCount);
        }

    }


}
class Input extends Thread{
    public static String[] str;
    public int fromInd;
    public int ind;
    public int count;
    volatile public int i;// 遍历String数组的i
    volatile public static int totalCount;

    public Input(String[] str) {
        this.str = str;
    }
    synchronized public void CountNumber(){
        while (i <2 && (ind = str[i++].indexOf("u51", fromInd)) != -1){
            count++;
            fromInd = ind + "u51".length();
        }
        totalCount += count;
    }

    @Override
    public void run() {
        CountNumber();
    }
}



