package a_OA;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xu on 2022/9/8 14:59.
 */
public class TokenBucketThreads {

    static volatile AtomicInteger totalTokens = null;
    static volatile long start_time = 0L;
    static int msPerToken = 0;
    static volatile int maxToken = 0;

    public TokenBucketThreads(int msPerToken, int maxToken) {
        this.maxToken = maxToken;
        this.msPerToken = msPerToken;
        start_time = System.currentTimeMillis();
        totalTokens = new AtomicInteger(0);
    }

    public static class Worker implements Runnable {
        public AtomicInteger totalTokens = null;
        public long start_time = 0L;
        public int msPerToken = 0;
        public int maxToken = 0;

        public Worker(AtomicInteger totalTokens, int msPerToken, int maxToken) {
            this.totalTokens = totalTokens;
            this.msPerToken = msPerToken;
            this.maxToken = maxToken;
        }

        public void worker() {
            if (totalTokens.get() >= maxToken)
                return;
            long stop_time = System.currentTimeMillis();
            long time_interval = stop_time - start_time;
            while (time_interval >= msPerToken) {
                time_interval -= msPerToken;
                totalTokens.incrementAndGet();
                if (totalTokens.get() == maxToken)
                    break;
            }
            System.out.println("worker - number of tokens:" + totalTokens);
            start_time = System.currentTimeMillis();
        }

        @Override
        public void run() {
            worker();
        }
    }


    static class AcquireToken implements Runnable {
        int numTokens = 0;
        AtomicInteger totalTokens = null;
        int maxToken = 0;

        public AcquireToken(int numTokens, AtomicInteger totalTokens, int maxToken) {
            this.numTokens = numTokens;
            this.totalTokens = totalTokens;
            this.maxToken = maxToken;
        }


        @Override
        public void run() {
            acquireToken();
            System.out.println("AcquireToken " + Thread.currentThread().getName() + ", total left num=" + totalTokens.get());
        }

        public int acquireToken() {

            if (numTokens < 0)
                return -1;
            if (numTokens >= totalTokens.get()) {
                int res = totalTokens.get();
                totalTokens = new AtomicInteger(0);
                return res;
            } else if (numTokens < totalTokens.get()) {
                totalTokens.addAndGet(-numTokens);
                return numTokens;
            }
            return -1;

        }
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketThreads tb = new TokenBucketThreads(100, 5);
        Thread.sleep(300);
        Thread.sleep(450);
        Worker wt = new Worker(TokenBucketThreads.totalTokens, TokenBucketThreads.msPerToken, TokenBucketThreads.maxToken);
        AcquireToken at1 = new AcquireToken(2, TokenBucketThreads.totalTokens, TokenBucketThreads.maxToken);
        AcquireToken at2 = new AcquireToken(2, TokenBucketThreads.totalTokens, TokenBucketThreads.maxToken);
        AcquireToken at3 = new AcquireToken(2, TokenBucketThreads.totalTokens, TokenBucketThreads.maxToken);
        AcquireToken at4 = new AcquireToken(3, TokenBucketThreads.totalTokens, TokenBucketThreads.maxToken);
        Thread startworker = new Thread(wt);
        Thread t1 = new Thread(at1);
        Thread t2 = new Thread(at2);
        Thread t3 = new Thread(at3);
        Thread t4 = new Thread(at4);
        startworker.setDaemon(true);
        startworker.start();
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(350);
        t4.start();
    }

}
