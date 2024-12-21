/*
 * Copyright 2024 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.Recursive
import dev.shtanko.algorithms.annotations.TwoPointers

/**
 * 2181. Merge Nodes in Between Zeros
 * @see <a href="https://leetcode.com/problems/merge-nodes-in-between-zeros">Source</a>
 */
fun interface MergeNodesInBetweenZeros {
    operator fun invoke(head: ListNode?): ListNode?
}

@TwoPointers
class MergeNodesTwoPointer : MergeNodesInBetweenZeros {
    override fun invoke(head: ListNode?): ListNode? {
        var modify = head?.next
        var nextSum = modify

        while (nextSum != null) {
            var sum = 0
            // Find the sum of all nodes until you encounter a 0.
            while (nextSum?.value != 0) {
                sum += nextSum?.value ?: 0
                nextSum = nextSum?.next
            }

            // Assign the sum to the current node's value.
            modify?.value = sum
            // Move nextSum to the first non-zero value of the next block.
            nextSum = nextSum.next
            // Move modify also to this node.
            modify?.next = nextSum
            modify = modify?.next
        }
        return head?.next
    }
}

@Recursive
class MergeNodesRecursion : MergeNodesInBetweenZeros {
    override fun invoke(head: ListNode?): ListNode? {
        var root = head
        root = root?.next
        if (root == null) {
            return root
        }
        // Initialize a dummy head node.
        var temp = root
        var sum = 0
        while (temp?.value != 0) {
            sum += temp?.value ?: 0
            temp = temp?.next
        }
        root.value = sum
        root.next = invoke(temp)
        return root
    }
}
