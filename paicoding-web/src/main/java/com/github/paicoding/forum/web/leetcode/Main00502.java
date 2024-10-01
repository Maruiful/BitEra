package com.github.paicoding.forum.web.leetcode;

import java.util.Scanner;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
public class Main00502 {
    public static void main(String[] args)  {}
}


class Solution00502 {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // 创建动态规划表
        String res = ""; // 用于存储最长回文子串

        for (int len = 0; len < n; len++) { // len 表示检查的子串长度
            for (int start = 0; start + len < n; start++) { // start 表示子串的开始位置
                int end = start + len; // end 表示子串的结束位置

                // 对于长度为 1 和 2 的子串，只需比较两端字符是否相等
                if (len == 0) {
                    dp[start][end] = true;
                } else if (len == 1) {
                    dp[start][end] = s.charAt(start) == s.charAt(end);
                } else {
                    // 对于长度大于 2 的子串，除了比较两端字符外，还需要检查内部子串是否为回文
                    dp[start][end] = (s.charAt(start) == s.charAt(end)) && dp[start + 1][end - 1];
                }

                // 更新最长回文子串
                if (dp[start][end] && len + 1 > res.length()) {
                    res = s.substring(start, end + 1);
                }
            }
        }
        return res;
    }
}