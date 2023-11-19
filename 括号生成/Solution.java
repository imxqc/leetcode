import java.util.ArrayList;
import java.util.List;自己的答案 效率太低
class Solution {
    ArrayList list = new ArrayList();
    public List<String> generateParenthesis(int n) {
        if (n == 0)
            return list;
        list.add("()");
        if (n == 1) {
            return list;
        }

        getKH(n, 1, list);
        return list;
    }

    public void getKH(int n, int num, List<String> list) {//递归方法
        if (num != n ) {
            int len = list.size();
            for (int i = 0; i < len; i++) {
                String str = list.get(0);
                for (int j = str.length() - 1; j >= 0; j--) {
                    StringBuilder st = new StringBuilder(str);
                    st.insert(j + 1, "()");
                    if (list.indexOf(st.toString()) == -1) {//list不存在和st相同的元素
                        list.add(st.toString());
                    }
                }
                list.remove(0);
            }
            getKH(n, num + 1, list);
        } else {
            return;
        }
        return;
    }
}

官方题解1
深度优先遍历
规则：左括号永远比右括号多，此规则作为递归的终止条件
        所有正确的括号的左右括号个数都为n，可作为添加条件
减法版本
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        dfs("", n, n, res);
        return res;
    }

    private void dfs(String curStr, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }
}
加法版本

public class Solution {

    // 做加法

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        dfs("", 0, 0, n, res);
        return res;
    }

    private void dfs(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs(curStr + ")", left, right + 1, n, res);
        }
    }
}

