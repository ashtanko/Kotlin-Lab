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

/**
 * Object that contains methods to calculate the maximum path sum in a binary tree.
 */
object BinaryTreeMaximumPathSum {

    /**
     * The maximum value found so far.
     */
    private var maxValue = Int.MIN_VALUE

    /**
     * Extension function on TreeNode? to calculate the maximum path sum in the binary tree.
     * The path may start and end at any node in the tree.
     *
     * @return Int The maximum path sum in the binary tree.
     */
    fun TreeNode?.maxPathSum(): Int {
        maxValue = Int.MIN_VALUE
        this.maxPathDown()
        return maxValue
    }

    /**
     * Extension function on TreeNode? to calculate the maximum path sum from the current node down.
     * The path may start at the current node and end at any node in the tree.
     *
     * @return Int The maximum path sum from the current node down.
     */
    private fun TreeNode?.maxPathDown(): Int {
        if (this == null) return 0
        val left = 0.coerceAtLeast(this.left.maxPathDown())
        val right = 0.coerceAtLeast(this.right.maxPathDown())
        maxValue = maxValue.coerceAtLeast(left + right + this.value)
        return left.coerceAtLeast(right) + this.value
    }
}
