package graph.dag;

import java.util.ArrayDeque;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TopoSort {

    private ArrayDeque<Integer> stack;

    public TopoSort() {
        stack = new ArrayDeque<>();
    }

    public int[] topological(int adjacency_matrix[][]) throws NullPointerException {
        int number_of_nodes = adjacency_matrix[0].length - 1;
        int[] topological_sort = new int[number_of_nodes + 1];
        int pos = 1;
        int curr;


        //将所有入度为0的入栈
        int[] num_indegree = new int[number_of_nodes + 1];

        /**
         *  GIVEN: m rows * n columns
         *  create a 2-d arr with an extra space
         * 	adjacency list arr[m+1][n+1]
         *
         * 		v1  v2  v3  v4 	v5
         * v1   .	.	.	.	.
         * v2	.	.	.	.	.
         * v3	.	.	.	.	.
         * v4 	.	.	.	.	.
         * v5 	.	.	.	.	.
         *  for(int row=1; row<=m; row++)
         *  	for(int col=1; col<=n; col++)
         */


        for (int k = 1; k <= number_of_nodes; k++) {

            for (int q = 1; q <= number_of_nodes; q++)
                num_indegree[k] += adjacency_matrix[q][k];

            if (num_indegree[k] == 0) {
                stack.push(k);
            }
        }
        //判断source是否在栈顶

        while (!stack.isEmpty()) {
            curr = stack.peek();

            topological_sort[pos++] = stack.pop();

            for (int i = 1; i <= number_of_nodes; i++) {

                if (adjacency_matrix[curr][i] == 1) {
                    if (--num_indegree[i] == 0) {
                        stack.push(i);
                    }
                    //重新开始匹配
                }
            }

        }
        return topological_sort;
    }

    public static void main(String... arg) {
        int number_no_nodes;
        Scanner scanner = null;
        int topological_sort[];
        try {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            number_no_nodes = scanner.nextInt();

            int adjacency_matrix[][] = new int[number_no_nodes + 1][number_no_nodes + 1];
            System.out.println("Enter the adjacency matrix");


            for (int i = 1; i <= number_no_nodes; i++)
                for (int j = 1; j <= number_no_nodes; j++)
                    adjacency_matrix[i][j] = scanner.nextInt();
            System.out.println("The Topological sort for the graph is given by ");
            TopoSort toposort = new TopoSort();
            topological_sort = toposort.topological(adjacency_matrix);
            System.out.println();
            for (int i = 1; i <= topological_sort.length - 1; i++) {
                if (topological_sort[i] != 0) // 0 3124
                    System.out.print(topological_sort[i] + "\t");
            }
        } catch (InputMismatchException inputMismatch) {
            System.out.println("Wrong Input format");
        } catch (NullPointerException nullPointer) {
        }
        scanner.close();
    }
}