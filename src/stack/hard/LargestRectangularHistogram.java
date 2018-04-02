package stack.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/*
84. Largest Rectangle in Histogram

Given n non-negative integers representing the histogram's bar height
where the width of each bar is 1,find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.

https://leetcode.com/problems/largest-rectangle-in-histogram/description/
https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
* */
public class LargestRectangularHistogram {
    public int largestRectangleArea(int[] hist) {
        int n = hist.length;
        Deque<Integer> s = new ArrayDeque<>();

        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < n) {
            //stack 存储非递减序列索引
            //每一个元素进栈一次
            // only here: i increase
            if (s.isEmpty() || hist[s.peek()] <= hist[i])
                s.push(i++);
            else {
                // here i does not increase
                // i: denotes the right index
                // tp: denotes the left index, with smallest height bar
                // so we can compute the area by height * width
                tp = s.pop();//以当前bar hist[tp]作为高度最低的bar，求面积
                area_with_top = hist[tp] * (s.isEmpty()?i:i-s.peek()-1);
                max_area = Math.max(max_area, area_with_top);
            }
        }

        // 继续处理堆栈中的数据,此时i == hist.length;
        // 将堆中数据依次弹出，以每一个弹出的bar作为smallest bar 计算面积
        while (!s.isEmpty()) {
            tp = s.pop();
            area_with_top = hist[tp] * (s.isEmpty()?i:i-s.peek()-1);
            max_area = Math.max(max_area, area_with_top);
        }
        return max_area;
    }
}
