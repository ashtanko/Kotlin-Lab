/*
 * Copyright 2021 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

fun interface SortListStrategy {
    operator fun invoke(head: ListNode): ListNode?
}

class TopDownMergeSort : SortListStrategy {
    override operator fun invoke(head: ListNode): ListNode? {
        if (head.next == null) return head
        val mid = getMid(head) ?: ListNode(0)
        val left = invoke(head)
        val right = invoke(mid)
        return merge(left, right)
    }

    private fun merge(x: ListNode?, y: ListNode?): ListNode? {
        var a: ListNode? = x
        var b: ListNode? = y
        val dummyHead = ListNode(0)
        var tail: ListNode? = dummyHead
        while (a != null && b != null) {
            if (a.value < b.value) {
                tail?.next = a
                a = a.next
                tail = tail?.next
            } else {
                tail?.next = b
                b = b.next
                tail = tail?.next
            }
        }
        tail?.next = a ?: b
        return dummyHead.next
    }

    private fun getMid(head: ListNode): ListNode? {
        var midPrev: ListNode? = null
        var node: ListNode? = head
        while (node?.next != null) {
            midPrev = if (midPrev == null) node else midPrev.next
            node = node.next?.next
        }
        val mid = midPrev?.next
        midPrev?.next = null
        return mid
    }
}

class BottomUpMergeSort : SortListStrategy {
    var tail: ListNode? = ListNode(0)
    var nextSubList: ListNode? = ListNode(0)

    override operator fun invoke(head: ListNode): ListNode? {
        return sortList(head)
    }

    private fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        val n = getCount(head)
        var start = head
        val dummyHead = ListNode(0)
        var size = 1
        while (size < n) {
            tail = dummyHead
            while (start != null) {
                if (start.next == null) {
                    tail?.next = start
                    break
                }
                val mid = split(start, size)
                merge(start, mid)
                start = nextSubList
            }
            start = dummyHead.next
            size *= 2
        }
        return dummyHead.next
    }

    private fun split(start: ListNode, size: Int): ListNode? {
        var midPrev: ListNode? = start
        var end = start.next
        // use fast and slow approach to find middle and end of second linked list
        var index = 1
        while (index < size && (midPrev?.next != null || end?.next != null)) {
            if (end?.next != null) {
                end = if (end.next?.next != null) end.next?.next else end.next
            }
            if (midPrev?.next != null) {
                midPrev = midPrev.next
            }
            index++
        }
        val mid = midPrev?.next
        midPrev?.next = null
        nextSubList = end?.next
        end?.next = null
        // return the start of second linked list
        return mid
    }

    private fun merge(list1: ListNode?, list2: ListNode?) {
        var a = list1
        var b = list2
        val dummyHead = ListNode(0)
        var newTail: ListNode? = dummyHead
        while (a != null && b != null) {
            if (a.value < b.value) {
                newTail?.next = a
                a = a.next
                newTail = newTail?.next
            } else {
                newTail?.next = b
                b = b.next
                newTail = newTail?.next
            }
        }
        newTail?.next = a ?: b
        // traverse till the end of merged list to get the newTail
        while (newTail?.next != null) {
            newTail = newTail.next
        }
        // link the old tail with the head of merged list
        tail?.next = dummyHead.next
        // update the old tail to the new tail of merged list
        tail = newTail
    }

    private fun getCount(head: ListNode?): Int {
        var cnt = 0
        var ptr = head
        while (ptr != null) {
            ptr = ptr.next
            cnt++
        }
        return cnt
    }
}
