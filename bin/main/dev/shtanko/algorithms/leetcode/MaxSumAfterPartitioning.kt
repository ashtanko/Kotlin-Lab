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
 * 1043. Partition Array for Maximum Sum
 * @see <a href="https://leetcode.com/problems/partition-array-for-maximum-sum/">Source</a>
 */
fun interface MaxSumAfterPartitioning {
    operator fun invoke(arr: IntArray, k: Int): Int
}

class MaxSumAfterPartitioningDP : MaxSumAfterPartitioning {
    override operator fun invoke(arr: IntArray, k: Int): Int {
        val dp = IntArray(arr.size)
        for (to in arr.indices) {
            var currMax = 0
            for (j in 0 until k) {
                val from = to - j
                if (from < 0) {
                    continue
                }
                currMax = max(currMax, arr[from])
                val newSplitVal = currMax * (j + 1) + getVal(dp, from - 1)
                dp[to] = max(dp[to], newSplitVal)
            }
        }
        return dp[arr.size - 1]
    }

    private fun getVal(dp: IntArray, i: Int): Int {
        return if (i < 0) {
            0
        } else {
            dp[i]
        }
    }
}

class MaxSumAfterPartitioningMemo : MaxSumAfterPartitioning {
    override operator fun invoke(arr: IntArray, k: Int): Int {
        val memo = arrayOfNulls<Int>(arr.size)
        return maxSumAfterPartitioning(arr, k, 0, memo)
    }

    private fun maxSumAfterPartitioning(arr: IntArray, k: Int, i: Int, memo: Array<Int?>): Int {
        if (i == arr.size) {
            return 0
        }
        if (memo[i] != null) {
            return memo[i] ?: 0
        }
        var currMax = 0
        var sumMax = 0
        for (j in 0 until k) {
            val to = i + j
            if (to >= arr.size) {
                break
            }
            currMax = max(currMax, arr[to])
            sumMax = max(sumMax, currMax * (j + 1) + maxSumAfterPartitioning(arr, k, to + 1, memo))
        }
        return sumMax.also { memo[i] = it }
    }
}
