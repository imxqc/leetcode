自己的答案
class Solution {

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


    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode start = head;
        ListNode sam;
        int num;

        while (head != null) {
            Change(head, head.next);
            sam = head.next.next;
            if (sam != null) {
                if (sam.next == null) {
                    break;
                } else
                    head = sam;
            } else
                break;
        }

        return start;
    }

    public static void Change(ListNode l1, ListNode l2) {
        int num;
        num = l1.val;
        l1.val = l2.val;
        l2.val = num;
    }
}

自己写的递归
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null)
            return null;
        return recursive(head);
    }

    public static ListNode recursive(ListNode l) {
        ListNode start = l;

        if (l.next != null) {
            int num = l.val;
            l.val = l.next.val;
            l.next.val = num;
            if (l.next.next != null)
                recursive(l.next.next);
        }
        return start;
    }
}

官网答案1
每次交换只涉及两位
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){//结束条件 数目为奇数个或者next.next为null时无需交换直接return
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);//将next后面通过递归正确排序返回给head.next
        next.next = head;//完成最首位置的递归
        return next;
    }
}

