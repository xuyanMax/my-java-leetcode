package stack;

import java.util.LinkedList;
import java.util.List;

// study later
// https://discuss.leetcode.com/topic/41870/real-iterator-in-python-java-c/51
public class NestedListIterator {

	public static void main(String[] args) {
		
	}
	private LinkedList<NestedInteger> stack = new LinkedList<>();
    public NestedListIterator (List<NestedInteger> nestedList) {
        for (int i=nestedList.size()-1; i>=0; i--)
            stack.push(nestedList.get(i));
    }

    
    public Integer next() {
        if (hasNext())
           return stack.pop().getInteger();
        else
            return null;
    }   
    
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            NestedInteger term = stack.peek();
            if (term.isInteger()){
                return true;
            }else {// the top element is a List
                stack.pop();// first remove it from the stack
                for (int i=term.getList().size()-1; i>=0; i--) {//add all to the stack in reverse order
                    stack.push(term.getList().get(i)); 
                }
            }
        }
        return false;
        
    }
}
abstract class NestedInteger{// actually it is an interface!!! But interface cannot be written in the same class
	public abstract boolean isInteger();
	public abstract Integer getInteger();
	public abstract List<NestedInteger> getList();
}

  
 
