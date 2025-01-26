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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Queue

/**
 * 2471. Minimum Number of Operations to Sort a Binary Tree by Level
 * @see <a href="https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/">Source</a>
 */
@Medium
sealed interface MinOperationsToSortBinaryTreeByLevel {
    operator fun invoke(root: TreeNode?): Int

    @BFS
    data object HashMapBFS : MinOperationsToSortBinaryTreeByLevel {
        override fun invoke(root: TreeNode?): Int {
            val queue: Queue<TreeNode> = java.util.LinkedList()
            queue.add(root)
            var totalSwaps = 0

            while (queue.isNotEmpty()) {
                val levelSize = queue.size
                val levelValues = IntArray(levelSize)

                for (i in 0 until levelSize) {
                    val node: TreeNode? = queue.poll()
                    node?.let {
                        levelValues[i] = node.value
                        node.left?.let { queue.add(it) }
                        node.right?.let { queue.add(it) }
                    }
                }

                totalSwaps += getMinSwaps(levelValues)
            }

            return totalSwaps
        }

        /**
         * Get the minimum number of swaps to sort the given array
         *
         * @param original The original array
         * @return The minimum number of swaps
         */
        private fun getMinSwaps(original: IntArray): Int {
            var swaps = 0
            val sorted = original.sorted()
            var positions = mutableMapOf<Int, Int>()
            for (i in original.indices) {
                positions[original[i]] = i
            }

            for (i in original.indices) {
                if (original[i] != sorted[i]) {
                    swaps++
                    val current = positions[sorted[i]]
                    current?.let {
                        positions[original[i]] = current
                        original[current] = original[i]
                    }
                }
            }
            return swaps
        }
    }

    data object BitManipulationBFS : MinOperationsToSortBinaryTreeByLevel {

        private const val SHIFT = 20
        private const val MASK = 0xFFFFF

        override fun invoke(root: TreeNode?): Int {
            val queue: Queue<TreeNode> = java.util.LinkedList()
            queue.add(root)
            var swaps = 0
            while (queue.isNotEmpty()) {
                val levelSize = queue.size
                val nodes = LongArray(levelSize)

                for (i in 0 until levelSize) {
                    val node: TreeNode? = queue.poll()
                    node?.let {
                        nodes[i] = node.value.toLong() shl SHIFT or i.toLong()
                        node.left?.let { queue.add(it) }
                        node.right?.let { queue.add(it) }
                    }
                }
                nodes.sort()

                for (i in 0 until levelSize) {
                    val origPos = nodes[i].toInt() and MASK
                    if (origPos != i) {
                        val tmp = nodes[i]
                        nodes[i] = nodes[origPos]
                        nodes[origPos] = tmp
                        swaps++
                    }
                }
            }
            return swaps
        }
    }
}
