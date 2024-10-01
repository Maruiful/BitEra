package com.github.paicoding.forum.web.leetcode;

class Solution04102 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 将每个数放到正确的位置上
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 交换 nums[i] 和 nums[nums[i] - 1]
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        // 寻找第一个不满足条件的位置
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 如果所有位置都满足条件，则返回 n + 1
        return n + 1;
    }

    public static void main(String[] args)  {}
}