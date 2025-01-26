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

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.LinkedList
import java.util.Queue

/**
 * 2641. Cousins in Binary Tree II
 * @see <a href="https://leetcode.com/problems/cousins-in-binary-tree-ii/">Source</a>
 */
@Medium("https://leetcode.com/problems/cousins-in-binary-tree-ii")
fun interface CousinsInBinaryTree2 {
    operator fun invoke(root: TreeNode?): TreeNode?
}

class CousinsInBinaryTree2BFS : CousinsInBinaryTree2 {
    override fun invoke(root: TreeNode?): TreeNode? {
        if (root == null) {
            return root
        }

        val nodeQueue: Queue<TreeNode> = LinkedList()
        nodeQueue.offer(root)
        var previousLevelSum = root.value

        while (nodeQueue.isNotEmpty()) {
            val levelSize = nodeQueue.size
            var currentLevelSum = 0

            repeat(levelSize) {
                val currentNode = nodeQueue.poll()
                currentNode.value = previousLevelSum - currentNode.value

                val siblingSum =
                    (currentNode.left?.value ?: 0) +
                        (currentNode.right?.value ?: 0)

                currentNode.left?.let {
                    currentLevelSum += it.value
                    it.value = siblingSum
                    nodeQueue.offer(it)
                }

                currentNode.right?.let {
                    currentLevelSum += it.value
                    it.value = siblingSum
                    nodeQueue.offer(it)
                }
            }
            previousLevelSum = currentLevelSum
        }
        return root
    }
}
