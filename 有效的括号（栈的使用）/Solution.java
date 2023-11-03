自己的错误答案
class Solution {//()[}{]
    public  boolean isValid(String s) {
        char ops;
        boolean rt = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']' || s.charAt(i) == '}' || s.charAt(i) == ')' || s.length() % 2 != 0)
                break;
            if (s.charAt(i) == '[')
                ops = ']';
            else if (s.charAt(i) == '{')
                ops = '}';
            else if (s.charAt(i) == '(')
                ops = ')';
            else
                return false;
            rt = isTrue(s, ops, i + 1);
            if (rt && i == s.length() - 2)
                i++;
        }
        return rt;
    }

    public  boolean isTrue(String s, char ops, int n) {
        boolean it = false;
        if (n > s.length() - 1)
            return false;
        if (s.charAt(n) != ops)
            it = isTrue(s, ops, n + 2);
        if (s.charAt(n) == ops)
            return true;
        return it;
    }
}

自己的正确答案
class Solution {//()[}{]


    public static boolean isValid(String s) {
        if(s.length() == 1)
            return false;
        char[] stack = new char[s.length()];
        stack[0] = s.charAt(0);
        int index = 1;//stack里括号的个数
        boolean answer = true;

        if (stack[0]=='('||stack[0]=='{'||stack[0]=='['){//第一个必须是左括号才能继续进行
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '[' || s.charAt(i) == '(' || s.charAt(i) == '{') {//左括号入栈
                    stack[index] = s.charAt(i);
                    index++;
                    continue;
                } else if (s.charAt(i) == ']' || s.charAt(i) == ')' || s.charAt(i) == '}') {//右括号和栈尾匹配
                    if (index == 0) {//栈空，右括号在开头是false，避免nullpointer
                        answer = false;
                        break;
                    }
                    if (isMatch(stack[index - 1], s.charAt(i))) {//该括号和栈尾括号匹配，删除栈尾
                        index--;
                        continue;
                    } else {//该括号和栈尾不括号匹配
                        answer = false;
                        break;
                    }
                } else {//不是括号
                    answer = false;
                    break;
                }
            }
            if (index==0)
                return answer;
            else
                return false;
        }else
            return false;
    }

    public static boolean isMatch(char a, char b) {
        if (a == '[' && b == ']')
            return true;
        if (a == '(' && b == ')')
            return true;
        if (a == '{' && b == '}')
            return true;
        return false;
    }
}