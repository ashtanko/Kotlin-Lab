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

import java.util.Deque
import java.util.LinkedList

/**
 * 1932. Merge BSTs to Create Single BST
 * @see <a href="https://leetcode.com/problems/merge-bsts-to-create-single-bst/">Source</a>
 */
fun interface CanMergeBST {
    operator fun invoke(trees: List<TreeNode>): TreeNode?
}

class CanMergeBSTImpl : CanMergeBST {
    override operator fun invoke(trees: List<TreeNode>): TreeNode? {
        val rootMap: MutableMap<Int, TreeNode> = HashMap()
        val rootCount: MutableMap<Int, Int> = HashMap()
        for (treeNode in trees) {
            rootMap[treeNode.value] = treeNode
        }

        // Optimization: Count numbers for a root value in the trees. Find the root that the value is unique,
        // no other nodes in other trees have the same value, it must be the root for the answer,
        // so that does not need to traverse every tree later.
        for (treeNode in trees) {
            countElements(treeNode, rootCount)
        }
        var root: TreeNode? = null
        for (treeNode in trees) {
            if (rootCount[treeNode.value] == 1) {
                root = treeNode
                rootMap.remove(treeNode.value)
                break
            }
        }
        if (root == null) {
            return root
        }
        val node: TreeNode = root
        val queue: Deque<TreeNode?> = LinkedList()
        queue.offerFirst(node)
        while (queue.isNotEmpty()) {
            val cur = queue.pollLast()
            // If cur is a leaf node and find the matching root
            if (cur != null && cur.left == null && cur.right == null && rootMap.containsKey(cur.value)) {
                val matchingRoot = rootMap[cur.value]
                cur.left = matchingRoot?.left
                cur.right = matchingRoot?.right
                rootMap.remove(cur.value)
            }
            if (cur?.left != null) {
                queue.offerFirst(cur.left)
            }
            if (cur?.right != null) {
                queue.offerFirst(cur.right)
            }
        }

        // Optimization: merge all, do validation once in the end, instead of validating every time when merging a tree
        return if (rootMap.isEmpty() && isBST(root, Int.MIN_VALUE, Int.MAX_VALUE)) {
            root
        } else {
            null
        }
    }

    private fun countElements(node: TreeNode?, map: MutableMap<Int, Int>) {
        if (node != null) {
            val count = map.getOrDefault(node.value, 0) + 1
            map[node.value] = count
            countElements(node.left, map)
            countElements(node.right, map)
        }
    }

    private fun isBST(root: TreeNode?, min: Int, max: Int): Boolean {
        if (root == null) {
            return true
        }
        return if (root.value <= min || root.value >= max) {
            false
        } else {
            isBST(root.left, min, root.value) && isBST(root.right, root.value, max)
        }
    }
}
