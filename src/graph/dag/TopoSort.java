package graph.dag;

import java.util.*;

public class TopoSort {

    private ArrayDeque<Integer> stack;

    public TopoSort() {
        stack = new ArrayDeque<>();
    }

    public int[] topological(int adjacency_matrix[][]) throws NullPointerException {
        int numNodes = adjacency_matrix[0].length - 1;
        int[] topoPath = new int[numNodes + 1];
        int pos = 1, count = 0;
        int curr;

        //将所有入度为0的入栈
        int[] indegree = new int[numNodes + 1];

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


        for (int k = 1; k <= numNodes; k++) {
            for (int q = 1; q <= numNodes; q++)
                indegree[k] += adjacency_matrix[q][k];
            if (indegree[k] == 0)
                stack.addFirst(k);
        }
        //判断source是否在栈顶
        while (!stack.isEmpty()) {
            curr = stack.peek();

            topoPath[pos++] = stack.removeFirst();

            for (int i = 1; i <= numNodes; i++) {
                if (adjacency_matrix[curr][i] == 1) {
                    if (--indegree[i] == 0)
                        stack.addFirst(i);
                }
            }

        }

        if (count != numNodes)
            // 存在环，拓扑排序不存在
            return new int[]{};

        return topoPath;
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

    //labuladong
    /**
     * 1. 判断是否有环
     **/
    // 记录遍历过的节点，防止走回头路
    boolean[] visited;
    // 记录一次递归/回溯堆栈中的节点，如果回到相同的一点，说明成环
    boolean[] onPath;
    //是否成环
    boolean hasCycle = false;

    boolean topo_dfs(int num_nodes, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(num_nodes, prerequisites);

        visited = new boolean[num_nodes];
        //注意图中并不是所有节点都相连，所以要用一个 for 循环将所有节点都作为起点调用一次 DFS 搜索算法。
        for (int i = 0; i < num_nodes; i++)
            traverse_dfs_cyclic(graph, i);

        return hasCycle;
    }

    //构建邻接表
    List<Integer>[] buildGraph(int num, int[][] matrix) {
        List<Integer>[] graph = new LinkedList[num];
        for (int i = 0; i < num; i++)
            graph[i] = new LinkedList<>();

        for (int[] edge : matrix) {
            int from = edge[1], to = edge[0];
            // 添加一条从 from 指向 to 的有向边
            // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
            graph[from].add(to);
        }

        return graph;
    }

    void traverse_dfs_cyclic(List<Integer>[] graph, int s) {
        // 代码见上文
        if (onPath[s]) {
            hasCycle = true;
            return;
        }
        if (visited[s])
            return;
        // 前序代码位置
        onPath[s] = true;
        visited[s] = true;

        for (int next : graph[s])
            traverse_dfs_cyclic(graph, next);

        // 后序代码位置
        onPath[s] = false;
    }

    /**
     * 2. 判断是否成环，如果是则返回环包含的节点
     * 3. 拓扑排序，顺序输出成环包含的节点
     * 只需要修改traverse方法即可
     **/
    int[] topo_dfs_path(int num_nodes, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(num_nodes, prerequisites);

        visited = new boolean[num_nodes];
        //注意图中并不是所有节点都相连，所以要用一个 for 循环将所有节点都作为起点调用一次 DFS 搜索算法。
        for (int i = 0; i < num_nodes; i++)
            traverse_dfs_topo_path(graph, i);
        // 有环图无法进行拓扑排序
        if (hasCycle)
            return new int[]{};

        // 逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[postorder.size()];
        for (int i = 0; i < postorder.size(); i++)
            res[i] = postorder.get(i);

        return res;
    }

    List<Integer> postorder = new ArrayList<>();
    //只要图中无环，那么我们就调用 traverse_dfs_topo_path 函数对图进行 DFS 遍历，记录后序遍历结果，最后把后序遍历结果反转，作为最终的答案。
    void traverse_dfs_topo_path(List<Integer>[] graph, int s) {
        // 代码见上文
        if (onPath[s])
            hasCycle = true;

        if (visited[s] || hasCycle)
            return;
        // 前序代码位置
        onPath[s] = true;
        visited[s] = true;

        for (int next : graph[s])
            traverse_dfs_cyclic(graph, next);

        // 后序代码位置
        postorder.add(s);
        onPath[s] = false;
    }

}