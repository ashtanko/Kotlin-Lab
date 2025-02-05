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

import kotlin.math.min

/**
 * 530. Minimum Absolute Difference in BST
 * @see <a href="https://leetcode.com/problems/minimum-absolute-difference-in-bst/">Source</a>
 */
fun interface MinimumAbsoluteDifferenceBST {
    fun getMinimumDifference(root: TreeNode?): Int
}

/**
 * Approach 1: Depth First Search
 */
class MinimumAbsoluteDifferenceBSTDFS : MinimumAbsoluteDifferenceBST {

    // List to store the node values.
    private val nodeValues: MutableList<Int> = ArrayList()

    override fun getMinimumDifference(root: TreeNode?): Int {
        dfs(root)

        nodeValues.sort()
        var minDifference = Int.MAX_VALUE
        // Find the diff between every two consecutive values in the list.
        // Find the diff between every two consecutive values in the list.
        for (i in 1 until nodeValues.size) {
            minDifference = min(minDifference, nodeValues[i] - nodeValues[i - 1])
        }

        return minDifference
    }

    private fun dfs(node: TreeNode?) {
        if (node == null) {
            return
        }
        nodeValues.add(node.value)
        dfs(node.left)
        dfs(node.right)
    }
}

/**
 * Approach 2: In-order Traversal Using List
 */
class MinimumAbsoluteDifferenceBSTInOrder : MinimumAbsoluteDifferenceBST {
    // List to store the tree nodes in the inorder traversal.
    private val inorderNodes: MutableList<Int> = ArrayList()

    override fun getMinimumDifference(root: TreeNode?): Int {
        inorderTraversal(root)

        var minDifference = Int.MAX_VALUE
        // Find the diff between every two consecutive values in the list.
        for (i in 1 until inorderNodes.size) {
            minDifference = min(minDifference, inorderNodes[i] - inorderNodes[i - 1])
        }

        return minDifference
    }

    private fun inorderTraversal(node: TreeNode?) {
        if (node == null) {
            return
        }
        inorderTraversal(node.left)
        // Store the nodes in the list.
        inorderNodes.add(node.value)
        inorderTraversal(node.right)
    }
}

/**
 * Approach 3: In-order Traversal Without List
 */
class MinimumAbsoluteDifferenceBSTInOrderOpt : MinimumAbsoluteDifferenceBST {
    private var minDifference = Int.MAX_VALUE

    // Initially, it will be null.
    private var prevNode: TreeNode? = null

    override fun getMinimumDifference(root: TreeNode?): Int {
        inorderTraversal(root)
        return minDifference
    }

    private fun inorderTraversal(node: TreeNode?) {
        if (node == null) {
            return
        }
        inorderTraversal(node.left)
        // Find the difference with the previous value if it is there.
        if (prevNode != null) {
            minDifference = min(minDifference, node.value - prevNode!!.value)
        }
        prevNode = node
        inorderTraversal(node.right)
    }
}
