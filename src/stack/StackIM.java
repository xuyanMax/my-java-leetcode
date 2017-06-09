package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class StackIM{
	
	
	Object[] objects;
	private int maxSize;
	private int top;
	
	public StackIM(int size){
		
		this.maxSize = size;
		this.objects = new Object[maxSize];
		top = -1;
	}
	
	public int size(){
		
		return maxSize;
	}
	
	public int getSize(){
		
		return top+1;
		//return top;
		
		
	}
	public boolean isEmpty(){
		
		return getSize() == 0;
	}
	
	public boolean isFull(){
		
		return top==maxSize-1;
	}
	
	public Object peak(){
		
		int len = getSize();
		if(len == 0){
			
			System.out.println("Empty stack!");
		}
		
		return this.objects[len-1];
		
		
	}
	public boolean push(Object obj){
		
		if(isFull()) {
			System.out.println("Full stack!");
			return false;
		}
		
		this.objects[++top] = obj;
		
		return true;
	}
	
	public Object pop(){
		
		return this.objects[top--];
		
	}
	
	public static void main(String[] args) {
	Stack<String> stack = new Stack();
	
	Vector<String> vc = new Vector();
	
	StackIM st = new StackIM(5);
	String st1 = "A";
	String st2 = "B";
	String st3 = "A";
	String st4 = "C";
	String st5 = "Full";
	st.push(st1);
	st.push(st2);
	st.push(st3);
	st.push(st4);
	st.push(st5);
	st.push(st5);
	
	System.out.println(st.pop()+" "+st.getSize());
	
	
	
	}

}
