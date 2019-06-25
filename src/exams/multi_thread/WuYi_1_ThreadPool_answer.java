package exams.multi_thread;


import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xu on 19/09/2017.
 */
public class WuYi_1_ThreadPool_answer {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String[] strs = new String[200000];
        System.out.println("aaa".concat("bb"));

        int cnt = 0;

        while (in.hasNext()) {
            strs[cnt++] = in.nextLine();
        }

        ExecutorService service = Executors.newFixedThreadPool(3);
        // Callable + Future 组合实现算法
        int final_cnt = cnt;
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < final_cnt; i++) {
                    if (strs[i].contains("u51_1018"))
                        count.incrementAndGet();
                }
                return count.get();
            }
        });
        // output
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
