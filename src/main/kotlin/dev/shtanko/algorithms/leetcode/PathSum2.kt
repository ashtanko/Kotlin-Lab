/*
 * Designed and developed by 2021 ashtanko (Oleksii Shtanko)
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

import dev.shtanko.algorithms.annotations.DFS

/**
 * 113. Path Sum II
 * @see <a href="https://leetcode.com/problems/path-sum-ii/">Source</a>
 */
fun interface PathSum2 {
    operator fun invoke(root: TreeNode?, targetSum: Int): List<List<Int>>
}

/**
 * Approach: Depth First Traversal | Recursion
 */
@DFS
class PathSum2DFS : PathSum2 {
    override operator fun invoke(root: TreeNode?, targetSum: Int): List<List<Int>> {
        val pathsList: MutableList<List<Int>> = ArrayList()
        val pathNodes: MutableList<Int> = ArrayList()
        recurseTree(root, targetSum, pathNodes, pathsList)
        return pathsList
    }

    private fun recurseTree(
        node: TreeNode?,
        remainingSum: Int,
        pathNodes: MutableList<Int>,
        pathsList: MutableList<List<Int>>,
    ) {
        if (node == null) {
            return
        }
        // Add the current node to the path's list
        pathNodes.add(node.value)

        // Check if the current node is a leaf and also, if it
        // equals our remaining sum. If it does, we add the path to
        // our list of paths
        if (remainingSum == node.value && node.left == null && node.right == null) {
            pathsList.add(ArrayList(pathNodes))
        } else {
            // Else, we will recurse on the left and the right children
            recurseTree(node.left, remainingSum - node.value, pathNodes, pathsList)
            recurseTree(node.right, remainingSum - node.value, pathNodes, pathsList)
        }

        // We need to pop the node once we are done processing ALL of it's
        // subtrees.
        pathNodes.removeAt(pathNodes.size - 1)
    }
}
