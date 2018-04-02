package design;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/articles/implement-stack-using-queues/

// Approach #1 (Two Queues, push - O(1), pop O(n) )
public class StackByTwoQueues {
	private Queue<Integer> queue1 = new LinkedList<>();
	private Queue<Integer> queue2 = new LinkedList<>();
	int top;
	
	public static void main(String[] args) {
		

	}
	/** Push element x onto stack.
	 *  Time complexity: O(1) 
	 * */
	public void push(int x) {
		queue1.add(x);
		top = x;
	}
	/** Removes the element on top of the stack and returns that element. */
	public int pop() {
		while(queue1.size() > 1){
			top = queue1.remove();
			queue2.add(top);
		}
		// swap queue1 and queue2
		top = queue1.remove(); // now queue1 is empty;
		Queue<Integer> tmp = queue2;
		queue2 = queue1;
		queue1 = tmp ;
		
		return top;
	}
	/** Returns whether the stack is empty. */
	public boolean empty(){
		return queue1.size() == 0;
	}
	/** Get the top element. */
	public int top(){
		return top;
	}

}
