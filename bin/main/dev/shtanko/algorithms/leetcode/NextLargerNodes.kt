/*
 * Copyright 2023 Oleksii Shtanko
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

import java.util.Stack

/**
 * 1019. Next Greater Node In Linked List
 * @see <a href="https://leetcode.com/problems/next-greater-node-in-linked-list/">Source</a>
 */
fun interface NextLargerNodes {
    operator fun invoke(head: ListNode?): IntArray
}

class NextLargerNodesStack : NextLargerNodes {
    override operator fun invoke(head: ListNode?): IntArray {
        val arr: ArrayList<Int> = ArrayList()
        var node = head
        while (node != null) {
            arr.add(node.value)
            node = node.next
        }
        val res = IntArray(arr.size)
        val stack: Stack<Int> = Stack()
        for (i in 0 until arr.size) {
            while (stack.isNotEmpty() && arr[stack.peek()] < arr[i]) res[stack.pop()] = arr[i]
            stack.push(i)
        }
        return res
    }
}

class NextLargerNodesOnePass : NextLargerNodes {
    override operator fun invoke(head: ListNode?): IntArray {
        // Keeps track of indices of values in nums
        val stack = Stack<Int>()

        // Store node values as we go,
        // updates to output value ("next greatest") within while loop as we see them
        val nums: MutableList<Int> = ArrayList()

        var n = head

        // For generating the corresponding index in nums as we step through LinkedList
        var index = 0

        while (n != null) {
            nums.add(n.value)

            // Process anything that is less than current node value
            // i.e. current node value is the "next"greatest for elements (index-referenced) in the stack
            while (stack.isNotEmpty() && nums[stack.peek()] < n.value) {
                nums[stack.pop()] = n.value
            }

            // Set up for next iteration.
            // Note: Every node gets into the stack.
            stack.push(index)
            n = n.next
            index++
        }
        // Handle remaining items in stack / write in 0 (no "next greatest" found for these)
        while (stack.isNotEmpty()) {
            nums[stack.pop()] = 0
        }

        // Format output
        val result = IntArray(nums.size)
        for (i in result.indices) {
            result[i] = nums[i]
        }

        return result
    }
}
