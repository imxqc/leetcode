import java.util.HashMap;自己的答案 时间复杂度高
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int length, length_test, pre;
        pre = 0;
        length = length_test = 1;
        char[] chars = s.toCharArray();
        boolean isSame ;

        for (int i = 1; i < s.length(); i++) {
            isSame = false;
            for (int j = pre; j < i; j++) {//遍历子串 查找第i位置的字符是否和当前子串字符重复
                if (chars[j] == chars[i]) {
                    pre = j + 1;
                    if (length_test > length)
                        length = length_test;
                    length_test = 1;
                    isSame = true;
                    break;
                }
            }
            if (!isSame){
                length_test++;
                if (i == s.length() - 1&& length_test > length)//only 2
                    length = length_test;
            }else{
                i = pre;//找到了相同的 auaf aucuf
            }
        }
        if (s.length()==0)
            return 0;
        return length;
    }
}

官方答案 滑动窗口
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;//最大字符串长度
        int left = 0;//窗口起点
        for(int i = 0; i < s.length(); i ++){
            //自己的方法 先把字符串放到数组里 然后每次添加都和窗口内的比较
            //该方法 把全部字符串都加入map 但不同的是 我的方法是用数组存，需要遍历数组
            // 而该方法用集合框架 可以用对应的方法查
            //相比我的方法 该方法更简洁 且更新max更方便
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);//更新窗口起点
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);//更新字符串最大长度
        }
        return max;
    }
}
