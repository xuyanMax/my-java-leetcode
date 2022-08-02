package a_OA;

public class RoundRobin {

    public static void getWaitingTime(int[] processes, int[] burst_time, int quantum, int current_time) {
        int[] remain_burst_time = new int[processes.length];
        int[] waiting_time = new int[processes.length];

        for (int i = 0; i < burst_time.length; i++)
            remain_burst_time[i] = burst_time[i];
        int clock = current_time;

        boolean done = false;
        while (true) {
            done = true;
            for (int i = 0; i < processes.length; i++) {
                if (remain_burst_time[i] > 0) { //防止空转一轮
                    if (remain_burst_time[i] > quantum) {
                        remain_burst_time[i] -= quantum;
                        clock += quantum;
                        done = false;
                    } else {
                        clock += remain_burst_time[i];
                        System.out.println("---" + clock);
                        waiting_time[i] = clock - burst_time[i] - current_time;
                        remain_burst_time[i] = 0;
                    }
                }
            }
            if (done) break;
        }

        for (int val : waiting_time)
            System.out.println(val);
        System.out.println("clock now " + clock);

    }

    public static void main(String[] args) {
        int[] processes = new int[]{1, 2, 3, 4};
        int[] burst_time = new int[]{5, 4, 2, 1};

        getWaitingTime(processes, burst_time, 2, 0);

    }
}
