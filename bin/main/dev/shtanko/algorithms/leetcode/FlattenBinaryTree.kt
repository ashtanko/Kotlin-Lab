/*
 * Copyright 2021 Oleksii Shtanko
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

import java.util.Stack

/**
 * 114. Flatten Binary Tree to Linked List
 * @see <a href="https://leetcode.com/problems/flatten-binary-tree-to-linked-list/">Source</a>
 */
fun interface FlattenBinaryTree {
    fun flatten(root: TreeNode?)
}

class FlattenRecursion : FlattenBinaryTree {
    override fun flatten(root: TreeNode?) {
        flattenTree(root)
    }

    private fun flattenTree(node: TreeNode?): TreeNode? {
        if (node == null) {
            return null
        }
        if (node.left == null && node.right == null) {
            return node
        }
        val leftTail = flattenTree(node.left)
        val rightTail = flattenTree(node.right)

        if (leftTail != null) {
            leftTail.right = node.right
            node.right = node.left
            node.left = null
        }

        return rightTail ?: leftTail
    }
}

class FlattenStack : FlattenBinaryTree {
    override fun flatten(root: TreeNode?) {
        if (root == null) {
            return
        }

        val start = 1
        val end = 2

        var tailNode: TreeNode? = null
        val stack: Stack<Pair<TreeNode, Int>> = Stack<Pair<TreeNode, Int>>()
        stack.push(root to start)

        while (stack.isNotEmpty()) {
            val nodeData: Pair<TreeNode, Int> = stack.pop()
            val currentNode: TreeNode = nodeData.first
            val recursionState: Int = nodeData.second
            if (currentNode.left == null && currentNode.right == null) {
                tailNode = currentNode
                continue
            }
            if (recursionState == start) {
                if (currentNode.left != null) {
                    stack.push(Pair(currentNode, end))
                    currentNode.left?.let {
                        stack.push(it to start)
                    }
                } else if (currentNode.right != null) {
                    stack.push(Pair(currentNode.right!!, start))
                }
            } else {
                var rightNode = currentNode.right
                if (tailNode != null) {
                    tailNode.right = currentNode.right
                    currentNode.right = currentNode.left
                    currentNode.left = null
                    rightNode = tailNode.right
                }
                if (rightNode != null) {
                    stack.push(Pair(rightNode, start))
                }
            }
        }
    }
}
