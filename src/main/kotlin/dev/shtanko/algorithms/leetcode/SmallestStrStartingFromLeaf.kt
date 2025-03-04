/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
 * 988. Smallest String Starting From Leaf
 * @see <a href="https://leetcode.com/problems/smallest-string-starting-from-leaf">Source</a>
 */
fun interface SmallestStrStartingFromLeaf {
    operator fun invoke(root: TreeNode?): String
}

@DFS
class SmallestStrStartingFromLeafDFS : SmallestStrStartingFromLeaf {

    private var smallest = ""

    override fun invoke(root: TreeNode?): String {
        dfs(root, StringBuilder())
        return smallest
    }

    private fun dfs(root: TreeNode?, sb: StringBuilder) {
        if (root == null) {
            return
        }

        sb.append(('a' + root.value))

        if (root.left == null && root.right == null) {
            val path = sb.reverse().toString()
            if (smallest.isEmpty() || path < smallest) {
                smallest = path
            }
            sb.reverse()
        }

        if (root.left != null) {
            dfs(root.left, StringBuilder(sb))
        }
        if (root.right != null) {
            dfs(root.right, StringBuilder(sb))
        }
    }
}

@BFS
class SmallestStrStartingFromLeafBFS : SmallestStrStartingFromLeaf {
    override fun invoke(root: TreeNode?): String {
        var smallestString = ""
        val nodeQueue: Queue<Pair<TreeNode, String>> = LinkedList()

        // Add root node to queue along with its value converted to a character
        root?.let {
            nodeQueue.add(Pair(it, "${('a' + it.value)}"))
        }

        // Perform BFS traversal until queue is empty
        while (nodeQueue.isNotEmpty()) {
            // Pop the leftmost node and its corresponding string from queue
            val pair = nodeQueue.poll()
            val node = pair.first
            val currentString = pair.second

            // If current node is a leaf node
            if (node.left == null && node.right == null) {
                // Update smallestString if it's empty or current string is smaller
                smallestString = if (smallestString.isEmpty()) {
                    currentString
                } else {
                    if (currentString < smallestString) currentString else smallestString
                }
            }

            // If current node has a left child, append it to queue
            node.left?.let {
                nodeQueue.add(Pair(it, "${('a' + it.value)}$currentString"))
            }

            // If current node has a right child, append it to queue
            node.right?.let {
                nodeQueue.add(Pair(it, "${('a' + it.value)}$currentString"))
            }
        }

        return smallestString
    }
}
