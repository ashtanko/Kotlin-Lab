/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.algorithms.interview

import dev.shtanko.algorithms.DECIMAL
import java.util.Stack

internal class Node(var data: Int) {
    var next: Node? = null

    /**
     * Drop the next node in the linked list.
     */
    fun dropNext() {
        this.next = this.next?.next
    }

    companion object {
        /**
         * Create a linked list from an array of integers.
         *
         * @param arr The array of integers.
         * @return The head of the linked list.
         */
        fun fromArray(arr: IntArray): Node {
            var head: Node? = null
            var current: Node? = null

            for (item in arr) {
                val nextNode = Node(item)

                if (head == null) {
                    head = nextNode
                } else {
                    current?.next = nextNode
                }

                current = nextNode
            }

            return head!!
        }
    }
}

internal class LinkedList(val head: Node) : Iterable<Node> {
    override fun iterator(): Iterator<Node> = LinkedListIterator(this)

    fun getTailNode(): Node = this.last()

    fun asArray(): IntArray =
        this.asSequence()
            .map { e -> e.data }
            .toList()
            .toTypedArray()
            .toIntArray()

    companion object {
        fun fromArray(arr: IntArray): LinkedList {
            val head = Node.fromArray(arr)
            return LinkedList(head)
        }
    }
}

internal class LinkedListIterator(list: LinkedList) : Iterator<Node> {
    private var current: Node? = list.head

    override fun hasNext(): Boolean = current != null

    override fun next(): Node {
        if (!hasNext()) throw NoSuchElementException()
        val oldCurrent = current!!
        current = oldCurrent.next

        return oldCurrent
    }
}

internal fun removeDuplicates(list: LinkedList) {
    val seen = HashSet<Int>()
    val previousNode = Node(-1)
    previousNode.next = list.head

    var current: Node? = previousNode

    while (current?.next != null) {
        val next: Node? = current.next

        if (seen.contains(next?.data)) {
            current.dropNext()
        } else {
            seen.add(next?.data ?: 0)
        }

        current = next
    }
}

internal fun getKthToLast(list: LinkedList, k: Int): Int {
    var trailingNode: Node? = list.head
    var leadingNode: Node? = list.head

    for (i in 0 until k) {
        leadingNode = leadingNode?.next
    }

    while (leadingNode?.next != null) {
        leadingNode = leadingNode.next
        trailingNode = trailingNode?.next
    }

    return trailingNode?.data ?: 0
}

internal fun deleteCurrentNodeValue(node: Node) {
    var currentNode = node

    while (currentNode.next != null) {
        val next = currentNode.next
        currentNode.data = next?.data ?: -1

        if (next?.next == null) {
            currentNode.next = null
        }

        currentNode = next ?: Node(-1)
    }
}

internal fun check(pivot: Int, list: IntArray): Boolean {
    var passedPivot = false

    for (item in list) {
        val itemOnRightOfPivot = item >= pivot
        if (itemOnRightOfPivot) {
            passedPivot = true
        }

        if (!passedPivot && itemOnRightOfPivot) {
            return false
        }

        if (passedPivot && !itemOnRightOfPivot) {
            return false
        }
    }

    return true
}

internal fun partition(pivot: Int, list: LinkedList) {
    val originalTail = list.getTailNode()
    var tail = originalTail

    var lastNode: Node? = null
    for (node in list) {
        if (node == originalTail) {
            break
        }

        if (node.data >= pivot) {
            if (lastNode != null) {
                lastNode.next = node.next
            }

            tail.next = node
            node.next = null
            tail = node
        } else {
            lastNode = node
        }
    }
}

internal fun add(a: LinkedList, b: LinkedList): LinkedList {
    val beforeHead = Node(-1)
    var tail = beforeHead

    var aNode: Node? = a.head
    var bNode: Node? = b.head
    var carry = 0

    while (aNode != null || bNode != null) {
        val placeSum = aNode.getData() + bNode.getData() + carry
        val place = placeSum % DECIMAL
        carry = (placeSum - place) / DECIMAL

        val placeNode = Node(place)
        tail.next = placeNode
        tail = placeNode

        aNode = aNode?.next
        bNode = bNode?.next
    }

    if (carry != 0) {
        val placeNode = Node(carry)
        tail.next = placeNode
    }

    return LinkedList(beforeHead.next ?: throw IllegalStateException("Node shouldn't be null"))
}

internal fun Node?.getData(): Int {
    if (this == null) {
        return 0
    }

    return this.data
}

internal fun isPalindrome(list: LinkedList): Boolean {
    val stack = Stack<Int>()
    for (node in list) {
        stack.push(node.data)
    }

    for (node in list) {
        if (node.data != stack.pop()) {
            return false
        }
    }

    return true
}

internal data class TraversalInformation(val size: Int, val tail: Node, val linkedList: LinkedList) {
    companion object {
        fun getShorterLonger(
            a: TraversalInformation,
            b: TraversalInformation,
        ): Pair<TraversalInformation, TraversalInformation> {
            return if (a.size <= b.size) {
                Pair(a, b)
            } else {
                Pair(b, a)
            }
        }
    }
}

internal fun LinkedList.getTail(): TraversalInformation {
    val (size, tail) = this.asSequence()
        .withIndex()
        .map { indexedValue -> Pair(indexedValue.index + 1, indexedValue.value) }
        .last()

    return TraversalInformation(size, tail, this)
}

internal fun findFirstIntersection(a: LinkedList, b: LinkedList): Node? {
    val aTraversal = a.getTail()
    val bTraversal = b.getTail()

    if (aTraversal.tail != bTraversal.tail) {
        return null
    }

    val (shorter, longer) = TraversalInformation.getShorterLonger(aTraversal, bTraversal)

    val longerDifference = longer.size - shorter.size

    val shorterSequence = shorter.linkedList.asSequence()
    val longerSequence = longer.linkedList.asSequence()

    for ((shorterNode, longerNode) in shorterSequence zip longerSequence.drop(longerDifference)) {
        if (shorterNode == longerNode) {
            return shorterNode
        }
    }

    throw IllegalStateException("Found a matching tail, but couldn't find the shared node.")
}

internal fun detectLoop(list: LinkedList): Node? {
    var slow: Node? = list.head
    var fast: Node? = list.head

    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next

        if (slow == fast) {
            slow = list.head

            while (slow != fast) {
                slow = slow?.next
                fast = fast?.next
            }

            return fast
        }
    }

    return null
}
