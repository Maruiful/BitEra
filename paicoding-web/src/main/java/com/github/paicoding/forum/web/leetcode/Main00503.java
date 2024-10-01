package com.github.paicoding.forum.web.leetcode;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
public class Main00503 {
    public static void main(String[] args)  {}
}
class Solution00503 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0; // 最长回文子串的起始位置
        int end = 0;   // 最长回文子串的结束位置
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 以单个字符为中心的回文长度
            int len2 = expandAroundCenter(s, i, i + 1); // 以两个字符之间为中心的回文长度
            int len = Math.max(len1, len2); // 当前找到的最长回文长度
            if (len > end - start) { // 更新最长回文子串的位置
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1); // 返回最长回文子串
    }

    // 中心扩展函数
    private int expandAroundCenter(String s, int left, int right)  { return 0; }
}