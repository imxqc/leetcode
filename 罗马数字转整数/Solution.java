import java.util.HashMap;
import java.util.Map;自己的答案
class Solution {
    public  int romanToInt(String s) {
        char[] rcv = s.toCharArray();
        int num = 0;

        for (int i = 0; i < rcv.length; i++) {
            if (rcv[i] == 'M') {
                num += 1000;
                continue;
            }
            if (rcv[i] == 'D') {
                num += 500;
                continue;
            }
            if (rcv[i] == 'L') {
                num += 50;
                continue;
            }
            if (rcv[i] == 'V') {
                num += 5;
                continue;
            }
            if (i == rcv.length - 1){
                if ( (rcv[i] == 'I' && i == rcv.length - 1)){
                    num+=1;
                    break;
                }
                if ( (rcv[i] == 'X' && i == rcv.length - 1)){
                    num+=10;
                    break;
                }if ( (rcv[i] == 'C' && i == rcv.length - 1)){
                    num+=100;
                    break;
                }

            }
            if ((rcv[i] == 'C' && (rcv[i + 1] != 'D' && rcv[i + 1] != 'M')) || (rcv[i] == 'C' && i == rcv.length - 1)) {
                num += 100;
                continue;
            }
            if ((rcv[i] == 'X' && (rcv[i + 1] != 'L' && rcv[i + 1] != 'C')) || (rcv[i] == 'X' && i == rcv.length - 1)) {
                num += 10;
                continue;
            }
            if ((rcv[i] == 'I' && (rcv[i + 1] != 'V' && rcv[i + 1] != 'X')) || (rcv[i] == 'I' && i == rcv.length - 1)) {
                num += 1;
                continue;
            }

            //4or9
            if (rcv[i] == 'C' && rcv[i + 1] == 'D') {
                num += 400;
                i++;
                continue;
            }
            if (rcv[i] == 'C' && rcv[i + 1] == 'M') {
                num += 900;
                i++;
                continue;
            }
            if (rcv[i] == 'X' && rcv[i + 1] == 'L') {
                num += 40;
                i++;
                continue;
            }
            if (rcv[i] == 'X' && rcv[i + 1] == 'C') {
                num += 90;
                i++;
                continue;
            }
            if (rcv[i] == 'I' && rcv[i + 1] == 'V') {
                num += 4;
                break;
            }
            if (rcv[i] == 'I' && rcv[i + 1] == 'X') {
                num += 9;
                break;
            }
        }
        return num;
    }
}

答案1
关键点 可以使用getvalue方法来获取不同字母的数字
若特殊情况小数在大数前，可以先减小数再加大树
class Solution {
    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
另外一个版本
但要考虑读取两位的情况
class Solution {
    public int romanToInt(String s) {
        s = s.replace("IV","a");
        s = s.replace("IX","b");
        s = s.replace("XL","c");
        s = s.replace("XC","d");
        s = s.replace("CD","e");
        s = s.replace("CM","f");

        int result = 0;
        for (int i=0; i<s.length(); i++) {
            result += which(s.charAt(i));
        }
        return result;
    }

    public int which(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            case 'a': return 4;
            case 'b': return 9;
            case 'c': return 40;
            case 'd': return 90;
            case 'e': return 400;
            case 'f': return 900;
        }
        return 0;
    }
}

答案2
用map键值对方法读取数字 和上面类似
class Solution {
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }
}
