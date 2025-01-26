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

import java.util.LinkedList
import java.util.Queue

/**
 * 513. Find Bottom Left Tree Value
 * @see <a href="https://leetcode.com/problems/find-bottom-left-tree-value">Source</a>
 */
fun interface FindBottomLeftTreeVal {
    operator fun invoke(root: TreeNode?): Int
}

class FindBottomLeftTreeValDFS : FindBottomLeftTreeVal {
    private var maxDepth = -1
    private var bottomLeftValue = 0

    override fun invoke(root: TreeNode?): Int {
        dfs(root, 0)
        return bottomLeftValue
    }

    private fun dfs(current: TreeNode?, depth: Int) {
        if (current == null) {
            return
        }
        if (depth > maxDepth) {
            maxDepth = depth
            bottomLeftValue = current.value
        }
        dfs(current.left, depth + 1)
        dfs(current.right, depth + 1)
    }
}

class FindBottomLeftTreeValBFS : FindBottomLeftTreeVal {
    override fun invoke(root: TreeNode?): Int {
        val queue: Queue<TreeNode> = LinkedList()
        var current = root
        queue.add(current)

        while (queue.isNotEmpty()) {
            current = queue.poll()
            if (current.right != null) {
                queue.add(current.right)
            }
            if (current.left != null) {
                queue.add(current.left)
            }
        }
        return current?.value ?: 0
    }
}
