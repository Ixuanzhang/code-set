package leetcode.middle;

import java.util.*;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * Example:
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 排列问题每个元素只能选中一次，和组合问题区别
 * 输入数组可能有重复元素，应先排序，每个元素只能被选中1次，且需 for 循环遍历每个元素（每次从 0 开始，和组合问题区别），故需要 visied 数组记录遍历过的元素
 * 在添加一个元素时，判断这个元素是否等于前一个元素，如果等于，并且前一个元素还未访问，那么就跳过这个元素。
 * <p>
 * 参考元素不重复 M46_Permutations
 */
public class M47_PermutationsII {
    List<List<Integer>> res;
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        used = new boolean[nums.length];
        generatePermutations(nums, new LinkedList<>());
        return res;
    }

    private void generatePermutations(int[] nums, LinkedList<Integer> ls) {
        if (ls.size() == nums.length) {
            res.add(new LinkedList<>(ls));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            ls.add(nums[i]);
            generatePermutations(nums, ls);
            used[i] = false;
            ls.removeLast();
        }
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        boolean[] v = new boolean[nums.length];
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, nums, v);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int[] nums, boolean[] v) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //当前元素和前面元素相等，且前面元素没有被访问时跳过当前元素
            if (i > 0 && nums[i] == nums[i - 1] && !v[i - 1]) {
                continue;
            }
            //若当前元素未被访问过
            if (!v[i]) {
                v[i] = true;
                tmp.add(nums[i]);
                helper(res, tmp, nums, v);
                v[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    //求元素可能重复的数组的全排列，需 visited 标记数组
    //https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
    public List<List<Integer>> permuteUnique3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        //注意：求全排列每次从 0 开始，求子集每次从 start 开始
        for (int i = 0; i < nums.length; i++) {
            //两种情况跳过：
            //1. used[i] 为 true，即 i 位置元素已访问过，则跳过
            //2. 当前位置大于 0 并与前一位置元素相等，且前一位元素未访问即 used[i - 1] 为 false，则跳过
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, used);
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }
}
