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

import kotlin.math.abs

/**
 * 979. Distribute Coins in Binary Tree
 * @see <a href="https://leetcode.com/problems/distribute-coins-in-binary-tree">Source</a>
 */
fun interface DistributeCoins {
    operator fun invoke(root: TreeNode?): Int
}

class DistributeCoinsDFS : DistributeCoins {
    private var moves = 0

    override fun invoke(root: TreeNode?): Int {
        dfs(root)
        return moves
    }

    private fun dfs(node: TreeNode?): Int {
        if (node == null) return 0
        val left = dfs(node.left)
        val right = dfs(node.right)
        moves += abs(left) + abs(right)
        return node.value + left + right - 1
    }
}
