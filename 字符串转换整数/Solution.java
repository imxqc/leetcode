自己的答案 未通过
class Solution {
    public int myAtoi(String s) {
        int re, start, last;
        re = start = last = 0;
        boolean findFirst = false;
        boolean isnegative = false;
        boolean got = false;
        if (s.length() == 0)
            return 0;
        //确定位数 起始位置
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == '+' || s.charAt(i) == '-') && got)
                return 0;
            if (s.charAt(i) == '+') {
                got = true;
                continue;
            } else if (s.charAt(i) == '-') {
                isnegative = true;
                got = true;
                continue;
            } else if (Character.isDigit(s.charAt(i))&&s.charAt(i)!='0') {
                start = i;
                break;
            } else if (s.charAt(i) == ' ') {
                continue;
            } else if (s.charAt(i)=='0') {
                continue;
            } else
                return 0;
        }

        if (start + 1 == s.length())
            last = start;
        for (int j = start + 1; j < s.length(); j++) {
            if (!Character.isDigit(s.charAt(j))) {
                if (s.charAt(j)==' '||s.charAt(j)=='.'){
                    last = j - 1;
                    break;
                }else
                    return 0;
            } else{
                if (s.charAt(j)=='0'){
                    if (Character.isDigit(s.charAt(start))&&s.charAt(start)!='0')
                        last = j;
                    else
                        return 0;
                }else{
                    last = j;
                }

            }
        }

        if (start==0&&last==0&&s.charAt(0)=='0')
            return 0;
        if (last - start > 9 && isnegative)
            return -2147483648;
        if (last - start > 9 && !isnegative)
            return 2147483647;

        if (start == 0 && (s.charAt(0) == ' ' || s.charAt(0) == '+' || s.charAt(0) == '-'))
            return 0;
        for (int i = start; i <= last; i++) {
            re += Integer.parseInt(s.charAt(i) + "");
            if (re > 214748364 || (re == 2147483647 && (int) s.charAt(i + 1) > 7))
                return 2147483647;
            if (re < -214748364 || (re == -2147483648 && (int) s.charAt(i + 1) > 8))
                return -2147483648;
            if (i + 1 <= last)
                re *= 10;
        }
        if (isnegative)
            return -re;
        return re;
    }
}

"00000-42a1234" 要求输出0 "4193 with words"要求输出 4193 "3.14159" 要求输出3 " -0012a42"
        要求输出-12 看不懂啥意思 一开始以为只要是最后一位数的后面是.或者空格 就可以把该数输出 结果又来第四个

自己的代码：
1.算出起始位的目的是从小位到大位算出re，
        但可以让re=0， res = res * 10 + sign * (currChar - '0'); 从大位到小位算出re
2.0可以忽略，加到re中对re没有影响
3.为对+-符号特殊处理
4.应前导跳过空格
题解1
public class Solution {

    public int myAtoi(String str) {
        int len = str.length();
        // str.charAt(i) 方法回去检查下标的合法性，一般先转换成字符数组
        char[] charArray = str.toCharArray();

        // 1、去除前导空格
        int index = 0;
        while (index < len && charArray[index] == ' ') {
            index++;
        }

        // 2、如果已经遍历完成（针对极端用例 "      "）
        if (index == len) {
            return 0;
        }

        // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        int sign = 1;
        char firstChar = charArray[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // 4、将后续出现的数字字符进行转换
        // 不能使用 long 类型，这是题目说的
        int res = 0;
        while (index < len) {
            char currChar = charArray[index];
            // 4.1 先判断不合法的情况
            if (currChar > '9' || currChar < '0') {
                break;
            }

            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            res = res * 10 + sign * (currChar - '0');
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "2147483646";
        int res = solution.myAtoi(str);
        System.out.println(res);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}

改良版
class Solution {
    public int myAtoi(String s) {
        int sign = 1, ans = 0, index = 0;
        char[] array = s.toCharArray();
        while (index < array.length && array[index] == ' ') {
            index ++;
        }
        if (index < array.length && (array[index] == '-' || array[index] == '+')) {
            sign = array[index++] == '-' ? -1 : 1;
        }
        while (index < array.length && array[index] <= '9' && array[index] >= '0') {
            int digit = array[index++] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            ans = ans * 10 + digit;
        }
        return ans * sign;
    }
}

    自动机
            思路

字符串处理的题目往往涉及复杂的流程以及条件情况，如果直接上手写程序，一不小心就会写出极其臃肿的代码。

        因此，为了有条理地分析每个输入字符的处理方法，我们可以使用自动机这个概念：

        我们的程序在每个时刻有一个状态 s，每次从序列中输入一个字符 c，并根据字符 c 转移到下一个状态 s'
        。这样，我们只需要建立一个覆盖所有情况的从 s 与 c 映射到 s' 的表格即可解决题目中的问题。

class Solution {
    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }
}

class Automaton {
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}

问题：异常状态怎么处理 例a43