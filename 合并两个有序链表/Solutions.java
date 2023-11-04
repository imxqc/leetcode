


class Solution{
    public static void main(String[] args) {
        ListNode l1 = new ListNode(-10);
        l1.next = new ListNode(-10);
        l1.next.next= new ListNode(-9);
        l1.next.next.next= new ListNode(-4);
        l1.next.next.next.next= new ListNode(1);
        l1.next.next.next.next.next= new ListNode(6);
        l1.next.next.next.next.next.next= new ListNode(6);


        ListNode l2 = new ListNode(-7);


        ListNode l3 = mergeTwoLists(l1, l2);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null)
            return null;
        if (list1 != null && list2 == null)
            return list1;
        if (list2 != null && list1 == null)
            return list2;

        ListNode list3 = new ListNode();
        int[] rcv = new int[100];
        int index = 0;


        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                rcv[index] = list2.val;
                list2 = list2.next;
                index++;
            } else if (list1.val < list2.val) {
                rcv[index] = list1.val;
                list1 = list1.next;
                index++;
            } else {
                rcv[index] = list1.val;
                list1 = list1.next;
                index++;
            }
        }
        if (list1 == null) {
            while (list2 != null) {
                rcv[index] = list2.val;
                list2 = list2.next;
                index++;
            }
        } else {
            while (list1 != null){
                rcv[index] = list1.val;
                list1 = list1.next;
                index++;
            }
        }

        list3.val = rcv[0];
        ListNode l_first = list3;
        for (int i = 1; i < index; i++) {
            list3.next = new ListNode(rcv[i]);
            list3 = list3.next;
        }

        return l_first;
    }
}

自己的答案 时间复杂度过高

官网答案 方法1 递归（终止条件和递归方式） 直接改变原链表节省空间
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {//若有一条链表为空 该链表尾直接链接非空链
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {//通过递归来升序排序 ，
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;//返回每一级所对应的next
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

方法2 迭代

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);//哨兵节点，确定头结点的位置

        ListNode prev = prehead;//跟踪节点
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}