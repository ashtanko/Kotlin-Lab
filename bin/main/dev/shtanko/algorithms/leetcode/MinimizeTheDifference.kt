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

import kotlin.math.abs
import kotlin.math.min

/**
 * 1981. Minimize the Difference Between Target and Chosen Elements
 * @see <a href="https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements">
 *     Source</a>
 */
fun interface MinimizeTheDifference {
    operator fun invoke(mat: Array<IntArray>, target: Int): Int
}

/**
 * DP + Memo
 */
class MinimizeTheDifferenceDP : MinimizeTheDifference {
    override operator fun invoke(mat: Array<IntArray>, target: Int): Int {
        val dp = Array(mat.size) { arrayOfNulls<Int>(SIZE) }
        return minDiff(mat, 0, target, 0, dp)
    }

    private fun minDiff(mat: Array<IntArray>, index: Int, target: Int, value: Int, dp: Array<Array<Int?>>): Int {
        if (index == mat.size) {
            return abs(value - target)
        }
        if (dp[index][value] != null) {
            return dp[index][value] ?: 0
        }
        var res = Int.MAX_VALUE
        for (i in mat[0].indices) {
            res = min(res, minDiff(mat, index + 1, target, value + mat[index][i], dp))
        }
        return res.also { dp[index][value] = it }
    }

    companion object {
        private const val SIZE = 5001
    }
}
