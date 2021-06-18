package mrgao.com.myaigorithms;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class StringTypes {
    private int num;
    private int targetNum;
    private String tartgetStr;

    @Test
    public void addition_isCorrect() {
        char[] a = {'a', 'b'};
        assertEquals(1, consumer());
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

    public String longestPalindrome(String s) {
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


    public int countSubstrings(String s) {
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


    //    22. 括号生成
//    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    //*****************************************************************************************************
    public List<String> generateParenthesis(int n) {
        ArrayList<String> strings = new ArrayList<>();
        if (n > 0) {
            String s = "";
            dfs(n - 1, s, strings);
            return strings;
        }

        return strings;
    }

    void dfs(int n, String s, ArrayList<String> strings) {
        if (n == 0) {
            strings.add("(" + s + ")");
        }
        s += "()";
        dfs(n - 1, s, strings);
        s = s.substring(0, s.length() - 2);
        s += ")(";
        dfs(n - 1, s, strings);
    }
    // 做加法

    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        dfs("", 0, 0, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n      左括号、右括号一共得用几个
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs(curStr + ")", left, right + 1, n, res);
        }
    }

    //理解自己思考（）和）（
    //官方回答为考虑单个（  然后）要和（对应
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


//**********************************************************************************************************************************************************


    public int test(int num) {
        this.num = num;
        this.targetNum = -1;
        ArrayList<Integer> integers = getList(num);
        find(integers, 0);
        return targetNum == -1 ? num : targetNum;
    }

    public void find(ArrayList<Integer> integers, int i) {
        int size = integers.size();
        if (size == 0) {
            if (i > num) {
                System.out.println(i);
                if (targetNum == -1) {
                    targetNum = i;
                } else {
                    targetNum = Math.min(targetNum, i);
                }
            }
            return;
        }
        for (int j = 0; j < size; j++) {
            int m = i * 10 + integers.get(j);
            ArrayList<Integer> newList = new ArrayList<>();
            newList.addAll(integers);
            newList.remove(j);
            find(newList, m);
        }
    }

    public ArrayList<Integer> getList(int num) {
        ArrayList<Integer> list = new ArrayList<>();
        while (num > 0) {
            int i = num % 10;
            list.add(i);
            num = num / 10;
        }
        return list;
    }

    public String FindIncreseSubStr(String num) {
        this.tartgetStr = "";
        int length = num.length();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                String substring = num.substring(i, j);
                checkIsIncress(substring);
            }
        }
        return tartgetStr;
    }


    private void checkIsIncress(String substring) {
        char[] chars = substring.toCharArray();
        String pattern = "[0-9]{1,}";
        boolean matches = Pattern.matches(pattern, substring);
        System.out.println(matches);
        System.out.println(substring);

        String subStr = checkIsAllNum(substring, chars);
        if (subStr.length() > tartgetStr.length()) {
            tartgetStr = subStr;
        }

    }

    private String checkIsAllNum(String substring, char[] chars) {

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return "";
            }
            if (i > 0 && chars[i] < chars[i - 1]) {
                return "";
            }
        }
        return substring;
    }

    int goodNum = 0;

    public int consumer() {
        final goods goods = new goods();
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    while (true) {
                        goods.provide();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    while (true) {

                        goods.consume();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread1.start();
        return 1;
    }


}


class goods {
    int goodNum = 0;


    public synchronized void provide() throws InterruptedException {
        while (goodNum >= 5) {
            this.wait();
        }
        this.goodNum++;
        System.out.println("provide" + this.goodNum);
        this.notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (goodNum <= 0) {
            this.wait();
        }
        this.goodNum--;
        System.out.println("consume" + this.goodNum);
        this.notifyAll();
    }


}