package a_OA.nowcoder;

/**
 * [编程题]二维数组中的查找
 * 热度指数：933794时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Search2DMatrix {

    /**
     * 矩阵左下角开始进行搜索
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0) return false;
        int col = array[0].length, row = array.length;

        for (int i = row - 1, j = 0; i >= 0 && j < col; ) {
            if (array[i][j] == target)
                return true;
            else if (array[i][j] > target)
                i--;
            else
                j++;
        }
        return false;
    }
}
