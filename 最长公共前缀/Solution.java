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

答案1
        String substring(int beginIndex, int endIndex) ：返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串。
substring搭配startwith，以第一个字符串为准全拼为准，如果不匹配前缩一位
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0)return "";
        //公共前缀比所有字符串都短，随便选一个先
        String s=strs[0];
        for (String string : strs) {
            while(!string.startsWith(s)){
                if(s.length()==0)return "";
                //公共前缀不匹配就让它变短！
                s=s.substring(0,s.length()-1);
            }
        }
        return s;
    }
}

答案2递归

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];//对比基准
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;//index求出str1和str2前index位相同
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);//返回0，index位的str1
    }
}

总的思路是创建第一个的string 然后（利用substring，charat，startwith等方法，若不等则用substring长度减一）依次和对面对比