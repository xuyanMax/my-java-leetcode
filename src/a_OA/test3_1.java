package a_OA;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;
// you can also use imports, for example:
// import java.util.*;

public class test3_1 {

    public static void main(String[] args) {
        // you can write to stdout for debugging purposes, e.g.
        System.out.println("This is a debug message");
        test3_1 solution = new test3_1();
        solution.new SerialWorker().Start();
        solution.new MultiThreadWorker().Start();

        // TODO: check your performance after implementation
        solution.new MyWorker().Start();
    }

    public abstract class AbstractWorker {
        public static final int max = 100_000_000;
        public volatile int counter = 0;

        public int getCount() {
            return this.counter;
        }

        public void doWork() {
            this.counter++;
        }

        public abstract void Start();
    }

    public class SerialWorker extends AbstractWorker {
        @Override
        public void Start() {
            long start = System.currentTimeMillis();
            for (int i = 0; i < max; i++) {
                doWork();
            }
            long end = System.currentTimeMillis();
            System.out.println("serial exec time: " + (end - start) + "s");
            System.out.println("counter: " + counter);
        }
    }

    public class MultiThreadWorker extends AbstractWorker {

        @Override
        public void Start() {
            long start = System.currentTimeMillis();
            MyRunnable myRunnable1 = new MyRunnable();
            Thread[] threads = new Thread[4];
            for (int i = 0; i < 4; i++) {
                threads[i] = new Thread(myRunnable1);
                threads[i].start();
            }
            for (int i = 0; i < 4; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("multi thread exce time: " + (end - start) + "s");
            System.out.println("counter: " + counter);
        }

        class MyRunnable implements Runnable {
            public void run() {
                while (counter < max) {
                    synchronized (this) {
                        if (counter < max) {
                            doWork();
                        }
                    }
                }
            }
        }
    }

    public class MyWorker extends AbstractWorker {

        private AtomicInteger count = new AtomicInteger();
        private final int FIX_THREAD = 4;
        private CountDownLatch latch = new CountDownLatch(FIX_THREAD);

        @Override
        public void Start() {
            long start = System.currentTimeMillis();
            process();
            long end = System.currentTimeMillis();
            System.out.println("multi thread exce time: " + (end - start) + "s");
            System.out.println("counter: " + counter);
        }

        // TODO:
        private void process() {

            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(FIX_THREAD);

            for (int i = 0; i < 4; i++) {
                fixedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < max / FIX_THREAD; i++)
                            count.incrementAndGet();

                        latch.countDown();
                    }
                });
            }
            try {
                System.out.println("Waiting for threads to complete counting.");
                latch.await();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Final count=" + count.get());
            fixedThreadPool.shutdown();
        }
    }
}
