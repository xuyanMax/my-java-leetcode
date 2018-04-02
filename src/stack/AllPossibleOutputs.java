package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xu on 11/01/2018.
 */
public class AllPossibleOutputs {
     public static void main(String[] args){
        output();
    }

    public static void output(){
        int[] arr = new int[]{1,2,3,4,5};
        LinkedList<Integer> stack = new LinkedList<>();
        helper(stack, 0, arr, new ArrayList<>());
    }
    public static void helper(LinkedList<Integer> stack, int index, int[] arr, List<Integer> list) {
        if (index >= arr.length){
            //最后输出结果由 栈+队列组成
            for(int num:stack)
                System.out.print(num + " ");
            System.out.print("+");
            for(int num:list)
                System.out.print(num+" ");
            System.out.println("\n\n");
            return;
        }
        stack.push(arr[index]);
        helper(stack, index+1, arr, list);
        stack.poll();
        if (!stack.isEmpty()){
            list.add(stack.peek());
            stack.poll();
            helper(stack, index, arr, list);
            stack.push(list.get(list.size()-1));
            list.remove(list.size()-1);
        }
    }
}
