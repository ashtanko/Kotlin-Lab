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

package dev.shtanko.algorithms.tree.traversals.inorder

import dev.shtanko.algorithms.tree.traversals.BinaryTreeNode
import dev.shtanko.algorithms.tree.traversals.BinaryTreeTraversalStrategy
import java.util.LinkedList
import java.util.Stack

class BinaryTreeInorderIterativeTraversal : BinaryTreeTraversalStrategy {
    override fun invoke(root: BinaryTreeNode): List<Int> {
        var node: BinaryTreeNode? = getLeftmost(root)
        val list = LinkedList<Int>()

        while (node != null) {
            list.add(node.data)

            val right = node.right
            if (right != null) {
                node = getLeftmost(right)
            } else {
                while (node?.parent != null && node.isRightChild()) {
                    node = node.parent
                }

                node = node?.parent
            }
        }
        return list
    }

    private fun getLeftmost(startingNode: BinaryTreeNode): BinaryTreeNode {
        var node: BinaryTreeNode? = startingNode
        while (node?.left != null) {
            node = node.left
        }

        return node ?: startingNode
    }
}

class BinaryTreeInorderStackTraversal : BinaryTreeTraversalStrategy {
    override fun invoke(root: BinaryTreeNode): List<Int> {
        val stack = Stack<BinaryTreeNode>()
        var current: BinaryTreeNode? = root
        val list = LinkedList<Int>()

        while (!stack.empty() || current != null) {
            if (current != null) {
                stack.push(current)
                current = current.left
            } else {
                current = stack.pop()
                list.add(current.data)
                current = current.right
            }
        }
        return list
    }
}
