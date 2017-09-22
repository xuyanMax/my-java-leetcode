package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xu
 * 
 * https://leetcode.com/problems/number-of-boomerangs/#/description
 * http://hongzheng.me/leetcode/leetcode-447-number-of-boomerangs/
 */
public class NumberOfBoomerangs {

	public static void main(String[] args) {
		int[][] arr = new int[][]{{0,0},{1,0},{2,0}};
		for (int[] a: arr){
			for (int b:a)
				System.out.print(b);
			System.out.println("");
		}
		System.out.print(solution(arr));
	}
	static int solution(int[][] arr) {
		
		/* 1、针对每一个点，计算其他点与其的距离，并存放在HashMap 中，key为距离，value为点的的数量
		 * 2、现在Map中存储着[2:3, 4:2, 5:1, 3:1, ...]很多pair，根据排列的公式，有P_2 种可能
		 * 	  例如，距离为2的有3*2种，4的2*1种，5的1*0种，即0种，3的0种，总6+2共8种
		 *  
		 * */
		
		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;
		/* 遍历n^2对点组合 */
		for (int i=0; i<arr.length; i++) {
			// OR and no need to use map.claer(); Map<Integer, Integer> map = new HashMap<>();
			for (int j=i; j<arr.length; j++) {
				if (i==j) continue;// 避免自身对比
				
				int dist  = getDist(arr[i], arr[j]);
				map.put(dist, map.getOrDefault(dist, 0) + 1);//map.compute(key, remappingFunction)
			}
		
			
			for (int n : map.values()) 
				count += n*(n-1);// select two points from n and find their permutation.
			map.clear();
		
		}
		return count;
		
	}
	static int getDist(int[]rowA, int[]rowB) {
		return (rowA[0]-rowB[0])*(rowA[0]-rowB[0]) + (rowA[1]-rowB[1])*(rowA[1]-rowB[1]);
		// no need to use Math.sqrt--> and HashMap<Double, Integer> 
	}
}
