
null.next会报错nullpointerexception
所以要添加哨兵节点sentinel
import java.util.Deque;
import java.util.LinkedList;myans
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null)
            return head;
        ListNode first= head;
        ListNode pre= head;
        ListNode del= head;

        while (head!=null){
            for (int i = 0; i <=n; i++) {
                if (del==null)
                    return head.next;
                del=del.next;
            }
            if (del==null){
                pre.next = pre.next.next;
                break;
            }
            head=head.next;
            del = head;
            pre = head;
        }
        return first;
    }
}

答案1 利用栈 先全进栈 再出栈n个，得到指向待删除的前一个节点然后        prev.next = prev.next.next;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}

方法2 双指针
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
