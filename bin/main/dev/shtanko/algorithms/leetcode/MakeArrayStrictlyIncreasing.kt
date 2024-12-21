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

import java.util.Arrays
import kotlin.math.min

/**
 * 1187. Make Array Strictly Increasing
 * @see <a href="https://leetcode.com/problems/make-array-strictly-increasing/">Source</a>
 */
fun interface MakeArrayStrictlyIncreasing {
    operator fun invoke(arr1: IntArray, arr2: IntArray): Int

    fun bisectRight(arr: IntArray, value: Int): Int {
        var left = 0
        var right = arr.size
        while (left < right) {
            val mid = (left + right) / 2
            if (arr[mid] <= value) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}

class MakeArrayStrictlyIncreasingTopDown : MakeArrayStrictlyIncreasing {

    private val dp: MutableMap<Pair<Int, Int>, Int> = HashMap()

    companion object {
        private const val COST_LIMIT = 2001
    }

    override operator fun invoke(arr1: IntArray, arr2: IntArray): Int {
        arr2.sort()
        val answer = dfs(0, -1, arr1, arr2)
        return if (answer < COST_LIMIT) answer else -1
    }

    private fun dfs(i: Int, prev: Int, arr1: IntArray, arr2: IntArray): Int {
        if (i == arr1.size) {
            return 0
        }
        if (dp.containsKey(Pair(i, prev))) {
            return dp[Pair(i, prev)]!!
        }
        var cost = COST_LIMIT

        // If arr1[i] is already greater than prev, we can leave it be.
        if (arr1[i] > prev) {
            cost = dfs(i + 1, arr1[i], arr1, arr2)
        }

        // Find a replacement with the smallest value in arr2.
        val idx = bisectRight(arr2, prev)

        // Replace arr1[i], with a cost of 1 operation.
        if (idx < arr2.size) {
            cost = min(cost, 1 + dfs(i + 1, arr2[idx], arr1, arr2))
        }
        dp[Pair(i, prev)] = cost
        return cost
    }
}

class MakeArrayStrictlyIncreasingBottomUp : MakeArrayStrictlyIncreasing {
    override operator fun invoke(arr1: IntArray, arr2: IntArray): Int {
        var dp: MutableMap<Int, Int> = HashMap()
        Arrays.sort(arr2)
        val n: Int = arr2.size
        dp[-1] = 0

        for (i in arr1.indices) {
            val newDp: MutableMap<Int, Int> = HashMap()
            for (prev in dp.keys) {
                if (arr1[i] > prev) {
                    newDp[arr1[i]] = min(
                        newDp.getOrDefault(arr1[i], Int.MAX_VALUE),
                        dp[prev]!!,
                    )
                }
                val idx = bisectRight(arr2, prev)
                if (idx < n) {
                    newDp[arr2[idx]] =
                        min(newDp.getOrDefault(arr2[idx], Int.MAX_VALUE), 1 + dp[prev]!!)
                }
            }
            dp = newDp
        }

        var answer = Int.MAX_VALUE
        for (value in dp.values) {
            answer = min(answer, value)
        }

        return if (answer == Int.MAX_VALUE) -1 else answer
    }
}
