package algorithm;

/**
 * @author xu
 * <p>
 * the puzzle has following two rules
 * 1. you can't place a larger disk on a smaller one
 * 2. only one disk can be moved at a time
 * <p>
 * For n disks, total 2^n-1 moves are required
 * <p>
 * <p>
 * references:
 * <p>
 * https://www.youtube.com/watch?v=5_6nsViVM00
 * http://www.geeksforgeeks.org/iterative-tower-of-hanoi/
 * http://quiz.geeksforgeeks.org/c-program-for-tower-of-hanoi/
 * https://en.wikipedia.org/wiki/Tower_of_Hanoi
 */
public class TowerHanoi {

    public static void main(String[] args) {

        hanoi(3);
    }

    /**
     * recursive
     *
     * @num - number of disks
     */
    static void hanoi(int num) {
        hanoiUtil(num, "A", "B", "C");
    }

    /*
     * steps:
     * 1. hanoiUtil(N-1, start, end, tmp): move N-1 disks from start to tmp
     * 2. hanoiUtil(1, start, tmp, end ): move 1 disk from start to end
     * 3. hanoiUtil(N-1, tmp, start, end): move N-1 disks from tmp to end
     */
    static void hanoiUtil(int num, String start, String tmp, String dest) {
        if (num == 1) System.out.println("Move disk from " + start + " to " + dest);
        else {
            hanoiUtil(num - 1, start, dest, tmp);
            hanoiUtil(1, start, tmp, dest);
            hanoiUtil(num - 1, tmp, start, dest);
        }

    }

    /**
     * iterative
     */
}
