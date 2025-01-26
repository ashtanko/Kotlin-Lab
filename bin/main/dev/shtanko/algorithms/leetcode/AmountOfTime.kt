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
import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.LinkedList

/**
 * 2385. Amount of Time for Binary Tree to Be Infected
 * @see <a href="https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected">Source</a>
 */
@Medium(link = "https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected")
fun interface AmountOfTime {
    operator fun invoke(root: TreeNode?, start: Int): Int
}

@BFS
class AmountOfTimeBFS : AmountOfTime {
    override fun invoke(root: TreeNode?, start: Int): Int {
        val treeMap: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
        convert(root, 0, treeMap)
        val queue = LinkedList<Int>()
        queue.offer(start)
        var minute = 0
        val visited = mutableSetOf(start)

        while (queue.isNotEmpty()) {
            var levelSize = queue.size
            while (levelSize > 0) {
                val current = queue.pollFirst()
                for (num in treeMap.getOrDefault(current, mutableSetOf())) {
                    if (!visited.contains(num)) {
                        visited.add(num)
                        queue.offer(num)
                    }
                }
                levelSize--
            }
            minute++
        }

        return minute - 1
    }

    private fun convert(current: TreeNode?, parent: Int, treeMap: MutableMap<Int, MutableSet<Int>>) {
        if (current == null) {
            return
        }
        if (!treeMap.containsKey(current.value)) {
            treeMap[current.value] = mutableSetOf()
        }
        val adjacentList = treeMap.getOrDefault(current.value, mutableSetOf())
        if (parent != 0) {
            adjacentList.add(parent)
        }
        current.left?.value?.let {
            adjacentList.add(it)
        }
        current.right?.value?.let {
            adjacentList.add(it)
        }
        convert(current.left, current.value, treeMap)
        convert(current.right, current.value, treeMap)
    }
}

@DFS
class AmountOfTimeDFS : AmountOfTime {

    private var maxDistance = 0

    override fun invoke(root: TreeNode?, start: Int): Int {
        traverse(root, start)
        return maxDistance
    }

    private fun traverse(root: TreeNode?, start: Int): Int {
        val depth: Int
        if (root == null) {
            return 0
        }

        val leftDepth = traverse(root.left, start)
        val rightDepth = traverse(root.right, start)

        if (root.value == start) {
            maxDistance = kotlin.math.max(leftDepth, rightDepth)
            depth = -1
        } else if (leftDepth >= 0 && rightDepth >= 0) {
            depth = kotlin.math.max(leftDepth, rightDepth) + 1
        } else {
            val distance = kotlin.math.abs(leftDepth) + kotlin.math.abs(rightDepth)
            maxDistance = kotlin.math.max(maxDistance, distance)
            depth = kotlin.math.min(leftDepth, rightDepth) - 1
        }

        return depth
    }
}
