package stack;
//https://leetcode.com/problems/mini-parser/#/description
/*
 * Given s = "324",

   You should return a NestedInteger object which contains a single integer 324.
 * 
 * Given s = "[123,[456,[789]]]",

   Return a NestedInteger object containing a nested list with 2 elements:
 */
public class Deserialize {
	
}
/*
 * Use two pointers left right, Integer.valueOf (str.substring(left, right-left+1)) to convert string to integer
 * Use a stack to hold the different levels of nested ones
 * 
 * Iterative approach
 * key-structure: a stack & two pointers for windowing substring & a current NestedInteger object
 * 
 * current NestedInteger was set to null at first. 
 * case1:'[' 
 * if current ≠ null 
 * 	push to stack
 * else
 * 	curr = new NestedInteged
 * 
 * 
 * case2:']'
 * 	
 *  do pop out the stack and end the current and pop.push(current) until the stack is empty
 * 
 * case3:'0-9'
 * 	do nothing, but move the right pointer one step;
 * 
 * case4: ',' 
 * 	add the number string to the current NestedInteger 
 * 
 */
