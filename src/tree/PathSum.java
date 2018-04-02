package tree;

/**
 * @author xu 
 * 
 * 
 * https://leetcode.com/problems/path-sum/#/description
 * https://leetcode.com/problems/path-sum-ii/#/description
 * https://leetcode.com/problems/path-sum-iii/#/description
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathSum {

	public static void main(String[] args) {
		

	}
	/*
	 * first question 
	 * Given a binary tree and a sum, find if there exist a root-to-leaf path where the path's sum equals the given sum.
	 * 
	 */
	static boolean hasPathSum(AvlNode root, int sum) {
		if (root == null) return false;
		if (root.left==null && root.right==null)
			return sum == root.key;
		
		return hasPathSum(root.left, sum - root.key) || hasPathSum(root.right, sum - root.key);
	}
	
	/*
	 * second question
	 * Given a binary tree and a sum, find all ROOT-to-LEAVF paths where each path's sum equals the given sum.
	 * 
	 */
	static List<List<Integer>> pathSum2(AvlNode root, int  sum) {
		
		List<List<Integer>> paths = new ArrayList<>();
		if (root==null) return paths;
		
		List<Integer> path = new ArrayList<>();
		
		pathSum2Util(paths, path, root, sum);
		
		return paths;
		
		
	}
	static void pathSum2Util(List<List<Integer>> paths, List<Integer> path , AvlNode root, int sum) {
		
		if (root == null)
			return;
		
		path.add(root.key);
		
		if (root.left == null & root.right == null && sum == root.key)
			paths.add(new ArrayList<>(path));
		else { // 如果找到了path，不需要继续调用递归，因为已经搜索到leaf节点
			pathSum2Util(paths, path, root.left, sum-root.key);
			pathSum2Util(paths, path, root.right, sum-root.key);
		}
		path.remove(path.size()-1); // don't forget to remove the last integer
	
	}
	
	
	/*
	 * third question
	 * You are given a binary tree in which each node contains an integer value.
	 * 
	 * Find the number of paths that sum to a given value.
	 * 
	 * The path does NOT need to start or end at the root or a leaf, but it must go downwards 
	 * 
	 * (traveling only from parent nodes to child nodes).
	 * 
	 * https://discuss.leetcode.com/topic/64526/17-ms-o-n-java-prefix-sum-method/27
	 * 
	 */
	static int pathSum3(AvlNode root, int sum) {
		Map<Integer, Integer> map = new HashMap<>();
		
		map.put(0, 1); 
		
		return pathSum3Util(root, sum, 0, map);
	
	} 
	static int pathSum3Util(AvlNode root, int sum, int preSum, Map<Integer, Integer> map) {
		if (root == null)
			return 0;
		// update the prefix sum by adding the current val
		preSum += root.key;
		
		// GET the number of valid sub-path, ended by the current node, summed by sum.
		int NumPathsToCurrNode = map.getOrDefault(preSum - sum, 0); // 1 or 0
		
		map.put(preSum, map.getOrDefault(preSum, 0) + 1);
		
		int NumOfPathsIncludeCurrNode = NumPathsToCurrNode + pathSum3Util(root.left, sum, preSum, map) + pathSum3Util(root.right, sum, preSum, map);
		
		// unmake the HashMap, remove preSum to this node
		map.put(preSum, map.get(preSum)-1);
		
		return NumOfPathsIncludeCurrNode;
	}
	 
	
	
	
	/*
	 * store all paths' sum from ROOT to LEAVES.
	 */
	static List<Integer> allPathSum(AvlNode root) {
		
		if (root==null) return null;
		List<Integer> pathSums = new ArrayList<>();
		
		int sum = root.key;
		
		allPathSumUtil(root, pathSums, sum);		
		
		return pathSums;
	}
	/* A utility function */
	static void allPathSumUtil(AvlNode root, List<Integer> pathSums, int sum) {
		
		if (root==null)
			pathSums.add(sum);
		
		if (root.left!=null)
			allPathSumUtil(root.left, pathSums, sum + root.left.key);
		
		if (root.right!=null)
			allPathSumUtil(root.right, pathSums, sum + root.right.key);;
		
	}

}
