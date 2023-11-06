import java.util.ArrayList;假设numRows为4，那就是那s每一位的行数就是：1234321234321

class Solution {

    public String convert(String s, int numRows) {
        if (numRows==1)
            return s;
        int[] mark = new int[s.length()];
        int index = 1;
        char[] rt = new char[s.length()];
        //为字符串s标记
        if (numRows == 2) {
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 0)
                    mark[i] = 1;
                else
                    mark[i] = 2;
            }
        }
        else{
            for (int i = 0; i < s.length(); i++) {
                mark[i] = index;
                if (index < numRows)
                    index++;
                if (index == numRows) {
                    i++;
                    for (int j = numRows; j >= 1 && i + 1 <= s.length(); j--) {
                        mark[i] = j;
                        if (j != 1)
                            i++;
                    }
                    index = 2;
                }
            }
        }

        for (int i = 0; i <= s.length(); i++) {
            for (int z = 1; z <= numRows; z++) {
                for (int j = 0; j < s.length(); j++) {//从mark中找索引为1的，将该位置j添加到rt里
                    if (mark[j] == z) {
                        rt[i] = s.charAt(j);
                        i++;
                    }
                }
            }
        }

        String str = new String(rt);
        return str;
    }
}


官方答案1
class Solution {
    public String convert(String s, int numRows) {
        if(numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();//建立集合，模拟每一行
        //虽然集合添加的是一行的元素，但可以想象成n字形排列
        for(int i = 0; i < numRows; i++)
            rows.add(new StringBuilder());
        int i = 0, flag = -1;//flag作用是让字符串形成n字形，
        // 也就是输入以从上往下、从左到右
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1)//i==0/i==numrows-1时，行需要递增/递减
                flag = - flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) //将每一行都加入到字符串里
            res.append(row);
        return res.toString();
    }
}

方法2 利用二维矩阵模拟
行数为numROWS
        根据题意，当我们在矩阵上填写字符时，会向下填写 r 个字符，
        然后向右上继续填写 r−2r-2r−2 个字符，最后回到第一行，
        因此 Z 字形变换的周期 t=r+r−2=2r−2t=r+r-2=2r-2t=r+r−2=2r−2，
        每个周期会占用矩阵上的 1+r−2=r−11+r-2=r-11+r−2=r−1 列。
所以周期为[n/t]
所以列数为 [n/t]*(r-1)

class Solution {
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2;//周期
        int c = (n + t - 1) / t * (r - 1);//列数
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }
}

