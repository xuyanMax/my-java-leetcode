package a_OA;

public class continue_break {
    public static void main(String[] args) {

        int i = 0;
        //point
        retry:
        for (; ; ) {
            System.out.printf("outer i"+i);
            for (; ; ) {
                i++;
                System.out.println(i);
                if (i == 100)
                    break retry;

                if (i == 10)
                    continue retry;
            }
        }
        System.out.println(i);
    }
}
