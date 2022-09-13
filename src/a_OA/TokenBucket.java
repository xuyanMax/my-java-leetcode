package a_OA;

/**
 * Created by xu on 2022/9/7 22:44.
 */
public class TokenBucket {

    int totalTokens = 0;
    long start_time = 0L;
    int msPerToken = 0;
    int maxToken = 0;

    public TokenBucket(int msPerToken, int maxToken) {
        this.maxToken = maxToken;
        this.msPerToken = msPerToken;
        start_time = System.currentTimeMillis();
    }

    public void worker() {
        if (totalTokens >= maxToken)
            return;
        long stop_time = System.currentTimeMillis();
        long time_interval = stop_time - start_time;
        while (time_interval >= msPerToken) {
            time_interval -= msPerToken;
            totalTokens++;
            if (totalTokens == maxToken)
                break;
        }
        System.out.println("worker - number of tokens:" + totalTokens);
        start_time = System.currentTimeMillis();
    }

    public int acquireToken(int numTokens) {
        worker();
        if (numTokens < 0)
            return -1;
        if (numTokens >= totalTokens) {
            int res = totalTokens;
            totalTokens = 0;
            return res;
        } else if (numTokens < totalTokens) {
            totalTokens -= numTokens;
            return numTokens;
        }
        return -1;

    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket tb = new TokenBucket(100, 5);
        Thread.sleep(300);
        Thread.sleep(450);
        System.out.println(tb.acquireToken(3));//expect 3
        System.out.println(tb.acquireToken(4)); // expect 2 as the max is 5
        System.out.println(tb.acquireToken(0)); // expect 0 as the max is 5
        Thread.sleep(200);
        System.out.println(tb.acquireToken(2));//expect 2
        System.out.println(tb.acquireToken(1));//expect 0

    }

}
