/*
 * Copyright 2021 Oleksii Shtanko
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
 * 124. Binary Tree Maximum Path Sum
 * @see <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/">Source</a>
 */
fun interface MaxPathSum {
    operator fun invoke(root: TreeNode?): Int
}

class MaxPathSumRecursion : MaxPathSum {
    private var maxSum = Int.MIN_VALUE

    override operator fun invoke(root: TreeNode?): Int {
        maxGain(root)
        return maxSum
    }

    private fun maxGain(node: TreeNode?): Int {
        if (node == null) return 0

        // max sum on the left and right sub-trees of node
        val leftGain = max(maxGain(node.left), 0)
        val rightGain = max(maxGain(node.right), 0)

        // the price to start a new path where `node` is a highest node
        val priceNewPath: Int = node.value + leftGain + rightGain

        // update max_sum if it's better to start a new path
        maxSum = max(maxSum, priceNewPath)

        // for recursion :
        // return the max gain if continue the same path
        return node.value + max(leftGain, rightGain)
    }
}
