/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
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

package dev.shtanko.algorithms.templates

import dev.shtanko.algorithms.leetcode.TreeNode
import java.util.LinkedList
import java.util.Queue

/**
 * Binary tree: BFS
 */
private fun fn(root: TreeNode?): Int {
    val queue: Queue<TreeNode> = LinkedList()
    queue.add(root)
    val ans = 0
    while (queue.isNotEmpty()) {
        val currentLength: Int = queue.size
        // do logic for current level
        for (i in 0 until currentLength) {
            val node: TreeNode = queue.remove()
            // do logic
            if (node.left != null) {
                queue.add(node.left)
            }
            if (node.right != null) {
                queue.add(node.right)
            }
        }
    }
    return ans
}
