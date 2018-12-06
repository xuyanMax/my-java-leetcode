package sortAlgorithms;

/**
 * @author xu
 * Heap sort is an in-place sort
 * time complexity - O(nlgn)
 * time complexity of heapify is O(lgn). Time complexity of create and build Min heap is O(n)
 * so overall complexity O(nlgn)
 * <p>
 * unstable
 * 建堆时间复杂度 O(n)
 * <p>
 * 调堆的时间复杂度为logN
 * <p>
 * 如果从底部最后的父节点开始建堆，那么我们可以大概算一下：
 * 假如有N个节点，那么高度为H=logN，最后一层每个父节点最多只需要下调1次，倒数第二层最多只需要下调2次，顶点最多需要下调H次，
 * 而最后一层父节点共有2^(H-1)个,倒数第二层公有2^(H-2),顶点只有1(2^0)个，
 * 所以总共的时间复杂度为s = 1 * 2^(H-1) + 2 * 2^(H-2) + ... + (H-1) * 2^1 + H * 2^0
 * 将H代入后s= 2N - 2 - log2(N)，近似的时间复杂度就是O(N)。
 * <p>
 * http://quiz.geeksforgeeks.org/heap-sort/
 */
public class HeapSort {

    public void swap(int[] array, int a, int b) {

        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;

    }

    public void heapSorting(int[] nums) {

        int i = 0;
        //对排序初步调整为大顶堆，父结点大于左右子结点，//右结点不一定>左结点
        // arr.length/2 -1
        for (i = nums.length / 2 - 1; i >= 0; i--)
            heapify(nums, i, nums.length - 1);

        for (i = nums.length - 1; i > 0; i--) {
            swap(nums, i, 0);// 参考大话数据结构 堆排序
            heapify(nums, 0, i - 1);//堆排序，从叶结点向跟结点逼近
        }
    }

    /**
     * 对输入的数组进行初始化－堆排序
     * 大顶堆: 左孩子，右孩子大小关系不确定。
     * s = adjust start index
     * m = length of arr
     */
    public void heapify(int[] nums, int s, int n) {

        int tmp, j;

        tmp = nums[s];

        for (j = 2 * s + 1; j <= n; ) {

            //j<m 说明j 不是最后一个结点
            if (j < n && nums[j] < nums[j + 1])
                j++;

            if (tmp >= nums[j])
                break;
//			swap(arr, s, j	);
            //始终用tmp 做比较，如果大于branch 则break 否则被branch最大值替换。继续下一层分支比较，tmp值为初始值
            nums[s] = nums[j];

            s = j;
            j = j * 2 + 1;
        }
        //不用swap，则需要下面一行
        nums[s] = tmp;


    }

    public void heapifyRecur(int[] nums, int i, int n) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int max = i; //initialize largest as root
        if (l < n && nums[l] > nums[i])
            max = l;
        if (l < n && nums[r] > nums[i])
            max = r;
        if (max != i) {// if max is not root any longer
            swap(nums, max, i);

            // recursively heapify
            heapifyRecur(nums, max, n);
        }

    }

}
