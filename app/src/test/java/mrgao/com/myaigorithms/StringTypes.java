package mrgao.com.myaigorithms;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringTypes {
@Test
    public  void addition_isCorrect() {
        assertEquals(3, countSubstrings("abc"));
    }

    /*5. 最长回文子串
    给你一个字符串 s，找到 s 中最长的回文子串。

    示例 1：
    输入：s = "babad"
    输出："bab"
    解释："aba" 同样是符合题意的答案。

    示例 2：
    输入：s = "cbbd"
    输出："bb"

    示例 3：
    输入：s = "a"
    输出："a"

    示例 4：
    输入：s = "ac"
    输出："a"
    提示：
            1 <= s.length <= 1000
    s 仅由数字和英文字母（大写和/或小写）组成*/
//    方法一：动态规划

    public  String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        //l为角标差
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    //所有的单独一个的都是满足条件的
                    dp[i][j] = true;
                } else if (l == 1) {
                    //角标差为1，代表的当前有两个，这两个需要相同才满足
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    //超过两个的，判断两侧字符是否相等并上中间的部分，中间部分在之前就已经确定下结果
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                //如果满足条件，看当前的长度是否大于之前确定下的，如果是则替换
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }


//
//    给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
//
//    具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
//
//             
//
//    示例 1：
//
//    输入："abc"
//    输出：3
//    解释：三个回文子串: "a", "b", "c"
//    示例 2：
//
//    输入："aaa"
//    输出：6
//    解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
//             
//
//    提示：
//
//    输入的字符串长度不会超过 1000 。



    public  int countSubstrings(String s) {
        int n = "abc".length();
        int sum = 0;
        boolean[][] sub = new boolean[n][n];
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (l == 0) {
                    sub[i][j] = true;
                } else if (l == 1) {
                    sub[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    sub[i][j] = s.charAt(i) == s.charAt(j) && sub[i + 1][j - 1];
                }
                if (sub[i][j]) {
                    sum++;
                }
            }
        }
        return sum;
    }
        //中心拓展
        public int countSubstrings2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

}