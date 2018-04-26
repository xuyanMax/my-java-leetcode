package dynamic;

import java.util.Arrays;

/**
 * 
 * @author xu
 * Allow rotations and all rotations are used to stack a maximum height 
 * Total rotations is three times the size of the input
 * 
 * Given boxes of different dimensions, stack them on top of each other to get maximum height 
 * such that box on top has strictly less length and width than box under it.
 *  
 * Solution:
 * 1、Create all rotations 
 * 2、Sort by base area in non increasing order
 * 3、Create dynamic[] table and traceback[] table of the same size to
 * 4、apply longest increasing sequence to get the max height
 * 
 * time complextiy: O(N^2)
 * 
 */
public class BoxStacking {

	public static void main(String[] args) {
		BoxStacking bstack = new BoxStacking();
		Dimension[] input = new Dimension[2];
		input[0] = new Dimension(3,2,5);
		input[1] = new Dimension(1,2,4);
		int maxHeight = bstack.MaxHeight(input);
		System.out.println(maxHeight);

	}
	public int MaxHeight(Dimension[] input){
		// get all rotations of the dimensions
		Dimension[] allInput = new Dimension[input.length * 3];
		createAllDimensions(input, allInput);
		Arrays.sort(allInput);
		
		int[] dp = new int[allInput.length];
		int[] traceback = new int[allInput.length];

		//初始化dp
		for (int i=0; i<dp.length; i++) {
			dp[i] = allInput[i].height;
			traceback[i] = -1;
		}
		for (int i=1; i<dp.length; i++) {
			for (int j=0; j<i; j++) { // before i are those with larger base area 
				if (allInput[i].length < allInput[j].length &&
						allInput[i].width < allInput[j].width){
					if (dp[i] < dp[j] + allInput[i].height) {
						dp[i] = dp[j] + allInput[i].height;
						traceback[i] = j;
					}
				}
			}
		}
		/**
		 * find the largest height
		 */
		int maxHeight = Integer.MIN_VALUE;
		int index = -1;
		for (int n=0; n<dp.length; n++){
			System.out.println(dp[n]);
			if(dp[n] > maxHeight) {
				maxHeight = dp[n];
				index = n;
			}
		}
		/**
		 * print the stacking boxes
		 */
		while (traceback[index] != -1) {
			System.out.println(allInput[index]);
			index = traceback[index];
		}
		Arrays.sort(allInput);
		
		return maxHeight;
	}
	public Dimension[] createAllDimensions(Dimension[] input, Dimension[] allRotationInput){
		
		int index = 0;
		for (int i=0; i<input.length; i++) {
			// create three possibilities of rotation with length, width and height being the height 
			allRotationInput[index++] = Dimension.createDimention(input[i].length, input[i].height, input[i].width);
			allRotationInput[index++] = Dimension.createDimention(input[i].width, input[i].height, input[i].length);
			allRotationInput[index++] = Dimension.createDimention(input[i].height, input[i].width, input[i].length);
		}	
		return allRotationInput;
	}
	
}

/**
 * Utility class to hold dimensions
 * By default, length is always larger than width 
 */
class Dimension implements Comparable<Dimension>{
	int height;
	int width;
	int length;
	
	public Dimension(int h, int w, int l) {
		this.height = h;
		this.width = w;
		this.length = l;
	}
    public Dimension() {
    	
	}
	
	@Override /* non increasing order*/
	public int compareTo(Dimension d){
		if (this.width * this.length >= d.length * d.width)
			return -1;
		else 
			return 1;
	}
	@Override
	public String toString(){
		return "Dimension[height: "+this.height + ", length: " + this.length +", width: " + width +"]";
	}
	
	// create a Dimension with the length > width
	static Dimension createDimention(int height, int length, int width){
		Dimension d = new Dimension();
		
		if (length > width){
			d.length = length;
			d.width = width;
		} else {
			d.length = width;
			d.width = length;
		}
		d.height = height;
		return d;
	}
	
}
