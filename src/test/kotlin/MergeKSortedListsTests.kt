import org.junit.Test
import MergeKSortedLists.ListNode
import org.assertj.core.api.Assertions.assertThat

class MergeKSortedListsTests {

    @Test
    fun `example test`(){
        val head1 = ListNode(1)
        var tail1 = head1
        tail1.next = ListNode(10)
        tail1 = tail1.next!!
        tail1.next = ListNode(20)

        val head2 = ListNode(4)
        var tail2 = head2
        tail2.next = ListNode(11)
        tail2 = tail2.next!!
        tail2.next = ListNode(13)

        val head3 = ListNode(3)
        var tail3 = head3
        tail3.next = ListNode(8)
        tail3 = tail3.next!!
        tail3.next = ListNode(9)

        val input = listOf(head1, head2, head3)
        val output = MergeKSortedLists.mergeKLists(input)
        var curNode : ListNode? = output
        val outputToList = mutableListOf<Int>()
        while(curNode != null) {
            outputToList.add(curNode.x)
            curNode = curNode.next
        }
        val expectedList = listOf(1, 3, 4, 8, 9, 10, 11, 13, 20)
        assertThat(outputToList).isEqualTo(expectedList)
    }
}