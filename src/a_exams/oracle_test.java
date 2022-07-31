package a_exams;

/**
 * Created by xu on 21/09/2017.
 */
public class oracle_test {
    public static void main(String[] args) {
        System.out.println(new Item());
    }

}

abstract class Thing {
    static int count = -1;

    abstract int getCount();

    public String toString() {
        return getClass().getName() + count;
    }

}

class Item extends Thing {
    static int count = -1;

    @Override
    int getCount() {
        return count;
    }

    Item() {
        count++;
    }

}