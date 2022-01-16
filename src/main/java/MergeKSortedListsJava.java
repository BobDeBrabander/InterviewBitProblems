import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedListsJava {

    static class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null;}
    }

    public ListNode mergeKLists(ArrayList<ListNode> a) {
        Comparator<ListNode> byValue = (node1, node2) -> node1.val - node2.val;
        PriorityQueue<ListNode> q = new PriorityQueue<>(byValue);
        q.addAll(a);
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode curTail = head;
        while(!q.isEmpty()){
            ListNode next = q.poll();
            if (next.next != null) q.add(next.next);
            curTail.next = next;
            curTail = next;
        }
        return head.next;
    }
}