/*
 * Copyright 2023 Oleksii Shtanko
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

import java.util.AbstractMap.SimpleEntry
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

/**
 * 662. Maximum Width of Binary Tree
 * @see <a href="https://leetcode.com/problems/maximum-width-of-binary-tree/">Source</a>
 */
fun interface MaximumWidthOfBinaryTree {
    fun widthOfBinaryTree(root: TreeNode?): Int
}

class MaximumWidthOfBinaryTreeDFS : MaximumWidthOfBinaryTree {
    override fun widthOfBinaryTree(root: TreeNode?): Int {
        val lefts: MutableList<Int> = ArrayList() // left most nodes at each level;
        val res = IntArray(1) // max width

        dfs(root, 1, 0, lefts, res)
        return res[0]
    }

    private fun dfs(node: TreeNode?, id: Int, depth: Int, lefts: MutableList<Int>, res: IntArray) {
        if (node == null) return
        if (depth >= lefts.size) lefts.add(id) // add left most node
        res[0] = Integer.max(res[0], id + 1 - lefts[depth])
        dfs(node.left, id * 2, depth + 1, lefts, res)
        dfs(node.right, id * 2 + 1, depth + 1, lefts, res)
    }
}

class MaximumWidthOfBinaryTreeBFS : MaximumWidthOfBinaryTree {
    override fun widthOfBinaryTree(root: TreeNode?): Int {
        if (root == null) return 0
        var max = 0
        val q: Queue<SimpleEntry<TreeNode, Int>> = LinkedList()
        q.offer(SimpleEntry(root, 1))

        while (q.isNotEmpty()) {
            val l: Int = q.peek().value
            var r = l // right started same as left
            var i = 0
            val n: Int = q.size
            while (i < n) {
                val node: TreeNode = q.peek().key
                r = q.poll().value
                if (node.left != null) q.offer(SimpleEntry(node.left, r * 2))
                if (node.right != null) q.offer(SimpleEntry(node.right, r * 2 + 1))
                i++
            }
            max = max(max, r + 1 - l)
        }

        return max
    }
}
