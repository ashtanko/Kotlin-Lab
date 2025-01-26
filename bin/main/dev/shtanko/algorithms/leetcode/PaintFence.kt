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
 * Paint Fence.
 */
fun interface PaintFence {
    fun numWays(n: Int, k: Int): Int
}

/**
 * Approach 1: Dynamic Programming 1D - Top Down.
 * Time complexity : O(n).
 * Space complexity : O(n).
 */
class PaintFence1DTopDown : PaintFence {

    private lateinit var memo: IntArray

    override fun numWays(n: Int, k: Int): Int {
        if (n == 0) {
            return 0
        }
        this.memo = IntArray(n + 1)
        return this.totalWays(n, k)
    }

    private fun totalWays(posts: Int, k: Int): Int {
        if (posts == 1) {
            return k
        }
        if (posts == 2) {
            return k * k
        }
        if (memo[posts - 1] == 0) {
            memo[posts - 1] = totalWays(posts - 1, k)
        }
        if (memo[posts - 2] == 0) {
            memo[posts - 2] = totalWays(posts - 2, k)
        }
        return (memo[posts - 1] + memo[posts - 2]) * (k - 1)
    }
}

/**
 * Approach 2: Dynamic Programming 1D - Bottom Up.
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PaintFence1DBottomUp : PaintFence {
    override fun numWays(n: Int, k: Int): Int {
        if (n == 0) {
            return 0
        }
        if (n == 1) {
            return k
        }
        var last = k * k
        var secondLast = k
        for (i in 3..n) {
            val tmp = last.plus(secondLast).times(k.minus(1))
            secondLast = last
            last = tmp
        }
        return last
    }
}

/**
 * Approach 3: Dynamic Programming 2D - Bottom Up.
 * Time complexity : O(n).
 * Space complexity : O(1).
 */
class PaintFence2DBottomUp : PaintFence {
    override fun numWays(n: Int, k: Int): Int {
        if (n == 0) {
            return 0
        }
        var same = 0
        var diff = k
        for (i in 2..n) {
            val tmp = same
            same = diff
            diff = (tmp + diff) * (k - 1)
        }
        return same + diff
    }
}
