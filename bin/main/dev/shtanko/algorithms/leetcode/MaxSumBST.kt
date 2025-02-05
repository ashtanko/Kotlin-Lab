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

import kotlin.math.max
import kotlin.math.min

/**
 * 1373. Maximum Sum BST in Binary Tree
 * @see <a href="https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/">Source</a>
 */
fun interface MaxSumBST {
    operator fun invoke(root: TreeNode): Int
}

class MaxSumBSTPostOrder : MaxSumBST {
    override operator fun invoke(root: TreeNode): Int {
        val res = dfs(root)
        return max(res[3], 0)
    }

    fun dfs(root: TreeNode?): IntArray {
        if (root == null) return intArrayOf(Int.MAX_VALUE, Int.MIN_VALUE, 0, Int.MIN_VALUE)
        val left = dfs(root.left)
        val right = dfs(root.right)
        return if (root.value > left[1] && root.value < right[0]) {
            val min = min(left[0], root.value)
            val max = max(right[1], root.value)
            val sum: Int = left[2] + right[2] + root.value
            val maxSum = max(sum, max(left[3], right[3]))
            intArrayOf(min, max, sum, maxSum)
        } else {
            intArrayOf(
                Int.MIN_VALUE,
                Int.MAX_VALUE,
                max(
                    left[2],
                    right[2],
                ),
                max(left[3], right[3]),
            )
        }
    }
}
