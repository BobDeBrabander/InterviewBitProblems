import java.util.PriorityQueue

object MergeKSortedLists {

    data class ListNode(val x: Int) : Comparable<ListNode>{
        var next: ListNode? = null

        override fun compareTo(other: ListNode) : Int {
            return x - other.x
        }
    }

    fun mergeKLists(lists: List<ListNode>) : ListNode {
        val q = PriorityQueue<ListNode>()
        q.addAll(lists)
        val head = ListNode(Integer.MIN_VALUE)
        var curTail = head
        while(q.isNotEmpty()) {
            val next = q.poll()
            if(next.next != null) q.add(next.next)
            curTail.next = next
            curTail = next
        }
        return head.next ?: throw IllegalArgumentException("No nodes were passed to function at all")
    }
}