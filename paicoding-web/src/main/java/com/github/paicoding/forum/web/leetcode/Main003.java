package com.github.paicoding.forum.web.leetcode;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
import java.util.Scanner;

public class Main003 {
    public static void main(String[] args)  {}
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean[] book = new boolean[128]; // ASCII 字符集
            for (int j = i; j >= 0; j--) {
                if (book[s.charAt(j)]) {
                    break;
                }
                book[s.charAt(j)] = true;
                res = Math.max(res, i - j + 1);
            }
        }
        return res;
    }
}
