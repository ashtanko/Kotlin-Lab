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

import java.util.ArrayDeque
import java.util.Deque

/**
 * @see <a href="https://leetcode.com/problems/deepest-leaves-sum">Source</a>
 */
fun interface DeepestLeavesSum {
    operator fun invoke(root: TreeNode): Int
}

class DeepestLeavesSumDFS : DeepestLeavesSum {
    override operator fun invoke(root: TreeNode): Int {
        var deepestSum = 0
        var depth = 0
        var currDepth: Int
        var node: TreeNode = root
        val stack: Deque<Pair<TreeNode, Int>> = ArrayDeque()
        stack.push(node to 0)

        while (stack.isNotEmpty()) {
            val p = stack.pop()
            node = p.first
            currDepth = p.second
            if (node.left == null && node.right == null) {
                if (depth < currDepth) {
                    deepestSum = node.value
                    depth = currDepth
                } else if (depth == currDepth) {
                    deepestSum += node.value
                }
            } else {
                node.right?.let {
                    stack.push(it to currDepth + 1)
                }
                node.left?.let {
                    stack.push(it to currDepth + 1)
                }
            }
        }
        return deepestSum
    }
}

/**
 * Approach 2: Iterative BFS Traversal.
 */
class DeepestLeavesSumBFS : DeepestLeavesSum {
    override operator fun invoke(root: TreeNode): Int {
        var deepestSum = 0
        var depth = 0
        var currDepth: Int
        val queue: Deque<Pair<TreeNode, Int>> = ArrayDeque()
        var node: TreeNode = root
        queue.offer(Pair(node, 0))

        while (queue.isNotEmpty()) {
            val p = queue.poll()
            node = p.first
            currDepth = p.second
            if (node.left == null && node.right == null) {
                if (depth < currDepth) {
                    deepestSum = node.value
                    depth = currDepth
                } else if (depth == currDepth) {
                    deepestSum += node.value
                }
            } else {
                node.right?.let {
                    queue.push(it to currDepth + 1)
                }
                node.left?.let {
                    queue.push(it to currDepth + 1)
                }
            }
        }
        return deepestSum
    }
}

/**
 * Approach 3: Optimized Iterative BFS Traversal.
 */
class DeepestLeavesSumOptimizedBFS : DeepestLeavesSum {
    override operator fun invoke(root: TreeNode): Int {
        val nextLevel: ArrayDeque<TreeNode> = ArrayDeque()
        var currLevel: ArrayDeque<TreeNode> = ArrayDeque()
        nextLevel.offer(root)

        while (nextLevel.isNotEmpty()) {
            currLevel = nextLevel.clone()
            nextLevel.clear()
            for (node in currLevel) {
                node.right?.let {
                    nextLevel.offer(it)
                }
                node.left?.let {
                    nextLevel.offer(it)
                }
            }
        }
        var deepestSum = 0
        for (node in currLevel) {
            deepestSum += node.value
        }
        return deepestSum
    }
}
