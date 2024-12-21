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

import dev.shtanko.algorithms.annotations.BruteForce
import dev.shtanko.algorithms.annotations.Iterative
import dev.shtanko.algorithms.annotations.Recursive
import java.util.Stack

/**
 * 1038. Binary Search Tree to Greater Sum Tree
 */
fun interface BstToGst {
    operator fun invoke(root: TreeNode): TreeNode
}

/**
 * BstToGstBruteForce is a brute force implementation of the BstToGst interface.
 * It first collects the inorder values of the tree, then reverses them to get the values in descending order.
 * Finally, it updates the node values with the sum of values greater than the current node value.
 */
@BruteForce
class BstToGstBruteForce : BstToGst {
    override fun invoke(root: TreeNode): TreeNode {
        val inorderValues: MutableList<Int> = ArrayList()
        collectInorderValues(root, inorderValues)

        inorderValues.reverse()

        updateNodeValues(root, inorderValues)
        return root
    }

    /**
     * collectInorderValues is a helper function that collects the inorder values of the tree.
     */
    private fun collectInorderValues(node: TreeNode?, inorderValues: MutableList<Int>) {
        if (node == null) {
            return
        }
        collectInorderValues(node.left, inorderValues)
        inorderValues.add(node.value)
        collectInorderValues(node.right, inorderValues)
    }

    /**
     * updateNodeValues is a helper function that updates the node values with the sum of values greater than the
     * current node value.
     */
    private fun updateNodeValues(node: TreeNode?, inorderValues: List<Int>) {
        if (node == null) {
            return
        }
        updateNodeValues(node.left, inorderValues)
        updateNodeValues(node.right, inorderValues)

        var greaterValuesSum = 0
        for (value in inorderValues) {
            if (value > node.value) {
                greaterValuesSum += value
            } else {
                break
            }
        }

        node.value += greaterValuesSum
    }
}

/**
 * BstToGstInOrder is a recursive implementation of the BstToGst interface.
 * It updates the node values in the inorder traversal order.
 */
@Recursive
class BstToGstInOrder : BstToGst {
    override fun invoke(root: TreeNode): TreeNode {
        val accumulatedSum = IntArray(1)
        updateNodeValuesInOrder(root, accumulatedSum)
        return root
    }

    /**
     * updateNodeValuesInOrder is a helper function that updates the node values in the inorder traversal order.
     */
    private fun updateNodeValuesInOrder(node: TreeNode?, accumulatedSum: IntArray) {
        if (node == null) {
            return
        }

        updateNodeValuesInOrder(node.right, accumulatedSum)
        accumulatedSum[0] += node.value
        node.value = accumulatedSum[0]
        updateNodeValuesInOrder(node.left, accumulatedSum)
    }
}

/**
 * BstToGstInOrderIterative is an iterative implementation of the BstToGst interface.
 * It uses a stack to simulate the recursive process of the inorder traversal.
 */
@Iterative
class BstToGstInOrderIterative : BstToGst {
    override fun invoke(root: TreeNode): TreeNode {
        var accumulatedSum = 0
        val nodeStack: Stack<TreeNode> = Stack()
        var currentNode: TreeNode? = root

        while (!nodeStack.empty() || currentNode != null) {
            while (currentNode != null) {
                nodeStack.push(currentNode)
                currentNode = currentNode.right
            }

            currentNode = nodeStack.pop()

            accumulatedSum += currentNode.value
            currentNode.value = accumulatedSum

            currentNode = currentNode.left
        }
        return root
    }
}

/**
 * BstToGstMorrisTraversal is an implementation of the BstToGst interface using Morris Traversal.
 * It does not use any extra space and modifies the tree in place.
 */
class BstToGstMorrisTraversal : BstToGst {
    override fun invoke(root: TreeNode): TreeNode {
        var accumulatedSum = 0
        var currentNode: TreeNode? = root

        while (currentNode != null) {
            if (currentNode.right == null) {
                accumulatedSum += currentNode.value
                currentNode.value = accumulatedSum
                currentNode = currentNode.left
            } else {
                val successor = findSuccessor(currentNode)
                if (successor?.left == null) {
                    successor?.left = currentNode
                    currentNode = currentNode.right
                } else {
                    successor.left = null
                    accumulatedSum += currentNode.value
                    currentNode.value = accumulatedSum
                    currentNode = currentNode.left
                }
            }
        }

        return root
    }

    /**
     * findSuccessor is a helper function that finds the inorder successor of a given node.
     */
    private fun findSuccessor(node: TreeNode): TreeNode? {
        var successor = node.right
        while (successor?.left != null && successor.left != node) {
            successor = successor.left
        }
        return successor
    }
}
