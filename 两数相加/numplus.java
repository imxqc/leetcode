两数相加
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int bit1 = getBit(l1);
        int bit2 = getBit(l2);
        int midpara = 0;
        long  x = 1;//用作算数字的大小
        long num1 = 0;
        long num2 = 0;
        long num = 0;

        midpara = bit1;

        while (bit1 > 0) {
            num1 += l1.val * x;
            bit1--;
            x *= 10;
            l1 = l1.next;
        }

        bit1 = midpara;
        x = 1;
        midpara = bit2;
        while (bit2 > 0) {
            num2 += l2.val * x;
            bit2--;
            x *= 10;
            l2 = l2.next;
        }

        num = num1 + num2;

        ListNode l3 = new ListNode((int)(num % 10));
        ListNode l4 = l3;
        num /= 10;
        while (num > 0) {
            l3.next = new ListNode((int)(num % 10));
            num /= 10;
            l3 = l3.next;
        }

        return l4;
    }
    public  int getBit(ListNode l) {
        int bit = 1;
        while (l.next != null) {
            l = l.next;
            // bit *= 10;
            bit++;
        }
        return bit;
    }
}

该题错误第二版

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

class Solution {

    public static void main(String[] args) {
//        Solution s1 = new Solution();
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//
//        ListNode l3 = s1.addTwoNumbers(l1, l2);

        Solution s1 = new Solution();
        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
//        l2.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);


        ListNode l3 = s1.addTwoNumbers(l1, l2);

    }

    @Test
    public void test1() {
        Solution s1 = new Solution();
        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);


        ListNode l3 = s1.addTwoNumbers(l1, l2);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int bit1 = getBit(l1);
        int bit2 = getBit(l2);
        ListNode l_confirm = (bit1 > bit2) ? l1 : l2;
        int bit;

        ListNode l3 = createLinkedList(l_confirm);
        ListNode l_copy = l3;//确定l3初始位置

        if (l_confirm == l1) {
            l_confirm = l2;
            bit = bit2;
        } else {
            l_confirm = l1;
            bit = bit1;
        }

        while (bit > 0) {
            if (l_confirm.val + l3.val >= 10) {
                l3.val = (l_confirm.val + l3.val) % 10;
                linkedListCount(l3.next);
            } else {
                l3.val = l_confirm.val + l3.val;
            }
            l3 = l3.next;
            l_confirm = l_confirm.next;
            bit--;
        }
        return l_copy;
    }

    public ListNode createLinkedList(ListNode l) {
        ListNode p = new ListNode();
        ListNode po = p;

        while (l != null) {
            p.val = l.val;
            if (l.next != null) {
                p.next = new ListNode();
                p = p.next;
            }
            l = l.next;
        }
        return po;
    }

    public ListNode reverseLinkedList(ListNode l, int bit) {
        int[] num = new int[bit];
        for (int i = 0; i < bit; i++, l = l.next) {
            num[i] = l.val;
        }
        ListNode p = new ListNode();
        ListNode po = p;
        for (int i = bit - 1; i < 0; i--) {
            p.val = num[i];
            p.next = new ListNode();
            p = p.next;
        }

        return po;
    }


    public void linkedListCount(ListNode l) {
        if (l == null) {
            l = new ListNode(1);
        } else {
            if (l.val + 1 == 10) {
                linkedListCount(l.next);
                l.val = 0;
            } else {
                l.val += 1;
            }
        }
    }

    public static int getBit(ListNode l) {
        int bit = 1;
        while (l.next != null) {
            l = l.next;
            // bit *= 10;
            bit++;
        }
        return bit;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}





import org.junit.Test;

import javax.swing.*;
import java.awt.*;

class Solution {

    public static void main(String[] args) {
//        Solution s1 = new Solution();
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//
//        ListNode l3 = s1.addTwoNumbers(l1, l2);

        Solution s1 = new Solution();
        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
//        l2.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);


        ListNode l3 = s1.addTwoNumbers(l1, l2);

    }

    @Test
    public void test1() {
        Solution s1 = new Solution();
        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);


        ListNode l3 = s1.addTwoNumbers(l1, l2);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int bit1 = getBit(l1);
        int bit2 = getBit(l2);
        int bit;
        ListNode copy;
        ListNode l3 = reverseLinkedList(l1, bit1);
        ListNode l4 = reverseLinkedList(l2, bit2);

