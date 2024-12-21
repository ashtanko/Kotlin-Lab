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
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * @see <a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal">
 *     Source</a>
 */
fun interface ConstructBinaryTree2 {
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode?
}

class ConstructBinaryTree2Recursive : ConstructBinaryTree2 {
    override fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        if (inorder.size != postorder.size) return null
        val map: HashMap<Int, Int> = HashMap()
        for (i in inorder.indices) {
            map[inorder[i]] = i
        }
        return buildBinaryTree(inorder, 0, inorder.size - 1, postorder, 0, postorder.size - 1, map)
    }

    private fun buildBinaryTree(
        inorder: IntArray?,
        inorderStart: Int,
        inorderEnd: Int,
        postorder: IntArray,
        postorderStart: Int,
        postorderEnd: Int,
        map: HashMap<Int, Int>,
    ): TreeNode? {
        if (postorderStart > postorderEnd || inorderStart > inorderEnd) return null
        val node = TreeNode(postorder[postorderEnd])
        val rootIndex = map.getOrDefault(postorder[postorderEnd], 0)
        val leftChild = buildBinaryTree(
            inorder,
            inorderStart,
            rootIndex - 1,
            postorder,
            postorderStart,
            postorderStart + rootIndex - inorderStart - 1,
            map,
        )
        val rightChild = buildBinaryTree(
            inorder,
            rootIndex + 1,
            inorderEnd,
            postorder,
            postorderStart + rootIndex - inorderStart,
            postorderEnd - 1,
            map,
        )
        node.left = leftChild
        node.right = rightChild
        return node
    }
}
