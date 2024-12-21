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
import kotlin.math.min

/**
 * 111. Minimum Depth of Binary Tree
 * @see <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/">Source</a>
 */
fun interface MinDepth {
    operator fun invoke(root: TreeNode?): Int
}

/**
 * Approach 1: Depth-First Search (DFS)
 */
class MinDepthDFS : MinDepth {
    override operator fun invoke(root: TreeNode?): Int {
        return dfs(root)
    }

    private fun dfs(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        // If only one of child is non-null, then go into that recursion.
        if (root.left == null) {
            return 1 + dfs(root.right)
        } else if (root.right == null) {
            return 1 + dfs(root.left)
        }

        // Both children are non-null, hence call for both childs.
        return 1 + min(dfs(root.left), dfs(root.right))
    }
}

/**
 * Approach 2: Breadth-First Search (BFS)
 */
class MinDepthBFS : MinDepth {
    override operator fun invoke(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val q: Queue<TreeNode> = LinkedList()
        q.add(root)
        var depth = 1

        while (q.isNotEmpty()) {
            var qSize: Int = q.size
            while (qSize > 0) {
                qSize--
                val node: TreeNode = q.remove() ?: continue
                // Since we added nodes without checking null, we need to skip them here.

                // The first leaf would be at minimum depth, hence return it.
                if (node.left == null && node.right == null) {
                    return depth
                }
                q.add(node.left)
                q.add(node.right)
            }
            depth++
        }
        return -1
    }
}
