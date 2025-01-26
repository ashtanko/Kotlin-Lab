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

import dev.shtanko.algorithms.annotations.BFS
import dev.shtanko.algorithms.annotations.DFS
import java.util.LinkedList
import java.util.Queue

/**
 * 958. Check Completeness of a Binary Tree
 * @see <a href="https://leetcode.com/problems/check-completeness-of-a-binary-tree/">Source</a>
 */
fun interface CheckCompletenessOfBinaryTree {
    fun isCompleteTree(root: TreeNode?): Boolean
}

@BFS
class CheckCompletenessOfBinaryTreeBFS : CheckCompletenessOfBinaryTree {
    override fun isCompleteTree(root: TreeNode?): Boolean {
        val bfs: Queue<TreeNode> = LinkedList()
        bfs.offer(root)
        while (bfs.peek() != null) {
            val node = bfs.poll()
            bfs.offer(node.left)
            bfs.offer(node.right)
        }
        while (bfs.isNotEmpty() && bfs.peek() == null) bfs.poll()
        return bfs.isEmpty()
    }
}

@DFS
class CheckCompletenessOfBinaryTreeDFS : CheckCompletenessOfBinaryTree {
    override fun isCompleteTree(root: TreeNode?): Boolean {
        return dfs(root) >= 0
    }

    private fun dfs(root: TreeNode?): Int {
        if (root == null) return 0
        val l = dfs(root.left)
        val r = dfs(root.right)
        if (l and l + 1 == 0 && l / 2 <= r && r <= l) {
            return l + r + 1
        }
        return if (r and r + 1 == 0 && r <= l && l <= r * 2 + 1) {
            l + r + 1
        } else {
            -1
        }
    }
}
