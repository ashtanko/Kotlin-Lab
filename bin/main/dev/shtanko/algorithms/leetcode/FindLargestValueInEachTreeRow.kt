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

import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import kotlin.math.max

/**
 * 515. Find Largest Value in Each Tree Row
 */
fun interface FindLargestValueInEachTreeRow {
    operator fun invoke(root: TreeNode?): List<Int>
}

/**
 * Approach 1: Breadth First Search (BFS)
 */
class LargestValueInEachTreeRowBFS : FindLargestValueInEachTreeRow {
    override fun invoke(root: TreeNode?): List<Int> {
        if (root == null) {
            return ArrayList()
        }

        val ans: MutableList<Int> = ArrayList()
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val currentLength: Int = queue.size
            var currMax = Int.MIN_VALUE
            for (i in 0 until currentLength) {
                val node: TreeNode = queue.remove()
                currMax = max(currMax, node.value)
                if (node.left != null) {
                    queue.add(node.left)
                }
                if (node.right != null) {
                    queue.add(node.right)
                }
            }
            ans.add(currMax)
        }

        return ans
    }
}

/**
 * Approach 2: Depth First Search (DFS)
 */
class LargestValueInEachTreeRowDFS : FindLargestValueInEachTreeRow {
    private val ans: MutableList<Int> = mutableListOf()

    override fun invoke(root: TreeNode?): List<Int> {
        dfs(root, 0)
        return ans
    }

    private fun dfs(node: TreeNode?, depth: Int) {
        if (node == null) {
            return
        }
        if (depth == ans.size) {
            ans.add(node.value)
        } else {
            ans[depth] = max(ans[depth], node.value)
        }
        dfs(node.left, depth + 1)
        dfs(node.right, depth + 1)
    }
}

/**
 * Approach 3: DFS, Iterative
 */
class LargestValueInEachTreeRowDFSIter : FindLargestValueInEachTreeRow {
    override fun invoke(root: TreeNode?): List<Int> {
        if (root == null) {
            return ArrayList()
        }

        val ans: MutableList<Int> = ArrayList()
        val stack: Stack<Pair<TreeNode, Int>> = Stack()
        stack.push(Pair(root, 0))

        while (stack.isNotEmpty()) {
            val pair: Pair<TreeNode, Int> = stack.pop()
            val node: TreeNode = pair.first
            val depth: Int = pair.second
            if (depth == ans.size) {
                ans.add(node.value)
            } else {
                ans[depth] = max(ans[depth], node.value)
            }
            if (node.left != null) {
                stack.push(Pair(node.left!!, depth + 1))
            }
            if (node.right != null) {
                stack.push(Pair(node.right!!, depth + 1))
            }
        }

        return ans
    }
}
