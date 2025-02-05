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

import java.util.LinkedList
import java.util.Queue
import java.util.Stack

/**
 * 404. Sum of Left Leaves
 * @see <a href="https://leetcode.com/problems/sum-of-left-leaves">Source</a>
 */
fun interface SumOfLeftLeavesStrategy {
    /**
     * Calculates the sum of left leaves in a binary tree.
     * @param root The root node of the binary tree.
     * @return The sum of left leaves.
     */
    operator fun invoke(root: TreeNode?): Int
}

/**
 * Implementation of SumOfLeftLeavesStrategy using an iterative approach.
 */
class SumOfLeftLeavesIterative : SumOfLeftLeavesStrategy {
    /**
     * Calculates the sum of left leaves in a binary tree using an iterative approach.
     * @param root The root node of the binary tree.
     * @return The sum of left leaves.
     */
    override operator fun invoke(root: TreeNode?): Int {
        if (root == null) return 0
        var ans = 0
        val stack: Stack<TreeNode> = Stack<TreeNode>()
        stack.push(root)

        while (!stack.empty()) {
            val node: TreeNode = stack.pop()
            if (node.left != null) {
                if (node.left?.left == null && node.left?.right == null) {
                    ans += node.left?.value ?: 0
                } else {
                    stack.push(node.left)
                }
            }
            if (node.right != null && node.right?.left != null || node.right?.right != null) {
                stack.push(node.right)
            }
        }
        return ans
    }
}

/**
 * Implementation of SumOfLeftLeavesStrategy using a recursive approach.
 */
class SumOfLeftLeavesRecursive : SumOfLeftLeavesStrategy {
    /**
     * Calculates the sum of left leaves in a binary tree using a recursive approach.
     * @param root The root node of the binary tree.
     * @return The sum of left leaves.
     */
    override operator fun invoke(root: TreeNode?): Int {
        if (root == null) return 0
        var ans = 0
        if (root.left != null) {
            ans += if (root.left?.left == null && root.left?.right == null) {
                root.left?.value ?: 0
            } else {
                invoke(root.left)
            }
        }
        ans += invoke(root.right)
        return ans
    }
}

/**
 * Implementation of SumOfLeftLeavesStrategy using a breadth-first search (BFS) approach.
 */
class SumOfLeftLeavesBSF : SumOfLeftLeavesStrategy {
    /**
     * Calculates the sum of left leaves in a binary tree using a breadth-first search (BFS) approach.
     * @param root The root node of the binary tree.
     * @return The sum of left leaves.
     */
    override operator fun invoke(root: TreeNode?): Int {
        if (root == null) return 0

        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        var sum = 0
        while (queue.isNotEmpty()) {
            val presentNode: TreeNode = queue.poll()
            if (presentNode.left != null) {
                queue.add(presentNode.left)
            }
            if (presentNode.right != null) {
                queue.add(presentNode.right)
            }
            if (presentNode.left != null && presentNode.left?.left == null && presentNode.left?.right == null) {
                sum += presentNode.left?.value ?: 0
            }
        }
        return sum
    }
}
