package twoPointers;

/**
 * 
 * https://leetcode.com/problems/reverse-string/#/description
 * reverse a string
 * @author xu
 *
 */
public class ReverserString {

	public static void main(String[] args) {
		System.out.println(reverse("abcde"));

	}
	public static String reverse (String input){
		char[] str = input.toCharArray();
		int left = 0, right = input.length()-1;
		while (left < right) {
			swap(left,right, str);
			left++;
			right--;
		}
		return String.valueOf(str);// return new String (str);
	}
	private static void swap (int left, int right, char[] str) {
		char tmp = str[left];
		str[left] = str[right];
		str[right] = tmp;
	}

}
