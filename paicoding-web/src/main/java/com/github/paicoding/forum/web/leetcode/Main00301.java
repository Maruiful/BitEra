package com.github.paicoding.forum.web.leetcode;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
import java.util.HashMap;
import java.util.Scanner;

public class Main00301 {
    public static void main(String[] args)  {}

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> rempos = new HashMap<>();
        int res = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            System.out.println("i: " + i + ", j: " + j);
            if (rempos.containsKey(s.charAt(i))) {
                j = Math.max(j, rempos.get(s.charAt(i)));
            }
            System.out.println("i: " + i + ", j: " + j);
            // 把子串也打印出来
            System.out.println("sub: " + s.substring(j, i + 1));
            res = Math.max(res, i - j + 1);
            rempos.put(s.charAt(i), i + 1);
        }
        return res;
    }
}

