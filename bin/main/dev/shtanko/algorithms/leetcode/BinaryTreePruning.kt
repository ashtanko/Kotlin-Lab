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
import dev.shtanko.algorithms.annotations.StraightForward
import dev.shtanko.algorithms.annotations.level.Medium

/**
 * 814. Binary Tree Pruning
 * @see <a href="https://leetcode.com/problems/binary-tree-pruning/">Source</a>
 */
@Medium("https://leetcode.com/problems/binary-tree-pruning")
fun interface BinaryTreePruning {
    operator fun invoke(root: TreeNode?): TreeNode?
}

@Recursive
class BinaryTreePruningRecursion : BinaryTreePruning {
    override fun invoke(root: TreeNode?): TreeNode? {
        return if (containsOne(root)) root else null
    }

    private fun containsOne(node: TreeNode?): Boolean {
        if (node == null) return false

        // Check if any node in the left subtree contains a 1.
        val leftContainsOne = containsOne(node.left)

        // Check if any node in the right subtree contains a 1.
        val rightContainsOne = containsOne(node.right)

        // If the left subtree does not contain a 1, prune the subtree.
        if (!leftContainsOne) node.left = null

        // If the right subtree does not contain a 1, prune the subtree.
        if (!rightContainsOne) node.right = null

        // Return true if the current node, its left or right subtree contains a 1.
        return node.value == 1 || leftContainsOne || rightContainsOne
    }
}

@StraightForward
@Recursive
class BinaryTreePruningSimple : BinaryTreePruning {
    override fun invoke(root: TreeNode?): TreeNode? {
        if (root == null) return null
        root.left = invoke(root.left)
        root.right = invoke(root.right)
        if (root.left == null && root.right == null && root.value == 0) return null
        return root
    }
}
