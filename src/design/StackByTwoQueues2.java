package design;

import java.util.LinkedList;
import java.util.Queue;

// Approach #2 (Two Queues, push - O(n), pop O(1))

/**
 * 
 * @author xu
 * ..POP..
 * 1. The algorithm inserts each new element to queue q2 and keep it as the top element. 
 * 2. In case queue q1 is not empty (there are elements in the stack), 
 *    we remove all elements from q1 and add them to q2. 
 *    In this way the new inserted element (top element in the stack) 
 *    will be always positioned at the front of q2. 
 * 3. We swap q1 with q2 to avoid copying all elements from q2 to q1.
 */
public class StackByTwoQueues2 {
	private Queue<Integer> queue1 = new LinkedList<Integer>();
    private Queue<Integer> queue2 = new LinkedList<Integer>();
    private int top;
    
	public static void main(String[] args) {
		

	}
	/** Push element x onto stack. */
    // O(n)
    public void push(int x) {
       queue2.add(x);
       top = x;
       while (!queue1.isEmpty()) {
    	   queue2.add(queue1.remove());
       }
       Queue<Integer> tmp = queue1;
       queue1 = queue2;
       queue2 = tmp;
       
    }
    
    /** Removes the element on top of the stack and returns that element. */
    // O(1)
    public int pop() {
       int tmp_top = queue1.remove();
       // reset top 
       if(!queue1.isEmpty()) 
    	   top = queue1.peek();
       
       return tmp_top; 
    }
    
    /** Get the top element. */
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}
