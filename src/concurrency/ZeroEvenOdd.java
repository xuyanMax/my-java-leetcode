package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Suppose you are given the following code:
 * <p>
 * class ZeroEvenOdd {
 * public ZeroEvenOdd(int n) { ... }      // constructor
 * public void zero(printNumber) { ... }  // only output 0's
 * public void even(printNumber) { ... }  // only output even numbers
 * public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 * <p>
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer.
 * Modify the given program to output the series 010203040506... where the length of the series must be 2n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(),
 * the other calls even(), and the last one calls odd(). "0102" is the correct output.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: "0102030405"
 */
public class ZeroEvenOdd {
    private static int n;
    private static int i = 1;
    private static int lengthOfSeries;
    private static Semaphore z, e, o;

    public ZeroEvenOdd(int n) {
        this.n = n;
        lengthOfSeries = 2 * n;
        z = new Semaphore(1);
        e = new Semaphore(0);
        o = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public static void zero(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            z.acquire();
            if (lengthOfSeries == 0) {
                e.release();
                o.release();
                break;
            }
            printNumber.accept(0);
            lengthOfSeries--;
            if (i % 2 == 0) {
                e.release();
            } else {
                o.release();
            }
        }
    }

    public static void even(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            e.acquire();
            if (lengthOfSeries == 0) {
                z.release();
                break;
            }
            printNumber.accept(i);
            i++;
            lengthOfSeries--;
            z.release();
        }
    }

    public static void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            o.acquire();
            if (lengthOfSeries == 0) {
                z.release();
                break;
            }
            printNumber.accept(i);
            i++;
            lengthOfSeries--;
            z.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    zero(x -> System.out.print(x));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    even(x -> System.out.print(x));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    odd(x -> System.out.print(x));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
       
        executorService.shutdown();
    }
}