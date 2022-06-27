package algorithm;

import java.util.stream.LongStream;

public class Fibonacci {
    
    public long fab1(int n) {
        return n == 1 ? 1 : n * fab1(n - 1);
    }

    // 尾递归 不用为每一个递归调用分配单独的栈帧用于跟踪每次递归调用的中间值
    // 编译器可复用某个栈帧进行计算，即只使用一个栈帧
    public long fab2(long n) {
        return fabHelper(1, n);
    }

    public long fabHelper(long acc, long n) {
        return n == 1 ? acc : fabHelper(acc * n, n - 1);
    }

    //尽力使用stream代替迭代操作，避免变化带来的影响
    public long fab3(long n) {
        return LongStream.rangeClosed(1, n).reduce(1, (a, b) -> (a * b));
    }
}
