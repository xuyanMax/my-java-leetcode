package concurrency;

import java.util.concurrent.Semaphore;

public class FooBar {
    private int n;
    private Semaphore fooSem, barSem;

    public FooBar(int n) {
        this.n = n;
        fooSem = new Semaphore(1);
        barSem = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooSem.acquire();
            printFoo.run();
            barSem.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barSem.acquire();
            printBar.run();
            fooSem.release();
        }
    }
}