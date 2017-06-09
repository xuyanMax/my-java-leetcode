package greedy;
//https://leetcode.com/problems/non-overlapping-intervals/#/description

// Given a collection of intervals, find the minimum number of intervals 
// you need to remove to make the rest of the intervals non-overlapping.
import java.util.Arrays;
import java.util.Comparator;

public class EraseOverlapIntervals {

	public static void main(String[] args) {


	}
	/**
	 * The problem is the same as "Given a collection of intervals, find the max number of non-overlapping intervals"
	 * one way: Sorting intervals.end in ascending order O(nlgn) 
	 * traverse intervals array to get the max number of non overlapping intervals O(n)
	 * 
	 * Time complexity - O(nlgn)
	 * @return
	 */
	class Interval{
		int start;
		int end;
		public Interval() {
			start = 0;
			end = 0;
		}
		Interval(int s, int e){
			start = s;
			end = e;
		}
	}
	public int solution(Interval[] intervals) {
		
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				return Integer.compare(a.end, b.end);
			}
		});
		
		int end = intervals[0].end;
		int non_overlapping_count = 1; //count = 0;
		for (int i=1; i<intervals.length; i++) {
			if (intervals[i].start  < end) 
				continue; 
			// or we can directly count how many we need to kick out
			// by: count++; // https://discuss.leetcode.com/topic/65594/java-least-is-most/13
			else {
				end = intervals[i].end;
				non_overlapping_count++;
			}
		}		
		
		return intervals.length - non_overlapping_count;
		// return count;
	}

}
