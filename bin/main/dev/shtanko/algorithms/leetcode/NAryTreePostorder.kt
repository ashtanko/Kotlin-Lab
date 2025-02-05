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
 * 590. N-ary Tree Postorder Traversal
 * @see <a href="https://leetcode.com/problems/n-ary-tree-postorder-traversal/">N-ary Tree Postorder Traversal</a>
 */
fun interface NAryTreePostorder {
    operator fun invoke(root: NAryNode?): List<Int>
}

class NAryTreePostorderRecursive : NAryTreePostorder {
    override fun invoke(root: NAryNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result
        traversePostorder(root, result)
        return result
    }

    private fun traversePostorder(currentNode: NAryNode?, postorderList: MutableList<Int>) {
        if (currentNode == null) return

        // First, visit all children
        currentNode.children.forEach { childNode ->
            traversePostorder(childNode, postorderList)
        }

        // Then, add the current node's value
        postorderList.add(currentNode.value)
    }
}

class NAryTreePostorderIterative : NAryTreePostorder {
    override fun invoke(root: NAryNode?): List<Int> {
        val result = mutableListOf<Int>()

        // If the root is null, return the empty list
        if (root == null) return result

        val nodeStack = ArrayDeque<NAryNode>()
        nodeStack.add(root)

        // Traverse the tree using the stack
        while (nodeStack.isNotEmpty()) {
            val currentNode = nodeStack.removeLast()
            result.add(currentNode.value)
            // Push all the children of the current node to the stack
            currentNode.children.forEach { child ->
                child?.let {
                    nodeStack.add(it)
                }
            }
        }

        // Reverse the result list to get the correct postorder traversal
        result.reverse()
        return result
    }
}

class NAryTreePostorderTwoStacks : NAryTreePostorder {
    override fun invoke(root: NAryNode?): List<Int> {
        val result = mutableListOf<Int>()

        // If the root is null, return the empty list
        if (root == null) return result

        val nodeStack = ArrayDeque<NAryNode>() // Stack for traversal
        val reverseStack = ArrayDeque<NAryNode>() // Stack to reverse the order

        nodeStack.add(root)

        // Traverse the tree using the nodeStack
        while (nodeStack.isNotEmpty()) {
            val currentNode = nodeStack.removeLast()
            reverseStack.add(currentNode)

            // Push all the children of the current node to nodeStack
            currentNode.children.forEach { child ->
                child?.let {
                    nodeStack.add(it)
                }
            }
        }

        // Pop nodes from reverseStack and add their values to the result list
        while (reverseStack.isNotEmpty()) {
            val currentNode = reverseStack.removeLast()
            result.add(currentNode.value)
        }

        return result
    }
}

class NAryTreePostorderNoReverse : NAryTreePostorder {
    override fun invoke(root: NAryNode?): List<Int> {
        val result = mutableListOf<Int>()
        // If the root is null, return the empty list
        if (root == null) return result

        val nodeStack = ArrayDeque<Pair<NAryNode, Boolean>>()
        nodeStack.add(Pair(root, false))

        while (nodeStack.isNotEmpty()) {
            val (currentNode, isVisited) = nodeStack.removeLast()

            if (isVisited) {
                // If the node has been visited, add its value to the result
                result.add(currentNode.value)
            } else {
                // Mark the current node as visited and push it back to the stack
                nodeStack.add(Pair(currentNode, true))

                // Push all children to the stack in reverse order
                currentNode.children.reversed()?.forEach { child ->
                    child?.let {
                        nodeStack.add(Pair(child, false))
                    }
                }
            }
        }

        return result
    }
}
