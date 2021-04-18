package dev.shtanko.algorithms.leetcode

/**
 * Remove Nth Node From End of List
 * @link https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
interface RemoveNthFromEnd {
    fun perform(head: ListNode?, n: Int): ListNode?
}

/**
 * Approach 1: Two pass algorithm
 * Time complexity : O(L)
 * Space complexity : O(1)
 */
class RemoveNthFromEndTwoPass : RemoveNthFromEnd {
    override fun perform(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        var length = 0
        var first = head
        while (first != null) {
            length++
            first = first.next
        }
        length -= n
        first = dummy
        while (length > 0) {
            length--
            first = first?.next
        }
        first!!.next = first.next?.next
        return dummy.next
    }
}

/**
 * Approach 2: One pass algorithm
 * Time complexity : O(L)
 * Space complexity : O(1)
 */
class RemoveNthFromEndOnePass : RemoveNthFromEnd {
    override fun perform(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        var first: ListNode? = dummy
        var second: ListNode? = dummy
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (i in 1..n + 1) {
            first = first?.next
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next
            second = second?.next
        }
        second!!.next = second.next?.next
        return dummy.next
    }
}
