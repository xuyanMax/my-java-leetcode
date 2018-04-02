package design;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author xu
 * Approach #3 (One Queue, push - O(n)O(n), pop O(1)O(1) )
 * 
 * The mentioned above two approaches have one weakness, they use two queues. 
 * This could be optimized as we use only ONE queue, instead of two.
 * 
 */
public class StackByOneQueue {
	private Queue<Integer> queue = new LinkedList<>();
	private int top;
	public static void main(String[] args) {
		

	}
	/**
	 * when we push an element into a queue, it will be stored at the back of the queue
	 * due to queue's properties. But we need to implement a stack, where last inserted element should be in the
	 * front of the queue, not at the back.
	 * To achieve this, we invert the order of the queue when we push a new element.
	 * 
	 */
	 public void push(int x) {
	      	queue.add(x);
	      	
	      	int size = queue.size();
	      	while(size > 1) {
	      		queue.add(queue.remove());
	      		size--;
	      	}
	    }
	    
	    /** Removes the element on top of the stack and returns that element. */
	    // O(1)
	    public int pop() {
	      int tmp_top = queue.remove();
	      if (!queue.isEmpty())
	    	  top = queue.peek();// reset the top
	      return tmp_top;
	    }
	    
	    /** Get the top element. */
	    public int top() {
	        return queue.peek();
	    }
	    
	    /** Returns whether the stack is empty. */
	    public boolean empty() {
	        return queue.isEmpty();
	    }

}
