package stack;

import java.util.Stack;

/*
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/#/description
 * https://discuss.leetcode.com/topic/35973/java-intuitive-22ms-solution-with-stack
 */
public class VerifyPreorderSerialization {

	public static void main(String[] args) {

	}
	/*
	 * WE CONSIDER NULL AS LEAVE, then
	 * 1、non-null node provides two outdegree and one indegree except root
	 * 2、null node provides one indegree and 0 outdegree.
	 * 
	 * As we are building this binary tree, we record the difference between outdegree and indegree (diff = out - in).
	 * When the next node comes, we first decrease diff by 1 (indegree); if the node is non-null, add diff by 2 (two outdegrees).
	 * 
	 * If the serialization is correct, the diff will be non-negative, otherwise invalid.
	 * Finally, the in-degree should be equal to out-degree, meaning diff = 0;
	 */
	public boolean isValidSerialization(String preorder) {
		String[] nodes = preorder.split(",");
		
		int diff = 1; // initialization for the first node root that will decrement by 1
		for (String str:nodes) {
			diff--;
			if (diff < 0)
				return false;
			if (!str.equals("#"))// if it is non-null node
				diff += 2;
		}
		return diff == 0;
	}
	
	/*
	 * STILL take NON_NULL as LEAVES
	 * non-leaves can only be "#"
	 * #leaves = 1 + #non-leaves
	 * 
	 * We need to find the SHORTEST prefix of the serialization satisfying this property.
	 * If such prefix does not exist, the invalid.
	 * If it does exists, its prefix should be ENTIRE serialization otherwise it is a invalid serialization.
	 * For example. [9,3,4,#,#, 1, #,#,#,2,	#, 6, #, #] 
	 */
	public boolean isValidSerialization2(String preorder) {
		String[] node = preorder.split(",");
		int numLeaves = 0;
		int numNonLeaves = 0;
		int pre=0;
		for (; pre<node.length && (numLeaves + 1 != numNonLeaves); pre++){
			if (!node[pre].equals("#")) 
				numLeaves++;
			else 
				numNonLeaves++;
		}
		return (pre==node.length && numLeaves + 1 == numNonLeaves);
			
	}
	public boolean isValidSerialization3(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) {
            return false;
        }
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            st.push(curr);
        }
        return st.size() == 1 && st.peek().equals("#");
    }
	

}
