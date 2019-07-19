package twoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xu
 * 
 * Given two arrays, write a function to compute their intersection.

	Example:
	Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
	
	Note:
	Each element in the result should appear as many times as it shows in both arrays.
	The result can be in any order.
	
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/#/description
 * 
 * - Hash table
 * - two pointers for each arr
 * - we use list to store the result first and then convert to int arr since we don't know the length
 * 
 */
public class IntersectionOfTwoArrays {

	// two pointers + Arrays.sort()
	// Time complexity O(nlgn) very bad !!

	public int[] solution1(int[] arr1, int[] arr2) {
		int i = 0, j = 0;
		List<Integer> list = new ArrayList<>();


		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] == arr2[j]) {
				list.add(arr1[i]);
				i++;
				j++;
			} else if (arr1[i] > arr2[j]) {
				j++;
			} else
				i++;
		}
		int[] result = new int[list.size()];
		int index = 0;

		for (Integer n : list)
			result[index++] = n;

		return result;

	}
	// hashtable
	// time complexity O(m + n) space complextiy O(m) or O(n)

	public int[] solution2(int[] arr1, int[] arr2) {

		Map<Integer, Integer> maps = new HashMap<>();

		List<Integer> list = new ArrayList<>();
		for (int n : arr1.length >= arr2.length ? arr1 : arr2) // choose a larger one
			maps.put(n, maps.getOrDefault(n, 0) + 1);
		for (int n : arr1.length < arr2.length ? arr1 : arr2) {
			if (maps.containsKey(n)) {
				if (maps.get(n) > 0) {
					list.add(n);
					maps.put(n, maps.get(n) - 1);
				}

			}
		}
		int[] result = list.stream().mapToInt(i -> i).toArray();
		return result;
	}

}
