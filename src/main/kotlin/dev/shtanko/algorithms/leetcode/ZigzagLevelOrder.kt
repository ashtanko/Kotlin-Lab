/*
 * Designed and developed by 2023 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.algorithms.leetcode

import dev.shtanko.algorithms.annotations.level.Medium
import java.util.LinkedList
import java.util.Queue

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * @see <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/">Source</a>
 */
@Medium("https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal")
fun interface ZigzagLevelOrder {
    operator fun invoke(root: TreeNode?): List<List<Int>>
}

class ZigzagLevelOrderQueue : ZigzagLevelOrder {
    override operator fun invoke(root: TreeNode?): List<List<Int>> {
        val queue: Queue<TreeNode> = LinkedList()
        queue.add(root)
        val res: MutableList<List<Int>> = ArrayList()
        if (root == null) return res
        var sub: MutableList<Int>
        var level = 0
        while (queue.isNotEmpty()) {
            val size: Int = queue.size
            sub = ArrayList()
            for (i in 0 until size) {
                val temp: TreeNode = queue.remove()
                sub.add(temp.value)
                if (temp.left != null) queue.add(temp.left)
                if (temp.right != null) queue.add(temp.right)
            }
            if (level % 2 != 0) sub.reverse()
            level++
            res.add(sub)
        }
        return res
    }
}
