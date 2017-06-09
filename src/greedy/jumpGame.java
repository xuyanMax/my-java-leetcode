package greedy;

public class jumpGame {

	public static void main(String[] args) {
		System.out.println(jumpGame1(new int[]{2,5,0,0}));
		System.out.println(jumpGame1(new int[]{0,1}));

	}
	public static boolean jumpGame1(int[] nums) {
		int reach = 0;
		for (int i=0; i<nums.length && i<=reach; i++) {
			reach = Math.max(i + nums[i], reach);
		}
		if (reach >= nums.length-1)
			return true;
		else 
			return false;
	}
	public static boolean jumGame2(int[] nums) {
		int maxStep = 0;
		int i=0;
		for (; i<nums.length && i + maxStep < nums.length;) {
			if (i == nums.length - 1) // [0]
				return true;
			maxStep = Math.max(nums[i], maxStep);
	
			if (maxStep == 0 && i <nums.length)
                return false; // [0, 1]
			i++;
			maxStep--;
		}
		if (i + maxStep >= nums.length-1 || i >= nums.length-1)
			return true;
		else 
			return false;
	}

}
