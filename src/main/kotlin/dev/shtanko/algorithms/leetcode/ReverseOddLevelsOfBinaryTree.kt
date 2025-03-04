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
import dev.shtanko.algorithms.annotations.level.Medium
import dev.shtanko.extensions.isEven
import java.util.Queue

/**
 * 2415. Reverse Odd Levels of Binary Tree
 * @see <a href="https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/">Reverse Odd Levels of Binary Tree</a>
 */
@Medium
sealed interface ReverseOddLevelsOfBinaryTree {
    operator fun invoke(root: TreeNode?): TreeNode?

    @DFS
    data object DepthFirstSearch : ReverseOddLevelsOfBinaryTree {
        override fun invoke(root: TreeNode?): TreeNode? {
            dfs(root?.left, root?.right, 0)
            return root
        }

        private fun dfs(
            leftChild: TreeNode?,
            rightChild: TreeNode?,
            level: Int,
        ) {
            if (leftChild == null || rightChild == null) {
                return
            }
            if (level.isEven) {
                val temp = leftChild.value
                leftChild.value = rightChild.value
                rightChild.value = temp
            }
            dfs(leftChild.left, rightChild.right, level + 1)
            dfs(leftChild.right, rightChild.left, level + 1)
        }
    }

    @BFS
    data object BreadthFirstSearch : ReverseOddLevelsOfBinaryTree {
        override fun invoke(root: TreeNode?): TreeNode? {
            if (root == null) {
                return null
            }

            val queue: Queue<TreeNode> = java.util.LinkedList()
            queue.add(root)
            var level = 0
            while (queue.isNotEmpty()) {
                val size = queue.size
                val currentLevelNodes = mutableListOf<TreeNode>()

                for (i in 0 until size) {
                    val node: TreeNode? = queue.poll()
                    node?.let {
                        currentLevelNodes.add(node)
                        node.left?.let {
                            currentLevelNodes.add(it)
                        }
                        node.right?.let {
                            currentLevelNodes.add(it)
                        }
                    }
                }

                if (!level.isEven) {
                    var left = 0
                    var right = currentLevelNodes.size - 1
                    while (left < right) {
                        val tmp = currentLevelNodes[left].value
                        currentLevelNodes[left].value = currentLevelNodes[right].value
                        currentLevelNodes[right].value = tmp
                        left--
                        right--
                    }
                }
                level++
            }

            return root
        }
    }
}