        if (bit1 > bit2) {
            bit = bit1;
            copy = l3;
            while (bit2 > 0) {
                if (l3.val + l4.val >= 10) {
                    l3.val = (l3.val + l4.val) % 10;
                    linkedListCount(l3.next);
                } else {
                    l3.val = l3.val + l4.val;
                }
                l3 = l3.next;
                l4 = l4.next;
                bit2--;
            }
        } else {
            bit = bit2;
            copy = l4;
            while (bit1 > 0) {
                if (l3.val + l4.val >= 10) {
                    l4.val = (l3.val + l4.val) % 10;
                    linkedListCount(l4.next);
                } else {
                    l4.val = l3.val + l4.val;
                }
                l3 = l3.next;
                l4 = l4.next;
                bit1--;
            }
        }//完成后 copy对应正确的链表的开头 但是正常顺序 还要反转一次

        copy = reverseLinkedList(copy, bit);

        return copy;
    }


    public ListNode reverseLinkedList(ListNode l, int bit) {
        int[] num = new int[bit];
        for (int i = 0; i < bit; i++, l = l.next) {
            num[i] = l.val;
        }
        ListNode p = new ListNode();
        ListNode po = p;
        for (int i = bit - 1; i >= 0; i--) {
            p.val = num[i];
            if (i - 1 >= 0) {
                p.next = new ListNode();
                p = p.next;
            }
        }

        return po;
    }


    public void linkedListCount(ListNode l) {
        if (l == null) {
            l = new ListNode(1);
        } else {
            if (l.val + 1 == 10) {
                linkedListCount(l.next);
                l.val = 0;
            } else {
                l.val += 1;
            }
        }
    }

    public static int getBit(ListNode l) {
        int bit = 1;
        while (l.next != null) {
            l = l.next;
            // bit *= 10;
            bit++;
        }
        return bit;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

自己写的正确版本
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

class Solution {

    public static void main(String[] args) {
        Solution s1 = new Solution();
        ListNode l1 = new ListNode(5);
        l1.next = new ListNode(5);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(5);


        ListNode l3 = s1.addTwoNumbers(l1, l2);

//        Solution s1 = new Solution();
//        ListNode l1 = new ListNode(9);
//
//        ListNode l2 = new ListNode(1);
//        l2.next = new ListNode(9);
//        l2.next.next = new ListNode(9);
//        l2.next.next.next = new ListNode(9);
//        l2.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next = new ListNode(9);
//        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);
//
//
//        ListNode l3 = s1.addTwoNumbers(l1, l2);

    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int bit1 = getBit(l1);
        int bit2 = getBit(l2);
        ListNode l_confirm = (bit1 > bit2) ? l1 : l2;
        int bit;

        ListNode l3 = createLinkedList(l_confirm);
        ListNode l_copy = l3;//确定l3初始位置

        if (l_confirm == l1) {
            l_confirm = l2;
            bit = bit2;
        } else {
            l_confirm = l1;
            bit = bit1;
        }

        while (bit > 0) {
            if (l_confirm.val + l3.val >= 10) {
                l3.val = (l_confirm.val + l3.val) % 10;
                if (l3.next==null){
                    l3.next= new ListNode(1);
                    break;
                }
                linkedListCount(l3.next);
            } else {
                l3.val = l_confirm.val + l3.val;
            }
            l3 = l3.next;
            l_confirm = l_confirm.next;
            bit--;
        }
        return l_copy;
    }

    public ListNode createLinkedList(ListNode l) {
        ListNode p = new ListNode();
        ListNode po = p;

        while (l != null) {
            p.val = l.val;
            if (l.next != null) {
                p.next = new ListNode();
                p = p.next;
            }
            l = l.next;
        }
        return po;
    }


    public void linkedListCount(ListNode l) {

        if (l.next == null && (l.val + 1 == 10)) {
            l.next = new ListNode(1);
            l.val = 0;
            return;
        }

        if (l.val + 1 == 10) {
            linkedListCount(l.next);
            l.val = 0;
        } else {
            l.val += 1;
        }

    }

    public static int getBit(ListNode l) {
        int bit = 1;
        while (l.next != null) {
            l = l.next;
            // bit *= 10;
            bit++;
        }
        return bit;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}

答案1
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}

作者：力扣官方题解
链接：https://leetcode.cn/problems/add-two-numbers/solutions/435246/liang-shu-xiang-jia-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

方法2：递归（学习）
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return dfs(l1, l2, 0);
    }

    ListNode dfs(ListNode l, ListNode r, int i) {
        if (l == null && r == null && i == 0) return null;
        int sum = (l != null ? l.val : 0) + (r != null ? r.val : 0) + i;
        var node = new ListNode(sum % 10);
        node.next = dfs(l != null ? l.next : null, r != null ? r.next : null, sum / 10);
        return node;
    }
}

作者：咕咕咕
链接：https://leetcode.cn/problems/add-two-numbers/solutions/426228/di-gui-si-lu-jian-dan-dai-ma-duan-by-dnanki/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。