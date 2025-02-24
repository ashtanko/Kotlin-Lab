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

import dev.shtanko.algorithms.annotations.Recursive
import dev.shtanko.algorithms.annotations.level.Medium
import java.util.Stack

/**
 * 173. Binary Search Tree Iterator
 * @see <a href="https://leetcode.com/problems/binary-search-tree-iterator/">Source</a>
 */
@Medium("https://leetcode.com/problems/binary-search-tree-iterator")
interface BSTIterator {
    fun next(): Int

    fun hasNext(): Boolean
}

@Recursive
class BSTIteratorFlattening(root: TreeNode?) : BSTIterator {

    private val nodesSorted: MutableList<Int> = ArrayList()
    private var index = -1

    init {
        inorder(root)
    }

    override fun next(): Int = nodesSorted[++this.index]

    override fun hasNext(): Boolean = index + 1 < nodesSorted.size

    private fun inorder(root: TreeNode?) {
        val tree = root ?: return
        inorder(tree.left)
        nodesSorted.add(tree.value)
        inorder(tree.right)
    }
}

@Recursive
class BSTIteratorControlledRecursion(root: TreeNode?) : BSTIterator {

    private val stack = Stack<TreeNode>()

    init {
        leftMostInorder(root)
    }

    override fun next(): Int {
        val topmostNode = this.stack.pop()
        if (topmostNode.right != null) {
            leftMostInorder(topmostNode.right)
        }
        return topmostNode.value
    }

    override fun hasNext(): Boolean = this.stack.isNotEmpty()

    private fun leftMostInorder(root: TreeNode?) {
        var tree = root
        while (tree != null) {
            this.stack.push(tree)
            tree = tree.left
        }
    }
}
