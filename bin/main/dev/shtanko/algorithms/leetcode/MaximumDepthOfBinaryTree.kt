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

import java.util.Stack

fun interface MaxDepthStrategy {
    operator fun invoke(root: TreeNode?): Int
}

class MaxDepthRecursive : MaxDepthStrategy {
    override operator fun invoke(root: TreeNode?): Int {
        if (root == null) return 0
        return 1 + invoke(root.left).coerceAtLeast(invoke(root.right))
    }
}

class MaxDepthIterative : MaxDepthStrategy {
    override operator fun invoke(root: TreeNode?): Int {
        if (root == null) return 0
        val nodeStack = Stack<TreeNode>()
        val depthStack = Stack<Int>()
        nodeStack.push(root)
        depthStack.push(1)
        var max = 1

        while (nodeStack.isNotEmpty()) {
            val curr = nodeStack.pop()
            val depth = depthStack.pop()

            if (curr.left == null && curr.right == null) {
                max = max.coerceAtLeast(depth)
            }

            if (curr.right != null) {
                nodeStack.push(curr.right)
                depthStack.push(depth + 1)
            }
            if (curr.left != null) {
                nodeStack.push(curr.left)
                depthStack.push(depth + 1)
            }
        }
        return max
    }
}
