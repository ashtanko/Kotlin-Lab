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

import java.util.ArrayDeque
import java.util.Deque
import java.util.LinkedList

/**
 * 98. Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
fun interface ValidateBinarySearchTree {
    operator fun invoke(root: TreeNode): Boolean
}

/**
 * Approach 1: Recursive Traversal with Valid Range
 */
class RecursiveTraversalValidRange : ValidateBinarySearchTree {
    override fun invoke(root: TreeNode): Boolean {
        return validate(root, null, null)
    }

    private fun validate(root: TreeNode?, low: Int?, high: Int?): Boolean {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true
        }
        // The current node's value must be between low and high.
        return if (low != null && root.value <= low || high != null && root.value >= high) {
            false
        } else {
            validate(root.right, root.value, high) && validate(root.left, low, root.value)
        }
        // The left and right subtree must also be valid.
    }
}

class IterativeTraversalValidRange : ValidateBinarySearchTree {

    private val stack: Deque<TreeNode?> = LinkedList()
    private val upperLimits: Deque<Int> = LinkedList()
    private val lowerLimits: Deque<Int> = LinkedList()

    override fun invoke(root: TreeNode): Boolean {
        var low: Int? = null
        var high: Int? = null
        var value: Int
        var node: TreeNode? = root
        update(node, low, high)

        while (stack.isNotEmpty()) {
            node = stack.poll()
            low = lowerLimits.poll()
            high = upperLimits.poll()
            if (node == null) continue
            value = node.value
            if (low != null && value <= low) {
                return false
            }
            if (high != null && value >= high) {
                return false
            }
            update(node.right, value, high)
            update(node.left, low, value)
        }
        return true
    }

    private fun update(root: TreeNode?, low: Int?, high: Int?) {
        stack.add(root)
        lowerLimits.add(low)
        upperLimits.add(high)
    }
}

/**
 * Approach 3: Recursive Inorder Traversal
 */
class RecursiveInorderTraversal : ValidateBinarySearchTree {

    private var prev: Int? = null

    override fun invoke(root: TreeNode): Boolean {
        return inorder(root)
    }

    private fun inorder(root: TreeNode?): Boolean {
        if (root == null) {
            return true
        }
        if (!inorder(root.left)) {
            return false
        }
        if (prev != null && root.value <= prev!!) {
            return false
        }
        prev = root.value
        return inorder(root.right)
    }
}

/**
 * Approach 4: Iterative Inorder Traversal
 */
class IterativeInorderTraversal : ValidateBinarySearchTree {
    override fun invoke(root: TreeNode): Boolean {
        val stack: Deque<TreeNode> = ArrayDeque()
        var prev: Int? = null
        var node: TreeNode? = root
        while (stack.isNotEmpty() || node != null) {
            while (node != null) {
                stack.push(node)
                node = node.left
            }
            node = stack.pop()
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (prev != null && node.value <= prev) {
                return false
            }
            prev = node.value
            node = node.right
        }
        return true
    }
}
