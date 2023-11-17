自己的答案
public class solution class Solution {

    public static String intToRoman(int num) {
        int sample = num;
        StringBuilder str = new StringBuilder("");
        if (sample / 1000 > 0) {
            String s = trans(sample / 1000, "M", null, null);
            str.append(s);
            sample %= 1000;
        }
        if (sample / 100 > 0) {
            String s = trans(sample / 100, "C", "D", "M");
            str.append(s);
            sample %= 100;
        }
        if (sample / 10 > 0) {
            String s = trans(sample / 10, "X", "L", "C");
            str.append(s);
            sample %= 10;
        }
        if (sample > 0) {
            String s = trans(sample, "I", "V", "X");
            str.append(s);
        }
        String ans = str.toString();
        return ans;
    }

    public static String trans(int i, String o, String f, String t) {
        StringBuilder str = new StringBuilder("");
        if (i >= 1 && i <= 3) {
            for (int j = 0; j <i; j++) {
                str.append(o);
            }
        }
        if (i == 4) {
            str.append(o);
            str.append(f);
        }
        if (i >= 5 && i <= 8) {
            str.append(f);
            for (int j = 5; j < i; j++) {
                str.append(o);
            }
        }
        if (i == 9) {
            str.append(o);
            str.append(t);
        }
        String ans = str.toString();
        return ans;
    }
}

贪心算法 尽可能优先使用较大数值对应的字符，最后转换得到的罗马数字的字符个数更少
        条件：
4、9、40、90、400、900对应的罗马数字字符只出现一次（一直减该数直到该数<nums[index]）
其余字符可以连续出现的次数不超过 3次(从大到小减)
public class Solution {
    public String intToRoman(int num) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中，并且按照阿拉伯数字的大小降序排列
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < 13) {
            // 特别注意：这里是等号 ，从1000到1 避免了上方两个条件的发送
            while (num >= nums[index]) {
                stringBuilder.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return stringBuilder.toString();
    }
}

方法2 写出每一种方法
class Solution {
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }
}

