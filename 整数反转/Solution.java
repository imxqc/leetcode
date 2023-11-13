自己的答案
class Solution {
    public int reverse(int x) {
        if (x==0)
            return x;
        int re = 0;//反转数字
        int standard[] = new int[]{2, 1, 4, 7, 4, 8, 3, 6, 4, 7};
        int num[] = new int[10];
        int index = 0;
        int mul = 1;
        if (x < 0)
            standard[9] = 8;
        //将数字存入数组
        while (x != 0) {
            num[index] = Math.abs(x % 10);//取绝对值
            x /= 10;
            index++;
        }
        //为mul正确赋值
        for (int i = 1; i < index; i++) {
            mul *= 10;
        }
        //判断反转后的数字是否越界
        if (index == 10) {//都是十位 反转数字可能超过了32位
            for (int i = 0; i < index; i++) {
                if (standard[i] < num[i]) {
                    return 0;
                }else if(standard[i] > num[i])
                    break;
            }
        }
        //从num数组输出数字
        for (int i = 0; i < index; i++, mul /= 10) {
            re += mul * num[i];
        }
        if (standard[9] == 8)
            return -re;
        return re;
    }
}

官方题解1 如果有10位，判断退出条件 第九位==最大数/10且第十位大于最大数的第十位 或者 第九位>最大数 负数类似
class Solution {
    public int reverse(int x) {
        int res = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }
}

me：先获得数的所有最末位存入数组num 然后再通过该数组计算出数re
该方法：获得最末位的同时计算数re

方法2 如果有10位，判断退出条件 反转数re大于最大数/10 或者 第九位==最大数/10且第十位大于最大数的第十位
class Solution {
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }
}
