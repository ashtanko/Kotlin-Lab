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

import kotlin.math.max

/**
 * 1372. Longest ZigZag Path in a Binary Tree
 * @see <a href="https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/">Source</a>
 */
fun interface LongestZigZagPath {
    fun longestZigZag(root: TreeNode?): Int
}

class LongestZigZagPathDFS : LongestZigZagPath {
    private var pathLength = 0

    override fun longestZigZag(root: TreeNode?): Int {
        dfs(root, false, 0)
        dfs(root, true, 0)
        return pathLength
    }

    private fun dfs(node: TreeNode?, goLeft: Boolean, steps: Int) {
        if (node == null) {
            return
        }
        pathLength = max(pathLength, steps)
        if (goLeft) {
            dfs(node.left, false, steps + 1)
            dfs(node.right, true, 1)
        } else {
            dfs(node.left, false, 1)
            dfs(node.right, true, steps + 1)
        }
    }
}
