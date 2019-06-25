package exams.multi_thread;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xu on 19/09/2017.
 */
public class WuYi_170918_1_answer {
    public static void main(String[] args) {
        long var = (long) 99;

        Scanner in = new Scanner(System.in);
        int cnt = 0;
        String[] strs = new String[200000];
        while (in.hasNext()) {
            strs[cnt++] = in.nextLine();
        }
        // 第一个传递给Task类它的static变量即可
        Task t1 = new Task(strs, cnt);
        Task t2 = new Task();
        Task t3 = new Task();
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Task.getCount());
    }
}

class Task extends Thread {
    private static AtomicInteger count = new AtomicInteger(0);
    private static AtomicInteger index = new AtomicInteger(0);
    private static String[] strs;
    private static int size;

    public Task() {
    }

    public Task(String[] str, int maxLen) {
        this.size = maxLen;
        this.strs = str;
    }

    @Override
    public void run() {
        String str;
        while ((str = getStrFromStrs()) != null) {
            if (str.contains("u51_1018"))
                count.incrementAndGet();
        }

//        while (index.get() < size) {
//            str = strs[index.get()];
//            if (str.contains("u51_1018"))
//                count.incrementAndGet();
//            index.incrementAndGet();
//        }
    }

    public String getStrFromStrs() {
        int ind = index.getAndIncrement();
        if (ind < size)
            return strs[ind];
        else
            return null;
    }

    public static int getCount() {
        return count.get();
    }
}