package a_exams.quner;

/**
 * Created by xu on 14/10/2017.
 */
public class T3 {

//    private static String[] target;
//    private static String line;
//    private static int n;
//    private static int min = Integer.MAX_VALUE;
//    private static List<List<String>> list = new ArrayList<List<String>>();
//    private static int[] arr;
//    public static void main(String[] args) {
//        try {
//            Scanner scanner = new Scanner(System.in);
//            n = scanner.nextInt();
//            list.add(new ArrayList<String>());
//            arr = new int[n + 1];
//            for (int i = 0; i < n; i++) {
//                int index = scanner.nextInt();
//                arr[index] = scanner.nextInt();
//                List<String> airline = new ArrayList<String>();
//                int m = scanner.nextInt();
//                for (int j = 0; j < m; j++) {
//                    airline.add(scanner.next());
//                }
//                list.add(airline);
//            }
//            int m = scanner.nextInt();
//            target = new String[m];
//            for (int i = 0; i < m; i++) {
//                target[i] = scanner.next();
//            }
//            scanner.close();
//            String nowStr = target[0];
//            for (int i = 1; i <= n; i++) {
//                if (list.get(i).contains(nowStr)) {
//                    find(String.valueOf(i), 1, i, 0, arr[i]);
//                }
//            }
//            if (min == Integer.MAX_VALUE) {
//                System.out.println("Error");
//            } else {
//                System.out.println(min);
//                System.out.println(line);
//            }
//        } catch (Exception e) {
//            System.out.println("Error");
//        }
// 
//    }
// 
//            private static void find(String already, int targetIndex, int airlineIndex, int nowIndex, int sum) {
//        String targetStr = target[targetIndex];
//        List<String> airline = list.get(airlineIndex);
//        if (airline.contains(targetStr)) {
//            if (targetIndex == target.length -1) {
//                if (sum < min) {
//                    min = sum;
//                    line = already;
//                }
//            } else {
//                find(already, targetIndex + 1, airlineIndex, airline.indexOf(targetStr), sum);
//            }
//        }
//        for (int i = nowIndex + 1; i < airline.size(); i++) {
//            find(already, targetIndex, airlineIndex, i, sum);
//        }
//        String nowStr = airline.get(nowIndex);
//        for (int i = 1; i <= n; i++) {
//            if (already.indexOf(String.valueOf(i)) == -1) {
//                if (list.get(i).get(0).contains(nowStr)) {
//                    find(already + " " + i, targetIndex++, i, 0, sum + arr[i]);
//                }
//            }
//        }
//    }

}
