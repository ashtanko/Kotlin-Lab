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

/**
 * 1325. Delete Leaves With a Given Value
 * @see <a href="https://leetcode.com/problems/delete-leaves-with-a-given-value">Source</a>
 */
fun interface RemoveLeafNodes {
    operator fun invoke(root: TreeNode?, target: Int): TreeNode?
}

class RemoveLeafNodesRecursive : RemoveLeafNodes {
    override fun invoke(root: TreeNode?, target: Int): TreeNode? {
        if (root == null) return null
        root.left = invoke(root.left, target)
        root.right = invoke(root.right, target)
        return if (root.left == null && root.right == null && root.value == target) null else root
    }
}

class RemoveLeafNodesIterative : RemoveLeafNodes {
    override fun invoke(root: TreeNode?, target: Int): TreeNode? {
        val stack = mutableListOf<TreeNode>()
        val dummy = TreeNode(-1)
        dummy.left = root
        stack.add(dummy)
        while (stack.isNotEmpty()) {
            val current = stack.removeAt(stack.size - 1)
            current.left?.let {
                stack.add(it)
            }
            current.right?.let {
                stack.add(it)
            }
            if (isLeafNode(current.left, target)) {
                current.left = null
            }
            if (isLeafNode(current.right, target)) {
                current.right = null
            }
        }
        return dummy.left
    }

    private fun isLeafNode(node: TreeNode?, target: Int): Boolean {
        return node != null && node.left == null && node.right == null && node.value == target
    }
}
