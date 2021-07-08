package mrgao.com.myaigorithms;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class StringTypes {
    private int num;
    private int targetNum;
    private String tartgetStr;

    @Test
    public void addition_isCorrect() {
        char[] a = {'a', 'b'};
        ArrayList<Object> objects = new ArrayList<>(1);
        objects.add(new Object());
        assertEquals("kjihgfedcba", reverse("abcdefghijk"));
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


    public int consumer() {
        final Goods goods = new Goods();

        Consumer consumer = new Consumer(goods);
        Provider provider = new Provider(goods);
        consumer.start();
        provider.start();


        return 1;
    }

    //反转数组
    public String reverse(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return "";
        }
        if (chars.length == 1) {
            return s;
        }
        for (int i = 0; i < chars.length / 2; i++) {
            swap(chars, i);
        }
        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i) {
        char ch1 = chars[i];
        chars[i] = chars[chars.length - 1 - i];
        chars[chars.length - 1 - i] = ch1;
    }


    public ListNode reverseList(ListNode head) {
        ListNode listNode = new ListNode(-1);

        while (head != null) {
//            ListNode next = listNode.next;
//            listNode.next = head;
//            head = head.next;
//            listNode.next.next = next;
            ListNode next = listNode.next;
            listNode.next = head;
            head = head.next;
            listNode.next.next = next;
        }

        return listNode.next;


    }

}

//    定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
//    示例:
//    输入: 1->2->3->4->5->NULL
//    输出: 5->4->3->2->1->NULL
//    限制：
//            0 <= 节点个数 <= 5000

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Consumer extends Thread {
    Goods goods;

    Consumer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        try {
            while (true) {
                goods.consume();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Provider extends Thread {
    Goods goods;

    Provider(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        try {
            while (true) {
                goods.provide();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Goods {
    int maxNum = 10;
    public List list = new ArrayList<Object>();

    public synchronized void provide() throws InterruptedException {
        while (list.size() == maxNum) {
            this.wait();
        }
        list.add(new Object());
        System.out.println("provide" + list.size());
        this.notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (list.isEmpty()) {
            this.wait();
        }
        list.remove(0);
        System.out.println("consume" + list.size());
        this.notifyAll();
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            swap(root);
        }
        return root;
    }

    private void swap(TreeNode root) {
        if (root.left != null || root.right != null) {
            TreeNode node = root.left;
            root.left = root.right;
            root.right = node;
            if (root.left != null) {
                swap(root.left);
            }

            if (root.right != null) {
                swap(root.right);
            }
        }

    }

    public ListNode reverseBetween(ListNode head, int left, int right) {

        // 定义一个dummyHead, 方便处理
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 初始化指针
        ListNode pre = dummyHead;
        ListNode now = dummyHead.next;

        // 将指针移到相应的位置
        for (int step = 1; step < left; step++) {
            pre = pre.next;
            now = now.next;
        }

        // 头插法插入节点
        for (int i = 0; i < right - left; i++) {
            ListNode removed = now.next;
            now.next = now.next.next;

            removed.next = pre.next;
            pre.next = removed;
        }

        return dummyHead.next;

    }

    ////////////////////////////////// 二叉树的中序遍历///////////////////////////////////
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList();
        addToArray(root, list);
        return list;

    }

    private void addToArray(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
//是否有必要生成新的节点？没有必要，因为没有涉及到更改
        TreeNode leftNode = root.left;
        addToArray(leftNode, list);
        list.add(root.val);
        TreeNode rightNode = root.right;
        addToArray(rightNode, list);
        int[] arr = new int[4];
        bubbleSort(arr);
    }


    public void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; i < length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public void quickSort(int[] arr, int begin, int end) {
        if (arr != null && arr.length > 0 && begin < end) {
            int temp = arr[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                while (i < j) {
                    if (arr[j] < temp) {
                        swap(arr, i, j);
                        break;
                    } else {
                        j--;
                    }
                }

                while (i < j) {
                    if (arr[i] > temp) {
                        swap(arr, i, j);
                        break;
                    } else {
                        i++;
                    }
                }
                quickSort(arr, begin, i - 1);
                quickSort(arr, j + 1, end);
            }
        }
    }

    /**
     * 根据下标交换数组的两个元素
     *
     * @param arr    数组
     * @param index1 下标1
     * @param index2 下标2
     */
//    public static void swap(int[] arr, int index1, int index2) {
//        int temp = arr[index1];
//        arr[index1] = arr[index2];
//        arr[index2] = temp;
//    }
//
//    /**
//     * 递归循环实现快排
//     *
//     * @param arr        数组
//     * @param startIndex 快排的开始下标
//     * @param endIndex   快排的结束下标
//     */
//    public static void quickSort(int[] arr, int startIndex, int endIndex) {
//        if (arr != null && arr.length > 0) {
//            int start = startIndex, end = endIndex;
//            //target是本次循环要排序的元素，每次循环都是确定一个元素的排序位置，这个元素都是开始下标对应的元素
//            int target = arr[startIndex];
//            //开始循环，从两头往中间循环，相遇后循环结束
//            while (start < end) {
//                //从右向左循环比较，如果比target小，就和target交换位置，让所有比target小的元素到target的左边去
//                while (start < end) {
//                    if (arr[end] < target) {
//                        swap(arr, start, end);
//                        break;
//                    } else {
//                        end--;
//                    }
//                }
//
//                //从左向右循环比较，如果比target大，就和target交换位置，让所有比target大的元素到target的右边去
//                while (start < end) {
//                    if (arr[start] > target) {
//                        swap(arr, start, end);
//                        break;
//                    } else {
//                        start++;
//                    }
//                }
//            }
//            //确定target的排序后，如果target左边还有元素，继续递归排序
//            if ((start - 1) > startIndex) {
//                quickSort(arr, startIndex, start - 1);
//            }
//            //确定target的排序后，如果target右边还有元素，继续递归排序
//            if ((end + 1) < endIndex) {
//                quickSort(arr, end + 1, endIndex);
//            }
//        }
//    }
    public void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int temp = arr[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                while (i < j) {
                    if (arr[j] >= temp) {
                        j--;
                    } else {
                        arr[i] = arr[j];
                        break;
                    }
                }

                while (i < j) {
                    if (arr[i] <= temp) {
                        i++;
                    } else {
                        arr[j] = arr[i];
                        break;
                    }
                }

                arr[i] = temp;
            }
            quickSort(arr, begin, i - 1);
            quickSort(arr, j + 1, end);
        }
    }

    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            Integer integer = map.get(nums[i]);
            int num = map.get(nums[i]);
            if (integer == null) {

                map.put(nums[i], 1);
            } else {
                map.put(nums[i], num + 1);
            }
            if (num >= length / 2) {
                return nums[i];
            }
        }
        return 0;

    }

}