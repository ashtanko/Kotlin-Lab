/*
 * Copyright 2022 Oleksii Shtanko
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

import java.util.LinkedList
import java.util.Queue
import java.util.stream.IntStream

/**
 * 1161. Maximum Level Sum of a Binary Tree
 * @see <a href="https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/">Source</a>
 */
fun interface MaximumLevelSumOfBinaryTree {
    fun maxLevelSum(root: TreeNode?): Int
}

class MaximumLevelSumOfBinaryTreeBFS : MaximumLevelSumOfBinaryTree {
    override fun maxLevelSum(root: TreeNode?): Int {
        var max = Int.MIN_VALUE
        var maxLevel = 1
        val q: Queue<TreeNode> = LinkedList()
        q.offer(root)
        var level = 1
        while (q.isNotEmpty()) {
            var sum = 0
            for (sz in q.size downTo 1) {
                val n: TreeNode = q.poll()
                sum += n.value
                if (n.left != null) {
                    q.offer(n.left)
                }
                if (n.right != null) {
                    q.offer(n.right)
                }
            }
            if (max < sum) {
                max = sum
                maxLevel = level
            }
            ++level
        }

        return maxLevel
    }
}

class MaximumLevelSumOfBinaryTreeDFS : MaximumLevelSumOfBinaryTree {
    override fun maxLevelSum(root: TreeNode?): Int {
        val list: MutableList<Int> = ArrayList()
        dfs(root, list, 0)
        return 1 + IntStream.range(0, list.size).reduce(0) { a, b -> if (list[a] < list[b]) b else a }
    }

    private fun dfs(n: TreeNode?, l: MutableList<Int>, level: Int) {
        if (n == null) {
            return
        }
        if (l.size == level) {
            l.add(n.value)
        } // never reach this level before, add first value.
        else {
            l[level] = l[level] + n.value
        } // reached the level before, accumulate current value to old value.
        dfs(n.left, l, level + 1)
        dfs(n.right, l, level + 1)
    }
}
