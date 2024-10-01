package com.github.paicoding.forum.web.leetcode;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
import java.util.ArrayList;
import java.util.List;

public class SubsetsSolution {

    public static void main(String[] args)  {}

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), 0, res);
        return res;
    }

    public void backtrack(int[] nums, List<Integer> current, int start, List<List<Integer>> res)  {}
}
