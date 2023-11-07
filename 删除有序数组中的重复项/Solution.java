自己的答案 空间复杂度高

class Solution {
    public int removeDuplicates(int[] nums) {
        int num = nums[0];
        int index = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != num) {//非严格递增
                nums[index] = nums[i];
                index++;
                num = nums[i];
            }
        }
        return index;
    }
}

方法1：双指针

public class removeDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {//不连续
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }
}

