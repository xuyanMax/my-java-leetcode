package weChat;
/**
 * 
 * @author xu
 * 给定一个整数sum, 从有n个有序元素的数组中寻找元素a，b，是的a+b的结果最接近sum，最快的平均时间复杂度是
 * O(n)
 * 1、题目说数组有序并缺要求a+b结果最接近sum即可，不要求相等
 * 利用两个pointer，一个指向head，一个指向tail
 * diff = head + tail -sum
 * 如果 diff<0 那么head++；如果>0 tail--；否则 diff=0 返回结果
 * 
 * 2、如果要求a+b和恰好等于sum 那么时间复杂度O(nlgn)，原因是“遍历每一个元素+二分查找”
 */
public class num44 {

	public static void main(String[] args) {
		int[] arr = new int[]{1,1,2,3,5,8,13,21};
		int sum = 16;
		int[] result = solution2(sum, arr);
		for (int n:result)
			System.out.print(n+" ");
		
	}
	/*
	 * 
	 */
	static int[] solution(int sum, int[]arr){
		int i=0;
		int j=arr.length-1;
		int diff = arr[0]+arr[arr.length-1]-sum;
		int tmp = 0;
		while (i<j) {
			  tmp = arr[i]+arr[j]-sum;
			  diff = Math.abs(tmp)<Math.abs(diff)?tmp:diff;
			  if (tmp<0)
				  i++;
			  else if (tmp>0)
				  j--;
			  else return new int[] {i,j,diff};
		}
		return new int[]{-1,-1,diff};
	}
	/*
	 * 遍历每个元素+二分查找
	 */
	static int[] solution2(int sum, int[]arr) {
		for (int i=0;i<arr.length;i++) {
			int target = sum-arr[i];
			if (binarySearch(arr, target, i, arr.length-1)!= -1)
				return new int[] {i,binarySearch(arr, target, i, arr.length-1)};
		}
		
		return new int[] {-1};
	}
	static int binarySearch(int[]arr, int target, int low, int high) {
		
		while (low<high) {
			int mid = (low+high)/2;
			if (arr[mid]<target)
				low = mid+1;
			else if (arr[mid]>target) 
				high = mid-1;
			else return mid;
		}
		return -1;
	}
}
