package com.github.paicoding.forum.web.leetcode;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
import java.util.Scanner;

public class InterleavingString {

    public static void main(String[] args)  {}

    public static boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();
        if (n + m != s3.length()) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] |= (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] |= (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return f[n][m];
    }
}
