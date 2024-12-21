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

import java.util.ArrayDeque
import java.util.Deque
import java.util.LinkedList

/**
 * Extension function for TreeNode? to perform a post-order traversal of a binary tree.
 *
 * Post-order traversal is a tree traversal method where each node is processed after its subtrees.
 * In this method, the root is visited last, hence the name. The order of the traversal is left subtree, right subtree,
 * and then the root.
 * This function uses an iterative approach with a stack to keep track of nodes.
 *
 * @return List<Int> A list of node values in the order they were visited during the post-order traversal.
 */
fun TreeNode?.postOrderTraversal(): List<Int> {
    val traversalResult: LinkedList<Int> = LinkedList() // Stores the result of the traversal
    val nodeStack: Deque<TreeNode> = ArrayDeque() // Stack to keep track of nodes to visit
    var currentNode: TreeNode? = this // The current node being processed
    while (nodeStack.isNotEmpty() || currentNode != null) {
        currentNode = if (currentNode != null) {
            nodeStack.push(currentNode) // Push the current node to the stack
            traversalResult.addFirst(currentNode.value) // Add the value of the current node to the result
            currentNode.right // Move to the right child
        } else {
            val poppedNode = nodeStack.pop() // Pop a node from the stack
            poppedNode.left // Move to the left child
        }
    }
    return traversalResult
}
