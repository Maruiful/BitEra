package com.github.paicoding.forum.web.leetcode;

/**
 * 微信搜索「沉默王二」，回复 Java
 * */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestoreIPAddresses {
    List<String> ans = new ArrayList<>();
    int[] anss = new int[4];

    public List<String> restoreIpAddresses(String s)  { return null; }

    public void dfs(String s, int cnt, int pos) {
        if (cnt == 4) {
            if (pos == s.length()) {
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    temp.append(anss[i]);
                    if (i != 3) {
                        temp.append(".");
                    }
                }
                ans.add(temp.toString());
            }
            return;
        }
        if (pos == s.length()) {
            return;
        }
        if (s.charAt(pos) == '0') {
            anss[cnt] = 0;
            dfs(s, cnt + 1, pos + 1);
        } else {
            int num = 0;
            for (int i = pos; i < s.length(); i++) {
                num = num * 10 + (s.charAt(i) - '0');
                if (num > 0 && num <= 255) {
                    anss[cnt] = num;
                    dfs(s, cnt + 1, i + 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args)  {}
}
