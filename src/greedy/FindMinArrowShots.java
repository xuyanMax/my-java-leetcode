package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/#/description
 *
 * An arrow can be shot up exactly vertically from different points along the x-axis.
 * A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend.
 * There is no limit to the number of arrows that can be shot.
 * An arrow once shot keeps travelling up infinitely.
 * The problem is to find the minimum number of arrows that must be shot to burst all balloons.
 */
public class FindMinArrowShots {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1, 6}, {2, 8}, {7, 12}, {10, 16}}));
    }

    public static int solution(int[][] points) {
        // sort points by their ends
        Arrays.sort(points, (p1, p2) -> (p1[1] - p2[1]));
        int numOfArrow = 1;
        int currentEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > currentEnd) {
                numOfArrow++;
                currentEnd = points[i][1];
            }
        }
        return numOfArrow;
    }

}
