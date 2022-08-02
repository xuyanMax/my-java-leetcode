package a_OA;


import java.util.*;

public class TopK {

    public static void main(String[] args) {
        String[] str = {"123", "123", "231", "32"};
        topKStrings(str, 3);
    }

    public static String[][] topKStrings(String[] strings, int k) {
        // write code here
        Map<String, Integer> frequency = new HashMap<>();
        String[][] res = new String[k][2];
        for (String str : strings)
            frequency.put(str, frequency.getOrDefault(str, 0) + 1);

        //solution 1
        sortAll(frequency, k, res);
        printResults(res, k);

        //solution 2 大小为K的小顶堆，每次将最小的元素弹出，一次完整遍历后，留下的K个都是最大的值
        PriorityQueue<Map.Entry<String, Integer>> queue = minKHeap(frequency, k, res);


        System.out.println();
        printResults(res, k);

        return res;
    }

    //大顶堆
    static class StringComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o1.getValue() == o2.getValue()) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                //desc
                return o2.getValue() - o1.getValue();
            }
        }
    }

    //小顶堆,
    static class AscStrComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o1.getValue() == o2.getValue())
                return o2.getKey().compareTo(o1.getKey());
            else
                return o1.getValue() - o2.getValue();

        }
    }

    public static void sortAll(Map<String, Integer> frequency, int k, String[][] res) {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(frequency.entrySet());

        Collections.sort(sortedList, new StringComparator());
        for (int i = 0; i < k; i++) {
            res[i][0] = sortedList.get(i).getKey();
            res[i][1] = String.valueOf(sortedList.get(i).getValue());
        }
    }

    public static PriorityQueue<Map.Entry<String, Integer>> minKHeap(Map<String, Integer> frequency, int k, String[][] res) {
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(k, new AscStrComparator());
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            queue.add(entry);
            if (queue.size() > k)
                queue.poll();
        }

        for (int i = k - 1; i >= 0; i--) {
            Map.Entry<String, Integer> entry = queue.poll();
            res[i][0] = entry.getKey();
            res[i][1] = String.valueOf(entry.getValue());
            System.out.println("queue entry - key=" + entry.getKey() + ", val=" + entry.getValue());
        }
        return queue;
    }

    public static void printResults(String[][] res, int k) {
        System.out.println("=====================");
        for (int i = 0; i < k; i++)
            System.out.println("key=" + res[i][0] + ", val=" + res[i][1]);
        System.out.println("=====================");
    }


}
