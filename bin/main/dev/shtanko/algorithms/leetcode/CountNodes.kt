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

/**
 * 222. Count Complete Tree Nodes
 * @see <a href="https://leetcode.com/problems/count-complete-tree-nodes/">Source</a>
 */
fun interface CountNodes {
    operator fun invoke(root: TreeNode?): Int
}

class CountNodesBitrise : CountNodes {
    override operator fun invoke(root: TreeNode?): Int {
        val h = root.height()
        return if (h < 0) {
            0
        } else if (root?.right.height() == h - 1) {
            (1 shl h) + invoke(root?.right)
        } else {
            1 shl h - 1 + invoke(root?.left)
        }
    }
}

class CountNodesIterative : CountNodes {
    override operator fun invoke(root: TreeNode?): Int {
        var nodes = 0
        var tree = root
        var h: Int = root.height()
        while (tree != null) {
            if (tree.right.height() == h - 1) {
                nodes += 1 shl h
                tree = tree.right
            } else {
                nodes += 1 shl h - 1
                tree = tree.left
            }
            h--
        }
        return nodes
    }
}
