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
 * 1367. Linked List in Binary Tree
 * @see <a href="https://leetcode.com/problems/linked-list-in-binary-tree/">Linked List in Binary Tree</a>
 */
fun interface LinkedListInBinaryTree {
    operator fun invoke(head: ListNode?, root: TreeNode?): Boolean
}

data object LinkedListInBinaryTreeDFS : LinkedListInBinaryTree {
    override fun invoke(
        head: ListNode?,
        root: TreeNode?,
    ): Boolean {
        if (root == null) {
            return false
        }
        return checkPath(root, head)
    }

    private fun checkPath(node: TreeNode?, head: ListNode?): Boolean {
        if (node == null) return false
        if (dfs(node, head)) return true // If a matching path is found
        // Recursively check left and right subtrees
        return checkPath(node.left, head) || checkPath(node.right, head)
    }

    private fun dfs(node: TreeNode?, head: ListNode?): Boolean {
        if (head == null) return true // All nodes in the list matched
        if (node == null) return false // Reached end of tree without matching all nodes
        if (node.value != head.value) return false // Value mismatch
        return dfs(node.left, head.next) || dfs(node.right, head.next)
    }
}

data object LinkedListInBinaryTreeKMP : LinkedListInBinaryTree {
    override fun invoke(
        head: ListNode?,
        root: TreeNode?,
    ): Boolean {
        // Build the pattern and prefix table from the linked list
        val pattern = mutableListOf<Int>()
        val prefixTable = mutableListOf<Int>()
        pattern.add(head?.value ?: return false)
        prefixTable.add(0)
        var patternIndex = 0
        var currentNode = head?.next

        while (currentNode != null) {
            while (patternIndex > 0 && currentNode.value != pattern[patternIndex]) {
                patternIndex = prefixTable[patternIndex - 1]
            }
            if (currentNode.value == pattern[patternIndex]) {
                patternIndex++
            }
            pattern.add(currentNode.value)
            prefixTable.add(patternIndex)
            currentNode = currentNode.next
        }

        // Perform DFS to search for the pattern in the tree
        return searchInTree(root, 0, pattern, prefixTable)
    }

    private fun searchInTree(
        node: TreeNode?,
        patternIndex: Int,
        pattern: List<Int>,
        prefixTable: List<Int>,
    ): Boolean {
        if (node == null) return false

        var currentPatternIndex = patternIndex
        while (currentPatternIndex > 0 && node.value != pattern[currentPatternIndex]) {
            currentPatternIndex = prefixTable[currentPatternIndex - 1]
        }
        if (node.value == pattern[currentPatternIndex]) {
            currentPatternIndex++
        }

        // Check if the pattern is fully matched
        if (currentPatternIndex == pattern.size) return true

        // Recursively search left and right subtrees
        return searchInTree(node.left, currentPatternIndex, pattern, prefixTable) ||
            searchInTree(node.right, currentPatternIndex, pattern, prefixTable)
    }
}

data object LinkedListInBinaryTreeIterative : LinkedListInBinaryTree {
    override fun invoke(
        head: ListNode?,
        root: TreeNode?,
    ): Boolean {
        if (root == null) {
            return false
        }

        val treeNodesStack = Stack<TreeNode>()
        treeNodesStack.push(root)

        while (treeNodesStack.isNotEmpty()) {
            val currentNode = treeNodesStack.pop()

            if (matchesPath(currentNode, head)) {
                return true
            }

            currentNode.left?.let { treeNodesStack.push(it) }
            currentNode.right?.let { treeNodesStack.push(it) }
        }

        return false
    }

    private fun matchesPath(treeNode: TreeNode?, listNode: ListNode?): Boolean {
        val pathStack = Stack<Pair<TreeNode?, ListNode?>>()
        pathStack.push(Pair(treeNode, listNode))

        while (pathStack.isNotEmpty()) {
            val (currentTreeNode, currentListNode) = pathStack.pop()

            var treeNodeToCheck = currentTreeNode
            var listNodeToCheck = currentListNode

            while (treeNodeToCheck != null && listNodeToCheck != null) {
                if (treeNodeToCheck.value != listNodeToCheck.value) {
                    break
                }
                listNodeToCheck = listNodeToCheck.next

                if (listNodeToCheck != null) {
                    treeNodeToCheck.left?.let { pathStack.push(Pair(it, listNodeToCheck)) }
                    treeNodeToCheck.right?.let { pathStack.push(Pair(it, listNodeToCheck)) }
                    break
                }
            }

            if (listNodeToCheck == null) {
                return true
            }
        }

        return false
    }
}
