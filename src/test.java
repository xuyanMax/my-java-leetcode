import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class test {
    public static void main(String[] args) {
        String s = "闫旭";
        System.out.println(s.substring(0, 2));
        System.out.println(s.getBytes().length == s.length());
        String[] strings = new String[5];
        strings[0] = "a";
        strings[1] = "ab";
        strings[2] = "abc";
        strings[3] = "abcd";
        strings[4] = "abcde";
        System.out.println(Arrays.stream(strings).reduce((s1, s2) -> (s1 + "\n" + s2)).get());
        String brackets = "asdasd(asd)";
        String brackets2 = "asdasd（asd）";
        System.out.println(brackets.indexOf("(") + "， " + brackets.indexOf("（"));
        System.out.println(brackets2.indexOf("(") + "， " + brackets2.indexOf("（"));

        System.out.println(Long.MAX_VALUE);
        
    }

    class thd extends Thread{
        public ThreadLocal<String> threadLocal;

        public thd(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }
        public String get(){
            return threadLocal.get();
        }
        public void set(String str) {
            threadLocal.set(str);
        }
    }
    class aqs extends AbstractQueuedSynchronizer{
        protected aqs() {
            super();
        }

        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
