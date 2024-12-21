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

import dev.shtanko.algorithms.annotations.DFS
import dev.shtanko.algorithms.annotations.DP
import kotlin.math.max

/**
 * 2458. Height of Binary Tree After Subtree Removal Queries
 * @see <a href="https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/">Source</a>
 */
fun interface TreeQueries {
    operator fun invoke(root: TreeNode, queries: IntArray): IntArray
}

@DP
@DFS
class TreeQueriesDP : TreeQueries {
    private val cache: HashMap<Int, Int> = HashMap()
    private val answer: IntArray = IntArray(SIZE)

    companion object {
        private const val SIZE = 100001
    }

    override operator fun invoke(root: TreeNode, queries: IntArray): IntArray {
        dfs(root, 0, 0)
        return queries.map { q -> answer[q] }.toIntArray()
    }

    private fun dfs(n: TreeNode?, d: Int, m: Int) {
        if (n == null) {
            return
        }
        answer[n.value] = m
        dfs(n.left, d + 1, max(m, d + helper(n.right)))
        dfs(n.right, d + 1, max(m, d + helper(n.left)))
    }

    private fun helper(n: TreeNode?): Int {
        if (n == null) return 0
        if (!cache.containsKey(n.value)) {
            cache[n.value] = 1 + max(helper(n.left), helper(n.right))
        }
        return cache[n.value] ?: 0
    }
}
