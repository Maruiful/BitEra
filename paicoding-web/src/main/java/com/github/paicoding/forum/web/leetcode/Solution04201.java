package com.github.paicoding.forum.web.leetcode;

public class Solution04201 {
    public int trap(int[] height) {
        int n = height.length;
        int totalWater = 0;

        for (int i = 0; i < n; i++) {
            int leftMax = 0, rightMax = 0;

            // 找到左边最高的柱子
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // 找到右边最高的柱子
            for (int j = i; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            // 当前柱子能接的雨水量
            totalWater += Math.min(leftMax, rightMax) - height[i];
        }

        return totalWater;
    }

    public static void main(String[] args)  {}
}
