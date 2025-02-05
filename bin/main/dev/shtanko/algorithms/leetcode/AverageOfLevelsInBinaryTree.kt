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

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.level.Easy
import java.util.LinkedList
import java.util.Queue

/**
 * 637. Average of Levels in Binary Tree
 * @see <a href="https://leetcode.com/problems/average-of-levels-in-binary-tree/">Source</a>
 */
@Easy("https://leetcode.com/problems/average-of-levels-in-binary-tree")
fun interface AverageOfLevelsInBinaryTreeStrategy {
    operator fun invoke(root: TreeNode?): DoubleArray
}

// Using Depth First Search
@DFS
class AverageOfLevelsInBinaryTreeDFS : AverageOfLevelsInBinaryTreeStrategy {
    override operator fun invoke(root: TreeNode?): DoubleArray {
        val count: MutableList<Int> = ArrayList()
        val res: MutableList<Double> = ArrayList()
        average(root, 0, res, count)
        for (i in res.indices) {
            res[i] = res[i] / count[i]
        }
        return res.toDoubleArray()
    }

    fun average(tree: TreeNode?, index: Int, sum: MutableList<Double>, count: MutableList<Int>) {
        if (tree == null) return
        if (index < sum.size) {
            sum[index] = sum[index] + tree.value
            count[index] = count[index] + 1
        } else {
            sum.add(1.0 * tree.value)
            count.add(1)
        }
        average(tree.left, index + 1, sum, count)
        average(tree.right, index + 1, sum, count)
    }
}

// Using Breadth First Search
@BFS
class AverageOfLevelsInBinaryTreeBFS : AverageOfLevelsInBinaryTreeStrategy {
    override operator fun invoke(root: TreeNode?): DoubleArray {
        val res: MutableList<Double> = ArrayList()
        var queue: Queue<TreeNode?> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            var sum: Long = 0
            var count: Long = 0
            val temp: Queue<TreeNode?> = LinkedList()
            while (queue.isNotEmpty()) {
                val node: TreeNode? = queue.remove()
                if (node != null) {
                    sum += node.value
                    count++
                    if (node.left != null) temp.add(node.left)
                    if (node.right != null) temp.add(node.right)
                }
            }
            queue = temp
            res.add(sum * 1.0 / count)
        }
        return res.toDoubleArray()
    }
}
