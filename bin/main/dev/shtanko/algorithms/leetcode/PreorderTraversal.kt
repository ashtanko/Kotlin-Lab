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
import java.util.Stack

/**
 * Extension function for TreeNode? to perform a pre-order traversal of a binary tree.
 *
 * Pre-order traversal is a tree traversal method where each node is processed before its subtrees.
 * In this method, the root is visited first, then the left subtree, and finally the right subtree.
 * This function uses an iterative approach with a stack to keep track of nodes.
 *
 * @return List<Int> A list of node values in the order they were visited during the pre-order traversal.
 */
fun TreeNode?.preorderTraversal(): List<Int> {
    val traversalResult: MutableList<Int> = LinkedList() // Stores the result of the traversal
    val nodeStack: Stack<TreeNode> = Stack() // Stack to keep track of nodes to visit
    var currentNode = this // The current node being processed
    while (currentNode != null) {
        traversalResult.add(currentNode.value) // Add the value of the current node to the result
        if (currentNode.right != null) {
            nodeStack.push(currentNode.right) // Push the right child to the stack if it exists
        }
        currentNode = currentNode.left // Move to the left child
        if (currentNode == null && nodeStack.isNotEmpty()) {
            currentNode = nodeStack.pop() // Pop a node from the stack if the current node is null
        }
    }

    return traversalResult
}
