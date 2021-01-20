package mrgao.com.myaigorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArrayType {
    @Test
    public void addition_isCorrect() {
        int ar[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        assertEquals(40, maxArea(ar));
    }

    private void assertEquals(int i, int i1) {
    }

    //3. 无重复字符的最长子串
    //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

    public int lengthOfLongestSubstring(String s) {
        int length = 0;
        char[] chars = s.toCharArray();
        return length;

    }

//    11. 盛最多水的容器
//    给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
//    说明：你不能倾斜容器。
//***********************************************************************************************************************************************************************

    public int maxArea(int[] height) {
        int area = 0;
        int length = height.length;
        for (int l = 1; l < length; l++) {
            for (int i = 0; i + l < length; i++) {
                int j = i + l;
                area = Math.max(area, getArea(i, j, height));
            }
        }
        return area;
    }

    private int getArea(int i, int j, int[] height) {
        return Math.min(height[i], height[j]) * (j - i);
    }

//双指针法


    //***********************************************************************************************************************************************************************
//15. 三数之和
//    给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
//
//    注意：答案中不可以包含重复的三元组。
    //***********************************************************************************************************************************************************************
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < length; k++) {
                    if (j > k + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    if ((nums[i] + nums[j] + nums[k]) == 0) {

                        ArrayList<Integer> objects = new ArrayList<>();
                        objects.add(nums[i]);
                        objects.add(nums[j]);
                        objects.add(nums[k]);

                        list.add(objects);
                    }
                }
            }
        }

        return list;
    }

    /**
     * 冒泡排序
     *
     * @param array
     * @return
     */
    public int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
        return array;
    }

    public List<List<Integer>> threeSumOffical(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }


    //***********************************************************************************************************************************************************************
//    78. 子集
//    给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。
//
//             
//    示例 1：
//
//    输入：nums = [1,2,3]
//    输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//    示例 2：
//
//    输入：nums = [0]
//    输出：[[],[0]]
//
    //***********************************************************************************************************************************************************************
    public List<List<Integer>> subsets_enumerate(int[] nums) {
        /**
         * 循环枚举
         */
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        int length = nums.length;
        for (int i = 0; i <length; i++) {
            for (int j = 0; j <length ; j++) {
                List<Integer> newSub = new ArrayList<>(res.get(j));
                newSub.add(nums[i]);
                res.add(newSub);
            }
        }
        return res;
    }

    /**
     * DFS，前序遍历
     */
    public static void preOrder(int[] nums, int i, ArrayList<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) return;
        // 到了新的状态，记录新的路径，要重新拷贝一份
        subset = new ArrayList<Integer>(subset);

        // 这里
        res.add(subset);
        preOrder(nums, i + 1, subset, res);
        subset.add(nums[i]);
        preOrder(nums, i + 1, subset, res);
    }


    /**
     * DFS，中序遍历
     */
    public static void inOrder(int[] nums, int i, ArrayList<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) return;
        subset = new ArrayList<Integer>(subset);

        inOrder(nums, i + 1, subset, res);
        subset.add(nums[i]);
        // 这里
        res.add(subset);
        inOrder(nums, i + 1, subset, res);
    }

    /**
     * DFS，后序遍历
     */
    public static void postOrder(int[] nums, int i, ArrayList<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) return;
        subset = new ArrayList<Integer>(subset);

        postOrder(nums, i + 1, subset, res);
        subset.add(nums[i]);
        postOrder(nums, i + 1, subset, res);
        // 这里
        res.add(subset);
    }




}