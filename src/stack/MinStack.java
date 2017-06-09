package stack;
/*
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * push(x) -- Push element x onto stack.
 * 
 * pop() -- Removes the element on top of the stack.
 * 
 * top() -- Get the top element.
 * 
 * getMin() -- Retrieve the minimum element in the stack.
 * 
 */
/**
 * https://leetcode.com/problems/min-stack/#/description
 * The main idea is to keep the gap between the min value and the current value
 * 1.PUSH
 *    if the stack is empty first input x will set min to x and set current to be OL
 *    if not, push the difference (x - min) to the stack.
 *    
 *    if (x < min) reset min = x; // to restore x we will use min - (current) = x - (x-min_pre) = min_pre
 * 2. POP
 * 	
 *     
 */
import java.util.Stack;

public class MinStack {

	Stack<Long> stack;
	long min; //stores the gap between the real top value and the fake one
	public static void main(String[] args) {
		

	}
	 /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
    }
    
    public void push(int x) {
        if (stack.isEmpty()) { // when the stack is empty we initialize the min as input
        	stack.push(0L);
        	min = x;
        } else {
        	stack.push(x - min);
        	if (x < min)
        		min = x;
        	
        }
    }
    
    public void pop() {
        long pop = stack.pop();
        if (pop < 0){
        	min = min - pop;// min = x - (x-min) = min
        }
    }
    
    public int top() {
        long pop = stack.peek();
        if (pop < 0){
        	return (int)min;
        }
        else
        	return (int)(min + pop);
    }
    
    public int getMin() {
        return (int)min;
    }

}
