package greedy;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/#/description
public class FindMinArrowShots {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{1,6},{2,8},{7,12},{10,16}}));

	}
	public static int solution(int[][] points) {

		// sort points by their ends	
		Arrays.sort(points, (p1, p2)->(p1[1]-p2[1]));
		int numOfArrow = 1;
		int currentEnd = points[0][1];
		for (int i=1; i<points.length; i++) {
			if (points[i][0] > currentEnd) { // if does not intersect, need one more arrow for next set of points
				numOfArrow++; // add more arrows
				currentEnd = points[i][1];// updateHighestHeightBtwLR current_end
			}
			else
				continue;
		}
		
		
		return numOfArrow;
	}

}
