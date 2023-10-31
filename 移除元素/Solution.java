自己答案
class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        if(len==1&&val==nums[0])
            return 0;

        //把val元素排到数组后面
        for(int i=0,j=len-1;i<j;i++){
            while(nums[j]==val){
                j--;
                len--;
                if(j<0)
                    break;
            }
            if(j<0||i>j)
                break;
            if(nums[i]==val){
                nums[i] = nums[j];
                nums[j]= val;
                j--;
                len--;
            }

            if(i+1==j&&nums[j]==val)
                len--;
        }
        return len;
    }
}

官网答案
class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }
}
双指针left right
只要nums[right]位置的值不为val，就将该值放在nums[left]上
为val就right++直到不为val的位置