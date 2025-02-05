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

/**
 *
 */
fun interface SortedListToBST {
    operator fun invoke(head: ListNode?): TreeNode?
}

/**
 * Approach 1: Recursion
 */
class SortedListToBSTRecursion : SortedListToBST {

    override operator fun invoke(head: ListNode?): TreeNode? {
        // If the head doesn't exist, then the linked list is empty
        if (head == null) {
            return null
        }

        // Find the middle element for the list.

        // Find the middle element for the list.
        val mid = findMiddleElement(head)

        // The mid becomes the root of the BST.
        val node = TreeNode(mid?.value!!)

        // Base case when there is just one element in the linked list

        // Base case when there is just one element in the linked list
        if (head === mid) {
            return node
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.

        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = this.invoke(head)
        node.right = this.invoke(mid.next)
        return node
    }

    private fun findMiddleElement(head: ListNode): ListNode? {
        // The pointer used to disconnect the left half from the mid-node.
        var prevPtr: ListNode? = null
        var slowPtr: ListNode? = head
        var fastPtr: ListNode? = head

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr?.next != null) {
            prevPtr = slowPtr
            slowPtr = slowPtr!!.next
            fastPtr = fastPtr.next!!.next
        }

        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null
        }
        return slowPtr
    }
}

/**
 * Approach 2: Recursion + Conversion to Array
 */
class SortedListToBSTArray : SortedListToBST {
    private val values: MutableList<Int> = ArrayList()

    override operator fun invoke(head: ListNode?): TreeNode {
        this.mapListToValues(head)
        // Convert the array to
        return convertListToBST(0, this.values.size - 1)!!
    }

    private fun mapListToValues(head: ListNode?) {
        var node: ListNode? = head
        while (node != null) {
            values.add(node.value)
            node = node.next
        }
    }

    private fun convertListToBST(left: Int, right: Int): TreeNode? {
        // Invalid case
        if (left > right) {
            return null
        }

        // Middle element forms the root.
        val mid = (left + right) / 2
        val node = TreeNode(values[mid])

        // Base case for when there is only one element left in the array
        if (left == right) {
            return node
        }

        // Recursively form BST on the two halves
        node.left = convertListToBST(left, mid - 1)
        node.right = convertListToBST(mid + 1, right)
        return node
    }
}

/**
 * Approach 3: Inorder Simulation
 */
class SortedListToBSTInorder : SortedListToBST {

    private var head: ListNode? = null

    override operator fun invoke(head: ListNode?): TreeNode? {
        // Get the size of the linked list first
        val size = findSize(head)
        this.head = head
        // Form the BST now that we know the size
        return convertListToBST(0, size - 1)
    }

    private fun convertListToBST(l: Int, r: Int): TreeNode? {
        // Invalid case
        if (l > r) {
            return null
        }
        val mid = (l + r) / 2

        // First step of simulated inorder traversal. Recursively form
        // the left half
        val left = convertListToBST(l, mid - 1)

        // Once left half is traversed, process the current node
        val node = TreeNode(head?.value!!)
        node.left = left

        // Maintain the invariance mentioned in the algorithm
        head = head!!.next

        // Recurse on the right hand side and form BST out of them
        node.right = convertListToBST(mid + 1, r)
        return node
    }

    private fun findSize(head: ListNode?): Int {
        var ptr: ListNode? = head
        var c = 0
        while (ptr != null) {
            ptr = ptr.next
            c += 1
        }
        return c
    }
}
