错误第一版
class Solution {
    public static void main(String[] args) {
        String str = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
    }

    public static String longestCommonPrefix(String[] strs) {
        char[] same = new char[10];
        System.out.println(same[0]);
        char[] rcv1 = strs[0].toCharArray();
        char[] rcv2 = strs[1].toCharArray();
        char[] rcv3 = strs[2].toCharArray();
        int index = 0;
        boolean isFind = false;

        while (true) {
            if ((rcv1[index] == rcv2[index]) && (rcv1[index] == rcv3[index])) {
                same[index] = rcv1[index];
                isFind = true;
                index++;
            } else if (isFind)
                break;
            if (!isFind)
                break;
        }


        if (!isFind)
            return "";
        else {
            char[] rt = new char[index];
            for (int i = 0; i < index; i++) {
                rt[i] = same[i];
            }
                return String.valueOf(rt);
        }
    }
}

自己写的答案 效率低
class Solution {

    public static String longestCommonPrefix(String[] strs) {
        char[] same = new char[1000];
        int len = strs.length;
        int min = strs[0].toCharArray().length;
        boolean isFind = false;
        int index = 0;

        if (len == 1)
            return String.valueOf(strs[0].toCharArray());

        for (int i = 0; i < len; i++) {
            char[] chars = strs[i].toCharArray();
            if (chars.length < min)
                min = chars.length;
        }

        if (min == 0)
            return "";

        for (int i = 0; i < min; i++) {//i代表该字符串的索引位置
            for (int j = 0; j < len; j++) {//j代表字符串在strs的位置
                char[] chars = strs[j].toCharArray();
                if (j == 0) {
                    same[i] = chars[i];//将第一个单词第i位赋值给same，因为第一个不用比
                    continue;
                }
                if (chars[i] != same[i]) {//i判断是否有相同的,'0'作为该位置不同的标记
                    same[i] = '0';
                    break;
                }
            }
            if (same[i] == '0' && i == 0)//第一个就不同
                return "";
            if (same[i] == '0' && i != 0) {//前面已经有相同的前缀,
                index = i;
                break;
            }
        }
        if (index == 0 && same[0] != '0' && same[1] == '0')//给只有一个相同的赋值index
            index = 1;
        if (index == 0 && same[min - 1] != '0')
            index = min;
        char[] rt = new char[index];
        for (int i = 0; i < index; i++) {//复制正确大小的same
            rt[i] = same[i];
        }
        return String.valueOf(rt);
    }


}
