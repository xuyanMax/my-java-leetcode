package binarysearchtree.bstDesign;

/**
 * Created by xu on 2017/6/27.
 */
public class BSTreeDriveTest {
     public static void main(String[] args){
         BSTree<Integer> bst = new BSTree();
         Integer[] a = {1,5,2,7,4};
         for(Integer n : a) bst.insert(n);
         bst.delete(1);
         bst.preOrder();

     }
}
