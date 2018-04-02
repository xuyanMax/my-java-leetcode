package backTracing.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xu
 * 60. Permutation Sequence
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

	By listing and labeling all of the permutations in order,
	We get the following sequence (i.e., for n = 3):
	
	"123"
	"132"
	"213"
	"231"
	"312"
	"321"
	
	Given n and k, return the k-th permutation sequence.
	
	Note: Given n will be between 1 and 9 inclusive.
 * 
 * https://leetcode.com/problems/permutation-sequence/#/description
 * https://discuss.leetcode.com/topic/17348/explain-like-i-m-five-java-solution-in-o-n/38
 * 
 */
public class PermutationSequence {

	public static String getPermutation(int n, int k) {
		StringBuilder result = new StringBuilder();
		int[] factorial = factorial(n);// n+1 by one 
		
		List<Integer> indexNum = setIndex(n);
		k--;
		
		for (int i=1; i<=n; i++) {
			
			int index = k / factorial[n-i];
			
			result.append( indexNum.get(index) );
			
			indexNum.remove(index); // important
			
			k = k - factorial[n-i] * index;
			
		}
		

		return result.toString();
	}
	// create a list of numbers to get their indices
	// list index: 0 1 2 3 4 ... n-1
//		     key: 1 2 3 4 5 ... n
	public static List<Integer> setIndex (int n){
		List<Integer> indexNum = new ArrayList<>();
		for (int i=1; i<=n;i++) 
			indexNum.add(i);
		
		return indexNum;
	}
	//create an arr of factorial lookup
	public static int[] factorial(int n)  {
		int[] factorial = new int[n + 1];
		int sum = 1;
		for (int i=1; i<=n; i++) {
			sum *= i;
			factorial[i] = sum;
		}
		factorial[0] = 1;
		
		return factorial;
	}
	


}
