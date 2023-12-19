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

/**
 * 2265. Count Nodes Equal to Average of Subtree
 * @see <a href="https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree">Source</a>
 */
fun interface AverageOfSubtree {
    operator fun invoke(root: TreeNode?): Int
}

sealed interface AverageOfSubtreeStrategy {

    /**
     * Approach: Depth First Search (DFS)
     */
    data object DFS : AverageOfSubtree, AverageOfSubtreeStrategy {
        private var result = 0

        override fun invoke(root: TreeNode?): Int {
            result = 0
            visitNode(root!!)
            return result
        }

        private fun visitNode(node: TreeNode?): Int {
            var nodes = 1
            val value = node?.value
            if (node?.left != null) {
                nodes += visitNode(node.left)
                node.value += node.left!!.value
            }
            if (node?.right != null) {
                nodes += visitNode(node.right)
                node.value += node.right!!.value
            }
            if (value == node!!.value / nodes) {
                result++
            }
            return nodes
        }
    }
}
