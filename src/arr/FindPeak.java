package arr;

public class FindPeak {

    public static void main(String[] args) {

        int[][] problem = new int[][]{{0, 0, 9, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 8, 9},
                {0, 2, 0, 0, 0, 0, 0}, {0, 3, 0, 0, 0, 0, 0}, {0, 5, 0, 0, 0, 0, 0}, {0, 4, 7, 0, 0, 0, 0}};
        int a = 1;
        int b = -1;
        System.out.println(~a + 1);
    }

    static int findPeakSolution(int[][] problem, int left, int right) {
        if (problem.length == 0) return 0;
        left = 0;
        right = problem[0].length;
        int colMid = (left + right) / 2;
        int globMaxIndex = FindGlobalColMax(problem, colMid);// globMaxIndex > globMaxIndex±1
        if (globMaxIndex > 1 && problem[globMaxIndex][colMid] > problem[globMaxIndex - 1][colMid] &&

                globMaxIndex < problem[0].length - 1 && problem[globMaxIndex][colMid] > problem[globMaxIndex + 1][colMid] &&

                colMid > 1 && problem[globMaxIndex][colMid] > problem[globMaxIndex][colMid - 1] &&

                colMid < problem[0].length - 1 && problem[globMaxIndex][colMid] > problem[globMaxIndex][colMid + 1]
        )
            return problem[globMaxIndex][colMid];
        else if (globMaxIndex > 1 && problem[globMaxIndex][colMid] < problem[globMaxIndex][colMid - 1]
        )
            return findPeakSolution(problem, left, colMid);

        else if (globMaxIndex < problem[0].length && problem[globMaxIndex][colMid] < problem[globMaxIndex][colMid + 1])

            return findPeakSolution(problem, colMid, right);
        else return problem[globMaxIndex][colMid];


    }

    static int FindGlobalColMax(int[][] problem, int col) {
        int max = 0, index = 0;
        for (int i = 0; i < problem.length; i++)
            index = max < problem[i][col] ? i : index;
        return index;

    }

    static int find1DPeak(int[][] problem, int col, int row) {// incorrect for 2-D peak find
        int midRow = problem.length / 2;
        if (midRow > 1 && problem[midRow][col] < problem[midRow - 1][col])
            return find1DPeak(problem, col, midRow);
        else if (midRow < problem.length - 1 && problem[midRow][col] < problem[midRow + 1][col])
            return find1DPeak(problem, col, midRow);
        else return midRow;
    }

}
