/*
 * Copyright 2022 Oleksii Shtanko
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

import dev.shtanko.algorithms.annotations.Recursive
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 1382. Balance a Binary Search Tree
 * @see <a href="https://leetcode.com/problems/balance-a-binary-search-tree/">Source</a>
 */
@Medium("https://leetcode.com/problems/balance-a-binary-search-tree")
fun interface BalanceBST {
    operator fun invoke(root: TreeNode?): TreeNode?
}

/**
 * Approach 1: Inorder Traversal + Recursive Construction
 */
@Recursive(info = "Inorder Traversal")
class BalanceBSTInorder : BalanceBST {
    private val sortedArr: MutableList<TreeNode> = ArrayList()

    override operator fun invoke(root: TreeNode?): TreeNode? {
        inorderTraverse(root)
        return sortedArrayToBST(0, sortedArr.size - 1)
    }

    private fun inorderTraverse(root: TreeNode?) {
        if (root == null) return
        inorderTraverse(root.left)
        sortedArr.add(root)
        inorderTraverse(root.right)
    }

    private fun sortedArrayToBST(start: Int, end: Int): TreeNode? {
        if (start > end) return null
        val mid = (start + end) / 2
        val root = sortedArr[mid]
        root.left = sortedArrayToBST(start, mid - 1)
        root.right = sortedArrayToBST(mid + 1, end)
        return root
    }
}

class BalanceBSTreeDSW : BalanceBST {
    override operator fun invoke(root: TreeNode?): TreeNode? {
        val pseudoRoot = TreeNode(0)
        pseudoRoot.right = root

        val nodeCount = flattenTreeWithRightRotations(pseudoRoot)
        val heightOfTree = (Math.log((nodeCount + 1).toDouble()) / Math.log(2.0)).toInt()
        val numOfNodesInTree = Math.pow(2.0, heightOfTree.toDouble()).toInt() - 1

        createBalancedTreeWithLeftRotation(pseudoRoot, nodeCount - numOfNodesInTree)
        var numOfNodesInSubTree = numOfNodesInTree / 2
        while (numOfNodesInSubTree > 0) {
            createBalancedTreeWithLeftRotation(pseudoRoot, numOfNodesInSubTree)
            numOfNodesInSubTree /= 2
        }

        return pseudoRoot.right
    }

    private fun flattenTreeWithRightRotations(root: TreeNode): Int {
        var tree = root
        var nodeCount = 0
        var pseudoRoot = tree.right
        while (pseudoRoot != null) {
            if (pseudoRoot.left != null) {
                val oldPseudoRoot: TreeNode = pseudoRoot
                pseudoRoot = pseudoRoot.left
                oldPseudoRoot.left = pseudoRoot?.right
                pseudoRoot?.right = oldPseudoRoot
                tree.right = pseudoRoot
            } else {
                ++nodeCount
                tree = pseudoRoot
                pseudoRoot = pseudoRoot.right
            }
        }
        return nodeCount
    }

    private fun createBalancedTreeWithLeftRotation(root: TreeNode?, numOfNodesInSubTree: Int) {
        var tree = root
        var num = numOfNodesInSubTree
        var pseudoRoot = tree?.right
        while (num-- > 0) {
            val oldPseudoRoot = pseudoRoot
            pseudoRoot = pseudoRoot?.right
            tree?.right = pseudoRoot
            oldPseudoRoot?.right = pseudoRoot?.left
            pseudoRoot?.left = oldPseudoRoot
            tree = pseudoRoot
            pseudoRoot = pseudoRoot?.right
        }
    }
}
