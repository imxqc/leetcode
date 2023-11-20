myans
class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 1)
            return dividend;
        if (divisor == -1) {
            if (dividend == -2147483648)
                return 2147483647;
            return -dividend;
        }
        int ret = 0;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            ret = rt(dividend, divisor);
        }
        if ((dividend > 0 && divisor < 0)) {
            if (divisor == -2147483648)
                return 0;
            ret = -rt(dividend, -divisor);
        }
        if ((dividend < 0 && divisor > 0)) {
            ret = -rt(dividend, -divisor);
        }
        return ret;
    }

    public int rt(int dd, int dr) {//传入的都是同一符号
        if (dr > dd && dd > 0 && dr > 0)
            return 0;
        if (dr < dd && dd < 0 && dr < 0)
            return 0;
        if (dr == dd)
            return 1;
        int num = 0;
        int sam = dr;
        if (dd > 0) {
            while (dr < dd && dr > 0) {
                num++;
                dr += sam;
            }
        }
        if (dd < 0) {
            while (dr > dd && dr < 0) {
                num++;
                dr += sam;
            }
        }
        if (dr == dd)
            return num + 1;
        return num;
    }
}

官方题解
/**
 1.利用二分法查找
 dividend/divisor=result ——》》 result*divisor==dividend

public static int divide(int dividend, int divisor) {
        long result = 0;
        long x = dividend;
        long y = divisor;
        boolean negative = (x < 0 && y > 0) || (x > 0 && y < 0);

        if (x < 0) {
        x = -x;
        }
        if (y < 0) {
        y = -y;
        }
        // 由于x/y的结果肯定在[0,x]范围内，所以对x使用二分法
        long left = 0;
        long right = x;
        while (left < right) {//二分法条件
        long mid = left + right + 1 >> 1;
        if (quickMulti(mid, y) <= x) {
        // 相乘结果不大于x，左指针右移
        left = mid;
        } else {
        right = mid - 1;
        }
        }
        result = negative ? -left : left;

        // 判断是否溢出
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
        return Integer.MAX_VALUE;
        }

        return (int)result;
        }


public static long quickMulti(long a, long b) { 判断是否满足
        long result = 0;//判断mid*y是否等于x

        while (b > 0) {//二进制乘法
        if ((b & 1) == 1) {//位运算符 判断a是否还要倍增
        // 当前最低位为1，结果里加上a
        result += a;
        }
        // 被乘数右移1位，相当于除以2
        b >>= 1;
        // 乘数倍增，相当于乘以2
        a += a;
        }

        return result;
        }
 */
