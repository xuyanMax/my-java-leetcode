package sort;

import java.util.Arrays;

/**
 * 有20 个数组，每个数组有500 个元素，并且是有序排列好的，现在在这20*500 个数中找出排名前500 的数。
 * 时间复杂度: Klog(N), K: top K, N: N arrays
 */
public class SortTopKByNSortedArrays {

    public static class HeapNode {
        int value;
        int arraysIndex;
        int arrayIndex;

        /**
         * @param value       数值
         * @param arraysIndex 数组的索引，表示第N个数组
         * @param arrayIndex  某个数组的元素索引
         */
        public HeapNode(int value, int arraysIndex, int arrayIndex) {
            this.value = value;
            this.arraysIndex = arraysIndex;
            this.arrayIndex = arrayIndex;
        }

    }

    public static void printTopK(int[][] matrix, int topK) {
        if (matrix == null || matrix.length == 0) return;
        int heapSize = matrix.length;
        HeapNode[] heap = new HeapNode[heapSize];
        for (int i = 0; i < heapSize; i++) {
            int arrayIndex = matrix[i].length - 1;
            heap[i] = new HeapNode(matrix[i][arrayIndex], i, arrayIndex);
            heapInsert(heap, i);
        }
        System.out.println("TOP K:");
        for (int i = 0; i < topK; i++) {
            if (heapSize == 0) break;

            System.out.println(heap[0].value);
            if (heap[0].arrayIndex == 0) {
                heapSize--;
                swap(heap, 0, heapSize);
            } else {
                heap[0].value = matrix[heap[0].arraysIndex][--heap[0].arrayIndex];
            }
            heapify(heap, 0, heapSize);
        }
    }

    public static void heapInsert(HeapNode[] heapNodes, int arraysIndex) {
        while (arraysIndex != 0) {
            int parent = (arraysIndex - 1) / 2;
            if (heapNodes[parent].value < heapNodes[arraysIndex].value) {
                swap(heapNodes, parent, arraysIndex);
                arraysIndex = parent;
            } else break;
        }
    }

    public static void heapify(HeapNode[] heapNodes, int s, int e) {
        int j = s, tmp = heapNodes[s].value;
        for (j = 2 * s + 1; j <= e; ) {
            if (j < e && heapNodes[j].value < heapNodes[j + 1].value) {
                j++;
            }
            if (tmp >= heapNodes[j].value)
                break;
            swap(heapNodes, s, j);
            s = j;
            j = j * 2 + 1;
        }
    }

    public static void swap(HeapNode[] heapNodes, int index, int index2) {
        HeapNode tmp = heapNodes[index];
        heapNodes[index] = heapNodes[index2];
        heapNodes[index2] = tmp;
    }

    public static int[][] generateRandomMatrix(int maxRow, int maxCol, int maxValue) {
        int[][] mat = new int[(int) Math.random() * maxRow][];
        for (int i = 0; i < mat.length; i++) {
            int col = (int) (Math.random() * maxCol);
            for (int j = 0; j < col; j++) {
                mat[i][j] = (int) Math.random() * maxValue;
            }
            Arrays.sort(mat[i]);
        }

        return mat;
    }

    public static void main(String[] args) {
        int[][] mat = generateRandomMatrix(10, 30, 100);
        printTopK(mat, 30);
    }


}
