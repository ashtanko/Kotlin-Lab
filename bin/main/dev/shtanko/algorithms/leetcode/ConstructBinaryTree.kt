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
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * @see <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal">
 *     Source</a>
 */
fun interface ConstructBinaryTree {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode?
}

class ConstructBinaryTreeRecursion : ConstructBinaryTree {
    override fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return helper(0, 0, inorder.size - 1, preorder, inorder)
    }

    private fun helper(preStart: Int, inStart: Int, inEnd: Int, preorder: IntArray, inorder: IntArray): TreeNode? {
        if (preStart > preorder.size - 1 || inStart > inEnd) {
            return null
        }
        val root = TreeNode(preorder[preStart])
        var inIndex = 0 // Index of current root in inorder
        for (i in inStart..inEnd) {
            if (inorder[i] == root.value) {
                inIndex = i
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder)
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder)
        return root
    }
}
