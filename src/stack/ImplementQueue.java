package stack;

import java.util.Stack;
/*
 * https://leetcode.com/problems/implement-queue-using-stacks/#/description
 * reference: https://discuss.leetcode.com/topic/17974/short-o-1-amortized-c-java-ruby
 * 
 * Idea is use two stacks.
 * One input stack, on which I push the incoming elements, and one output stack, from which I peek/pop.
 * 
 * We push the elements needed to input stack and remove elements from output stack when needed. ex., when I need to peek/pop but
 * the "output" stack is empty, when that happens, I move all elements from "input" to "output", thereby reversing the order so it's in the correct order.
 * for peek/pop.
 * Every one of the elements got moved only once,  so the amortized cost for each peek/pop is O(1)
 */
public class ImplementQueue {

	Stack<Integer> input = new Stack<Integer>();
	Stack<Integer> output = new Stack<Integer>();
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        return output.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (output.empty()){
        	while(!input.isEmpty())
        		output.push(input.pop());
        }
        return output.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return input.empty() && output.isEmpty();
    }

}
