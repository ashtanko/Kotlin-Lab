/*
 * Copyright 2020 Oleksii Shtanko
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

import dev.shtanko.extensions.isEven
import java.util.Stack

/**
 * 101. Symmetric Tree
 * @see <a href="https://leetcode.com/problems/symmetric-tree/">Source</a>
 */
fun interface SymmetricTree {
    operator fun invoke(root: TreeNode?): Boolean
}

class SymmetricTreeRecursive : SymmetricTree {
    override fun invoke(root: TreeNode?) = root.isSymmetricTree()

    private fun TreeNode?.isSymmetricTree(): Boolean {
        if (this == null) return true
        return (left to right).isSymmetric()
    }

    private fun Pair<TreeNode?, TreeNode?>.isSymmetric(): Boolean {
        if (first == null || second == null) {
            return first == second
        }
        if (first?.value != second?.value) {
            return false
        }
        return (first?.left to second?.right).isSymmetric() && (first?.right to second?.left).isSymmetric()
    }
}

class SymmetricTreeIterative : SymmetricTree {
    override fun invoke(root: TreeNode?): Boolean {
        if (root == null) return true
        val stack = Stack<TreeNode>()
        var left: TreeNode
        var right: TreeNode
        if (root.left != null) {
            if (root.right == null) {
                return false
            }
            stack.push(root.left)
            stack.push(root.right)
        } else if (root.right != null) {
            return false
        }
        while (stack.isNotEmpty()) {
            if (stack.size.isEven.not()) {
                return false
            }
            right = stack.pop()
            left = stack.pop()
            if (right.value != left.value) {
                return false
            }
            if (left.left != null) {
                if (right.right == null) {
                    return false
                }
                stack.push(left.left)
                stack.push(right.right)
            } else if (right.right != null) {
                return false
            }

            if (left.right != null) {
                if (right.left == null) {
                    return false
                }
                stack.push(left.right)
                stack.push(right.left)
            } else if (right.left != null) {
                return false
            }
        }

        return true
    }
}
