package bosonnlp_written;

/**
 * Created by xu on 2017/6/10.
 */
public class Q3 {
     public static void main(String[] args){
         int[][] arr = new int[][]{
             {5,6,7,8,9},
             {4,5,6,7,8},
             {3,4,5,6,7},
             {2,3,4,5,6},
             {1,2,3,4,5}};
         Q3 inst = new Q3();

         boolean isExist = inst.sol(arr, 3);
         System.out.println(isExist);


     }

    /*给定一个N*M的整数 矩阵，其中每行的元素是"递增"的，而每列元素递减。设计算法，判断整数x是否存在在矩阵中*/
    // 时间复杂度 O(n)
    public boolean sol(int[][] arr, int key) {
        if ( arr == null || arr.length == 0 )
            return false;

        int n = arr.length, m = arr[0].length;

        //边界
        if (key > arr[0][m-1] || key < arr[n-1][0])
            return false;
        int col = 0;
        while (col++ < m) {
            int pos = binarySearch(arr, n, m, col, key);
            if (pos != -1) {
                System.out.println("位置：row=" + pos + ", col=" +col);
                return true;
            }
            else
                continue;

        }

        //otherwise return false
        return false;
    }

    public int binarySearch(int[][] arr, int n, int m, int col, int key) {
        int top = 0;
        int bottom = n;

        //边界
        if (arr[0][col] < key || arr[bottom-1][col] > key)
            return -1;

        while (top <= bottom) {
            int mid = top + (bottom - top)/2;
            if (arr[mid][col] > key)
                top = mid+1;
            else if (arr[mid][col] < key)
                bottom = mid-1;
            else
                return mid;

        }
        return -1;
    }


}

