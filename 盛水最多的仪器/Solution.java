短板效应 height的最小值和首尾长度决定了容器大小
        area = min(height[i], height[j]) * (j - i)
因为长度一部分由height的较小值决定，所以先保存此时的面积（可以确保移动过程中如果面积减小，最大值仍然保存）
，然后向内移动较小值直到该较小值大于原来的较小值
移动过程中，比较原来的面积和移动后的面积，如果移动后的面积更大则代替（解决了长度）

答案
class Solution {
    public int maxArea(int[] height) {
        int s, l, maxarea;
        s = maxarea = 0;
        l = height.length - 1;

        while (s < l) {
            int area = Math.min(height[s], height[l]) * (l - s);
            maxarea = Math.max(area, maxarea);
            int min = Math.min(height[s], height[l]);

            while (height[s] > min && s<l) {//s为较大值
                s++;
            }
            while (height[l] <= min && s < l) {
                l--;
            }

        }
        return maxarea;
    }
}