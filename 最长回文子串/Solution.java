自己的答案 时间复杂度过高 空间复杂度一般
方法是将一个字符串从前往后（或者反过来）逐次递减返回回文

调用方法 同样将字符串从前往后（或者反过来）并调用方法，可以解决字符串中间是回文两端不是的问题
class Solution {


    public static String longestPalindrome(String s) {
        if (s.length() == 1)
            return s;
        String rt = s.charAt(0) + "";
        String rcv;
        String str = s;

        for (int i = 0; i < s.length() - 1; i++) {
            if (rt.length() >= str.length())
                break;
            rcv = isPalindrome(str, true, 0);
            if (rcv.length() > rt.length())
                rt = rcv;
            rcv = isPalindrome(str, false, str.length());
            if (rcv.length() > rt.length())
                rt = rcv;
            str = s.substring(i + 1, s.length());
        }
        str = s;
        for (int i = s.length(); i > 1; i--) {
            if (rt.length() >= str.length())
                break;
            rcv = isPalindrome(str, true, 0);
            if (rcv.length() > rt.length())
                rt = rcv;
            rcv = isPalindrome(str, false, str.length());
            if (rcv.length() > rt.length())
                rt = rcv;
            str = s.substring(0, i - 1);
        }


        return rt;
    }

    public static String isPalindrome(String s, boolean right, int num) {
        String rcv = s.charAt(0) + "";//不是回文返回第一个字符
//        String rcv = null;
        boolean isTrue = true;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                isTrue = false;
                break;
            }
        }
        if (isTrue)
            rcv = s;

        if (!isTrue) {//子串不是回文
            if (right && num < s.length() - 1) {//从左向右每次减一
                rcv = isPalindrome(s.substring(num + 1, s.length()), true, num + 1);
            } else if (!right) {
                rcv = isPalindrome(s.substring(0, num - 1), false, num - 1);
            }
        }

        return rcv;
    }
}

官方答案1 每次传入一个子串，如果是回文更新begin和回文长度
public class Solution {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {//j - i + 1 > maxLen意思是之后只看长度大于目前最长回文子串的子串
                    maxLen = j - i + 1;//validpalindromic方法每次传入一个子串
                    begin = i;//更新起始位
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串 s[left..right] 是否为回文串
     */
    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

方法二：动态规划
        回文天然具有「状态转移」性质：一个长度严格大于 222 的回文去掉头尾字符以后，剩下的部分依然是回文。
        反之，如果一个字符串头尾两个字符都不相等，那么这个字符串一定不是回文。「动态规划」的方法根据这样的性质得到。

public class Solution {

    public String longestPalindrome(String s) {
        // 特殊用例判断
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {//两个for循环设置字符串数组任意若干个间是否是回文的数组dp
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {//如果子串小于3，且满足外层相同，就是回文
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];//如果满足外层相同，则根据内层是不是回文决定该子串是不是回文
                    }//两个for循环已经将任意若干个间是否是回文的数组dp设定好了
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}

方法三：中心扩散法
        「中心扩散法」的基本思想是：遍历每一个下标，以这个下标为中心，利用「回文串」中心对称的特点，
        往两边扩散，看最多能扩散多远。
奇数个 中心就是中间的一个字符串 偶数个 中心就是中间的两个字符串

public class Solution {

    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2) return s;

        int maxLen = 0;
        // 数组第一位记录起始位置，第二位记录长度
        int[] res = new int[2];
        for (int i = 0; i < s.length() - 1; i++) {
            int[] odd = centerSpread(s, i, i);//奇数回文
            int[] even = centerSpread(s, i, i + 1);//偶数回文
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }

    private int[] centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {//如果匹配，l--，r++，看中心外一层是否匹配
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right - left - 1};
        //left+1,right-l-1第一个参数是因为 l如果匹配相等，则会-1，r会+1，left+1就是正确是回文串的左起始位
        //而第二个参数是回文串的长度，是Right-Left+1,但是right比实际大了1，l小了1
        //所以长度就是： （right-1）-（left+1）+1 = right-1-left-1+1=right-left-1
    }
}
