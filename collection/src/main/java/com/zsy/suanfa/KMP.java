package com.zsy.suanfa;

public class KMP {
    // 计算部分匹配表
    public static int[] computeLPSArray(String pat) {
        int[] lps = new int[pat.length()];
        int len = 0;
        lps[0] = 0; // lps[0] 总是 0

        int i = 1;
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len!= 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    // KMP匹配算法
    public static int KMPSearch(String pat, String txt) {
        int[] lps = computeLPSArray(pat);
        int i = 0; // 文本字符串的索引
        int j = 0; // 模式字符串的索引
        while (i < txt.length()) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }

            if (j == pat.length()) {
                return i - j; // 找到匹配
            } else if (i < txt.length() && pat.charAt(j)!= txt.charAt(i)) {
                if (j!= 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1; // 未找到匹配
    }
}
