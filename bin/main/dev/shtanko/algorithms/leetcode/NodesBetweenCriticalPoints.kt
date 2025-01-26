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

import dev.shtanko.algorithms.annotations.OnePass

/**
 * 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
 * @see <a href="https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points">
 *     Source</a>
 */
fun interface NodesBetweenCriticalPoints {
    operator fun invoke(head: ListNode?): IntArray
}

@OnePass
class NodesBetweenOnePass : NodesBetweenCriticalPoints {
    override fun invoke(head: ListNode?): IntArray {
        var result = intArrayOf(-1, -1)

        // Initialize minimum distance to the maximum possible value
        var minDistance = Int.MAX_VALUE

        // Pointers to track the previous node, current node, and indices
        var previousNode: ListNode? = head
        var currentNode: ListNode? = head?.next
        var currentIndex = 1
        var previousCriticalIndex = 0
        var firstCriticalIndex = 0

        while (currentNode?.next != null) {
            // Check if the current node is a local maxima or minima
            val isLocalMinima =
                currentNode.value < previousNode?.value!! && currentNode.value < currentNode.next!!.value
            val isLocalMaxima = currentNode.value > previousNode.value && currentNode.value > currentNode.next!!.value

            if (isLocalMinima || isLocalMaxima) {
                // If this is the first critical point found
                if (previousCriticalIndex == 0) {
                    previousCriticalIndex = currentIndex
                    firstCriticalIndex = currentIndex
                } else {
                    // Calculate the minimum distance between critical points
                    minDistance = kotlin.math.min(minDistance, currentIndex - previousCriticalIndex)
                    previousCriticalIndex = currentIndex
                }
            }

            // Move to the next node and update indices
            currentIndex++
            previousNode = currentNode
            currentNode = currentNode.next
        }

        // If at least two critical points were found
        if (minDistance != Int.MAX_VALUE) {
            val maxDistance = previousCriticalIndex - firstCriticalIndex
            result = intArrayOf(minDistance, maxDistance)
        }

        return result
    }
}
