package heap.design;

//https://mp.weixin.qq.com/s/o7tdyLiYm668dpUWd-x7Lg
public class BinaryMaxHeapArray<Key extends Comparable<Key>> {

    //注意数组的第一个索引 0 空着不用
    private Key[] pq;

    // number of elements in the queue
    private int count = 0;

    public BinaryMaxHeapArray(int size) {
        this.pq = (Key[]) new Comparable[size + 1];
    }

    //return max value, starting from index 1, ignoring the 0 index
    public Key max() {
        return pq[1];
    }

    public void insert(Key e) {
        count++;
        // add new element to the tail of the array
        pq[count] = e;
        // swim up to its proper position
        swim(count);
    }

    // return max and delete max from queue
    public void delMax() {

    }

    // 上浮第ind元素，维护大顶堆性质
    private void swim(int ind) {
        // node index > 1 and node value > parent value
        while (ind > 1 && less(parent(ind), ind)) {
            exch(ind, parent(ind));
            ind = parent(ind);
        }
    }

    //下沉第ind元素，维护大顶堆性质
    private void sink(int ind) {
        // node index < total number of heap
        while (ind < count) {
            // find the larger one between left and right children.
            int larger = left(ind);
            if (right(ind) <= count && less(larger, right(ind)))
                larger = right(ind);
            // if c urrent node value > larger children value, no need to exchange.
            if (less(larger, ind))
                break;
            exch(ind, larger);
            //sink ind node to lower level.
            ind = larger;
        }
    }

    private void exch(int var1, int var2) {
        Key tmp = pq[var1];
        pq[var1] = pq[var2];
        pq[var2] = tmp;

    }

    private boolean less(int var1, int var2) {
        return pq[var1].compareTo(pq[var2]) < 0;
    }

    //parent index
    public int parent(int node) {
        return node / 2;
    }

    public int left(int root) {
        return root * 2;
    }

    public int right(int root) {
        return root * 2 + 1;
    }

}
