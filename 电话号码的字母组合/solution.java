自己的答案 效率不高
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCombinations(String digits) {
        List list = new ArrayList();

        if (digits.length() == 0)
            return list;

        add(digits, 0, new StringBuilder(""),list);
        System.out.println(list);
        return list;
    }

    public void add(String dig, int num, StringBuilder rt,List<String> list) {//每次要重新new stringbuilder
        char[] arr = array(dig.charAt(num));
        String str = rt.toString();
        if (num == dig.length() - 1) {
            for (int i = 0; i < arr.length; i++) {
                StringBuilder rt_sam = new StringBuilder(str);
                rt_sam.append(arr[i]);
                list.add(rt_sam.toString());
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                StringBuilder rt_sam = new StringBuilder(str);
                rt_sam.append(arr[i]);
                add(dig, num + 1, rt_sam,list);
            }
        }
    }

    public char[] array(char word) //获取对应数组
    {
        if (word == '2')
            return new char[]{'a', 'b', 'c'};
        if (word == '3')
            return new char[]{'d', 'e', 'f'};
        if (word == '4')
            return new char[]{'g', 'h', 'i'};
        if (word == '5')
            return new char[]{'j', 'k', 'l'};
        if (word == '6')
            return new char[]{'m', 'n', 'o'};
        if (word == '7')
            return new char[]{'p', 'q', 'r', 's'};
        if (word == '8')
            return new char[]{'t', 'u', 'v'};
        if (word == '9')
            return new char[]{'w', 'x', 'y', 'z'};
        return null;
    }
}

官方答案1 学习数组的创建，以及通过ascii码获得数组上不同位置的元素
class Solution {
    String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits==null || digits.length()==0) {
            return new ArrayList<>();
        }
        iterStr(digits, new StringBuilder(), 0);
        return res;
    }

    void iterStr(String str, StringBuilder letter, int index) {
        if(index == str.length()) {
            res.add(letter.toString());
            return;
        }
        char c = str.charAt(index);
        int pos = c - '0';//获取数字
        String map_string = letter_map[pos];
        for(int i=0;i<map_string.length();i++) {
            letter.append(map_string.charAt(i));
            iterStr(str, letter, index+1);
            letter.deleteCharAt(letter.length()-1);//删去该数字上一次增加的字符
        }
    }
}

官方答案2 通过队列
class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits==null || digits.length()==0) {
            return new ArrayList<String>();
        }
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        String[] letter_map = {
                " ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
        };
        List<String> res = new ArrayList<>();
        //先往队列中加入一个空字符
        res.add("");
        for(int i=0;i<digits.length();i++) {
            //由当前遍历到的字符，取字典表中查找对应的字符串
            String letters = letter_map[digits.charAt(i)-'0'];
            int size = res.size();
            //计算出队列长度后，将队列中的每个元素挨个拿出来
            for(int j=0;j<size;j++) {
                //每次都从队列中拿出第一个元素
                String tmp = res.remove(0);
                //然后跟"def"这样的字符串拼接，并再次放到队列中
                for(int k=0;k<letters.length();k++) {
                    res.add(tmp+letters.charAt(k));
                }
            }
        }
        return res;
    }
}