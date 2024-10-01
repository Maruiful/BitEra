package com.github.paicoding.forum.web.leetcode;

public class Main02601 {
    public static void main(String[] args)  {}
}

class Solutions02601 {
    public int removeDuplicates(int[] nums) {
        int i = 0,j = 0;
        for(;i < nums.length;i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }
}