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

import java.util.Stack

/**
 * 2331. Evaluate Boolean Binary Tree
 * @see <a href="https://leetcode.com/problems/evaluate-boolean-binary-tree">Source</a>
 */
fun interface EvaluateBooleanBinaryTree {
    operator fun invoke(root: TreeNode?): Boolean
}

class EvaluateBooleanBinaryTreeDFS : EvaluateBooleanBinaryTree {
    override fun invoke(root: TreeNode?): Boolean {
        if (root?.left == null && root?.right == null) {
            return root?.value != 0
        }

        val evaluateLeftSubtree: Boolean = invoke(root.left)
        val evaluateRightSubtree: Boolean = invoke(root.right)
        val evaluateRoot = if (root.value == 2) {
            evaluateLeftSubtree or evaluateRightSubtree
        } else {
            evaluateLeftSubtree and evaluateRightSubtree
        }

        return evaluateRoot
    }
}

class EvaluateBooleanBinaryTreeDFSIterative : EvaluateBooleanBinaryTree {
    override fun invoke(root: TreeNode?): Boolean {
        val st: Stack<TreeNode> = Stack()
        st.push(root)
        val evaluated: HashMap<TreeNode?, Boolean> = HashMap()

        while (st.isNotEmpty()) {
            val topNode: TreeNode = st.peek()

            // If the node is a leaf node, store its value in the evaluated hashmap
            // and continue
            if (topNode.left == null && topNode.right == null) {
                st.pop()
                evaluated[topNode] = topNode.value == 1
                continue
            }

            // If both the children have already been evaluated, use their
            // values to evaluate the current node.
            if (evaluated.containsKey(topNode.left) &&
                evaluated.containsKey(topNode.right)
            ) {
                st.pop()
                val topLeftNode = evaluated.getOrDefault(topNode.left, false)
                val topRightNode = evaluated.getOrDefault(topNode.right, false)
                if (topNode.value == 2) {
                    evaluated[topNode] = topLeftNode || topRightNode
                } else {
                    evaluated[topNode] = topLeftNode && topRightNode
                }
            } else {
                // If both the children are not leaf nodes, push the current
                // node along with its left and right child back into the stack.
                st.push(topNode.right)
                st.push(topNode.left)
            }
        }

        return evaluated.getOrDefault(root, false)
    }
}
