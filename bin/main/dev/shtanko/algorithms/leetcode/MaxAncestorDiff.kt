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

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * 1026. Maximum Difference Between Node and Ancestor
 * @see <a href="https://leetcode.com/problems/maximum-difference-between-node-and-ancestor">
 *     Source</a>
 */
fun interface MaxAncestorDiff {
    operator fun invoke(root: TreeNode?): Int
}

/**
 * Approach #1: Recursion
 */
class MaxAncestorDiffRecursion : MaxAncestorDiff {

    // record the required maximum difference
    var result = 0

    override operator fun invoke(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }
        result = 0
        helper(root, root.value, root.value)
        return result
    }

    private fun helper(node: TreeNode?, curMax: Int, curMin: Int): Int {
        var cMax = curMax
        var cMin = curMin
        if (node == null) {
            return 0
        }
        val possibleResult: Int = max(abs(cMax - node.value), abs(cMin - node.value))
        result = max(result, possibleResult)
        cMax = max(cMax, node.value)
        cMin = min(cMin, node.value)
        helper(node.left, cMax, cMin)
        helper(node.right, cMax, cMin)
        return result
    }
}

/**
 * Approach #2: Maximum Minus Minimum
 */
class MaxAncestorDiffMM : MaxAncestorDiff {
    override operator fun invoke(root: TreeNode?): Int {
        return if (root == null) {
            0
        } else {
            helper(root, root.value, root.value)
        }
    }

    fun helper(node: TreeNode?, curMax: Int, curMin: Int): Int {
        // if encounter leaves, return the max-min along the path
        var cMax = curMax
        var cMin = curMin
        if (node == null) {
            return cMax - cMin
        }
        // else, update max and min
        // and return the max of left and right subtrees
        cMax = max(cMax, node.value)
        cMin = min(cMin, node.value)
        val left = helper(node.left, cMax, cMin)
        val right = helper(node.right, cMax, cMin)
        return max(left, right)
    }
}
